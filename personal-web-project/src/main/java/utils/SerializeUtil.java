package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 对象序列化及反序列化工具类
 */

public class SerializeUtil {
    private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);
    
  //默认的时间戳格式
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    //静态全局变量
    private static GsonBuilder gsonBuilder;

    //对象序列化
    public static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            logger.error("serialize error!!!",e);
            return null;
        }finally{
            // 关闭资源
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    logger.error("close ObjectInputStream error!!!", e);
                } finally {
                    objectOutputStream = null;
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    logger.error("close ObjectInputStream error!!!", e);
                } finally {
                    byteArrayOutputStream = null;
                }
            }
        }
    }

    // 对象反序列化
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois =null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("unserialize error!!!",e);
            return null;
        }finally{
            // 关闭资源
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    logger.error("close ObjectInputStream error!!!", e);
                } finally {
                    ois = null;
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    logger.error("close ObjectInputStream error!!!", e);
                } finally {
                    bais = null;
                }
            }
        }
    }

    // 转换为json数据
    public static String toJson(Object srcObj) {
        if (srcObj == null) {
            return "";
        }
        return getGson().toJson(srcObj);
    }

    // 将json字符串数据转换到指定Class对象上
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            logger.info("传递的json数据为空！",new IllegalArgumentException("json is null!!"));
            return null;
        }
        if (clazz == null) {
            logger.error("Class传递类型为空！",new IllegalArgumentException("class is null!!"));
            throw new IllegalArgumentException("class is null!!");
        }
        return getGson().fromJson(json, clazz);
    }

    //抽取的创建gson对象的方法
    private static Gson getGson() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder().setDateFormat(DEFAULT_DATETIME_PATTERN);
        }
        return gsonBuilder.create();
    }

}