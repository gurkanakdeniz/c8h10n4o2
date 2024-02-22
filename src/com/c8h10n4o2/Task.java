package com.c8h10n4o2;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task {

	private static Task instance = null;
	private ScheduledExecutorService executor = null;
	private Point location = null;
	private Random random = new Random();

	public static Task get() throws Exception {
		if (instance == null) {
			return build();
		}

		return instance;
	}

	private static synchronized Task build() throws Exception {
		if (instance == null) {
			instance = new Task();

		}

		return instance;
	}

	public boolean alive() {
		return executor != null;
	}

	public boolean start() throws Exception {
		if (executor != null) {
			return true;
		}

		Robot robot = new Robot();

		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(() -> {
			try {
				Point currentLocation = MouseInfo.getPointerInfo().getLocation();

				if (location == null
						|| (location.getX() == currentLocation.getX() && location.getY() == currentLocation.getY())) {
					robot.setAutoDelay(40);
					
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					
					robot.mouseMove(random.nextInt(400), random.nextInt(400));
				}

				location = MouseInfo.getPointerInfo().getLocation();
			} catch(Throwable ex) {
				ex.printStackTrace();
			}
		}, 30, 30, TimeUnit.SECONDS);

		return true;
	}

	public void stop() throws Exception {
		if (executor != null) {
			executor.shutdownNow();
			executor = null;
		}
	}

}
