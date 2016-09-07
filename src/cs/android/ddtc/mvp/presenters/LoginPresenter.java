package cs.android.ddtc.mvp.presenters;

import android.content.Context;
import android.widget.Toast;
import cs.android.ddtc.mvp.Iviews.ILoginView;
import cs.android.ddtc.mvp.model.DbOpenHelper;

public class LoginPresenter {
	/** the view to manipulate */
	private final ILoginView myView;
	private final DbOpenHelper myModel;
	
	/**
	 * Make the presenter.
	 * 
	 * @param view the view to use
	 * 
	 */
	public LoginPresenter(ILoginView view, DbOpenHelper model) {
		myView = view;
		myModel = model;
		myModel.open();
	}
	
	/**
	 * Handle a login button click in the ui. Just advance to the next screen,
	 * but don't do anything to the model.
	 */
	public void onLoginClick(String uid, String pass) {
		if(myModel.getMatchName(uid, pass)){
			success();
			myView.advanceToaccount(uid);
		}
		else{
			fail();
		}
	}
	
	protected void success() {
		Toast.makeText((Context) myView, "Login Success!",
				Toast.LENGTH_LONG).show();
	}
	
	protected void fail() {
		Toast.makeText((Context) myView, "Sorry, unrecognized username or password.",
				Toast.LENGTH_LONG).show();
	}

}
