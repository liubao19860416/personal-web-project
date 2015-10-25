package base.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

/**
 * struts2的上传文件使用的;在struts2中，利用拦截器fileUpload实现了文件上传
 * 
 * @author Liubao
 * @2014年11月25日
 * 
 */
public class UploadUtils {
    /**
     * struts2的文件上传过程中,首先是将文件生成一个临时文件.tmp文件,然后再对其进行上传操作的;
     */
    public static String saveUploadFile(File upload, String fileName) {
        // 把日期格式化成字符串的一个帮助类
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        /*
         * 得到upload文件夹的绝对路径 ServletActionContext.getServletContext()= C:\Program
         * Files\Apache Software Foundation\Tomcat
         * 6.0\webapps\itcastoa823\WEB-INF/upload/2012\02\16\aaaaadfasdf
         */

        // 这里是文件上传的路径
        String basePath = ServletActionContext.getServletContext().getRealPath(
                "/WEB-INF/upload");
        // String basePath = "d:\\upload";

        // 把日期类型格式化为"/yyyy/MM/dd/"这种形式的字符串
        String subPath = sdf.format(new Date());// 上面设置的格式,包含了2个文件分隔符;

        // UUID能够保证名字的唯一性
        String uuidFileName = UUID.randomUUID().toString() + "_" + fileName;

        // 这里可以利用hashcode算法,生成二级16*16的目录;方法见下面的代码;
        String subDir = generateHashCodeDir(uuidFileName);

        // System.out.println("HashCode生成目标文件路径:"+basePath+subDir+fileName);

        String destPath = basePath + subPath;// 目标文件所在的绝对路径

        String destFile = destPath + uuidFileName;// 包括文件名了;

        // 如果文件夹不存在，就创建文件夹;因为文件的存储路径已经获取了,所以可以这样进行文件夹的提前创建文件夹操作!
        File dir = new File(destPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // System.out.println("日期格式(/yyyy/MM/dd/)生成目标文件路径:"+destFile);

        File dest = new File(destFile);

        // 把文件移动到dest处;该操作在linux系统下不好用;所以需要进行改进;
        upload.renameTo(dest);

        // 方式二(文件上传)uploadFileMethod2
        // uploadFileMethod2(upload,dest);

        // return destPath; //路径不包括文件的名字
        return destFile; // 路径包括文件的名字

    }

    // 生成二级子目录的方式二
    public static String generateHashCodeDir(String uuidFileName) {
        int hashCode = uuidFileName.hashCode();
        // 一级目录
        int d1 = hashCode & 0xf;
        // 二级目录
        int d2 = (hashCode >> 4) & 0xf;// 先右移,在进行与运算,应该效率较高;
        return File.separator + d1 + File.separator + d2 + File.separator;
        // return "/" + d1 + "/" + d2+"/";
    }

    /**
     * 方式二:通过数据io流,读写上传文件;字符流不涉及编码的问题,而字节流就需要考虑传输过程指定编码码表;
     */
    public static void uploadFileMethod2(File source, File dest) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(source));
            out = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传文件路径不正确!或者其他异常!");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("IO流关闭异常!");
            }
        }
    }

}
