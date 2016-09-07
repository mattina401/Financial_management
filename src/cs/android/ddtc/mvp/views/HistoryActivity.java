package cs.android.ddtc.mvp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import cs.android.ddtc.mvp.R;
import cs.android.ddtc.mvp.Iviews.IHistView;
import cs.android.ddtc.mvp.model.DbOpenHelper;
import cs.android.ddtc.mvp.model.History;
import cs.android.ddtc.mvp.presenters.HistPresenter;
import cs.android.ddtc.mvp.support.HistListAdapter;

public class HistoryActivity extends Activity implements IHistView{
	
	private HistPresenter myPresenter;
	String uID;
	String acct;
	private ArrayList<History> hists;
	private HistListAdapter adapt;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		uID = extras.getString("uid");
		acct = extras.getString("acctname");
		myPresenter = new HistPresenter(this, new DbOpenHelper(this), uID, acct);
		
		adapt = new HistListAdapter(this, hists);
		getListViewField().setAdapter(adapt);
		
		getTextViewField().setText(acct);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}
	
	private ListView getListViewField(){
		return (ListView) findViewById(R.id.histList);
	}
	
	private TextView getTextViewField(){
		return (TextView) findViewById(R.id.HistAcctView);
	}
	
	public void OnXClick(View v){
		myPresenter.onXClick();
	}
	
	public void OnPlusClick(View v){
		myPresenter.onPlusClick();
	}

	@Override
	public void advanceToAdd() {
		Intent intent = new Intent(this, DepositActivity.class);
		Bundle extras = new Bundle();
		extras.putString("uid", uID);
		extras.putString("acctname", acct);
		intent.putExtras(extras);
		startActivity(intent);
		
	}

	@Override
	public void advance() {
		Intent intent = new Intent(this, AccountActivity.class);
		Bundle extras = new Bundle();
		extras.putString("uid", uID);
		extras.putString("acctname", acct);
		intent.putExtras(extras);
		startActivity(intent);
		
	}

	@Override
	public void setHistory(ArrayList<History> h) {
		hists = h;
		
	}

}
