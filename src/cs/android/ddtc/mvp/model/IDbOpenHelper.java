package cs.android.ddtc.mvp.model;

import java.util.ArrayList;


public interface IDbOpenHelper {
	
	void close();
	void insertColumn(User user);
	void insertDataToAccountHistory(History hist);
	void insertDataToAcctList(Account acct);
	ArrayList<Account> getAccounts(String uid);
	ArrayList<History> getHistory(String uid, String acct);
	void updateBalance(String uid, String acct, double amount);
	

}
