package cs.android.ddtc.mvp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.ILoginView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.presenters.LoginPresenter;

public class LoginActivity extends Activity implements ILoginView{
	
	private LoginPresenter myPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		myPresenter = new LoginPresenter(this, new DbOpenHelper(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void onLoginClick(View v) {
		String uid = getUidField().getText().toString();
		String pass = getPassField().getText().toString();
		myPresenter.onLoginClick(uid, pass);
	}
	
	private EditText getUidField()     {
        return (EditText) findViewById(R.id.loginID);
	}
	
	private EditText getPassField()     {
        return (EditText) findViewById(R.id.loginPW);
	}

	@Override
	public void advanceToaccount(String uid) {
		Intent intent = new Intent(this, AccountActivity.class);
		Bundle extras = new Bundle();
		extras.putString("uid", uid);
		intent.putExtras(extras);
		startActivity(intent);
		
	}

	@Override
	public void advanceTomain() {
		finish();
		
	}

}
