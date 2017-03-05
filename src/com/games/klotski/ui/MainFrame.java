package com.games.klotski.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputAdapter;

import com.games.klotski.commons.GameConfiguration;
import com.games.klotski.dao.DataProcessUtils;
import com.games.klotski.listener.BeginListener;
import com.games.klotski.service.GameModel;
import com.games.klotski.service.MusicService;
import com.games.klotski.service.impl.GameServiceImpl;
import com.games.klotski.util.ImageUtils;
import com.games.klotski.util.PersonUtils;

/**
 * ������
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public final int DEFAULTWIDTH = 700;
	public final int DEFAULTHEIGHT = 600;
	private JPanel backgroundPanel = null;
	private OptionLabel start;
	// private OptionLabel musicOff;
	// private OptionLabel musicOn;
	private OptionLabel music;
	private OptionLabel autoFindPath;
	private OptionLabel exit;
	private JPanel copyOfGamePanel;
	TetrisTask tetrisTask;
	// ����
	final GameConfiguration config = new GameConfiguration();
	final GameServiceImpl gameService = new GameServiceImpl(config);
	BeginListener beginListener;
	final GamePanel gamePanel = new GamePanel(gameService);
	int[][] presentPersonsPosition;
	int[][] path;
	int count; // һ�����ٲ�

	public MainFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "����",
					JOptionPane.WARNING_MESSAGE);
		}
		start = new OptionLabel("startIn", "startOut");
		music = new OptionLabel("musicOffIn", "musicOffOut");
		// musicOn = new OptionLabel("musicOnIn", "musicOnOut");
		exit = new OptionLabel("exitIn", "exitOut");
		autoFindPath = new OptionLabel("autoFindOnIn", "autoFindOnOut");
		addListener();
		initialize();
		setVisible(true);
		this.tetrisTask = new TetrisTask(this);
		Timer timer = new Timer();
		timer.schedule(this.tetrisTask, 0, 100);
		new GameTrayIcon().showTrayIcon();//��ʾϵͳ����
	}

	private void addListener() {
		music.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (GameConfiguration.isPlayMusic() == true) {// ���ڷ���Ч,����ð�ť���ǹر���Ч
					GameConfiguration.setPlayMusic(false);
					music.changeImage("musicOnIn", "musicOnOut");
					// validate();
				} else {// �����Ϊ�˿�����Ч
					GameConfiguration.setPlayMusic(true);
					music.changeImage("musicOffIn", "musicOffOut");
					// validate();
				}

			}
		});// end of addMouseListener

		autoFindPath.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (GameConfiguration.isAuto()) {
					autoFindPath.changeImage("autoFindOnIn", "autoFindOnOut");
					GameConfiguration.setAuto(false);
					presentPersonsPosition = null;
					path = null;
				} else {
					// ���ȵõ���ǰ���������
					presentPersonsPosition = PersonUtils.to2DArray(
							GameModel.getPersons(),
							config.getBaseImageLength(), config.getRowsNum(),
							config.getColumnsNum());
					// �õ������߳���һ��ͨ·
					path = DataProcessUtils
							.autoFindPath(presentPersonsPosition);
					count = path.length;// һ�����ٲ�
					autoFindPath.changeImage("autoFindOffIn", "autoFindOffOut");
					GameConfiguration.setAuto(true);
				}

			}
		});// end of autoFindPath
	}

	public void printComponents(Graphics g) {
		g.drawImage(ImageUtils.getImage("bg_image"), 0, 0, 500, 500, null);
	}

	@SuppressWarnings("serial")
	public JPanel createPanel(final ImageIcon imageIcon) {
		return new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(imageIcon.getImage(), 0, 0, getWidth(),
						getHeight(), null);
				// ����Ե��
				g.drawImage(ImageUtils.getImage("rightBorder"), 480, 0, 20,
						getHeight(), null);
				g.drawImage(ImageUtils.getImage("downBorder"), 0, 600, 120, 40,
						null);
				g.drawImage(ImageUtils.getImage("downBorder"), 360, 600, 120,
						40, null);

			}

		};
	}

	private void initialize() {
		setTitle("klotski");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// �Ӷ�ʮΪ�˻�������ı�Ե��
		setSize(DEFAULTWIDTH + 20, DEFAULTHEIGHT + 45);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setLayout(new BorderLayout(5, 0));
		backgroundPanel = createPanel(new ImageIcon(
				ImageUtils.getImage("bg_image")));
		add(backgroundPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(127, 174, 252));
		controlPanel.setBorder(new EtchedBorder());

		BoxLayout controlLayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
		controlPanel.setLayout(controlLayout);
		add(controlPanel, BorderLayout.WEST);

		JPanel logoPanel = new JPanel();
		logoPanel.setBorder(new EtchedBorder());
		logoPanel.setBackground(new Color(127, 174, 252));
		Image logoImage = null;
		logoImage = ImageUtils.getImage("logo");
		JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
		logoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						"�汾1.0 klotski\n��С�˿���  ���˱�������Ȩ��\n��ӭ��ҶԱ���Ϸ�������", "������Ϸ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		logoPanel.add(logoLabel);
		controlPanel.add(logoPanel);
		controlPanel.add(createBlankPanel());

		JPanel alogoPanel = new JPanel();
		alogoPanel.setBorder(new EtchedBorder());
		OptionLabel alogoLable = new OptionLabel("gameNameIn", "gameNameOut");
		alogoPanel.setBackground(new Color(127, 174, 252));
		alogoPanel.add(alogoLable);
		controlPanel.add(alogoPanel);
		controlPanel.add(createBlankPanel());

		JPanel pointTextPanel = new JPanel();
		pointTextPanel.setBackground(new Color(169, 210, 254));
		pointTextPanel.setBorder(new EtchedBorder());
		pointTextPanel.add(music);
		controlPanel.add(pointTextPanel);

		JPanel pointPanel = new JPanel();
		pointPanel.setBorder(new EtchedBorder());
		pointPanel.setBackground(new Color(208, 223, 255));
		pointPanel.add(autoFindPath);// �Զ�Ѱ·
		autoFindPath.setVisible(false);// ��ʼ֮ǰ����Ϊ���ɼ� ���ܲ���
		autoFindPath.setEnabled(false);
		controlPanel.add(pointPanel);
		controlPanel.add(createBlankPanel());

		JPanel timeTextPanel = new JPanel();
		timeTextPanel.setBackground(new Color(169, 210, 254));
		timeTextPanel.setBorder(new EtchedBorder());
		OptionLabel timeTextLabel = new OptionLabel("timerIn", "timerOut");
		timeTextPanel.add(timeTextLabel);
		controlPanel.add(timeTextPanel);

		// ʱ�����
		JPanel timePanel = new JPanel();
		timePanel.setBorder(new EtchedBorder());
		timePanel.setBackground(new Color(208, 223, 255));
		JLabel timeLabel = new JLabel();// ʱ�������
		timeLabel.setText("0   ��");
		timePanel.add(timeLabel);
		controlPanel.add(timePanel);
		controlPanel.add(createBlankPanel());

		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.X_AXIS));
		labelsPanel.setBackground(new Color(127, 174, 252));
		labelsPanel.add(start);
		JLabel blankLabel = new JLabel();
		blankLabel.setText("     ");
		labelsPanel.add(blankLabel);

		copyOfGamePanel = gamePanel;
		beginListener = new BeginListener(this, gamePanel, gameService,
				timeLabel, autoFindPath, config);
		start.addMouseListener(beginListener);

		exit.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(gamePanel, "ȷ���˳�?", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
					MusicService.play("exit");
					try {
						Thread.sleep(1000 * 2);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
						throw new RuntimeException(e1);
					}
					System.exit(0);
				}
			}
		});
		labelsPanel.add(exit);
		controlPanel.add(labelsPanel);
		controlPanel.add(createBlankPanel());
	}

	public void romoveBackGroundPanel() {
		this.remove(backgroundPanel);
	}

	private JPanel createBlankPanel() {
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(new Color(127, 174, 252));
		JLabel blankLabel = new JLabel();
		blankLabel.setText("      ");
		blankPanel.add(blankLabel);
		return blankPanel;
	}

	public JPanel getGamePanel() {
		return copyOfGamePanel;
	}

	public void nextStep() {
		// ��Ϸû��ʤ�����������Զ�Ѱ·ģʽ��
		if (!this.gameService.win() && GameConfiguration.isAuto()) {
			if (--count >= 0) {
				Map<String, PersonLabel> personLabels = gamePanel
						.getPersonLabels();

				// 0���ߵ�· 1�ܲ� 2���� 3�ŷ� 4���� 5���� 6�� 7�� 8�� 9�� 10��
				switch (path[count][0]) {
				case 1:
					move(personLabels.get("caocao"), path[count][1]);
					break;
				case 2:
					move(personLabels.get("guanyu"), path[count][1]);
					break;
				case 3:
					move(personLabels.get("zhangfei"), path[count][1]);

					break;
				case 4:
					move(personLabels.get("zhaozilong"), path[count][1]);

					break;
				case 5:
					move(personLabels.get("huangzhong"), path[count][1]);

					break;
				case 6:
					move(personLabels.get("machao"), path[count][1]);
					break;
				case 7:
					move(personLabels.get("samurai0"), path[count][1]);
					break;
				case 8:
					move(personLabels.get("samurai1"), path[count][1]);

					break;
				case 9:
					move(personLabels.get("samurai2"), path[count][1]);
					break;
				case 10:
					move(personLabels.get("samurai3"), path[count][1]);
					break;
				default:
					break;
				}// switch
				MusicService.play("hit");// ��������
				PersonUtils.to2DArray(GameModel.getPersons(), gameService
						.getGameModel().getGameConfig().getBaseImageLength(),
						5, 4);
			}// if
			else if (count <= 0) {
				autoFindPath.setEnabled(false);
			}
		}// if
		else {
			if (gameService.win()) {
				if (GameConfiguration.isAuto()) {
					// ��Ϸʤ�������������Ȼ�����ʤ����ͼƬ��
					// gamePanel.removeAll();
					GameConfiguration.setAuto(false);
					MusicService.play("win");
					autoFindPath.setEnabled(false);
					autoFindPath.setVisible(false);
					if (gamePanel.getPersonLabel("caocao") != null)
						gamePanel.getPersonLabel("caocao").getValue()
								.setVisible(false);
					JOptionPane.showMessageDialog(gamePanel, "�ܲٳɹ�������!", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);

					for (Map.Entry<String, PersonLabel> personLabel : gamePanel
							.getPersonLabels().entrySet()) {
						personLabel.getValue().setEnabled(false);
					}
					gameService.setWin(true);// ����״̬ΪӮ��
					beginListener.getTimer().cancel();
				}//isAuto
			}
		}
	}

	private void move(PersonLabel personLabel, int dir) {
		// ��ö��Ӧ�ø���
		// 1��,2��,3��,4��
		int xIndex = personLabel.getXIndex();
		int yIndex = personLabel.getYIndex();
		int beginX = personLabel.getCell().getBeginX();
		int beginY = personLabel.getCell().getBeginY();
		int baseLength = config.getBaseImageLength();
		int rows = config.getRowsNum();
		int columns = config.getColumnsNum();
		switch (dir) {
		case 1:
			if (yIndex - 1 >= 0) {
				personLabel.setYIndex(yIndex - 1);
				personLabel.setLocation(beginX, beginY - baseLength);
			}
			break;
		case 2:
			if (yIndex + 1 < rows) {
				personLabel.setYIndex(yIndex + 1);
				personLabel.setLocation(beginX, beginY + baseLength);
				break;
			}
		case 3:
			if (xIndex - 1 >= 0) {
				personLabel.setXIndex(xIndex - 1);
				personLabel.setLocation(beginX - baseLength, beginY);
			}
			break;
		case 4:
			if (xIndex + 1 < columns) {
				personLabel.setXIndex(xIndex + 1);
				personLabel.setLocation(beginX + baseLength, beginY);
			}
			break;
		default:
			break;
		}// switch
	}// function
}

class TetrisTask extends TimerTask {
	// ���������
	private MainFrame mainFrame;

	public TetrisTask(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void run() {
		mainFrame.nextStep();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		this.mainFrame.getGamePanel().repaint();
	}
}
