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
			System.out.println("");
			return;
		}
		Map<String,Object> map = getXMLInfo(new File("D:\\tempFile\\base.xml"));
		Set<String> unZipSet = new HashSet<String>((List<String>)map.get(Constans.XML_UNZIPFILE));
		Set<String> excludeFileSet = new HashSet<String>((List<String>)map.get(Constans.XML_EXCLUDECOVERY));
		
		File unzipFileSource = new File(zipFileAbsolutePath);
		File unzipFileDest = new File(unzipFileSource.getAbsolutePath()+File.separator);
		unzipFileDest.mkdirs();
		FileUtil.unzipFile(unzipFileSource, unzipFileDest, unZipSet);
		FileUtil.coverFile(unzipFileDest, new File((String)map.get(Constans.XML_DESTINATION)), excludeFileSet);
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
