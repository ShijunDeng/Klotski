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
	// 处理游戏的逻辑接口
	private GameServiceImpl gameService;
	private GamePanel gamePanel;
	Cell exit;// 得到出口
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
			// 游戏胜利清除面板的内容然后加上胜利的图片；
			// gamePanel.removeAll();
			MusicService.play("win");
			if (gamePanel.getPersonLabel("caocao") != null)
				gamePanel.getPersonLabel("caocao").getValue().setVisible(false);
			JOptionPane.showMessageDialog(gamePanel,
					"你用 " + beginListener.getGameTimer().getTime()
							+ " s的时间让曹操成功逃跑了!", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			// gamePanel.setOverImage(ImageUtils.getImage("win"));
			for (Map.Entry<String, PersonLabel> personLabel : gamePanel
					.getPersonLabels().entrySet()) {
				personLabel.getValue().setEnabled(false);
			}
			// gameService.setWin(true);// 设置状态为赢了
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
