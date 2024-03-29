package com.lzy.block.core.utils.img;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 图片压缩处理
 * @author 崔素强
 */
public class ImgCompress {
	private Image img;
	private int width;
	private int height;

	public static void main(String[] args) throws Exception {
		System.out.println("开始："  );
		String desc="d:/123";
		ImgCompress imgCom = new ImgCompress("C:/Users/Administrator/AppData/Local/Temp/31eb035d-f240-4a9e-835e-e06830e0bd92");
		imgCom.resizeFix(400, 400,desc);
		System.out.println("结束："  );
	}
	/**
	 * 构造函数
	 */
	public ImgCompress(String fileName) throws IOException {
		File file = new File(fileName);// 读入文件
		img = ImageIO.read(file);      // 构造Image对象
		width = img.getWidth(null);    // 得到源图宽
		height = img.getHeight(null);  // 得到源图长
	}
	/**
	 * 按照宽度还是高度进行压缩
	 * @param w int 最大宽度
	 * @param h int 最大高度
	 */
	public void resizeFix(int w, int h,String desc) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w,desc);
		} else {
			resizeByHeight(h,desc);
		}
	}
	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w int 新宽度
	 */
	public void resizeByWidth(int w,String desc) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h,desc);
	}
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 */
	public void resizeByHeight(int h,String desc) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h,desc);
	}
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	@SuppressWarnings("restriction")
	public void resize(int w, int h,String desc) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
//		File destFile = new File(desc);
//		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(image); // JPEG编码
//		out.close();
		String formatName = desc.substring(desc.lastIndexOf(".") + 1);
		ImageIO.write(image, /*"GIF"*/ formatName /* format desired */ , new File(desc) /* target */ );

	}
}
