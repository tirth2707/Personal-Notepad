package NotePad;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class Notepad extends JFrame {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	JMenuBar menuBar;
	JMenu file;
	JMenu edit;
	JMenuItem open, newFile,save, exit;
	JMenuItem undo,paste, selectAll ;
	JMenu format;
	JMenu help;
	JFileChooser fileChooser;
	JTextArea textArea;
	Clipboard clip ;
	
	Notepad() {
		frame = new JFrame("Notepad Application");
		file = new JMenu("File");
		edit = new JMenu("Edit");
		format = new JMenu("Format");
		help = new JMenu("Help");
		
		newFile = new JMenuItem("New");
		open = new JMenuItem("Open");		
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		undo = new  JMenuItem("Undo                 Ctrl+Z");
		paste = new JMenuItem("Paste                Ctrl+V");
		selectAll = new JMenuItem("Select All       Ctrl+A ");
		textArea = new JTextArea();
		fileChooser = new JFileChooser();
		menuBar = new JMenuBar();
		
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        frame.add(textArea);
		file.add(open);
		file.add(newFile);
		file.add(save);
		file.add(exit);
		edit.add(undo);
		edit.add(paste);
		edit.add(selectAll);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(format);
		menuBar.add(help);
		
		frame.setJMenuBar(menuBar);
		
		OpenListener openL = new OpenListener();
		NewListener NewL = new NewListener();
		SaveListener saveL = new SaveListener();
		ExitListener exitL = new ExitListener();
		open.addActionListener(openL);
		newFile.addActionListener(NewL);
		save.addActionListener(saveL);
		exit.addActionListener(exitL);
		//UndoListener UndoL = new UndoListener();
		PasteListener pasteL = new PasteListener();
		//EditListener EditL = new EditListener();
		//SelectListener SelectL = new SelectListener();
		//undo.addActionListener(UndoL);
		//paste.addActionListener(EditL);
		//selectAll.addActionListener(SelectL);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
				File file = fileChooser.getSelectedFile();
				textArea.setText("");
				Scanner in = null;
				try {
					in = new Scanner(file);
					while(in.hasNext()) {
						String line = in.nextLine();
						textArea.append(line+"\n");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					in.close();
				}
			}
		}
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)) {
				File file = fileChooser.getSelectedFile();
				PrintWriter out = null;
				try {
					out = new PrintWriter(file);
					String output = textArea.getText();
					System.out.println(output);
					out.println(output);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						out.flush();
						} catch(Exception ex1) 
						{
							
						}
					try {
						out.close();
						} catch(Exception ex1) {
							
						}
				}
			}
		}
	}
	
	class NewListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textArea.setText("");
			//frame.add(newFile);
			//textArea.(newFile+"\n");
			
			
			
		}
	}
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	
	
	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Transferable cliptran = clip.getContents(Notepad.this);
	         try
	             {
	             String sel = (String) cliptran.getTransferData(DataFlavor.stringFlavor);
	             textArea.replaceRange(sel,textArea.getSelectionStart(),textArea.getSelectionEnd());
	         }
	         catch(Exception exc)
	             {
	             System.out.println("not string flavour");
	         }
			
		}
	}
	
	
	public static void main(String args[]) {
		Notepad n = new Notepad();
	}
}
