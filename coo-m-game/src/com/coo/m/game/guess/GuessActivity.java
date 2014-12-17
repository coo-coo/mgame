package com.coo.m.game.guess;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.IGamePolicy;
import com.coo.m.game.R;
import com.coo.m.game.SimpleGamePolicy;
import com.kingstar.ngbf.ms.util.Reference;
import com.kingstar.ngbf.ms.util.android.CommonBizActivity;

public class GuessActivity extends GplusActivity {

	private Button btnGuess, btnresult;
	private RelativeLayout container;
	private GuessShow guessshow;
	private EditText guessanswer;
	private GuessBean gb;

	private void showgb() {
		container.removeAllViews();
		GuessManager gm = new GuessManager();
		gb = gm.mockGuessModel();

		guessshow = new GuessShow(this, gb);
		container.addView(guessshow, new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	private void judge() {
		boolean b = false;
		String answer = guessanswer.getText().toString();
		for (String an : gb.getAnswers()) {
			if (an.equals(answer)) {
				Toast.makeText(this, "你答对了", Toast.LENGTH_SHORT)
						.show();
				b = true;
				break;
			}
		}
		if (b == false) {
			Toast.makeText(this, "你答错了", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_guess_start:
			showgb();
			break;
		case R.id.btn_guess_result:
			judge();
			break;
		}
	}

	@Override
	@Reference(override = GplusActivity.class)
	public GameProperty getGameProperty() {
		return GplusManager.G_GUESS;
	}

	@Override
	@Reference(override = GplusActivity.class)
	public IGamePolicy getGamePolicy() {
		return new SimpleGamePolicy();
	}

	@Override
	@Reference(override = CommonBizActivity.class)
	public int getResViewLayoutId() {
		return R.layout.g_guess_activity;
	}

	@Override
	@Reference(override = CommonBizActivity.class)
	public void loadContent() {
		btnGuess = (Button) findViewById(R.id.btn_guess_start);
		btnresult = (Button) findViewById(R.id.btn_guess_result);
		container = (RelativeLayout) findViewById(R.id.rl_guess_container);
		guessanswer = (EditText) findViewById(R.id.et_guess_answer);

		btnGuess.setOnClickListener(this);
		btnresult.setOnClickListener(this);
	}

}
