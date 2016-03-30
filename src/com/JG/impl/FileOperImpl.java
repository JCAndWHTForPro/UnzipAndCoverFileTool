package com.JG.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileOperImpl implements IFileOper {
	@Override
	public void copyFile(String sourceFile, String destFile) {
		InputStream in = null;
		OutputStream out = null;
		File inFile = new File(sourceFile);
		File outFile = new File(destFile);
		try {
			if(outFile==null||!outFile.exists()){
				outFile.createNewFile();
			}
			in = new FileInputStream(inFile);
			out = new FileOutputStream(outFile);
			
			byte buffer[] = new byte[2046];
			
			int count = 0;
			while((count = in.read(buffer))!=-1){
				out.write(buffer, 0, count);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void coverFile(File sourcePath, File destPath,Set<String> excludeFileNameSet) {
		if(excludeFileNameSet!=null&&excludeFileNameSet.size()>0
				&&excludeFileNameSet.contains(destPath.getAbsolutePath())){
			System.out.println("exclude file'name is : "+destPath.getAbsolutePath());
			return;//�������Ҫ�ų���Ŀ¼�������ļ�����ֱ�ӷ���
		}
		if(sourcePath.isDirectory()){
			if(!destPath.exists()){
				destPath.mkdirs();
			}
			File[] listfiles = sourcePath.listFiles();
			for(File it : listfiles){
				String fileName = it.getName();
				File destFile = new File(destPath,fileName);
				coverFile(it,destFile,excludeFileNameSet);
			}
		}else{
			this.copyFile(sourcePath.getAbsolutePath(), destPath.getAbsolutePath());
		}
	}
	
	
	@Override
	public String getFileNameNoPoxiy(String fileName){
		int lastPoint = fileName.lastIndexOf('.');
		if(lastPoint==-1){
			return fileName;
		}else{
			return fileName.substring(0, lastPoint);
		}
		
	}
	
	private boolean isContainName(String name,Set<String> excludeFileNames){
		if(excludeFileNames==null||excludeFileNames.size()==0){
			return false;
		}
		for(Iterator<String> it = excludeFileNames.iterator();it.hasNext();){
			String value = it.next();
			if(name.startsWith(value)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void unzipFile(File zipFile,File destFile,Set<String> excludeFileNames){
		ZipInputStream zip = null;
		FileOutputStream output = null;
		byte buffer[] = new byte[2046];
		int count = 0;
		try {
			zip = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry entry;
			while((entry=zip.getNextEntry())!=null){
				//����û�а�����Ŀ¼
				if(!this.isContainName(entry.getName(), excludeFileNames)){
					continue;
				}
				File tempDestFile = new File(destFile.getAbsolutePath()+File.separator+entry.getName()+File.separator);
				if(entry.isDirectory()){
					tempDestFile.mkdirs();
				}else{
					tempDestFile.getParentFile().mkdirs();//����
					tempDestFile.createNewFile();
					output = new FileOutputStream(tempDestFile);
					while((count = zip.read(buffer))!=-1){
						output.write(buffer, 0, count);
					}
					output.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(zip!=null){
				try {
					zip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public boolean delDir(File srcFile) {
	    if (!srcFile.exists()){
            System.out.println("The dir need to delete not exists!");
            return false;
	    }
		if (!srcFile.isDirectory()) {
			System.out.println("The dir need to delete is not a directory!");
			return false;
		}
        // ɾ��Ŀ¼�е�����Ԫ�أ������ļ����ļ���
		File fileList[] = srcFile.listFiles();
		for (int k = 0; k < fileList.length; k++) {
			File delFile = fileList[k];
			if (delFile.isFile()) {
				boolean re = delFile.delete();
				if(!re){
					System.out.println("del file not success." + delFile.getAbsolutePath());
    			}
			} else if (delFile.isDirectory()) {
				delDir(delFile);
			}
		}
		// ɾ����ǰĿ¼
		return srcFile.delete();
	}
	
}
