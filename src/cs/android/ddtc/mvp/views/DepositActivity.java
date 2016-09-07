package cs.android.ddtc.mvp.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.IDepositView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.presenters.DepositPresenter;

public class DepositActivity extends Activity implements IDepositView {

	private DepositPresenter myPresenter;
	String uID;
	String acct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		uID = extras.getString("uid");
		acct = extras.getString("acctname");
		myPresenter = new DepositPresenter(this, new DbOpenHelper(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deposit, menu);
		return true;
	}

	public void OnClick(View v) {
		if ((getDateField().getText().toString().equals(""))
				|| (getAmountField().getText().toString().equals(""))) {

			Toast.makeText((Context) this, "Please enter date and amount",
					Toast.LENGTH_SHORT).show();

		}

		else {
			Double amount = Double.parseDouble(getAmountField().getText()
					.toString());
			String editDate = getDateField().getText().toString();
			switch (v.getId()) {
			case R.id.savings:
				myPresenter.OnActionClick(uID, acct, editDate, "Savings",
						amount);
				break;

			case R.id.salary:
				myPresenter
						.OnActionClick(uID, acct, editDate, "Salary", amount);
				break;

			case R.id.misc:
				myPresenter.OnActionClick(uID, acct, editDate, "Misc", amount);
				break;

			case R.id.food:
				myPresenter.OnActionClick(uID, acct, editDate, "Food", -amount);
				break;

			case R.id.shopping:
				myPresenter.OnActionClick(uID, acct, editDate, "Shopping",
						-amount);
				break;

			case R.id.gas:
				myPresenter.OnActionClick(uID, acct, editDate, "Gas", -amount);
				break;

			case R.id.grocery:
				myPresenter.OnActionClick(uID, acct, editDate, "Grocery",
						-amount);
				break;

			case R.id.utility:
				myPresenter.OnActionClick(uID, acct, editDate, "Utility",
						-amount);
				break;

			default:
				break;
			}
		}

	}

	public void OnXClick(View v) {
		myPresenter.OnXClick();
	}

	private EditText getAmountField() {
		return (EditText) findViewById(R.id.depositamount);
	}

	private EditText getDateField() {
		return (EditText) findViewById(R.id.depositDate);
	}

	@Override
	public void advance() {
		Intent intent = new Intent(this, HistoryActivity.class);
		Bundle extras = new Bundle();
		extras.putString("uid", uID);
		extras.putString("acctname", acct);
		intent.putExtras(extras);
		startActivity(intent);

	}

}
