package com.JG.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.JG.service.ClientService;

public class MainFrameGUI extends JFrame{
	private static final long serialVersionUID = 7723806482557461833L;

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
	public MainFrameGUI() {
		initGUI();
	}

	private void initGUI() {
		textPanel.add(labelForDirText);
		textPanel.add(dirText);
		textPanel.add(expLabel);
		textPanel.add(expText);
		
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
				try {
					ClientService.getService().deal(sourceText, destText, null);
				} catch (Exception e1) {
					e1.printStackTrace();
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
