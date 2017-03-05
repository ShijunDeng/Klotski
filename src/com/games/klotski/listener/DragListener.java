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
 * ������ק
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-23 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class DragListener extends MouseInputAdapter {
	/** ����� */
	private Point point = new Point(0, 0);
	private PersonLabel dragLabel;// �������Ķ���
	private GameServiceImpl gameService;

	public DragListener(PersonLabel dragLabel, GameServiceImpl gameService) {
		this.dragLabel = dragLabel;
		this.gameService = gameService;
	}

	/**
	 * ������϶�ʱ�������¼��� ��¼����갴��(��ʼ�϶�)��λ�á�
	 */
	public void mouseDragged(MouseEvent e) {
		if (!gameService.win()) {// ��û��Ӯ
			// ת������ϵͳ
			Point newPoint = SwingUtilities.convertPoint(dragLabel,
					e.getPoint(), dragLabel.getParent());

			int dx = newPoint.x - point.x;
			int dy = newPoint.y - point.y;

			// ����ƶ��ĵط��з���,���ƶ�,��Ϊx��y������������
			dragLabel.setLocation(dragLabel.getX() + dx, dragLabel.getY());
			if (intersects(dragLabel.getPerson())) {
				dragLabel.setLocation(dragLabel.getX() - dx, dragLabel.getY());
			}

			dragLabel.setLocation(dragLabel.getX(), dragLabel.getY() + dy);
			if (intersects(dragLabel.getPerson())) {
				dragLabel.setLocation(dragLabel.getX(), dragLabel.getY() - dy);
			}
			// ���ñ�ǩ����λ��
			// dragLabel.setLocation(dragLabel.getX() + dx, dragLabel.getY() +
			// dy);
			// ���������
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
	 * ����갴��ʱ�������¼��� ��¼����갴��(��ʼ�϶�)��λ�á�
	 */
	public void mousePressed(MouseEvent e) {
		if (!gameService.win()) {
			// �õ���ǰ�����
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
	 * @return:����Ƿ������Χ�������ͻ�Լ�Խ��
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
