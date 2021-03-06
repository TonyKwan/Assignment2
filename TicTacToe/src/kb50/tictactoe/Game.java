package kb50.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.util.Random;

public class Game extends Activity {
	private boolean turn;
	private boolean difEasy;
	private boolean gamedone;
	private ImageButton btn1;
	private ImageButton btn2;
	private ImageButton btn3;
	private ImageButton btn4;
	private ImageButton btn5;
	private ImageButton btn6;
	private ImageButton btn7;
	private ImageButton btn8;
	private ImageButton btn9;
	private Resources res;
	private Drawable cross;
	private Drawable circle;
	private String player1;
	private String player2;
	private boolean multiplayer;

	ImageButton[] btns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		btn1 = (ImageButton) findViewById(R.id.ib_1);
		btn2 = (ImageButton) findViewById(R.id.ib_2);
		btn3 = (ImageButton) findViewById(R.id.ib_3);
		btn4 = (ImageButton) findViewById(R.id.ib_4);
		btn5 = (ImageButton) findViewById(R.id.ib_5);
		btn6 = (ImageButton) findViewById(R.id.ib_6);
		btn7 = (ImageButton) findViewById(R.id.ib_7);
		btn8 = (ImageButton) findViewById(R.id.ib_8);
		btn9 = (ImageButton) findViewById(R.id.ib_9);
		btns = new ImageButton[] { btn1, btn2, btn3, btn4, btn5, btn6, btn7,
				btn8, btn9 };
		res = getResources();
		cross = res.getDrawable(R.drawable.candycane);
		circle = res.getDrawable(R.drawable.cookie);
		turn = true;
		setBaseVar();
		gamedone = false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// example = save value
		int sbValue = 1;

		outState.putInt("testKey", sbValue);
		outState.putBoolean("turn", turn);

		outState.putBoolean("dif", difEasy);
		outState.putBoolean("mul", multiplayer);
		outState.putString("p1", player1);
		outState.putString("p2", player2);

		Object d1 = btn1.getTag();
		Object d2 = btn2.getTag();
		Object d3 = btn3.getTag();
		Object d4 = btn4.getTag();
		Object d5 = btn5.getTag();
		Object d6 = btn6.getTag();
		Object d7 = btn7.getTag();
		Object d8 = btn8.getTag();
		Object d9 = btn9.getTag();
		String[] a = new String[9];

		if (d1 != null) {
			a[0] = d1.toString();
		}
		if (d2 != null) {
			a[1] = d2.toString();
		}
		if (d3 != null) {
			a[2] = d3.toString();
		}
		if (d4 != null) {
			a[3] = d4.toString();
		}
		if (d5 != null) {
			a[4] = d5.toString();
		}
		if (d6 != null) {
			a[5] = d6.toString();
		}
		if (d7 != null) {
			a[6] = d7.toString();
		}
		if (d8 != null) {
			a[7] = d8.toString();
		}
		if (d9 != null) {
			a[8] = d9.toString();
		}

		outState.putStringArray("a", a);

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {

		// example
		super.onRestoreInstanceState(savedInstanceState);
		// restore the values in demo
		int i = (int) savedInstanceState.getInt("testKey");

		turn = savedInstanceState.getBoolean("turn"); // example key
		multiplayer = savedInstanceState.getBoolean("mul");

		difEasy = savedInstanceState.getBoolean("dif");

		player1 = savedInstanceState.getString("p1");
		player2 = savedInstanceState.getString("p2");

		String[] a = new String[9];
		a = savedInstanceState.getStringArray("a");
		if (a[0] != null) {
			returnButtons(a[0], btn1);
		}
		if (a[1] != null) {
			returnButtons(a[1], btn2);
		}
		if (a[2] != null) {
			returnButtons(a[2], btn3);
		}
		if (a[3] != null) {
			returnButtons(a[3], btn4);
		}
		if (a[4] != null) {
			returnButtons(a[4], btn5);
		}
		if (a[5] != null) {
			returnButtons(a[5], btn6);
		}
		if (a[6] != null) {
			returnButtons(a[6], btn7);
		}
		if (a[7] != null) {
			returnButtons(a[7], btn8);
		}
		if (a[8] != null) {
			returnButtons(a[8], btn9);
		}

	}

	private void returnButtons(String a, ImageButton btn) {
		if (a.equals("cross")) {
			btn.setImageDrawable(cross);
			btn.setTag("cross");
			btn.setClickable(false);
		} else if (a.equals("circle")) {
			btn.setImageDrawable(circle);
			btn.setTag("circle");
			btn.setClickable(false);
		} else {
		}

	}

	private void setBaseVar() {
		try {
			String a = getIntent().getStringExtra("Player");
			if (a.equals("two")) {

				player1 = getIntent().getStringExtra("p1_name");
				player2 = getIntent().getStringExtra("p2_name");
				if (player1.equals("") || player2.equals("")) {
					// something went wrong
				} else {
					multiplayer = true;
					int ran = new Random().nextInt(2);

					if (ran == 1) {
						turn = true;

						TextView t1 = (TextView) findViewById(R.id.turn);
						t1.setText("It is " + player1 + " 's turn");

					} else {
						turn = false;

						TextView t1 = (TextView) findViewById(R.id.turn);

						t1.setText("It is " + player2 + " 's turn");

					}

				}

			} else {
				String dif = getIntent().getStringExtra("dif");
				if (dif.equals("easy")) {
					difEasy = true;
				} else {
					difEasy = false;
				}
				turn = true;
				multiplayer = false;
				TextView t1 = (TextView) findViewById(R.id.turn);
				t1.setText("It is your turn");

			}

		} catch (Exception e) {

		}

	}

	public void onClickBtn(View v) {
		Intent k = new Intent(this, MainActivity.class);
		startActivity(k);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			Intent k = new Intent(this, MainActivity.class);
			startActivity(k);
			break;
		case R.id.ib_1:
			if (turn == true) {
				btn1.setImageDrawable(cross);

				btn1.setTag("cross");

				btn1.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn1.setImageDrawable(circle);

				btn1.setTag("circle");

				btn1.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_2:
			if (turn == true) {
				btn2.setImageDrawable(cross);

				btn2.setTag("cross");

				btn2.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn2.setImageDrawable(circle);

				btn2.setTag("circle");

				btn2.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_3:
			if (turn == true) {
				btn3.setImageDrawable(cross);

				btn3.setTag("cross");

				btn3.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn3.setImageDrawable(circle);

				btn3.setTag("circle");

				btn3.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_4:
			if (turn == true) {
				btn4.setImageDrawable(cross);

				btn4.setTag("cross");

				btn4.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn4.setImageDrawable(circle);

				btn4.setTag("circle");

				btn4.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_5:
			if (turn == true) {
				btn5.setImageDrawable(cross);

				btn5.setTag("cross");

				btn5.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn5.setImageDrawable(circle);

				btn5.setTag("circle");

				btn5.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_6:
			if (turn == true) {
				btn6.setImageDrawable(cross);

				btn6.setTag("cross");

				btn6.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn6.setImageDrawable(circle);

				btn6.setTag("circle");

				btn6.setClickable(false);
				check();
				multiplayerCheck();

			}
			break;
		case R.id.ib_7:
			if (turn == true) {
				btn7.setImageDrawable(cross);

				btn7.setTag("cross");

				btn7.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn7.setImageDrawable(circle);

				btn7.setTag("circle");

				btn7.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_8:
			if (turn == true) {
				btn8.setImageDrawable(cross);

				btn8.setTag("cross");

				btn8.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn8.setImageDrawable(circle);

				btn8.setTag("circle");

				btn8.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		case R.id.ib_9:
			if (turn == true) {
				btn9.setImageDrawable(cross);

				btn9.setTag("cross");

				btn9.setClickable(false);
				check();
				multiplayerCheck();
			} else {
				btn9.setImageDrawable(circle);
				btn9.setClickable(false);
				check();
				multiplayerCheck();
			}
			break;
		}

	}

	private void multiplayerCheck() {
		if (multiplayer == false) {
			if (!gamedone) {

				turn = false;
				onePlayer();
				check();
				turn = true;
			}
		} else {
			twoPlayers();
			if (turn == true) {
				turn = false;
			} else {
				turn = true;
			}
		}

	}

	private void clearScreen() {
		btn1.setImageDrawable(null);
		btn1.setTag(null);
		btn1.setClickable(true);
		btn2.setImageDrawable(null);
		btn2.setTag(null);
		btn2.setClickable(true);
		btn3.setImageDrawable(null);
		btn3.setTag(null);
		btn3.setClickable(true);
		btn4.setImageDrawable(null);
		btn4.setTag(null);
		btn4.setClickable(true);
		btn5.setImageDrawable(null);
		btn5.setTag(null);
		btn5.setClickable(true);
		btn6.setImageDrawable(null);
		btn6.setTag(null);
		btn6.setClickable(true);
		btn7.setImageDrawable(null);
		btn7.setTag(null);
		btn7.setClickable(true);
		btn8.setImageDrawable(null);
		btn8.setTag(null);
		btn8.setClickable(true);
		btn9.setImageDrawable(null);
		btn9.setTag(null);
		btn9.setClickable(true);
		gamedone = false;
		if (multiplayer == false) {
			turn = true;
		}

	}

	private void onePlayer() {
		if (difEasy == true) {
			aiEasy(btns);
		} else {
			aiMedium();
		}
	}

	private void twoPlayers() {
		if (turn == true) {
			TextView t1 = (TextView) findViewById(R.id.turn);
			t1.setText("It is " + player2 + " 's turn");
		} else {
			TextView t1 = (TextView) findViewById(R.id.turn);
			t1.setText("It is " + player1 + " 's turn");
		}
	}

	private void aiMedium() {

		Object d1 = btn1.getTag();
		Object d2 = btn2.getTag();
		Object d3 = btn3.getTag();
		Object d4 = btn4.getTag();
		Object d5 = btn5.getTag();
		Object d6 = btn6.getTag();
		Object d7 = btn7.getTag();
		Object d8 = btn8.getTag();
		Object d9 = btn9.getTag();
		String[] a = new String[9];

		if (d1 != null) {
			a[0] = d1.toString();
		}
		if (d2 != null) {
			a[1] = d2.toString();
		}
		if (d3 != null) {
			a[2] = d3.toString();
		}
		if (d4 != null) {
			a[3] = d4.toString();
		}
		if (d5 != null) {
			a[4] = d5.toString();
		}
		if (d6 != null) {
			a[5] = d6.toString();
		}
		if (d7 != null) {
			a[6] = d7.toString();
		}
		if (d8 != null) {
			a[7] = d8.toString();
		}
		if (d9 != null) {
			a[8] = d9.toString();
		}

		Ai ai = new Ai(a, true);
		int moveAi = ai.move();
		setAiButton(moveAi);

	}

	private void setAiButton(int move) {
		switch (move) {
		case 0:
			btn1.setImageDrawable(circle);
			btn1.setTag("circle");
			btn1.setClickable(false);
			break;
		case 1:
			btn2.setImageDrawable(circle);
			btn2.setTag("circle");
			btn2.setClickable(false);

			break;
		case 2:
			btn3.setImageDrawable(circle);
			btn3.setTag("circle");
			btn3.setClickable(false);

			break;
		case 3:
			btn4.setImageDrawable(circle);
			btn4.setTag("circle");
			btn4.setClickable(false);

			break;
		case 4:
			btn5.setImageDrawable(circle);
			btn5.setTag("circle");
			btn5.setClickable(false);

			break;
		case 5:
			btn6.setImageDrawable(circle);
			btn6.setTag("circle");
			btn6.setClickable(false);

			break;
		case 6:
			btn7.setImageDrawable(circle);
			btn7.setTag("circle");
			btn7.setClickable(false);

			break;
		case 7:
			btn8.setImageDrawable(circle);
			btn8.setTag("circle");
			btn8.setClickable(false);

			break;
		case 8:
			btn9.setImageDrawable(circle);
			btn9.setTag("circle");
			btn9.setClickable(false);

			break;

		default:
			break;
		}
	}

	private void aiHard() {

		Object d1 = btn1.getTag();
		Object d2 = btn2.getTag();
		Object d3 = btn3.getTag();
		Object d4 = btn4.getTag();
		Object d5 = btn5.getTag();
		Object d6 = btn6.getTag();
		Object d7 = btn7.getTag();
		Object d8 = btn8.getTag();
		Object d9 = btn9.getTag();
		String[] a = new String[9];

		if (d1 != null) {
			a[0] = d1.toString();
		}
		if (d2 != null) {
			a[1] = d2.toString();
		}
		if (d3 != null) {
			a[2] = d3.toString();
		}
		if (d4 != null) {
			a[3] = d4.toString();
		}
		if (d5 != null) {
			a[4] = d5.toString();
		}
		if (d6 != null) {
			a[5] = d6.toString();
		}
		if (d7 != null) {
			a[6] = d7.toString();
		}
		if (d8 != null) {
			a[7] = d8.toString();
		}
		if (d9 != null) {
			a[8] = d9.toString();
		}

		Ai ai = new Ai(a, false);
		int moveAi = ai.move();
		setAiButton(moveAi);
		check();
	}

	private void dia() {
		if (turn == true) {
			winDialog();
		} else {
			loseDialog();
		}
		gamedone = true;
	}

	private void check() {
		Object d1 = btn1.getTag();
		Object d2 = btn2.getTag();
		Object d3 = btn3.getTag();
		Object d4 = btn4.getTag();
		Object d5 = btn5.getTag();
		Object d6 = btn6.getTag();
		Object d7 = btn7.getTag();
		Object d8 = btn8.getTag();
		Object d9 = btn9.getTag();

		String[] a = new String[9];

		if (d1 != null) {
			a[0] = d1.toString();
		} else {
			a[0] = "";
		}
		if (d2 != null) {
			a[1] = d2.toString();
		} else {
			a[1] = "";
		}
		if (d3 != null) {
			a[2] = d3.toString();
		} else {
			a[2] = "";
		}
		if (d4 != null) {
			a[3] = d4.toString();
		} else {
			a[3] = "";
		}
		if (d5 != null) {
			a[4] = d5.toString();
		} else {
			a[4] = "";
		}
		if (d6 != null) {
			a[5] = d6.toString();
		} else {
			a[5] = "";
		}
		if (d7 != null) {
			a[6] = d7.toString();
		} else {
			a[6] = "";
		}
		if (d8 != null) {
			a[7] = d8.toString();
		} else {
			a[7] = "";
		}
		if (d9 != null) {
			a[8] = d9.toString();
		} else {
			a[8] = "";
		}

		String cr = "cross";
		String ci = "circle";
		// hor
		if (a[0].equals(cr) && a[1].equals(cr) && a[2].equals(cr)) {

			dia();
		} else if (a[3].equals(cr) && a[4].equals(cr) && a[5].equals(cr)) {
			dia();
		} else if (a[6].equals(cr) && a[7].equals(cr) && a[8].equals(cr)) {
			dia();
		} else if (a[0].equals(ci) && a[1].equals(ci) && a[2].equals(ci)) {
			dia();
		} else if (a[3].equals(ci) && a[4].equals(ci) && a[5].equals(ci)) {
			dia();
		} else if (a[6].equals(ci) && a[7].equals(ci) && a[8].equals(ci)) {
			dia();
		}

		// ver
		else if (a[0].equals(cr) && a[3].equals(cr) && a[6].equals(cr)) {
			dia();
		} else if (a[1].equals(cr) && a[4].equals(cr) && a[7].equals(cr)) {
			dia();
		} else if (a[2].equals(cr) && a[5].equals(cr) && a[8].equals(cr)) {
			dia();
		} else if (a[0].equals(ci) && a[3].equals(ci) && a[6].equals(ci)) {
			dia();
		} else if (a[1].equals(ci) && a[4].equals(ci) && a[7].equals(ci)) {
			dia();
		} else if (a[2].equals(ci) && a[5].equals(ci) && a[8].equals(ci)) {
			dia();
		}

		// dia
		else if (a[0].equals(cr) && a[4].equals(cr) && a[8].equals(cr)) {
			dia();
		} else if (a[2].equals(cr) && a[4].equals(cr) && a[6].equals(cr)) {
			dia();
		} else if (a[0].equals(ci) && a[4].equals(ci) && a[8].equals(ci)) {
			dia();
		} else if (a[2].equals(ci) && a[4].equals(ci) && a[6].equals(ci)) {
			dia();
		}

		else if (!a[0].equals("") && !a[1].equals("") && !a[2].equals("")
				&& !a[3].equals("") && !a[4].equals("") && !a[5].equals("")
				&& !a[6].equals("") && !a[7].equals("") && !a[8].equals("")) {
			drawDialog();
		}
	}

	private void drawDialog() {
		Fragment dialogFragment = Fragment.newInstance(res
				.getString(R.string.drawDialog));
		dialogFragment.show(getFragmentManager(), "dialog");
		gamedone = true;
	}

	private void loseDialog() {
		if (multiplayer == false) {
			Fragment dialogFragment = Fragment.newInstance(res
					.getString(R.string.loseDialog));
			dialogFragment.show(getFragmentManager(), "dialog");
		} else {
			Fragment dialogFragment = Fragment.newInstance(player2 + " "
					+ res.getString(R.string.multplayerWin));
			dialogFragment.show(getFragmentManager(), "dialog");
		}
	}

	private void winDialog() {
		if (multiplayer == false) {
			Fragment dialogFragment = Fragment.newInstance(res
					.getString(R.string.winDialog));
			dialogFragment.show(getFragmentManager(), "dialog");
		} else {
			Fragment dialogFragment = Fragment.newInstance(player1 + " "
					+ res.getString(R.string.multplayerWin));
			dialogFragment.show(getFragmentManager(), "dialog");
		}
	}

	public void doPositiveClick() {
		clearScreen();
	}

	public void doNegativeClick() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	private Drawable getImage2(ImageButton a) {

		return a.getDrawable();
	}

	public void aiEasy(ImageButton[] o) {

		Random nm = new Random();
		List<ImageButton> btns = new ArrayList<ImageButton>();
		for (int i = 0; i < o.length; i++) {

			if (o[i].getTag() == null) {
				btns.add(o[i]);

			}

		}
		if (!btns.isEmpty()) {
			int max = btns.size();
			int placeRandom = nm.nextInt(max);
			btns.get(placeRandom).setImageDrawable(circle);
			btns.get(placeRandom).setTag("circle");
			btns.get(placeRandom).setClickable(false);
		}
	}

}
