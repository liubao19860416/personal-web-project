package mongodb;

import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * mongoDB相关CRUD操作测试类
 * 
 * @author Liubao
 * @2014年11月14日
 * 
 */

public class MongoDBJDBC {

    // 循环插入记录条数
    private static final int INSERTSIZE = 5;

    // 连接到 mongodb 服务
    private static MongoClient mongoClient = null;
    private static DB db = null;

    static {
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // 连接到数据库
        db = mongoClient.getDB("mydb");
        System.out.println("Connect to database successfully");
        char[] myPassword = "root".toCharArray();
        String myUserName = "root";
        @SuppressWarnings("deprecation")
        boolean auth = db.authenticate(myUserName, myPassword);
        System.out.println("Authentication: " + auth);
    }

    /**
     * 笔记：
     * 
     * 1.进行相应的增删改查操作，都是要针对使用append添加到对象中的属性进行转化为Bson数据的；自己的属性不会被转换；
     * 
     * 2.通过父类构造方法注入的key和value,或者使用put（key，value）方式，也是可以被Bson格式化的；
     * 
     * 3.同一个游标 cursor ，只能在一种操作方法中连续使用，否则跨方法有时候会报错！ 如果需要跨方法使用，则在每次使用前重新进行初始化；如
     * cursor = dBCollection.find();才可以在不同类型的方法之间连续使用；
     * 例如：cursor.length()和cursor2.itcount()不能连续使用；
     * 正确为：cursor.length()调用后进行初始化，cursor =
     * dBCollection.find();，再使用cursor2.itcount()；
     * 
     * 4.
     */
    @Test
    public void testMongoDbCRUD() {
        try {
            // 创建需要保存到数据库的集合对象
            DBObject dbObject = new BasicDBObject();
            // dbObject.put("name", "liubao111");//没用这里，下面只是创建了一个集合
            DBCollection coll = db.createCollection("orders", dbObject);
            System.out.println("Collection created successfully");

            MongoDBEntiy dbEntiy = new MongoDBEntiy();
            dbEntiy.setId(8);
            dbEntiy.setName("liubao");
            dbEntiy.setSalary(3333f);
            dbEntiy.setStartTime(new Timestamp(System.currentTimeMillis()));
            dbEntiy.append("Id", "liubao11")
                    .append("Name", 333311f)
                    .append("StartTime",
                            new Timestamp(System.currentTimeMillis()));

            // 读取数据库中的数据（可以获取的相当于是一个游标对象）
            DBCollection coll2 = db.getCollection("orders");
            System.out.println("Collection mycol selected successfully");

            // 插入一条文档记录信息
            // 循环插入3个记录
            for (int i = 0; i < INSERTSIZE; i++) {
                BasicDBObject doc = new BasicDBObject("title", "MongoDB" + i);
                doc.append("description", "database" + i).append("likes", 100)
                        .append("url", "http://www.w3cschool.cc/mongodb/" + i)
                        .append("by", "w3cschool.cc" + i)
                        .append("object", dbEntiy);// 这里添加的对象中属性没有被添加？
                coll.insert(doc);
                System.out.println("Document inserted successfully" + i);
            }

            // 获取的相当于是一个游标对象，进行查找数据库操作
            DBCursor cursor = coll2.find();
            int i = 1;
            while (cursor.hasNext()) {
                System.out.println("Inserted Document: " + i++);
                System.out.println(cursor.next());
            }

            // 重新重置初始游标位置，不管用
            // cursor.resetOptions();

            System.out.println("================================");

            cursor = coll2.find();
            i = 1;
            // 进行更新操作
            while (cursor.hasNext()) {
                // updateDocument中存放的是更新内容！！！
                DBObject updateDocument = new BasicDBObject();
                updateDocument.put("likes", "100" + i++);
                if (i % 2 == 0) {
                    updateDocument.put("name", "liubao" + i);
                }

                DBObject next = cursor.next();

                // 进行更新操作
                coll2.update(next, updateDocument);

                System.out.println(next);
                System.out.println(updateDocument);
                System.out.println("Document updated successfully");
            }

            // 重置游标
            cursor = coll2.find();

            System.out.println("================================");
            System.out.println("当前游标长度为1：" + cursor.length());
            System.out.println("当前游标长度为1：" + cursor.length());// 这里连续调用是OK的
            cursor = coll2.find();
            System.out.println("当前游标长度为2：" + cursor.itcount());
            i = 1;
            cursor = coll2.find();
            while (cursor.hasNext()) {
                System.out.println("Updated Document: " + i++);
                System.out.println(cursor.next());
            }

            // 删除操作
            DBObject myDoc = coll2.findOne();
            coll2.remove(myDoc);

            // 重置游标
            cursor = coll2.find();
            System.out.println("当前游标长度为1：" + cursor.length());
            cursor = coll2.find();
            System.out.println("当前游标长度为2：" + cursor.itcount());
            System.out.println("================================");

            // 其他操作方法
            long count = coll2.count();
            System.out.println("count为：" + count);
            DBObject query = new BasicDBObject();
            // 设置查询条件，待查询对象中的部分属性匹配满足就可以了
            query.put("likes", "1003");
            count = coll2.count(query);
            System.out.println("count为：" + count);

            // 包含指定字段“likes”的对象的values值的集合
            List<?> distinct = coll2.distinct("likes");
            for (Object object : distinct) {
                // 获取的就是对应的集合中的values的值，这里可以进行强转；
                System.out.println(object.toString());
            }
            System.out.println("当前集合长度为：" + distinct.size());
            distinct = coll2.distinct("name");
            System.out.println("当前集合长度为：" + distinct.size());

            // 等价于insert方法
            // 因为有一个“_id”是不一样的，所以不存在重复的(key==value)值
            query.put("likes", "1004");
            coll2.save(query);

            // 根据查询对象，进行对象查询，query为查询条件
            int count2 = coll2.find(query).count();
            Assert.assertEquals(1, count2);

            // 添加和设置索引？？？
            DBObject name = new BasicDBObject();
            name.put("", "");
            coll2.dropIndex(name);
            DBObject keys = new BasicDBObject();
            keys.put("", "");
            coll2.createIndex(keys);

            // 不是固定大小限制的集合
            boolean capped = coll2.isCapped();
            Assert.assertFalse(capped);

            // 打印当前集合的全路径，数据库名称+集合文档名称，如mydb.orders
            String fullName = coll2.getFullName();
            System.out.println("fullName====>>" + fullName);

            System.out.println("==============排序前==================");
            cursor = coll2.find();
            while (cursor.hasNext()) {
                System.out.println("Updated Document: " + i++);
                System.out.println(cursor.next());
            }

            // 默认按照“_id”进行升序排序
            // 设置排序字段进行排序，对应字段的值为-1，代表降序；其他代表升序
            DBObject orderBy = new BasicDBObject();
            orderBy.put("likes", 0);
            // sort方法
            cursor = coll2.find().sort(orderBy);
            ;
            System.out.println("==============排序后==================");
            while (cursor.hasNext()) {
                System.out.println("Updated Document: " + i++);
                System.out.println(cursor.next());
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Test
    public void testBatchInsert() throws Exception {
        // 创建需要保存到数据库的集合对象
        // DBObject dbObject = new BasicDBObject();
        DBCollection coll = db.createCollection("orders", null);

        MongoDBEntiy dbEntiy = new MongoDBEntiy();
        // 使用set无效，可以使用put方法或者append方法；
        // dbEntiy.setSalary(3333f);
        dbEntiy.setStartTime(new Timestamp(System.currentTimeMillis()));
        dbEntiy.append("Id", "liubao11").append("Name", 333311f)
                .append("StartTime", new Timestamp(System.currentTimeMillis()));

        // 方式一：批量插入多个记录
        ArrayList<DBObject> list = new ArrayList<DBObject>();
        for (int i = 0; i < INSERTSIZE; i++) {
            BasicDBObject doc = new BasicDBObject("title", "MongoDB" + i);
            doc.append("description", "database" + i).append("likes", 100)
                    .append("url", "http://www.w3cschool.cc/mongodb/" + i)
                    .append("by", "w3cschool.cc" + i).append("object", dbEntiy);// 这里添加的对象中属性没有被添加？
            // coll.insert(doc);
            list.add(doc);
        }
        // 在这里实现批量插入的操作了！！！
        coll.insert(list);

        // 方式二：或者如下
        BasicDBObject basicDB = new BasicDBObject();
        for (int i = 0; i < INSERTSIZE; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "标题" + i);
            map.put("description", "描述信息" + i);
            map.put("money", 100 + i);
            map.put("url", "网址url信息" + i);
            map.put("by", "w3cschool" + i);
            map.put("object", dbEntiy);
            basicDB.putAll(map);
        }
        // 在这里实现批量插入的操作了！！！
        coll.insert(basicDB);
    }

}
