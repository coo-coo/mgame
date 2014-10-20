package com.coo.m.game.color;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.coo.m.game.GameProperty;
import com.coo.m.game.GplusActivity;
import com.coo.m.game.GplusManager;
import com.coo.m.game.R;

public class ColorActivity extends GplusActivity {

	private Button btnColor;
	private ColorViewGroup cg;
	private RelativeLayout container;
	private int k = 0;

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.color_activity);
	//
	// btnColor = (Button) findViewById(R.id.btn_color_start);
	//
	// container = (RelativeLayout) findViewById(R.id.rl_color_container);
	// btnColor.setOnClickListener(this);
	// }

	@Override
	public GameProperty getGameProperty() {
		return GplusManager.G_COLOR;
	}

	@Override
	public int getResViewLayoutId() {
		return R.layout.color_activity;
	}

	@Override
	public void loadContent() {
		btnColor = (Button) findViewById(R.id.btn_color_start);

		container = (RelativeLayout) findViewById(R.id.rl_color_container);
		btnColor.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_color_start:
			showcg();
			k++;
			break;
		}
	}

	private void showcg() {
		if (k >= 19) {
			k = 0;
		}
		container.removeAllViews();
		cg = new ColorViewGroup(this);
		cg.setmCount(8);
		cg.setmColor(COLORS[k]);
		container.addView(cg, new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	public static final String[] COLORS = { "#FFFFB3", "#94FF85",
			"#75FFC2", "#8FDEFF", "#B3B3FF", "#042D42", "#0B84C2",
			"#075075", "#57BEF2", "#326E8C", "#CFF8F6", "#94D4D4",
			"#88B4BB", "#76AEAF", "#2A6D82", "#C12552", "#FF6600",
			"#F5C700", "#6A961F", "#008885" };

}
