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
	
	/**
	 * ��ȡ������׺�����ļ���
	 * @author Ji Cheng 
	 * @date 2016��3��30��
	 * @param fileName
	 * @return String
	 */
	public String getFileNameNoPoxiy(String fileName);
	
	/**
	 * ɾ��Ŀ¼�е������ļ����������Ŀ¼
	 * @author Ji Cheng 
	 * @date 2016��3��30��
	 * @param srcFile
	 * @return boolean
	 */
	public boolean delDir(File srcFile);
	
}
