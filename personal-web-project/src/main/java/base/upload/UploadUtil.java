package base.upload;

import java.io.File;
import java.util.UUID;

/**
 * uploadutil使用的普通上传文件使用;
 * 
 * @author Liubao
 * @2014年11月25日
 * 
 */
public class UploadUtil {
    /**
     * 截取真实文件名,我已经在外面直接获取了;
     */
    public static String getFileRealName(String fileName) {
        // 查找最后一个 \出现位置
        int index = fileName.lastIndexOf("\\");// 或者使用/
        if (index == -1) {// 该判断可以省略;
            return fileName;
        }
        return fileName.substring(index + 1);
    }

    /**
     * 获取单独的UUID随机数;
     */
    public static String getUUIDString() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获得随机[UUID_真实文件名]格式的文件名
     */
    public static String generateUUIDFileName(String fileName) {
        String realName = UploadUtil.getFileRealName(fileName);
        return UUID.randomUUID().toString() + "_" + realName;
    }

    /**
     * 获得[UUID.文件扩展名]格式的文件名
     */
    public static String generateUUIDExtFileName(String fileName) {
        // 获得扩展名
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extName;
    }

    /**
     * 获得hashcode算法生成二级子目录
     */
    public static String generateHashCodeDir(String uuidFileName) {
        int hashCode = uuidFileName.hashCode();

        // 一级目录
        int d1 = hashCode & 0xf;
        // 二级目录
        int d2 = (hashCode >> 4) & 0xf;// 先右移,在进行与运算,应该效率较高;
        return File.separator + d1 + File.separator + d2;
        // return "/" + d1 + "/" + d2;
    }
}
