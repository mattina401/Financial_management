package cs.android.ddtc.mvp.presenters;

import cs.android.ddtc.mvp.Iviews.ISpendingView;
import cs.android.ddtc.mvp.model.DbOpenHelper;

public class SpendingPresenter {

	private final ISpendingView myView;
	private final DbOpenHelper myModel;

	public SpendingPresenter(ISpendingView view, DbOpenHelper model) {
		myView = view;
		myModel = model;
		myModel.open();

	}

	public void onXClick() {
		myView.advance();
	}

	public void onUpdateClick(String uid) {
		double food = myModel.getTotal(uid, "Food");
		double grocery = myModel.getTotal(uid, "Grocery");
		double shopping = myModel.getTotal(uid, "Shopping");
		double gas = myModel.getTotal(uid, "Gas");
		double utility = myModel.getTotal(uid, "Gas");
		double total = food + grocery + shopping + gas + utility;
		
		myView.advanceToUpdate(-food, -grocery, -shopping, -gas, -utility, -total);
	}

	public void onGraphClick() {
		myView.advanceToGraph();
	}
}
