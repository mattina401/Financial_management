package cs.android.ddtc.mvp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.IMainView;
import cs.android.ddtc.mvp.presenters.MainPresenter;

/**
 * This is a MainActivity which displays a welcome screen.
 * 
 * @author Team DDTC
 *
 */
public class MainActivity extends Activity implements IMainView{
	
	/** The presenter for this activity */
    private MainPresenter myPresenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	myPresenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
	/**
	 * Button handler for the login button
	 * 
	 * @param v not used
	 */
	public void onLoginClick(View v) {
		myPresenter.onLoginClick();
	}
	
	/**
	 * Button handler for the register button
	 * 
	 * @param v not used
	 */
	
	public void onRegClick(View v){
		myPresenter.onRegisterClick();
	}


	@Override
	public void advanceToLogin() {
		//create an intent (a request for phone OS to do something)
		Intent intent = new Intent(this, LoginActivity.class);
		//now pass that to the phone OS and ask to start it
		startActivity(intent);
		
	}

	@Override
	public void advanceToReg() {
		//create an intent
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
		
	}
	
	

}
