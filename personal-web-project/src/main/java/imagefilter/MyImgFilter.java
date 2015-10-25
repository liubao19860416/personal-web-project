package imagefilter;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class MyImgFilter {
    //默认灰度临界值
    private static  final int DEFAULTGREYVALUE = 195;
    
    private static BufferedImage image=null;
    private static int iw, ih;
    private static int[] pixels;

    private MyImgFilter(BufferedImage image) {
        iw = image.getWidth();
        ih = image.getHeight();
        pixels = new int[iw * ih];
    }
    
    // TODO 测试main方法
    public static void main(String[] args) throws Exception {
        System.out.println("图片过滤转换就开始。。。");
        File sourceFile=new File("d:/temp/verifyCode.jpg");
        cleanImageMethod2(sourceFile);
        //System.out.println(sourceFile.getParent());
        System.out.println("图片过滤转换结束。。。");
    }
    
    
    public static void cleanImageMethod2(File sourceFile) throws Exception {
        // 读取指定文件
        FileInputStream in = new FileInputStream(sourceFile);
        image = ImageIO.read(in);
        // 构造方法初始化对象
        MyImgFilter myImgFilter = new MyImgFilter(image);
        // 重构图像，需要设置灰度临界值
        image = myImgFilter.changeGrey(DEFAULTGREYVALUE);
        // 设置灰度值默认样式?
        image = myImgFilter.getGrey();
        // 进行图像放大和缩小
        image = myImgFilter.getBrighten();
        // 获取当前图片对象
        image = myImgFilter.getProcessedImg();
        //保存文件
        File cleanedFile = new File(sourceFile.getParent(), "clean2_"+sourceFile.getName());
        // 将图形写到硬盘指定位置
        ImageIO.write(image, "jpg", cleanedFile);
        
    }
    
    
    //=============================私有private方法列表================================
    
    
    private BufferedImage changeGrey(int greyValue ) {
        PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih,
                pixels, 0, iw);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 设定二值化的域值，默认值为100
        int grey = greyValue;//100,(180-210)
        // 对图像进行二值化处理，Alpha值保持不变
        ColorModel cm = ColorModel.getRGBdefault();
        for (int i = 0; i < iw * ih; i++) {
            int red, green, blue;
            int alpha = cm.getAlpha(pixels[i]);
            if (cm.getRed(pixels[i]) > grey) {
                red = 255;
            } else {
                red = 0;
            }
            if (cm.getGreen(pixels[i]) > grey) {
                green = 255;
            } else {
                green = 0;
            }
            if (cm.getBlue(pixels[i]) > grey) {
                blue = 255;
            } else {
                blue = 0;
            }
            pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; // 通过移位重新构成某一点像素的RGB值
        }
        // 将数组中的象素产生一个图像
        Image tempImg = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(iw, ih, pixels, 0, iw));
        image = new BufferedImage(tempImg.getWidth(null),
                tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR);
        image.createGraphics().drawImage(tempImg, 0, 0, null);
        return image;
    }

    @SuppressWarnings("unused")
    private BufferedImage getMedian() {
        PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih,
                pixels, 0, iw);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 对图像进行中值滤波，Alpha值保持不变
        ColorModel cm = ColorModel.getRGBdefault();
        for (int i = 1; i < ih - 1; i++) {
            for (int j = 1; j < iw - 1; j++) {
                int red, green, blue;
                int alpha = cm.getAlpha(pixels[i * iw + j]);
                // int red2 = cm.getRed(pixels[(i - 1) * iw + j]);
                int red4 = cm.getRed(pixels[i * iw + j - 1]);
                int red5 = cm.getRed(pixels[i * iw + j]);
                int red6 = cm.getRed(pixels[i * iw + j + 1]);
                // int red8 = cm.getRed(pixels[(i + 1) * iw + j]);
                // 水平方向进行中值滤波
                if (red4 >= red5) {
                    if (red5 >= red6) {
                        red = red5;
                    } else {
                        if (red4 >= red6) {
                            red = red6;
                        } else {
                            red = red4;
                        }
                    }
                } else {
                    if (red4 > red6) {
                        red = red4;
                    } else {
                        if (red5 > red6) {
                            red = red6;
                        } else {
                            red = red5;
                        }
                    }
                }
                int green4 = cm.getGreen(pixels[i * iw + j - 1]);
                int green5 = cm.getGreen(pixels[i * iw + j]);
                int green6 = cm.getGreen(pixels[i * iw + j + 1]);
                // 水平方向进行中值滤波
                if (green4 >= green5) {
                    if (green5 >= green6) {
                        green = green5;
                    } else {
                        if (green4 >= green6) {
                            green = green6;
                        } else {
                            green = green4;
                        }
                    }
                } else {
                    if (green4 > green6) {
                        green = green4;
                    } else {
                        if (green5 > green6) {
                            green = green6;
                        } else {
                            green = green5;
                        }
                    }
                }
                // int blue2 = cm.getBlue(pixels[(i - 1) * iw + j]);
                int blue4 = cm.getBlue(pixels[i * iw + j - 1]);
                int blue5 = cm.getBlue(pixels[i * iw + j]);
                int blue6 = cm.getBlue(pixels[i * iw + j + 1]);
                // int blue8 = cm.getBlue(pixels[(i + 1) * iw + j]);
                // 水平方向进行中值滤波
                if (blue4 >= blue5) {
                    if (blue5 >= blue6) {
                        blue = blue5;
                    } else {
                        if (blue4 >= blue6) {
                            blue = blue6;
                        } else {
                            blue = blue4;
                        }
                    }
                } else {
                    if (blue4 > blue6) {
                        blue = blue4;
                    } else {
                        if (blue5 > blue6) {
                            blue = blue6;
                        } else {
                            blue = blue5;
                        }
                    }
                }
                pixels[i * iw + j] = alpha << 24 | red << 16 | green << 8
                        | blue;
            }
        }
        // 将数组中的象素产生一个图像
        Image tempImg = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(iw, ih, pixels, 0, iw));
        image = new BufferedImage(tempImg.getWidth(null),
                tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR);
        image.createGraphics().drawImage(tempImg, 0, 0, null);
        return image;
    }

    /**
     * 设置灰度值默认样式?
     */
    private BufferedImage getGrey() {
        ColorConvertOp ccp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        return image = ccp.filter(image, null);
    }

    /**
     *  Brighten using a linear formula that increases all color values
     *  进行图像放大和缩小
     * @return
     */
    private BufferedImage getBrighten() {
        RescaleOp rop = new RescaleOp(1.25f, 0, null);
        return image = rop.filter(image, null);
    }

    // Blur by "convolving" the image with a matrix
    @SuppressWarnings("unused")
    private BufferedImage getBlur() {
        float[] data = { .1111f, .1111f, .1111f, .1111f, .1111f, .1111f,
                .1111f, .1111f, .1111f, };
        ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
        return image = cop.filter(image, null);
    }

    // Sharpen by using a different matrix
    @SuppressWarnings("unused")
    private BufferedImage getSharpen() {
        float[] data = { 0.0f, -0.75f, 0.0f, -0.75f, 4.0f, -0.75f, 0.0f,
                -0.75f, 0.0f };
        ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
        return image = cop.filter(image, null);
    }

    // 11) Rotate the image 180 degrees about its center point
    @SuppressWarnings("unused")
    private BufferedImage getRotate() {
        AffineTransformOp atop = new AffineTransformOp(
                AffineTransform.getRotateInstance(Math.PI,
                        image.getWidth() / 2, image.getHeight() / 2),
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return image = atop.filter(image, null);
    }

    private BufferedImage getProcessedImg() {
        return image;
    }
    
}
