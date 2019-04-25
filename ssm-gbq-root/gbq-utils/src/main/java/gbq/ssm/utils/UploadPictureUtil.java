package gbq.ssm.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 图片上传工具类
* @ClassName: UploadPictureUtil
* @author 阿前
* @date 2018年10月12日
 */
public class UploadPictureUtil {
	
	/* 文件最大5M*/
    private static long upload_maxsize = 5 * 1024 * 1024;
    /* 文件允许格式*/
    private static String[] allowFiles = {".png", ".jpg", ".jpeg"};
//    private static String logoRealPathDir = "http://localhost:8081/picture/"; 
    // 上传到本地磁盘,此路径是vue项目路径的文件下，前端启动只需要访问http://localhost:8081/picture/
    private static String logoRealPathDir = "C://Users//28937//Desktop//lockplatform-admin//public//picture"; 
//    private static String logoRealPathDir ="D://picture//";
	
	/**
	 * 检查图片
	* @Title: uploadPicture
	* @param @param multipartFile
	* @param @return
	* @return String
	 */
	public static FileEntity CheckuploadPicture(CommonsMultipartFile multipartFile) {
		//获取图片名称
		String fileName = multipartFile.getOriginalFilename().toString();
		FileEntity entity = new FileEntity();
		boolean bflag = false;
		// 判断文件不为空
        if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
            bflag = true;
            // 判断文件大小
            if (multipartFile.getSize() <= upload_maxsize) {
                bflag = true;
                // 文件类型判断
                if (checkFileType(fileName)) {
                    bflag = true;
                } else {
                    entity.setBflag(false);
                    entity.setUploadInfo("文件类型不允许");
                    return entity;
                }
            } else {
            	entity.setBflag(false);
                entity.setUploadInfo("文件大小超范围");
                return entity;
            }
        } else {
        	entity.setBflag(false);
            entity.setUploadInfo("文件为空");
            return entity;
        }
        if(bflag) {
        	String pictureName = uploadPicture(multipartFile);
        	entity.setBflag(true);
            entity.setTitleOrig(pictureName);
            return entity;
        }else{
        	entity.setBflag(false);
            entity.setUploadInfo("上传失败");
            return entity;
        }
	}
	
	/**
	 * 上传图片
	* @Title: uploadPicture
	* @param @param multipartFile
	* @return void
	 */
	public static String uploadPicture(CommonsMultipartFile multipartFile) {
		//获取图片名称
		String fileName = multipartFile.getOriginalFilename().toString();
		System.out.println("图片的名称为："+fileName);
		//判断目标文件夹是否存在
		File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists()) {
            logoSaveFile.mkdirs();
        } 
        // 文件扩展名
        String fileEnd = getFileExt(fileName);
        //使用UUID产生一个随机的通用唯一识别码 加上 扩展名 组成一个一个新的文件名
        String newFileName = UUID.randomUUID().toString() + fileEnd;
        System.out.println("新的图片名称为："+newFileName);
        File filedirs = new File(logoRealPathDir, newFileName);
        // 转入文件
        try {
            multipartFile.transferTo(filedirs);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
	}
	
	
	/**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    public static boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private static  String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    /**
     * 删除图片
     */
    public static boolean deleteFile(String fileName){
        if (fileName.equals("1.png")) {
            return true;
        }
        fileName = logoRealPathDir +"//"+ fileName;
        File file = new File(fileName);
        file.delete();
        return true;
    }

}
