package com.JG.main;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.JG.common.Constans;

public class MainGUI {
	public static void main(String[] arg) throws Exception{
//		System.out.println(arg.length);
		String zipFileAbsolutePath = null;
		if(arg.length>0){
			zipFileAbsolutePath = arg[0];
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put(Constans.ZIP_FILE_ABSOLUTELY_PATH, zipFileAbsolutePath);
		JFrame mainFrame = new MainFrameGUI(map);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());
	}
}
