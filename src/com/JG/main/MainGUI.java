package com.JG.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainGUI {
	public static void main(String[] arg) throws Exception{
		
		JFrame mainFrame = new MainFrameGUI();
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());
	}
}
