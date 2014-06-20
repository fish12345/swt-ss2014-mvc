/*
 * --------------------------------------------------------------------------
 * 
 * Copyright (c) 2012 by Projektgruppe SWT, Technische Hochschule Mittelhessen.
 * 
 * All rights reserved.
 * 
 * --------------------------------------------------------------------------
 * 
 * 
 * --------------------------------------------------------------------------
 */

package swt.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import swt.BeatPlayer;
import swt.interfaces.BPMObserver;

/**
 * The DJView is the visual part of the DJ Application. It is used to provide
 * the user a gui for adjusting playing options.<
 * 
 */
public class DJView implements ActionListener, BPMObserver {

	private BeatPlayer player = new BeatPlayer();
	private int bpm = 60;
	private JFrame frame;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem startMenuItem;
	private JMenuItem stopMenuItem;

	private JLabel bpmLabel;
	private JFormattedTextField bpmTextField;
	private JButton setBPMButton;
	private JButton increaseBPMButton;
	private JButton decreaseBPMButton;

	public DJView() {
		
		initViews();
		update();
	}

	@Override
	public void update() {
		bpmTextField.setText("" + bpm);
	}

	/**
	 * Makes the StopMenuItem accessible for the user
	 */
	public void enableStopMenuItem() {
		stopMenuItem.setEnabled(true);
	}

	/**
	 * Makes the StopMenuItem inaccessible for the user
	 */
	public void disableStopMenuItem() {
		stopMenuItem.setEnabled(false);
	}

	/**
	 * Makes the StartMenuItem accessible for the user
	 */
	public void enableStartMenuItem() {
		startMenuItem.setEnabled(true);
	}

	/**
	 * Makes the StartMenuItem accessible for the user
	 */
	public void disableStartMenuItem() {
		startMenuItem.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == setBPMButton) {
			this.bpm = Integer.parseInt(bpmTextField.getText());
			player.setBPM(bpm);
			update();
		} else if (source == increaseBPMButton) {
			this.bpm = Integer.parseInt(bpmTextField.getText());
			player.setBPM(++bpm);
			update();
		} else if (source == decreaseBPMButton) {
			this.bpm = Integer.parseInt(bpmTextField.getText());
			player.setBPM(--bpm);
			update();
		} else if (source == startMenuItem) {
			player.start();
			disableStartMenuItem();
			enableStopMenuItem();
			update();
		} else if (source == stopMenuItem) {
			player.stop();
			enableStartMenuItem();
			disableStopMenuItem();
			update();
		}

	}
	
	private void initViews() {
		frame = new JFrame();
		menuBar = new JMenuBar();
		menu = new JMenu("Steuerung");
		menuBar.add(menu);
		startMenuItem = new JMenuItem("Start");
		stopMenuItem = new JMenuItem("Stop");
		menu.add(startMenuItem);
		menu.add(stopMenuItem);

		bpmLabel = new JLabel("BPM: ");
		bpmTextField = new JFormattedTextField(NumberFormat.getInstance());
		bpmTextField.setColumns(8);
		setBPMButton = new JButton("Setzen");

		increaseBPMButton = new JButton("Schneller");
		decreaseBPMButton = new JButton("Langsamer");

		setBPMButton.addActionListener(this);
		increaseBPMButton.addActionListener(this);
		decreaseBPMButton.addActionListener(this);
		startMenuItem.addActionListener(this);
		stopMenuItem.addActionListener(this);

		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(menuBar);
		frame.add(bpmLabel);
		frame.add(bpmTextField);
		frame.add(setBPMButton);
		frame.add(increaseBPMButton);
		frame.add(decreaseBPMButton);

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new DJView();
	}
	
}
