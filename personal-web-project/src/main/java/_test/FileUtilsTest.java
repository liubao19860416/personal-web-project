package _test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class FileUtilsTest {
    public static void main(String[] args) {

        File srcFile = new File("d:\\temp\\001.jpg");
        File destFile = new File("d:\\temp\\001_copy.jpg");
        try {
            
            FileUtils.openInputStream(srcFile);
            FileUtils.openOutputStream(destFile);
            srcFile.delete();
            
            // 拷贝文件
            FileUtils.copyFile(srcFile, destFile);

            Thread.sleep(1000);

            // 比较两个文件的内容是否相等
            boolean contentEquals = FileUtils.contentEquals(srcFile, destFile);
            System.out.println(contentEquals);
            // Assert.assertTrue(contentEquals);

            File srcDir = new File("d:\\temp\\");
            File destDir = new File("d:\\temp\\00\\");
            // 拷贝目录整体文件
            // FileUtils.copyDirectory(srcDir, destDir);

            String encoding = "UTF-8";
            File file = new File("d:\\temp\\2.txt");
            // 将文件内容读出为字符串返回
            String fileToString = FileUtils.readFileToString(file, encoding);
            System.out.println(fileToString);

            String data = "新写入的文字信息。。。";
            // 将字符串写入到文件中（可以追加写入）
            FileUtils.writeStringToFile(file, data, encoding, true);

            byte[] bytes = data.getBytes();
            // 将字节数据写入到文件中（可以追加写入）
            FileUtils.writeByteArrayToFile(file, bytes, true);

            file = new File("d:\\temp\\222.txt");
            // 当文件存在的时候，只是更新文件的修改时间，内容不变；当文件不存在的时候，创建该文件；可能会抛出异常信息；
            FileUtils.touch(file);

            // 返回文件的字节数
            long sizeOf = FileUtils.sizeOf(file);
            System.out.println(sizeOf);

            file = new File("d:\\temp\\222.txt");
            // 将文件移动到指定目录内，当指定目录内已经存在的时候，会抛出异常
            // FileUtils.moveToDirectory(file, destDir, true);

            // 将指定的url留信息写入到指定的文件中去
            URL source = new URL("http://www.baidu.com");
            FileUtils.copyURLToFile(source, file);

            // 判断指定的目录中是否存在指定的文件
            boolean result = FileUtils.directoryContains(destDir, file);
            System.out.println(result);
            result = FileUtils.directoryContains(srcDir, file);
            System.out.println(result);

            // 文件夹的字节数，文件夹不能为空
            long sizeOfDirectory = FileUtils.sizeOfDirectory(srcDir);
            System.out.println(sizeOfDirectory);

            IOFileFilter fileFilter = new CanReadFileFilter() {
                @Override
                public boolean accept(File file) {
                    // TODO Auto-generated method stub
                    return super.accept(file);
                }
            };
            IOFileFilter dirFilter = new CanReadFileFilter() {
                @Override
                public boolean accept(File file) {
                    return super.accept(file);
                }
            };
            // 方式1：添加过滤器实现
            Collection<File> files = FileUtils.listFiles(srcDir, fileFilter,
                    dirFilter);
            for (File file2 : files) {
                System.out.println(file2.getName());
            }

            System.out.println("=================");

            // 方式2：通过后缀名区分,后缀名不需要加上"."符号
            String[] strs = new String[] { "txt", "jpg" };
            files = FileUtils.listFiles(srcDir, strs, true);
            for (File file2 : files) {
                System.out.println(file2.getName());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
