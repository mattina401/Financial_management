package cs.android.ddtc.mvp.presenters;

import android.content.Context;
import android.widget.Toast;
import cs.android.ddtc.mvp.Iviews.IRegisterView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.model.User;

public class RegPresenter {
	/** the view to manipulate */
	private final IRegisterView myView;
	private final DbOpenHelper myModel;

	/**
	 * Make the presenter.
	 * 
	 * @param view
	 *            the view to use
	 */
	public RegPresenter(IRegisterView view, DbOpenHelper model) {
		myView = view;
		myModel = model;
		myModel.open();
	}

	/**
	 * Handle a Sign-up button click in the ui. Just advance to the next screen,
	 * but don't do anything to the model.
	 */
	public void onSignupClick(String uID, String pass, String confirm,
			String name) {
		if (pass.equals(confirm)) {
			myModel.insertColumn(new User(uID, pass, name));
			success();
			myView.advance();
		} else {
			fail();

		}
	}

	/**
	 * Handle a Cancel button click in the ui. Just advance to the next screen,
	 * but don't do anything to the model.
	 */
	public void onCancelClick() {
		myView.advance();
	}

	protected void success() {
		Toast.makeText((Context) myView, "Your account has been created!",
				Toast.LENGTH_LONG).show();
	}

	protected void fail() {
		Toast.makeText((Context) myView, "Password must match",
				Toast.LENGTH_SHORT).show();
	}

}
