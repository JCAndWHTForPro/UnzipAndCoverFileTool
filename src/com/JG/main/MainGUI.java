package com.JG.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.JG.common.Constans;
import com.JG.util.FileUtil;

public class MainGUI {
	@SuppressWarnings("unchecked")
	public static void main(String[] arg) throws Exception{
		String zipFileAbsolutePath = null;
		if(arg.length>0){
			zipFileAbsolutePath = arg[0];
		}else{
			zipFileAbsolutePath="D:\\netnumen\\mucf-SNAPSHOT.zip";
		}
		System.out.println(System.getProperty("user.dir"));
		File currentDir = new File(System.getProperty("user.dir"));
		Map<String,Object> map = getXMLInfo(new File(currentDir,"base.xml"));
		Set<String> unZipSet = new HashSet<String>((List<String>)map.get(Constans.UNZIPFILE_LIST));
		Set<String> excludeFileSet = new HashSet<String>((List<String>)map.get(Constans.EXCLUDEFILE_LIST));
		
		File unzipFileSource = new File(zipFileAbsolutePath);
		File unzipFileDest = new File(currentDir,FileUtil.getFileNameNoPoxiy(unzipFileSource.getName()));
		unzipFileDest.mkdirs();
		FileUtil.unzipFile(unzipFileSource, unzipFileDest, unZipSet);
		System.out.println("unzip file successful!");
		FileUtil.coverFile(unzipFileDest, new File((String)map.get(Constans.DEST_DIR)), excludeFileSet);
		System.out.println("cover file successful!");
		FileUtil.delDir(unzipFileDest);
		unzipFileSource.delete();
	}
	
	/**
	 * 解析xml中的文件路径信息
	 * @author Ji Cheng 
	 * @date 2016年4月16日
	 * @param file
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> getXMLInfo(File file) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		
		resultMap.put(Constans.DEST_DIR, root.elementText(Constans.XML_DESTINATION));
		Element zipListEl = root.element(Constans.XML_UNZIPFILE);
		List<String> unzipFileList = new ArrayList<String>();
		for(int i=0;zipListEl.elements()!=null&&i<zipListEl.elements().size();i++){
			Element tempEl = (Element)zipListEl.elements().get(i);
			unzipFileList.add(tempEl.getText());
		}
		resultMap.put(Constans.UNZIPFILE_LIST, unzipFileList);
		
		Element exludeListEl = root.element(Constans.XML_EXCLUDECOVERY);
		List<String> excludeFileList = new ArrayList<String>();
		for(int i=0;exludeListEl.elements()!=null&&i<exludeListEl.elements().size();i++){
			Element tempEl = (Element)exludeListEl.elements().get(i);
			excludeFileList.add(tempEl.getText());
		}
		resultMap.put(Constans.EXCLUDEFILE_LIST, excludeFileList);
		return resultMap;
	}
}
