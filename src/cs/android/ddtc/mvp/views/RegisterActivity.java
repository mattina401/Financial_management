package cs.android.ddtc.mvp.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.IRegisterView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.presenters.RegPresenter;

public class RegisterActivity extends Activity implements IRegisterView {
	
	/** The presenter for this activity */
	private RegPresenter myPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		myPresenter = new RegPresenter(this, new DbOpenHelper(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	/**
	 * Button handler for the login button
	 * 
	 * @param v not used
	 */
	public void onSignupClick(View v) {
		String uid = getUidField().getText().toString();
		String pass = getPassField().getText().toString();
		String confirm = getConfirmField().getText().toString();
		String name = getNameField().getText().toString();
//		String email = getEmailField().getText().toString();
		myPresenter.onSignupClick(uid, pass, confirm, name);
	}
	
	private EditText getUidField()     {
        return (EditText) findViewById(R.id.registerID);
	}
	
	private EditText getPassField()     {
        return (EditText) findViewById(R.id.registerPW);
	}
	
	private EditText getConfirmField()     {
        return (EditText) findViewById(R.id.confirmPW);
	}
	
	private EditText getNameField()     {
        return (EditText) findViewById(R.id.registerName);
	}
	
//	private EditText getEmailField()     {
//        return (EditText) findViewById(R.id.registerEmail);
//	}
	
	/**
	 * Button handler for the register button
	 * 
	 * @param v not used
	 */
	
	public void onCancelClick(View v){
		myPresenter.onCancelClick();
	}

	@Override
	public void advance() {
		//create an intent
		finish();
	}

}
