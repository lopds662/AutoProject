package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import controller.FileHandler;

public class Main_frame {

	private JFrame frame, animationFrame;
	private JPanel textPanel, menuPanel, menuPanelTop, errorPanel, aniPanelMain;
	private JToolBar toolBar, toolBarTop1, toolBarTop2;
	private JTabbedPane tabPanel, tabError;
	private JButton sizeBtt, moveButt, rectBtt, loopBtt, assignBtt, lineBtt, newBtt, openBtt, saveBtt, saveAsBtt,
			tokenBtt, compileBtt, execBtt;
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuRun, menuPref;
	private JMenuItem newMenu, openMenu, saveMenu, saveAsMenu, exitMenu, undoMenu, redoMenu, tokenMenu, compileMenu,
			execMenu, helpMenu, aboutMenu, closeTabMenu;
	private FileHandler fileRW;
	private JTextArea showErrorView, symbolView;
	private JCheckBox onOffAni;
	private Image sizeIcon = null, moveIcon = null, rectIcon = null, loopIcon = null, assignIcon = null,
			lineIcon = null;

	private Font font;
	private int tabNo;
	private boolean getShowConfig;

	public Main_frame() throws FileNotFoundException, IOException {
		fileRW = new FileHandler();
		font = new Font("Verdana", Font.PLAIN, 12);
		tabNo = 1;
		getShowConfig = readConfig();  
		new Tutor(getShowConfig);
		init();
	}

	public void init() {
		frame = new JFrame("Autometa Project: phase 1");
		frame.setBounds(100, 100, 650, 650);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

		animationFrame = new JFrame("Animation frame");
		animationFrame.setBounds(900, 100, 500, 500);
		animationFrame.setVisible(true);
		animationFrame.setDefaultCloseOperation(animationFrame.EXIT_ON_CLOSE);

		aniPanelMain = new JPanel();
		aniPanelMain.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Animation",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		animationFrame.add(aniPanelMain);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		menuRun = new JMenu("Run");
		menuBar.add(menuRun);

		menuPref = new JMenu("Preference");
		menuBar.add(menuPref);

		newMenu = new JMenuItem("New file (tab) ..... Ctrl+t");
		newMenu.setAccelerator(KeyStroke.getKeyStroke("control T"));
		newMenu.addActionListener(new listenerNewTab());

		openMenu = new JMenuItem("Open               ..... Ctrl+o");
		openMenu.setAccelerator(KeyStroke.getKeyStroke("control O"));
		openMenu.addActionListener(new listenerOpen());

		saveMenu = new JMenuItem("Save                 ..... Ctrl+s");
		saveMenu.setAccelerator(KeyStroke.getKeyStroke("control S"));
		saveMenu.addActionListener(new listenerSave());

		saveAsMenu = new JMenuItem("Save As              ..... Ctrl+s");
		saveAsMenu.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		saveAsMenu.addActionListener(new listenerSaveAs());

		closeTabMenu = new JMenuItem("Close tab");
		closeTabMenu.setAccelerator(KeyStroke.getKeyStroke("control W"));
		closeTabMenu.addActionListener(new listenerCloseTab());
		
		exitMenu = new JMenuItem("Exit");
		exitMenu.addActionListener(new listenerExit());

		menuFile.add(newMenu);
		menuFile.add(openMenu);
		menuFile.add(saveMenu);
		menuFile.add(saveAsMenu);
		menuFile.add(closeTabMenu);
		menuFile.add(exitMenu);

		undoMenu = new JMenuItem("Undo");
		redoMenu = new JMenuItem("Redo");

		menuEdit.add(undoMenu);
		menuEdit.add(redoMenu);

		tokenMenu = new JMenuItem("Tokenizer");
		compileMenu = new JMenuItem("Compiler");
		execMenu = new JMenuItem("Execute");

		menuRun.add(tokenMenu);
		menuRun.add(compileMenu);
		menuRun.add(execMenu);

		helpMenu = new JMenuItem("Help");
		aboutMenu = new JMenuItem("About");

		menuPref.add(helpMenu);
		menuPref.add(aboutMenu);

		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setPreferredSize(new Dimension(100, 650));
		frame.add(menuPanel, BorderLayout.WEST);

		menuPanelTop = new JPanel();
		menuPanelTop.setLayout(new BoxLayout(menuPanelTop, BoxLayout.Y_AXIS));
		frame.add(menuPanelTop, BorderLayout.NORTH);

		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		frame.add(textPanel, BorderLayout.CENTER);

		errorPanel = new JPanel();
		errorPanel.setPreferredSize(new Dimension(200, 650));
		errorPanel.setLayout(new BorderLayout());
		frame.add(errorPanel, BorderLayout.EAST);

		toolBar = new JToolBar();
		toolBar.setLayout(new GridLayout(10, 1));
		menuPanel.add(toolBar);

		toolBarTop1 = new JToolBar();
		toolBarTop1.setLayout(new GridLayout(1, 8));
		menuPanelTop.add(toolBarTop1);

		toolBarTop2 = new JToolBar();
		toolBarTop2.setLayout(new GridLayout(1, 8));
		menuPanelTop.add(toolBarTop2);

		tabPanel = new JTabbedPane(JTabbedPane.TOP);
		textPanel.add(tabPanel);

		// @ tap text panel
		tabPanel.addTab("untitled" + tabNo, new CustomTextArea());
		tabNo++;

		showErrorView = new JTextArea();
		showErrorView.setEditable(false);
		symbolView = new JTextArea();
		symbolView.setEditable(false);

		tabError = new JTabbedPane(JTabbedPane.TOP);
		errorPanel.add(tabError);

		tabError.addTab("Show Error", showErrorView);
		tabError.addTab("Symbol Table", symbolView);

		// sizeBtt, moveButt, rectBtt, loopBtt, assignBtt, lineBtt;
		try {
			sizeIcon = ImageIO.read(getClass().getResource("/images/sizeIcon.bmp"));
			moveIcon = ImageIO.read(getClass().getResource("/images/move.bmp"));
			rectIcon = ImageIO.read(getClass().getResource("/images/rectIcon.bmp"));
			loopIcon = ImageIO.read(getClass().getResource("/images/loopIcon.bmp"));
			assignIcon = ImageIO.read(getClass().getResource("/images/assignIcon.bmp"));
			lineIcon = ImageIO.read(getClass().getResource("/images/lineIcon.bmp"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		sizeBtt = new JButton("Size Btt");
		sizeBtt.setIcon(new ImageIcon(sizeIcon));
		sizeBtt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomTextArea cus = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
				new SizeFrame(cus);
			}
		});

		moveButt = new JButton("Move Btt");
		moveButt.setIcon(new ImageIcon(moveIcon));
		moveButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomTextArea cus = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
				new MoveFrame(cus);
			}
		});

		rectBtt = new JButton("Rect Btt");
		rectBtt.setIcon(new ImageIcon(rectIcon));
		rectBtt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomTextArea cus = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
				new RectFrame(cus);
			}
		});

		loopBtt = new JButton("LoopBtt");
		loopBtt.setIcon(new ImageIcon(loopIcon));
		loopBtt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomTextArea cus = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
				new loopFrame(cus);
			}
		});
		
		assignBtt = new JButton("AssignBtt");
		assignBtt.setIcon(new ImageIcon(assignIcon));
		assignBtt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomTextArea cus = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
				new AssignFrame(cus);
			}
		});

		lineBtt = new JButton("Line Btt");
		lineBtt.setIcon(new ImageIcon(lineIcon));
		lineBtt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomTextArea cus = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
				new lineFrame(cus);
			}
		});
		
		toolBar.add(sizeBtt);
		toolBar.add(moveButt);
		toolBar.add(rectBtt);
		toolBar.add(loopBtt);
		toolBar.add(assignBtt);
		toolBar.add(lineBtt);

		// newBtt, openBtt, saveBtt, saveAsBtt, tokenBtt, compileBtt, execBtt;
		newBtt = new JButton("New Btt");
		newBtt.addActionListener(new listenerNewTab());

		openBtt = new JButton("Open Btt");
		openBtt.addActionListener(new listenerOpen());

		saveBtt = new JButton("Save Btt");
		saveBtt.addActionListener(new listenerSave());

		saveAsBtt = new JButton("Save As Btt");
		saveAsBtt.addActionListener(new listenerSaveAs());

		onOffAni = new JCheckBox("O/F Ani Windows");
		onOffAni.setSelected(true);
		onOffAni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onOffAni.isSelected()) {
					animationFrame.setVisible(true);
				} else {
					animationFrame.setVisible(false);
				}
			}
		});

		toolBarTop1.add(newBtt);
		toolBarTop1.add(openBtt);
		toolBarTop1.add(saveBtt);
		toolBarTop1.add(saveAsBtt);
		toolBarTop1.add(onOffAni);

		tokenBtt = new JButton("Tokenizer Btt");
		compileBtt = new JButton("Compiler Btt");
		execBtt = new JButton("Execute Btt");

		toolBarTop2.add(tokenBtt);
		toolBarTop2.add(compileBtt);
		toolBarTop2.add(execBtt);
		
	}

	private class listenerNewTab implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tabPanel.addTab("untitled" + tabNo, new CustomTextArea());
			tabNo++;
		}
	}

	private class listenerOpen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File file = fileRW.openFile(frame);
			String textFromFile = "";
			try { textFromFile = fileRW.readFile(file);	} 
			catch (IOException e1) {e1.printStackTrace(); }
			tabPanel.addTab(file.getName(), new CustomTextArea());
			tabPanel.setSelectedIndex(tabPanel.getTabCount() - 1);
			CustomTextArea cusRead = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
			System.out.println("new tab has created.");
			System.out.println(textFromFile);
			cusRead.textArea.setText(textFromFile);
		}
	}

	private class listenerSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CustomTextArea cusOut = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
			String fileName = tabPanel.getTitleAt(tabPanel.getSelectedIndex());
			fileName = fileRW.save(fileName, cusOut, frame);
			tabPanel.setTitleAt(tabPanel.getSelectedIndex(), fileName);
		}
	}

	private class listenerSaveAs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CustomTextArea cusOut = (CustomTextArea) tabPanel.getComponent(tabPanel.getSelectedIndex());
			String fileName = fileRW.saveAs(cusOut, frame);
			tabPanel.setTitleAt(tabPanel.getSelectedIndex(), fileName);
		}
	}

	private class listenerCloseTab implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = tabPanel.getSelectedIndex();
			if (index >= 0) {
				tabPanel.removeTabAt(index);
			}
		}
	}

	private class listenerExit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	public boolean readConfig() throws IOException, FileNotFoundException{
		File file = null ;
		String temp = "";
		boolean out = true;
		BufferedReader reader;
		file = new File("D:/cs333-data/config.txt");
		if (file.exists()){
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			temp = reader.readLine().split(",")[1];
			out = Boolean.parseBoolean(temp);
		}else {
			BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));
			PrintWriter writer = new PrintWriter(file);
			String data = "Show_tutorial_shortcut,true";
			data = data.replaceAll("(?!\\r)\\n", "\r\n");
			writer.write(data);
			writer.flush();
			bfWriter.close();
			writer.close();
		}
		
		
		return out;
	}
}