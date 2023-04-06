package com.c8h10n4o2;

import java.awt.EventQueue;

public class Main {

	static UI frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				frame = new UI();
				frame.setResizable(false);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
