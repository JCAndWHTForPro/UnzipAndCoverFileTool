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
			throw new Exception("文件不存在，请检查文件路径!");
		}
		if(!sourceFile.getName().endsWith(".zip")){
			throw new Exception("源文件不是zip文件，请重新输入一个zip压缩文件！");
		}
		if(destFile == null||!destFile.isDirectory()){
			throw new Exception("目标不是目录，请检查目标！");
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
