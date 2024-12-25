package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;

public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 字节大小转B,KB,MB,GB
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(Long fileS) {
		if (fileS==null) {
			return "0B";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

    /**
     * 文件转byte[]
     * @param file 文件
     * @return byte数组
     */
    public static byte[] getFileByte(File file) {
        byte[] buffer = null;

        try (FileInputStream fis = new FileInputStream(file)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
            bos.close();
        } catch (IOException e) {
            logger.error("获取文件失败",e);
        }
        return buffer;
    }

    /**
     * Byte数组转文件
     * @param buffer 字节
     * @param filePath 文件路径
     * @return 文件
     */
    public static File byteToFile(byte[] buffer, final String filePath){
        File file = new File(filePath);
        try (BufferedOutputStream bufferedOutput =new BufferedOutputStream(new FileOutputStream(file))){
            bufferedOutput.write(buffer);
        } catch (IOException e) {
            logger.error("获取文件失败",e);
        }

        return file;
    }

    /**
     * 读取文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String getFileContent(File file){
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String s;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
        }catch(Exception e){
            logger.error("读取文件失败",e);
        }
        return result.toString();
    }

	/**
	 * @Description: 输入流转字节数组
	 *
	 * @param inputStream
	 * @returns:
	 * @Author: yaoxw4
	 * @Date: 2022/7/4
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len;
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
	
	public static String readText(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			String readLine;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			return sb.toString();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return "";
	}

	/***
	 * 将文件名称切分为文件名和文件类型
	 *
	 * @param originFileName 原始文件名称
	 * @return String[] 0:文件名 1:文件类型
	 */
	public static String[] splitFileName(String originFileName) {
		String[] result = new String[2];
		if (originFileName == null) {
			return result;
		}
		int dotIndex = originFileName.lastIndexOf(".");
		if (dotIndex == -1) {
			// 文件名中没有包含文件类型
			result[0] = originFileName;
			result[1] = "";
		} else {
			// 文件名中包含文件类型
			result[0] = originFileName.substring(0, dotIndex);
			result[1] = originFileName.substring(dotIndex + 1);
		}
		return result;
	}

}
