package cs.android.ddtc.mvp.presenters;

import android.content.Context;
import android.widget.Toast;
import cs.android.ddtc.mvp.Iviews.ICreateView;
import cs.android.ddtc.mvp.model.Account;
import cs.android.ddtc.mvp.model.DbOpenHelper;

public class CreatePresenter {
	
	private final ICreateView myView;
	private final DbOpenHelper myModel;
	
	/**
	 * Make the presenter.
	 * @param view the view to use
	 */
	public CreatePresenter(ICreateView view, DbOpenHelper model){
		myView = view;
		myModel = model;
		myModel.open();
	}
	
	public void onCreateClick(String uID, String acct, boolean checked){
		if(checked == true){
			myModel.insertDataToAcctList(new Account(uID, acct, 0));
			success();
			myView.advance(uID);
		}
		else{
			fail();
		}
	}
	
	protected void success() {
		Toast.makeText((Context) myView, "Your account has been created!",
				Toast.LENGTH_LONG).show();
	}

	protected void fail() {
		Toast.makeText((Context) myView, "Please check the agreement first",
				Toast.LENGTH_SHORT).show();
	}

	
	/**
	 * Handle a Cancel button click in the ui.
	 * Just advance to the next screen, but don't
	 * do anything to the model.
	 */
	public void onCancelClick(String uid){
		myView.advance(uid);
	}
	
	

}
