package imagefilter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 无效果
 * @author Liubao
 * @2014年11月17日
 *
 */
public class ImageUtils {

    /**
     * 对图片进行放大
     * 
     * @param originalImage
     *            原始图片
     * @param times
     *            放大倍数
     */

    public static BufferedImage zoomInImage(BufferedImage originalImage,
            Integer times) {

        int width = originalImage.getWidth() * times;

        int height = originalImage.getHeight() * times;

        BufferedImage newImage = new BufferedImage(width, height,
                originalImage.getType());

        Graphics g = newImage.getGraphics();

        g.drawImage(originalImage, 0, 0, width, height, null);

        g.dispose();

        return newImage;

    }

    /**
     * 
     * 对图片进行放大
     * 
     * @param srcPath
     *            原始图片路径(绝对路径)
     * 
     * @param newPath
     *            放大后图片路径（绝对路径）
     * 
     * @param times
     *            放大倍数
     * 
     * @return 是否放大成功
     */

    public static boolean zoomInImage(String srcPath, String newPath,
            Integer times) {

        BufferedImage bufferedImage = null;

        try {

            File of = new File(srcPath);

            if (of.canRead()) {

                bufferedImage = ImageIO.read(of);

            }

        } catch (IOException e) {

            // TODO: 打印日志

            return false;

        }

        if (bufferedImage != null) {

            bufferedImage = zoomInImage(bufferedImage, times);

            try {

                // TODO: 这个保存路径需要配置下子好一点

                ImageIO.write(bufferedImage, "JPG", new File(newPath)); // 保存修改后的图像,全部保存为JPG格式

            } catch (IOException e) {

                // TODO 打印错误信息

                return false;

            }

        }

        return true;

    }

    /**
     * 
     * 对图片进行缩小
     * 
     * @param originalImage
     *            原始图片
     * 
     * @param times
     *            缩小倍数
     * 
     * @return 缩小后的Image
     */

    public static BufferedImage zoomOutImage(BufferedImage originalImage,
            Integer times) {

        int width = originalImage.getWidth() / times;

        int height = originalImage.getHeight() / times;

        BufferedImage newImage = new BufferedImage(width, height,
                originalImage.getType());

        Graphics g = newImage.getGraphics();

        g.drawImage(originalImage, 0, 0, width, height, null);

        g.dispose();

        return newImage;

    }

    /**
     * 
     * 对图片进行缩小
     * 
     * @param srcPath
     *            源图片路径（绝对路径）
     * 
     * @param newPath
     *            新图片路径（绝对路径）
     * 
     * @param times
     *            缩小倍数
     * 
     * @return 保存是否成功
     */

    public static boolean zoomOutImage(String srcPath, String newPath,
            Integer times) {

        BufferedImage bufferedImage = null;

        try {

            File of = new File(srcPath);

            if (of.canRead()) {

                bufferedImage = ImageIO.read(of);

            }

        } catch (IOException e) {

            // TODO: 打印日志

            return false;

        }

        if (bufferedImage != null) {

            bufferedImage = zoomOutImage(bufferedImage, times);

            try {

                // TODO: 这个保存路径需要配置下子好一点

                ImageIO.write(bufferedImage, "JPG", new File(newPath)); // 保存修改后的图像,全部保存为JPG格式

            } catch (IOException e) {

                // TODO 打印错误信息

                return false;

            }

        }

        return true;

    }

    //测试main方法入口
    public static void main(String[] args) {
        boolean testIn = zoomInImage("D:/temp/verifyCode.jpg", "D:/temp/verifyCode_UP.jpg", 4);
        if (testIn) {
            System.out.println("in ok");
        }
    }
    
    

}
