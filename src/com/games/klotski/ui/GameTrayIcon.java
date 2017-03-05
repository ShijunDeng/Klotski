package com.games.klotski.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.games.klotski.util.ImageUtils;

/**
 * 
 * @author shijun 系统托盘
 */
public class GameTrayIcon {
	public void showTrayIcon() {
		final TrayIcon trayIcon;
		if (SystemTray.isSupported() == false) {
			System.out.println("系统托盘不支持!");
			return;
		}
		SystemTray tray = SystemTray.getSystemTray();
		Image image = ImageUtils.getImage("trayImage");
		PopupMenu popupMenu = new PopupMenu();
		MenuItem setItem = new MenuItem("设置");
		MenuItem helpItem = new MenuItem("帮助");
		MenuItem aboutItem = new MenuItem("关于");
		MenuItem exitItem = new MenuItem("退出");

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"版本1.0 klotski\n由小邓开发  本人保留所有权利\n欢迎大家对本游戏提出建议", "关于游戏",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		popupMenu.add(setItem);
		popupMenu.add(helpItem);
		popupMenu.add(aboutItem);
		popupMenu.addSeparator();
		popupMenu.add(exitItem);
		trayIcon = new TrayIcon(image, "klotski", popupMenu);
		try {
			tray.add(trayIcon);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

	}
}
