package imagefilter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码图片生成测试（类似）
 * 
 * @author Liubao
 * @2014年12月2日
 * 
 */
public class PictrueGenerateTest {

    public static void main(String[] args) throws IOException {
        String date = "ABCDEFGH";
        BufferedImage img = new BufferedImage(120, 40,BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 120, 40);
        Random r = new Random();
        int width = 5;
        int height = 30;
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(date.length());
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.drawString(date.charAt(index) + "", width, height);
            width += 30;
        }

        for (int i = 0; i < 10; i++) {
            int start_x = r.nextInt(118);
            int start_y = r.nextInt(38);
            int end_x = r.nextInt(118);
            int end_y = r.nextInt(38);
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawLine(start_x, start_y, end_x, end_y);

        }
        g.dispose();
        FileOutputStream fos = new FileOutputStream("d:\\temp\\1.jpg");
        ImageIO.write(img, "JPEG", fos);
        fos.close();

    }

}
