package com.coo.m.game.g2048;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.coo.m.game.IGame;
import com.coo.m.game.DeviceUtil;

public class G2048View extends LinearLayout {

	private final int LINES = 4;
	private Card2048[][] cardsMap = new Card2048[LINES][LINES];
	private List<Point> emptyPoints = new ArrayList<Point>();

	private int gridWidth;

	/**
	 * 添加分数,通知GameState进行记录
	 * 
	 * @param score
	 */
	private void addScore(int score) {
		int currentScore = g2048.getState().getScore() + score;

		Message cs = new Message();
		cs.what = 1;
		cs.obj = currentScore;
		g2048.handler.sendMessage(cs);

		Message msg = new Message();
		msg.what = IGame.MISSION_SCORE_ADD;
		msg.obj = score;
		g2048.notify(msg);
	}

	private G2048Activity g2048;

	public G2048View(G2048Activity g2048) {
		super(g2048);
		this.g2048 = g2048;
		int[] resolution = DeviceUtil.getResolution(g2048);
		gridWidth = (resolution[0]) / 4 - 5;
		initGameView();
		initCards();
	}

	public G2048View(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void initGameView() {
		setOrientation(LinearLayout.VERTICAL);
		// setBackgroundColor(Color.GRAY);

		setOnTouchListener(new View.OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;

					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX < -5) {
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						if (offsetY < -5) {
							swipeUp();
						} else if (offsetY > 5) {
							swipeDown();
						}
					}

					break;
				}
				return true;
			}
		});

	}

	// @Override
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	//
	// int size = MeasureSpec.getSize(widthMeasureSpec);
	// gridWidth = (size - 10) / LINES;
	// g2048.toast("size=" + size + "-" + gridWidth);
	//
	// initCards();
	// //
	// // // 初始化所有的的Card
	// // initCards();
	// // setMeasuredDimension(size, size);
	// }

	// @Override
	// protected void onSizeChanged(int width, int height, int oldWidth,
	// int oldHeight) {
	// super.onSizeChanged(width, height, oldWidth, oldHeight);
	//
	// Card2048.width = (Math.min(width, height) - 10) / LINES;
	// g2048.toast("onSizeChanged...");
	// // 初始化所有的的Card
	// initCards();
	// }

	public void initCards() {
		Card2048 c;
		LinearLayout line;
		LinearLayout.LayoutParams lineLp;

		for (int y = 0; y < LINES; y++) {
			line = new LinearLayout(getContext());
			lineLp = new LinearLayout.LayoutParams(-1, gridWidth);
			addView(line, lineLp);

			for (int x = 0; x < LINES; x++) {
				c = new Card2048(getContext());
				line.addView(c, gridWidth, gridWidth);
				cardsMap[x][y] = c;
			}
		}
	}

	/**
	 * 重新开始一个游戏....
	 */
	public void startGame() {
		for (int y = 0; y < LINES; y++) {
			for (int x = 0; x < LINES; x++) {
				cardsMap[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	private void addRandomNum() {

		emptyPoints.clear();

		// calculate how many empty points
		for (int y = 0; y < LINES; y++) {
			for (int x = 0; x < LINES; x++) {
				if (cardsMap[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}

		if (emptyPoints.size() > 0) {
			Point p = emptyPoints.remove((int) (Math.random() * emptyPoints
					.size()));
			cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
			cardsMap[p.x][p.y].addScaleAnimation();
		}
	}

	private void swipeLeft() {

		boolean merge = false;

		for (int y = 0; y < LINES; y++) {
			for (int x = 0; x < LINES; x++) {

				for (int x1 = x + 1; x1 < LINES; x1++) {
					if (cardsMap[x1][y].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							x--;
							merge = true;

						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {

							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);

							addScore(cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeRight() {

		boolean merge = false;

		for (int y = 0; y < LINES; y++) {
			for (int x = LINES - 1; x >= 0; x--) {

				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardsMap[x1][y].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {

							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							x++;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {

							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							addScore(cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeUp() {

		boolean merge = false;

		for (int x = 0; x < LINES; x++) {
			for (int y = 0; y < LINES; y++) {

				for (int y1 = y + 1; y1 < LINES; y1++) {
					if (cardsMap[x][y1].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {

							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y--;

							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {

							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							addScore(cardsMap[x][y].getNum());
							merge = true;
						}

						break;

					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeDown() {

		boolean merge = false;

		for (int x = 0; x < LINES; x++) {
			for (int y = LINES - 1; y >= 0; y--) {

				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardsMap[x][y1].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {

							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y++;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {

							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							addScore(cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void checkComplete() {

		boolean complete = true;

		ALL: for (int y = 0; y < LINES; y++) {
			for (int x = 0; x < LINES; x++) {
				if (cardsMap[x][y].getNum() == 0
						|| (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y]))
						|| (x < LINES - 1 && cardsMap[x][y]
								.equals(cardsMap[x + 1][y]))
						|| (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1]))
						|| (y < LINES - 1 && cardsMap[x][y]
								.equals(cardsMap[x][y + 1]))) {

					complete = false;
					break ALL;
				}
			}
		}

		if (complete) {
			g2048.notify(IGame.MISSION_FAIL);

			// new AlertDialog.Builder(getContext())
			// .setTitle("Finished")
			// .setMessage("Game Over")
			// .setPositiveButton(
			// "start again?",
			// new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(
			// DialogInterface dialog,
			// int which) {
			// startGame();
			// }
			// }).show();
		}

	}

	// public void setScore(G2048Activity.Score score) {
	// this.score = score;
	// }
}
