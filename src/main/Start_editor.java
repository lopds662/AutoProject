package main;

import java.awt.EventQueue;

import view.Main_frame;

public class Start_editor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_frame frame = new Main_frame();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
