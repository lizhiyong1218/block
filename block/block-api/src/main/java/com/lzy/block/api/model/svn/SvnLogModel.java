/**
 * 
 */
package com.lzy.block.api.model.svn;

import java.util.Date;

/**
 * 
 * @Description: svn日志
 * @author 李志勇
 * @date 2016年4月18日 下午2:33:46
 * 
 */
public class SvnLogModel {
	//修订版本
    private long revision;
	//提交者
    private String author;
	//日期
    private Date date;
	//注释信息
    private String message;
	//影响文件
    private String logEntry;
	//影响文件数
    private int entryCount;
    
	public long getRevision() {
		return revision;
	}
	public void setRevision(long revision) {
		this.revision = revision;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLogEntry() {
		return logEntry;
	}
	public void setLogEntry(String logEntry) {
		this.logEntry = logEntry;
	}
	public int getEntryCount() {
		return entryCount;
	}
	public void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
	}
	
	@Override
	public String toString() {
		return "SvnLogModel [revision=" + revision + ", author=" + author
				+ ", date=" + date + ", message=" + message + ", logEntry="
				+ logEntry + ", entryCount=" + entryCount + "]";
	}
}
