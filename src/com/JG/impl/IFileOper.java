package com.JG.impl;

import java.io.File;
import java.util.Set;

public interface IFileOper {
	
	/**
	 * 复制文件，覆盖文件
	 * @param soreceFile 源文件名
	 * @param destFile	目标文件名
	 */
	public void copyFile(String soreceFile,String destFile);
	
	/**
	 * 覆盖目录，如果存在文件就覆盖，如果不存在文件就直接移动到那里
	 * @param sourcePath
	 * @param destPath
	 */
	public void coverFile(File sourcePath, File destPath,Set<String> excludeFileNameSet);
	
	/**
	 * 解压zip压缩文件到指定目录
	 * @param zipFile
	 * @param destFile
	 */
	public void unzipFile(File zipFile,File destFile,Set<String> excludeFileNames);
	
	/**
	 * 获取不带后缀名的文件名
	 * @author Ji Cheng 
	 * @date 2016年3月30日
	 * @param fileName
	 * @return String
	 */
	public String getFileNameNoPoxiy(String fileName);
	
	/**
	 * 删除目录中的所有文件，包含这个目录
	 * @author Ji Cheng 
	 * @date 2016年3月30日
	 * @param srcFile
	 * @return boolean
	 */
	public boolean delDir(File srcFile);
	
}
