package cs.android.ddtc.mvp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.ICreateView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.presenters.CreatePresenter;

public class CreateActivity extends Activity implements ICreateView{
	
	private CreatePresenter myPresenter;
	private String uID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		myPresenter = new CreatePresenter(this, new DbOpenHelper(this));
    	Intent intent = getIntent();
    	uID = intent.getStringExtra("uID");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}
	
	public void OnCreateClick(View v){
		String acct = getAcctField().getText().toString();
		myPresenter.onCreateClick(uID, acct, getAgreeField());
	}
	
	private EditText getAcctField()     {
        return (EditText) findViewById(R.id.acctEdit);
	}
	/** check box should be handled in Presenter**/
	private boolean getAgreeField(){
		CheckBox check = (CheckBox)findViewById(R.id.agreeCheckbox);
		if(check.isChecked())
			return true;
		else
			return false;
	}
	
	public void OnCancelClick(View v){
		myPresenter.onCancelClick(uID);
	}

	@Override
	public void advance(String uid) {
		Intent intent = new Intent(this, AccountActivity.class);
		Bundle extras = new Bundle();
		extras.putString("uid", uid);
		intent.putExtras(extras);
		startActivity(intent);
		
	}

}
