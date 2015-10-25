package ocr;

import java.io.File;

public class TestOCR {

    /**
     * http://yuyue.rjh.com.cn/Guahao/GetValidateCode?
     * 测试OCR识别验证码，同时对验证码图片进行滤波等处理操作
     */
    public static void main(String[] args) {
        String destDir = "D:/temp";
        File fileList = new File(destDir);

        // 遍历文件，查询解析过滤后图片操作
        System.out
                .println("==================测试循环读取过滤前的单个文件======================");
        for (File testFile : fileList.listFiles()) {
            // 判断文件类型是jpg类型,简单判断
            if (!testFile.getName().endsWith(".jpg")) {
                // if(!ImageTypeChecker.isImage(testFile)){
                continue;
            }
            try {
                // 测试循环读取过滤前的单个文件
                // String valCode1 = new OCR().recognizeText(testFile, "jpg");
                // System.out.println(testFile.getName()+":"+valCode1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out
                .println("====================过滤前文件读取测试结束！！！===================");

        // 遍历文件，过滤图片
        for (File testFile : fileList.listFiles()) {
            // 判断文件类型是jpg类型,简单判断
            if (!testFile.getName().endsWith(".jpg")) {
                // if(!ImageTypeChecker.isImage(testFile)){
                continue;
            }
            try {
                System.out
                        .println("====================方式一：过滤单个文件=========================");
                // 方式一：过滤单个文件clean2_ 效果一般
                // ClearImageHelper.cleanImage(testFile, testFile.getParent());

                System.out
                        .println("====================方式二：过滤单个文件=========================");
                // 方式二：过滤单个文件clean1_ 效果要好一些
                // MyImgFilter.cleanImageMethod2(testFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out
                .println("====================过滤图片文件结束！！！===================");

        // 遍历文件，查询解析过滤后图片操作
        for (File testFile : fileList.listFiles()) {
            // 判断文件类型是jpg类型,简单判断
            if (!testFile.getName().endsWith(".jpg")) {
                // if(!ImageTypeChecker.isImage(testFile)){
                continue;
            }
            try {
                System.out
                        .println("====================测试循环读取过滤后的单个文件===================");
                // 测试循环读取过滤后的单个文件
                String valCode2 = new OCR().recognizeText(testFile, "jpg");
                System.out.println(testFile.getName() + ":" + valCode2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out
                .println("====================测试读取过滤后文件测试结束！！！===================");

    }

}