package cs.android.ddtc.mvp.presenters;

import cs.android.ddtc.mvp.Iviews.IHistView;
import cs.android.ddtc.mvp.model.DbOpenHelper;

public class HistPresenter {
	
	private final IHistView myView;
	private final DbOpenHelper myModel;
	
	public HistPresenter(IHistView view, DbOpenHelper model, String uid, String acct){
		myView = view;
		myModel = model;
		myModel.open();
		myView.setHistory(myModel.getHistory(uid, acct));
		
	}
	
	public void onXClick(){
		myView.advance();
	}
	
	public void onPlusClick(){
		myView.advanceToAdd();
	}

}
