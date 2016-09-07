package cs.android.ddtc.mvp.presenters;

import android.content.Context;
import android.widget.Toast;
import cs.android.ddtc.mvp.Iviews.IDepositView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.model.History;

public class DepositPresenter {

	private final IDepositView myView;
	private final DbOpenHelper myModel;

	public DepositPresenter(IDepositView view, DbOpenHelper model) {
		myView = view;
		myModel = model;
		myModel.open();

	}
	
	public void OnActionClick(String uid, String acct, String date, String category, double cost){
		myModel.insertDataToAccountHistory(new History(uid, acct, date, category, cost));
		myModel.updateBalance(uid, acct, cost);
		success();
		myView.advance();
	}
	
	public void OnXClick(){
		myView.advance();
	}
	
	protected void success() {
		Toast.makeText((Context) myView, "Trancaction Accepted.",
				Toast.LENGTH_SHORT).show();
	}

}
