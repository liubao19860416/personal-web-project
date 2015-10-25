package imagefilter;

import java.io.File;
import java.io.FileInputStream;

/**
 * 判断文件是否为图片文件(GIF,PNG,JPG)
 * 
 * 好像不太准确！！！
 */
public class ImageTypeChecker {
    public static boolean isImage(File srcFile) {
        FileInputStream imgFile = null;
        byte[] b = new byte[10];
        int l = -1;
        try {
            imgFile = new FileInputStream(srcFile);
            l = imgFile.read(b);
            imgFile.close();
        } catch (Exception e) {
            return false;
        }
        if (l == 10) {
            byte b0 = b[0];
            byte b1 = b[1];
            byte b2 = b[2];
            byte b3 = b[3];
            byte b6 = b[6];
            byte b7 = b[7];
            byte b8 = b[8];
            byte b9 = b[9];
            if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F') {
                return true;
            } else if (b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G') {
                return true;
            } else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I'
                    && b9 == (byte) 'F') {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String destDir = "D:/temp";
        destDir = "E:/整理新照片/20140310-生日";
        destDir = "E:\\整理新照片\\20140310-生日\\IMG_20140310_202234.jpg";
        File fileList = new File(destDir);
        // 遍历文件
        listFiles(fileList);
    }

    //遍历文件列表
    private static void listFiles(File fileList) {
        if (fileList.isDirectory()) {
            //文件是目录
            for (File file : fileList.listFiles()) {
                //列出文件信息
                listFiles(file);
            }
        }else{
            //文件不是目录，那时就是文件了
            if (ImageTypeChecker.isImage(fileList)) {
                System.out.println("图片文件名为：" + fileList.getAbsolutePath());
            } 
            //System.out.println("图片文件名为：" + fileList.getAbsolutePath());
        }
    }
    
    
    
}
