package com.JG.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.JG.common.Constans;
import com.JG.service.ClientService;

public class MainFrameGUI extends JFrame{
	private static final long serialVersionUID = 7723806482557461833L;

	Map<String,String> extend;
	
	private JButton ok = new JButton("开始");
	private JButton close = new JButton("关闭");
	
	private JLabel labelForDirText = new JLabel("覆盖文件夹路径："); 
	private JTextField dirText = new JTextField(40);
	
	private JTextField expText = new JTextField(40);
	private JLabel expLabel = new JLabel("不包含的目录名");
	
	private JTextField zipFileDirText = new JTextField(40);
	private JLabel zipFileLabel = new JLabel("压缩文件路径");
	
	private JPanel textPanel = new JPanel();
	private JPanel zipPanel = new JPanel();
	private JPanel containerPanel = new JPanel(new BorderLayout());
	private JPanel buttonPanel = new JPanel();
	public MainFrameGUI(Map<String,String> extend) {
		this.extend = extend;
		initGUI();
	}

	private void initGUI() {
		dirText.setText("D:\\netnumen\\ems\\");
		textPanel.add(labelForDirText);
		textPanel.add(dirText);
		expText.setText("D:\\netnumen\\ems\\ums-server\\procs\\ppus\\mucf.ppu\\mucf-main.pmu\\mucf-osf-mml.par\\conf");
		textPanel.add(expLabel);
		textPanel.add(expText);
		
		zipFileDirText.setText(this.extend.get(Constans.ZIP_FILE_ABSOLUTELY_PATH));
		zipPanel.add(zipFileLabel);
		zipPanel.add(zipFileDirText);
		
		containerPanel.add(textPanel, BorderLayout.NORTH);
		containerPanel.add(zipPanel, BorderLayout.SOUTH);
		
		
		buttonPanel.add(ok);
		buttonPanel.add(close);
		this.add(containerPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String destText = dirText.getText();
				String sourceText = zipFileDirText.getText();
				Set<String> set = new HashSet<String>();
				set.add(expText.getText());
				try {
					ClientService.getService().deal(sourceText, destText, set);
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally{
					if(extend.get(Constans.ZIP_FILE_ABSOLUTELY_PATH)!=null){
						new File(extend.get(Constans.ZIP_FILE_ABSOLUTELY_PATH)).delete();
					}
					MainFrameGUI.this.dispose();
				}
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameGUI.this.dispose();
			}
		});
		
		this.setLocation(200, 250);
		pack();
		setVisible(true);
	}
}
