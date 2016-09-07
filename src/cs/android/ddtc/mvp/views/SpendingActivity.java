package cs.android.ddtc.mvp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.ISpendingView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.presenters.SpendingPresenter;

public class SpendingActivity extends Activity implements ISpendingView{
	
	private SpendingPresenter myPresenter;
	
	private String uID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending);
		myPresenter = new SpendingPresenter(this, new DbOpenHelper(this));
		Intent intent = getIntent();
    	uID = intent.getStringExtra("uID");
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending, menu);
		return true;
	}
	
	public void OnXClick(View v){
		myPresenter.onXClick();
	}
	
	public void OnUpdateClick(View v){
		myPresenter.onUpdateClick(uID);
	}
	
	public void OnGraphClick(View v){
		myPresenter.onGraphClick();
	}
	
	private TextView getMealViewField(){
		return (TextView) findViewById(R.id.spendmealvalue);
	}
	
	private TextView getGroViewField(){
		return (TextView) findViewById(R.id.spendgroceryvalue);
	}
	
	private TextView getShopViewField(){
		return (TextView) findViewById(R.id.spendingshoppingvalue);
	}
	
	private TextView getGasViewField(){
		return (TextView) findViewById(R.id.spendinggasvalue);
	}
	
	private TextView getUtilViewField(){
		return (TextView) findViewById(R.id.spendingUtilityvalue);
	}
	
	private TextView getTotalViewField(){
		return (TextView) findViewById(R.id.spendingtotalvalue);
	}

	@Override
	public void advance() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ReportActivity.class);
		intent.putExtra("uID", uID);
		startActivity(intent);
		
		
	}

	@Override
	public void advanceToGraph() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advanceToUpdate(double f, double gro, double s, double gas, double u, double t) {
		getMealViewField().setText(String.valueOf(f));
		getGroViewField().setText(String.valueOf(gro));
		getShopViewField().setText(String.valueOf(s));
		getGasViewField().setText(String.valueOf(gas));
		getUtilViewField().setText(String.valueOf(u));
		getTotalViewField().setText(String.valueOf(t));
		
		
	}

}
