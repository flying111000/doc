p
import java.io.*;
import java.util.zip.*;

/**
 * 
 * http://blog.csdn.net/Miss_kun/article/details/41842157
 *
 */
public class WriteZip {
	static final int BUFFER = 2048;
	static boolean flag = false;

	public static void main(String args[]) throws IOException {
		File file = new File("D:/Temp");
		ZipSubdirectory(file);
		FileInputStream file1 = new FileInputStream(ZipSubdirectory(file));
		System.out.println(file1.toString());
	}

	// ZipSubdirectory函数将一个指定目录（包括它子目录）压缩成一个同名压缩文件(这里称为"ORIGIN")
	public static File ZipSubdirectory(File myDir) throws IOException {
		// 创建缓冲输入流BufferedInputStream
		BufferedInputStream origin = null;
		// 创建ZipOutputStream对象，将向它传递希望写入文件的输出流
		File zipFile = new File("D:/" + myDir.getName() + ".zip");
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(fos, BUFFER));
		// dirContents[]获取当前目录(myDir)所有文件对象（包括子目录名)
		File dirContents[] = myDir.listFiles();
		// 创建临时文件tempFile,使用后删除
		File tempFile = null;
		try {
			// 处理当前目录所有文件对象，包括子目录
			for (int i = 0; i < dirContents.length; i++) {
				// 使用递归方法将当前目录的子目录转成一个ZIP文件，并作为一个ENTRY加进"ORIGIN"
				if (dirContents[i].isDirectory()) {
					tempFile = ZipSubdirectory(dirContents[i]);
					flag = true;
				}
				// 如果当前文件不是子目录
				else {
					tempFile = dirContents[i];
					// flag标记tempFile是否由子目录压缩成的ZIP文件
					flag = false;
				}
				System.out.println("Compress file: " + tempFile.getName());
				FileInputStream fis = new FileInputStream(tempFile);
				origin = new BufferedInputStream(fis, BUFFER);
				// 为被读取的文件创建压缩条目
				ZipEntry entry = new ZipEntry(tempFile.getName());
				byte data[] = new byte[BUFFER];
				int count;
				// 在向ZIP输出流写入数据之前，必须首先使用out.putNextEntry(entry); 方法安置压缩条目对象
				out.putNextEntry(entry);
				// 向ZIP 文件写入数据
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				// tempFile是临时生成的ZIP文件,删除它
				if (flag == true) {
					flag = tempFile.delete();
					System.out.println("Delete file:" + tempFile.getName() + flag);
				}
				// 关闭输入流
				origin.close();
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		// 递归返回
		return zipFile;
	}
}