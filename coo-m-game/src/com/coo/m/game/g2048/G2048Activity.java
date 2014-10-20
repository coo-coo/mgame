package com.coo.m.game.g2048;

import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;

/**
 * 2048
 * 
 */
public class G2048Activity extends GplusActivity {

	private TextView scoreTextView;
	private TextView bestScoreTextView;
	private Button buttoNewGame;
	private G2048View gameView = null;

	// 获得的分数
	public Score score;

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_G2048;
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.g2048_activity;
	}

	@Override
	public void loadContent() {
		buttoNewGame = (Button) findViewById(R.id.btn_2048_newgame);
		scoreTextView = (TextView) findViewById(R.id.tv_2048_score);
		bestScoreTextView = (TextView) findViewById(R.id.tv_2048_bestScore);

		gameView = (G2048View) findViewById(R.id.gv_2048_gameView);

		score = new Score();
		gameView.setScore(score);

		buttoNewGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startNewGame();
			}
		});
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.g2048_activity);
	//
	//
	// }

	private void startNewGame() {
		score.clearScore();
		showScore();
		showBestScore();
		gameView.startGame();
	}

	private void showBestScore() {
		bestScoreTextView.setText(String.valueOf(score.getBestScore()));
	}

	private void showScore() {
		scoreTextView.setText(String.valueOf(score.getScore()));
	}

	public class Score {
		private int score = 0;
		private static final String SP_KEY_BEST_SCORE = "bestScore";

		public void clearScore() {
			score = 0;
		}

		public int getScore() {
			return score;
		}

		public void addScore(int s) {
			score += s;
			showScore();

			saveBestScore(Math.max(score, getBestScore()));
			showBestScore();
		}

		public void saveBestScore(int s) {
			Editor e = getPreferences(MODE_PRIVATE).edit();
			e.putInt(SP_KEY_BEST_SCORE, s);
			e.commit();
		}

		public int getBestScore() {
			return getPreferences(MODE_PRIVATE).getInt(
					SP_KEY_BEST_SCORE, 0);
		}

	}

}
