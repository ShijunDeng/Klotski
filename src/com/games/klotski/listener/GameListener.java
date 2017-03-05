package com.games.klotski.listener;

import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

import com.games.klotski.object.Cell;
import com.games.klotski.service.MusicService;
import com.games.klotski.service.impl.GameServiceImpl;
import com.games.klotski.ui.GamePanel;
import com.games.klotski.ui.PersonLabel;
import com.games.klotski.util.ImageUtils;

/**
 * GameListener
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-23 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class GameListener extends MouseInputAdapter {
	// ������Ϸ���߼��ӿ�
	private GameServiceImpl gameService;
	private GamePanel gamePanel;
	Cell exit;// �õ�����
	private BeginListener beginListener;
	private static JLabel fly = new JLabel(ImageUtils.getImageIcon("fly",
			".gif"));

	public Cell getExit() {
		return exit;
	}

	public void setExit(Cell exit) {
		this.exit = exit;
	}

	public GameListener(GameServiceImpl gameService, GamePanel gamePanel,
			BeginListener beginListener) {
		this.gameService = gameService;
		this.gamePanel = gamePanel;
		this.gamePanel.add(fly);
		fly.setVisible(false);
		fly.setBounds(0, 0, 96, 80);
		this.beginListener = beginListener;
	}

	public void mouseReleased(MouseEvent event) {
		if (gamePanel.getOverImage() != null) {
			return;
		}
		if (gameService.win()) {
			// ��Ϸʤ�������������Ȼ�����ʤ����ͼƬ��
			// gamePanel.removeAll();
			MusicService.play("win");
			if (gamePanel.getPersonLabel("caocao") != null)
				gamePanel.getPersonLabel("caocao").getValue().setVisible(false);
			JOptionPane.showMessageDialog(gamePanel,
					"���� " + beginListener.getGameTimer().getTime()
							+ " s��ʱ���òܲٳɹ�������!", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			// gamePanel.setOverImage(ImageUtils.getImage("win"));
			for (Map.Entry<String, PersonLabel> personLabel : gamePanel
					.getPersonLabels().entrySet()) {
				personLabel.getValue().setEnabled(false);
			}
			// gameService.setWin(true);// ����״̬ΪӮ��
			cancelTimer();
		}
		gamePanel.repaint();
	}

	public void cancelTimer() {
		this.beginListener.getTimer().cancel();
	}

	public void mouseEntered(MouseEvent e) {
		gamePanel.setCursor(ImageUtils.createCursor("cursor01", ".png"));
		fly.setVisible(true);
		gamePanel.repaint();
		// gamePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public static JLabel getFly() {
		return fly;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// fly.setVisible(false);
		gamePanel.repaint();
	}

}
