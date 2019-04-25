package gbq.ssm.utils;

import java.sql.Timestamp;

public class FileEntity {

    private String type;

    private String size;

    private String path;

    private String titleOrig;

    private String titleAlter;

    private Timestamp uploadTime;
    
    private boolean bflag;
    
    private String uploadInfo;

    public String getUploadInfo() {
		return uploadInfo;
	}

	public void setUploadInfo(String uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	public boolean isBflag() {
		return bflag;
	}

	public void setBflag(boolean bflag) {
		this.bflag = bflag;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitleOrig() {
        return titleOrig;
    }

    public void setTitleOrig(String titleOrig) {
        this.titleOrig = titleOrig;
    }

    public String getTitleAlter() {
        return titleAlter;
    }

    public void setTitleAlter(String titleAlter) {
        this.titleAlter = titleAlter;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
