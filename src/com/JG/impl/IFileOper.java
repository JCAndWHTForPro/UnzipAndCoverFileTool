package com.JG.impl;

import java.io.File;
import java.util.Set;

public interface IFileOper {
	
	/**
	 * �����ļ��������ļ�
	 * @param soreceFile Դ�ļ���
	 * @param destFile	Ŀ���ļ���
	 */
	public void copyFile(String soreceFile,String destFile);
	
	/**
	 * ����Ŀ¼����������ļ��͸��ǣ�����������ļ���ֱ���ƶ�������
	 * @param sourcePath
	 * @param destPath
	 */
	public void coverFile(File sourcePath, File destPath,Set<String> excludeFileNameSet);
	
	/**
	 * ��ѹzipѹ���ļ���ָ��Ŀ¼
	 * @param zipFile
	 * @param destFile
	 */
	public void unzipFile(File zipFile,File destFile,Set<String> excludeFileNames);
	
	public String getFileNameNoPoxiy(String fileName);
	
}
