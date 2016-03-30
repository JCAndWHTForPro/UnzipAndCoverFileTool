package com.JG.service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.JG.impl.IFileOper;

public class ClientService {
	private static ClientService service;
	
	private ClientService(){
		
	}
	
	public static ClientService getService(){
		if(service==null){
			service = new ClientService();
		}
		return service;
	}
	
	public void deal(String sourceFileName,String destFileName,String exceptFileName) throws Exception{
		MainService mainService = MainService.getMainService();
		File sourceFile = new File(sourceFileName);
		File destFile = new File(destFileName);
		if(!sourceFile.exists()||!destFile.exists()){
			throw new Exception("�ļ������ڣ������ļ�·��!");
		}
		if(!sourceFile.getName().endsWith(".zip")){
			throw new Exception("Դ�ļ�����zip�ļ�������������һ��zipѹ���ļ���");
		}
		if(destFile == null||!destFile.isDirectory()){
			throw new Exception("Ŀ�겻��Ŀ¼������Ŀ�꣡");
		}
		IFileOper fileOper = mainService.getFileOperService();
		String zipFileUnzipDir = sourceFile.getParent()+File.separator+destFile.getName()+File.separator;
		File unzipFileDir = new File(zipFileUnzipDir);
		unzipFileDir.mkdirs();
		
		Set<String> set = new HashSet<String>();
		set.add("ums-client/procs");
		set.add("ums-server/procs");
		fileOper.unzipFile(sourceFile, unzipFileDir, set);
		fileOper.coverFile(unzipFileDir, destFile, null);
		System.out.println("end");
	}
}
