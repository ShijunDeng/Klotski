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
 * 主窗口
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
	// 配置
	final GameConfiguration config = new GameConfiguration();
	final GameServiceImpl gameService = new GameServiceImpl(config);
	BeginListener beginListener;
	final GamePanel gamePanel = new GamePanel(gameService);
	int[][] presentPersonsPosition;
	int[][] path;
	int count; // 一共多少步

	public MainFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "警告",
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
		new GameTrayIcon().showTrayIcon();//显示系统托盘
	}

	private void addListener() {
		music.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (GameConfiguration.isPlayMusic() == true) {// 正在放音效,点击该按钮就是关闭音效
					GameConfiguration.setPlayMusic(false);
					music.changeImage("musicOnIn", "musicOnOut");
					// validate();
				} else {// 点击是为了开启音效
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
					// 首先得到当前人物的坐标
					presentPersonsPosition = PersonUtils.to2DArray(
							GameModel.getPersons(),
							config.getBaseImageLength(), config.getRowsNum(),
							config.getColumnsNum());
					// 得到可以走出的一条通路
					path = DataProcessUtils
							.autoFindPath(presentPersonsPosition);
					count = path.length;// 一共多少步
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
				// 画边缘线
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
		// 加二十为了画出下面的边缘框
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
						"版本1.0 klotski\n由小邓开发  本人保留所有权利\n欢迎大家对本游戏提出建议", "关于游戏",
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
		pointPanel.add(autoFindPath);// 自动寻路
		autoFindPath.setVisible(false);// 开始之前设置为不可见 不能操作
		autoFindPath.setEnabled(false);
		controlPanel.add(pointPanel);
		controlPanel.add(createBlankPanel());

		JPanel timeTextPanel = new JPanel();
		timeTextPanel.setBackground(new Color(169, 210, 254));
		timeTextPanel.setBorder(new EtchedBorder());
		OptionLabel timeTextLabel = new OptionLabel("timerIn", "timerOut");
		timeTextPanel.add(timeTextLabel);
		controlPanel.add(timeTextPanel);

		// 时间计算
		JPanel timePanel = new JPanel();
		timePanel.setBorder(new EtchedBorder());
		timePanel.setBackground(new Color(208, 223, 255));
		JLabel timeLabel = new JLabel();// 时间结果存放
		timeLabel.setText("0   秒");
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
				if (JOptionPane.showConfirmDialog(gamePanel, "确定退出?", "提示",
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
		// 游戏没有胜利而且是在自动寻路模式下
		if (!this.gameService.win() && GameConfiguration.isAuto()) {
			if (--count >= 0) {
				Map<String, PersonLabel> personLabels = gamePanel
						.getPersonLabels();

				// 0可走的路 1曹操 2关羽 3张飞 4赵云 5黄忠 6马超 7兵 8卒 9勇 10丁
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
				MusicService.play("hit");// 播放音乐
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
					// 游戏胜利清除面板的内容然后加上胜利的图片；
					// gamePanel.removeAll();
					GameConfiguration.setAuto(false);
					MusicService.play("win");
					autoFindPath.setEnabled(false);
					autoFindPath.setVisible(false);
					if (gamePanel.getPersonLabel("caocao") != null)
						gamePanel.getPersonLabel("caocao").getValue()
								.setVisible(false);
					JOptionPane.showMessageDialog(gamePanel, "曹操成功逃跑了!", "提示",
							JOptionPane.INFORMATION_MESSAGE);

					for (Map.Entry<String, PersonLabel> personLabel : gamePanel
							.getPersonLabels().entrySet()) {
						personLabel.getValue().setEnabled(false);
					}
					gameService.setWin(true);// 设置状态为赢了
					beginListener.getTimer().cancel();
				}//isAuto
			}
		}
	}

	private void move(PersonLabel personLabel, int dir) {
		// 用枚举应该更好
		// 1上,2下,3左,4右
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
	// 主界面对象
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
