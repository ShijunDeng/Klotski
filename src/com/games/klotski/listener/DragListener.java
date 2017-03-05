package com.games.klotski.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import com.games.klotski.person.Person;
import com.games.klotski.service.GameModel;
import com.games.klotski.service.MusicService;
import com.games.klotski.service.impl.GameServiceImpl;
import com.games.klotski.ui.PersonLabel;
import com.games.klotski.util.PersonUtils;

/**
 * 处理拖拽
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-23 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class DragListener extends MouseInputAdapter {
	/** 坐标点 */
	private Point point = new Point(0, 0);
	private PersonLabel dragLabel;// 被监听的对象
	private GameServiceImpl gameService;

	public DragListener(PersonLabel dragLabel, GameServiceImpl gameService) {
		this.dragLabel = dragLabel;
		this.gameService = gameService;
	}

	/**
	 * 当鼠标拖动时触发该事件。 记录下鼠标按下(开始拖动)的位置。
	 */
	public void mouseDragged(MouseEvent e) {
		if (!gameService.win()) {// 还没有赢
			// 转换坐标系统
			Point newPoint = SwingUtilities.convertPoint(dragLabel,
					e.getPoint(), dragLabel.getParent());

			int dx = newPoint.x - point.x;
			int dy = newPoint.y - point.y;

			// 如果移动的地方有方块,则不移动,分为x和y两个方向讨论
			dragLabel.setLocation(dragLabel.getX() + dx, dragLabel.getY());
			if (intersects(dragLabel.getPerson())) {
				dragLabel.setLocation(dragLabel.getX() - dx, dragLabel.getY());
			}

			dragLabel.setLocation(dragLabel.getX(), dragLabel.getY() + dy);
			if (intersects(dragLabel.getPerson())) {
				dragLabel.setLocation(dragLabel.getX(), dragLabel.getY() - dy);
			}
			// 设置标签的新位置
			// dragLabel.setLocation(dragLabel.getX() + dx, dragLabel.getY() +
			// dy);
			// 更改坐标点
			PersonUtils.countIndex(dragLabel.getPerson(), gameService
					.getGameModel().getGameConfig().getBaseImageLength());
			point = newPoint;
			PersonUtils.to2DArray(GameModel.getPersons(),
					gameService.getGameModel().getGameConfig()
							.getBaseImageLength(), 5, 4);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GameListener.getFly().setLocation(e.getX() + 30, e.getY() + 30);
	}

	/**
	 * 当鼠标按下时触发该事件。 记录下鼠标按下(开始拖动)的位置。
	 */
	public void mousePressed(MouseEvent e) {
		if (!gameService.win()) {
			// 得到当前坐标点
			point = SwingUtilities.convertPoint(dragLabel, e.getPoint(),
					dragLabel.getParent());
			MusicService.play(dragLabel.getPerson().getName());
		}

	}

	public void mouseReleased(MouseEvent e) {
		if (!gameService.win()) {
			int baseLength = this.gameService.getGameModel().getGameConfig()
					.getBaseImageLength();
			dragLabel.setLocation(
					((int) Math.round(dragLabel.getPerson().getCell()
							.getBeginX()
							/ baseLength))
							* baseLength,
					((int) Math.round(dragLabel.getPerson().getCell()
							.getBeginY()
							/ baseLength))
							* baseLength);
			PersonUtils.countIndex(dragLabel.getPerson(), gameService
					.getGameModel().getGameConfig().getBaseImageLength());
			MusicService.play("hit");
		}

	}

	public JLabel getDragLabel() {
		return dragLabel;
	}

	public void setDragLabel(PersonLabel dragLabel) {
		this.dragLabel = dragLabel;
	}

	/**
	 * 
	 * @param cell
	 * @return:检测是否会与周围的人物冲突以及越界
	 */
	public boolean intersects(Person person) {
		for (Entry<String, Person> per : GameModel.getPersons().entrySet()) {
			if (per.getValue().getName().equals(person.getName()) == false
					&& per.getValue().intersects(person)
					|| gameService.outOfBound(person)) {
				return true;
			}
		}
		return false;
	}

}
