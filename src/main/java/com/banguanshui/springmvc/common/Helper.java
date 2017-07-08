package com.banguanshui.springmvc.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Helper {
	public String timestampToDate(int t) {		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = new Timestamp(Long.parseLong(t + "000"));         
		return sdf.format(ts);
	}
	
	public String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//确定计算方法
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(str.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
		//加密后的字符串
	//	String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	//	return newstr;
	}
	
	public String handleUploadFile(MultipartFile uploadFile, String savePathDir) {
		if (!uploadFile.isEmpty()) {
			try {
				byte[] bytes = uploadFile.getBytes();
				String fileName = uploadFile.getOriginalFilename();
				String ext = uploadFile.getOriginalFilename().split("\\.")[1];
				String path = savePathDir;
				String fileNameNew = this.encoderByMd5(fileName + (int)(new Date().getTime())) + "." + ext;
				String fileUri = "uploads/" + fileNameNew;
				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.base");
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + fileNameNew);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return fileUri;
				//return "You successfully uploaded file=";
			} catch (Exception e) {
				//return "You failed to upload " + " => " + e.getMessage();
				return "";
			}
		}
		else {
			return "";
		}
	}
	
}
