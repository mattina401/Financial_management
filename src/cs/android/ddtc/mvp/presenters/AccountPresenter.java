package cs.android.ddtc.mvp.presenters;

import cs.android.ddtc.mvp.Iviews.IAccountView;
import cs.android.ddtc.mvp.model.DbOpenHelper;

public class AccountPresenter {
	
	private final IAccountView myView;
	private final DbOpenHelper myModel;
	
	
	public AccountPresenter(IAccountView view, DbOpenHelper model, String uid){
		myView = view;
		myModel = model;
		myModel.open();
		myView.setAccount(myModel.getAccounts(uid));
	}
	
	/**
	 * Handle a Create button click in the ui.
	 * Just advance to the next screen, but don't
	 * do anything to the model.
	 */
	public void onCreateClick(){
		myView.advanceToCreate();
	}
	
	/**
	 * Handle a Report button click in the ui.
	 * Just advance to the next screen, but don't
	 * do anything to the model.
	 */
	public void onReportClick(){
		myView.advanceToReport();
	}
	
	/**
	 * Handle a Main button click in the ui.
	 * Just advance to the next screen, but don't
	 * do anything to the model.
	 */
	public void onMainClick(){
		myView.advanceToMain();
	}
	

}
