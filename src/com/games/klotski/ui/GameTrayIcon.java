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
 * @author shijun ϵͳ����
 */
public class GameTrayIcon {
	public void showTrayIcon() {
		final TrayIcon trayIcon;
		if (SystemTray.isSupported() == false) {
			System.out.println("ϵͳ���̲�֧��!");
			return;
		}
		SystemTray tray = SystemTray.getSystemTray();
		Image image = ImageUtils.getImage("trayImage");
		PopupMenu popupMenu = new PopupMenu();
		MenuItem setItem = new MenuItem("����");
		MenuItem helpItem = new MenuItem("����");
		MenuItem aboutItem = new MenuItem("����");
		MenuItem exitItem = new MenuItem("�˳�");

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"�汾1.0 klotski\n��С�˿���  ���˱�������Ȩ��\n��ӭ��ҶԱ���Ϸ�������", "������Ϸ",
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
