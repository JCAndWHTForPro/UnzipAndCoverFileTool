package com.JG.service;

import com.JG.impl.FileOperImpl;
import com.JG.impl.IFileOper;

public class MainService {
	private static MainService service;
	
	private IFileOper fileOper;
	private MainService(){
		
	}
	public synchronized static MainService getMainService(){
		if(service == null){
			service = new MainService();
		}
		return service;
	}
	
	/**
	 * ��ȡ�ļ���������
	 * @return
	 */
	public IFileOper getFileOperService(){
		if(fileOper==null){
			fileOper = new FileOperImpl();
		}
		return fileOper;
		
	}
}
