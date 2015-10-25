package mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.CustomizedPropertyPlaceholderConfigurer;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

/**
 * 批处理工具类
 */
@SuppressWarnings("deprecation")
public class UtilMongDB {
    private static Logger logger = LoggerFactory.getLogger(UtilMongDB.class);
    UtilThreadLocal<ArrayList<DBObject>> localBatch;
    /** mongo单例对象 根据官方文档mongojava是线程安全的 */
    private static MongoClient mongoClient;
    // private static DBCollection coll;

    private static DB db;
    private static final String MongoServerUrl = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.replicationSet");
    private static final String ConnectionPerHost = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.connectionPerHost");
    private static final String ConnectionMultiplier = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.threadsAllowedToBlockForConnectionMultiplier");
    private static final String SocketTimeout = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.socketTimeout");
    private static final String MaxWaitTime = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.maxWaitTime");
    private static final String AutoConnectRetry = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.autoConnectRetry");
    private static final String ConnectionTimeout = CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.mall.connectionTimeout");

    static {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.autoConnectRetry(Boolean.valueOf(AutoConnectRetry));
        builder.maxWaitTime(Integer.valueOf(MaxWaitTime));
        builder.connectTimeout(Integer.valueOf(ConnectionTimeout));
        builder.socketTimeout(Integer.valueOf(SocketTimeout));
        builder.connectionsPerHost(Integer.valueOf(ConnectionPerHost));
        builder.threadsAllowedToBlockForConnectionMultiplier(Integer
                .valueOf(ConnectionMultiplier));
        MongoClientOptions tions = builder.build();
        try {
            List<ServerAddress> list = new ArrayList<ServerAddress>();
            if (StringUtils.isNotEmpty(MongoServerUrl)) {
                if (StringUtils.contains(MongoServerUrl, ",")) {
                    String[] urls = StringUtils.split(MongoServerUrl, ",");
                    for (String url : urls) {
                        String[] host = StringUtils.split(url, ":");
                        ServerAddress serverAddress = new ServerAddress(
                                host[0], Integer.valueOf(host[1]));
                        list.add(serverAddress);
                    }
                } else {
                    String[] host = StringUtils.split(":");
                    ServerAddress serverAddress = new ServerAddress(host[0],
                            Integer.valueOf(host[1]));
                    list.add(serverAddress);
                }
            }
            mongoClient = new MongoClient(list, tions);
            mongoClient.setReadPreference(ReadPreference.secondaryPreferred());
            getColl().createIndex(new BasicDBObject("cityCode",1));
            getColl().createIndex(new BasicDBObject("queryInfo",1));
            getColl().createIndex(new BasicDBObject("carNo",1));
            getColl().createIndex(new BasicDBObject("userId",1));
            getColl().createIndex(new BasicDBObject("queryTime",1));
            getColl().createIndex(new BasicDBObject("openId",1));
        } catch (Exception ex) {
            logger.error("mongo init error!!", ex);
        }

    }

    public UtilMongDB() {
        try {
            localBatch = new UtilThreadLocal<ArrayList<DBObject>>(
                    ArrayList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 全国省市数据查询
     * 
     * @author wangpeng
     * @return
     * @since JDK 1.7
     */
    public static DBCollection getCollByPeccancyCity() {
        return getDB()
                .getCollection(
                        CustomizedPropertyPlaceholderConfigurer
                                .getContextProperty("ebiz.osier.mongodb.collection.peccancycity"));
    }

    /**
     * 返回db对象
     * 
     * @return db
     */
    public static DB getDB() {
        if (db == null) {
            db = mongoClient.getDB(CustomizedPropertyPlaceholderConfigurer
            .getContextProperty("component.mongodb.osier.dbname"));
        }
        return db;
    }

    /**
     * 返回mongo
     * 
     * @return mongo连接池
     */
    public static MongoClient getMong() {
        return mongoClient;
    }

    /**
     * 读取集合
     * 
     * @return mongo集合
     * */
    public static DBCollection getColl(String collname) {
        return getDB().getCollection(collname);
    }

    public static DBCollection getColl() {
        return getDB()
                .getCollection(
                        CustomizedPropertyPlaceholderConfigurer
                                .getContextProperty("ebiz.osier.mongodb.collection.peccancyData"));
    }

    /** crud操作 */
    @SuppressWarnings("unused")
    public void addBatch(HashMap<String, Object> map) {
        BasicDBObject basicDB = new BasicDBObject();
        basicDB.putAll(map);
        /** 这里用线程本地变量，不用会存在竞技条件 */
        ArrayList<DBObject> newGet = localBatch.newGet();
        localBatch.newGet().add(basicDB);
    }

    /** crud操作 */
    public void addBatch(String key, String value) {
        BasicDBObject basicDB = new BasicDBObject();
        basicDB.put(key, value);
        /** 这里用线程本地变量，不用会存在竞技条件 */
        localBatch.newGet().add(basicDB);
    }

    /**
     * 执行批处理
     * */
    public void executeInsertBatch() {
        getColl().insert(localBatch.get());
        localBatch.get().clear();
    }

    /**
     * 
     * 删除缓存
     * 
     * @author Administrator
     * @param obj
     * @since JDK 1.7
     */
    public void remove(DBObject obj) {
        localBatch.newGet().add(obj);
    }

    /**
     * 执行批量删除
     */
    public void executeDeleteBatch() {
        ArrayList<DBObject> array = localBatch.get();
        for (DBObject obj : array) {
            getColl().remove(obj);
        }
        localBatch.get().clear();
    }

    /**
     * 按条件修改mongodb数据
     */
    public void update(DBObject updateCondition,DBObject updateValue){
    	localBatch.newGet().add(updateCondition);
    	DBObject updateSetValue=new BasicDBObject("$set",updateValue);
    	getColl().update(localBatch.get().get(0), updateSetValue);
    	localBatch.get().clear();
    }
    
    /**
     * 单个条件查询
     */
    public DBCursor query(String key, String value) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put(key, value);
        DBCursor dbCursor = getColl().find(basicDBObject);
        return dbCursor;
    }

    /**
     * 多条件查询
     */
    public DBCursor query(BasicDBList list) {
        BasicDBObject searchCond = new BasicDBObject();
        searchCond.put("$and", list);
        DBCursor dbCursor = getColl().find(searchCond);
        return dbCursor;
    }

}
