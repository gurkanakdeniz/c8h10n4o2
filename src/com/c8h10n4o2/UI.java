package com.c8h10n4o2;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {

	private static final long serialVersionUID = 6076738476146961224L;

	public UI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 150, 150);
		JPanel contentPane = new JPanel();
		setTitle(":)");
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToggleButton toggle = new JToggleButton(TaskStatus.WAITING.getCode());
		toggle.addActionListener(event -> {
			if (TaskStatus.WAITING.getCode().equals(toggle.getText())
					|| TaskStatus.PROBLEM.getCode().equals(toggle.getText())) {
				try {
					if (Task.get().alive()) {
						Task.get().stop();
						toggle.setText(TaskStatus.STOPPED.getCode());
						toggle.setForeground(Color.RED);
					} else {
						Task.get().start();
						toggle.setText(TaskStatus.RUNNING.getCode());
						toggle.setForeground(Color.GREEN);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					toggle.setText(TaskStatus.PROBLEM.getCode());
					toggle.setForeground(Color.RED);
				}

			} else if (TaskStatus.STOPPED.getCode().equals(toggle.getText())) {
				try {
					Task.get().start();
					toggle.setText(TaskStatus.RUNNING.getCode());
					toggle.setForeground(Color.GREEN);

				} catch (Exception ex) {
					ex.printStackTrace();
					toggle.setText(TaskStatus.PROBLEM.getCode());
					toggle.setForeground(Color.RED);
				}
			} else if (TaskStatus.RUNNING.getCode().equals(toggle.getText())) {
				try {
					Task.get().stop();
					toggle.setText(TaskStatus.STOPPED.getCode());
					toggle.setForeground(Color.RED);
				} catch (Exception ex) {
					ex.printStackTrace();
					toggle.setText(TaskStatus.PROBLEM.getCode());
					toggle.setForeground(Color.RED);
				}

			}
		});

		toggle.setForeground(Color.BLUE);
		toggle.setBackground(Color.LIGHT_GRAY);
		toggle.setBounds(20, 40, 117, 25);
		contentPane.add(toggle);
	}

}
