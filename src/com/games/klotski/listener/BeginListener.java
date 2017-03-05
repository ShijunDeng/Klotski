package com.games.klotski.listener;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

import com.games.klotski.commons.GameConfiguration;
import com.games.klotski.person.Person;
import com.games.klotski.service.GameModel;
import com.games.klotski.service.impl.GameServiceImpl;
import com.games.klotski.timer.GameTimer;
import com.games.klotski.ui.GamePanel;
import com.games.klotski.ui.MainFrame;
import com.games.klotski.ui.OptionLabel;
import com.games.klotski.ui.PersonLabel;

/**
 * BeginListener
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class BeginListener extends MouseInputAdapter {
	private GamePanel gamePanel;//��Ϸ���
	private GameServiceImpl gameService;//��Ϸ�߼�����
	private JLabel timeLabel;//��ʱ����ǩ
	private GameConfiguration config;//��Ϸ����
	private Timer timer;//��ʱ
	private GameTimer task;
	private MainFrame mainFrame;
	private OptionLabel autoFindPath;
	Map<String,PersonLabel> personLabels;
	GameListener gameListener;
	public BeginListener(MainFrame mainFrame, GamePanel gamePanel,
			GameServiceImpl gameService, JLabel timeLabel,OptionLabel autoFindPath,
			GameConfiguration config) {
		this.gamePanel = gamePanel;
		this.gameService = gameService;
		this.timeLabel = timeLabel;
		this.autoFindPath=autoFindPath;
		this.config = config;
		this.mainFrame = mainFrame;
	}

	public Timer getTimer() {
		return this.timer;
	}

	public GameTimer getGameTimer() {
		return this.task;
	}

	public void mousePressed(MouseEvent e) {
		mainFrame.romoveBackGroundPanel();
		// �ظ��İ���ʼ��ť�����ȱ��������������
		gamePanel.removeAll();
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		if (this.timer != null) {
			this.timer.cancel();
		}
		
		this.timer = new Timer();
		gamePanel.setOverImage(null);
		timeLabel.setText(String.valueOf(config.getLimitedTime()));
		gameService.setWin(false);// ����״̬ΪδӮ
		gameService.start();
		
		autoFindPath.setVisible(true);//��Ϸ��ʼ���Զ�Ѱ· ����Ϊ�ɼ�  �ɲ���
		autoFindPath.setEnabled(true);
		autoFindPath.changeImage("autoFindOnIn", "autoFindOnOut");//���¿�ʼ
		personLabels = new HashMap<String,PersonLabel>();
		for (Entry<String, Person> person : GameModel.getPersons().entrySet()) {
			// ��"caocao"�Ӽ������ж���Ϸ�Ƿ����
			if (person.getValue().getName().equals("caocao")) {
				gameListener = new GameListener(gameService,
						gamePanel, this);
				gameListener.setExit(gameService.getGameModel().getExit());
				PersonLabel personLabel = new PersonLabel(person.getValue());
				personLabel.addMouseListener(gameListener);
				personLabels.put(person.getValue().getName(),personLabel);
			} else {
				personLabels.put(person.getValue().getName(),new PersonLabel(person.getValue()));
			}
		}
		gamePanel.setPersonLabels(personLabels);
		// ��ʼ��
		gamePanel.initPersonLabel();
		//��ʱ��
		task = new GameTimer( 0,this.timeLabel);
		timer.schedule(task, 0, 1000);
		gamePanel.repaint();
	}

	public GameListener getGameListener() {
		return gameListener;
	}
	
}
