package cs.android.ddtc.mvp.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper implements IDbOpenHelper {
	private static String databaseName = "userInfo";
	private static final int DATABASE_VERSION = 1;
	public static SQLiteDatabase mDB;
	private DatabaseHelper mDBHelper;
	private Context mCtx;

	public class DatabaseHelper extends SQLiteOpenHelper {
		/**
		 * 
		 * @param context
		 *            context.
		 * @param name
		 *            name.
		 * @param factory
		 *            factory.
		 * @param version
		 *            version.
		 */
		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		/**
		 * @param db
		 *            Database.
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DataBases.UserInformation.CREATE);
			db.execSQL(DataBases.AccountHistory.CREATE);
			db.execSQL(DataBases.AcctList.CREATE);
		} // we get two DB so far

		// I need explanation
		/**
		 * @param db
		 *            database.
		 * @param oldVersion
		 *            oldVersion.
		 * @param newVersion
		 *            nerVersion.
		 */
		@Override
		// update old database if version is updated
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS"
					+ DataBases.UserInformation.TABLENAME);
			db.execSQL("DROP TABLE IF EXISTS"
					+ DataBases.AccountHistory.TABLENAME);
			db.execSQL("DROP TABLE IF EXISTS" + DataBases.AcctList.TABLENAME);
			onCreate(db);
		}
	}

	/**
	 * 
	 * @param context
	 *            context.
	 */
	public DbOpenHelper(Context context) {
		this.mCtx = context;
	}

	/**
	 * 
	 * @return return type database.
	 * @throws SQLException
	 *             throws SQLException
	 */
	public DbOpenHelper open() throws SQLException {
		mDBHelper = new DatabaseHelper(mCtx, databaseName, null,
				DATABASE_VERSION);
		mDB = mDBHelper.getWritableDatabase();
		return this;
	}

	/**
 * 
 */

	@Override
	public void close() {
		mDB.close();

	}

	@Override
	public void insertColumn(User user) {
		ContentValues values = new ContentValues();
		values.put(DataBases.UserInformation.ID, user.getUid());
		values.put(DataBases.UserInformation.PASSWORD, user.getPw());
		values.put(DataBases.UserInformation.NAME, user.getName());
		mDB.insert(DataBases.UserInformation.TABLENAME, null, values);
	}

	public Cursor getAllInfoColumns() {
		return mDB.query(DataBases.UserInformation.TABLENAME, null, null, null,
				null, null, null);
	}

	public boolean getMatchName(String uid, String pass) {
		Cursor c = mDB.rawQuery("select * from userInfo where id=" + "'" + uid
				+ "'" + "AND password=" + "'" + pass + "'", null);
		if (c.moveToNext())
			return true;
		else
			return false;
	}

	@Override
	public void insertDataToAcctList(Account acct) {
		ContentValues values = new ContentValues();
		values.put(DataBases.AcctList.ID, acct.getUid());
		values.put(DataBases.AcctList.ACCTNAME, acct.getAcct());
		values.put(DataBases.AcctList.BALANCE, acct.getBalance());
		mDB.insert(DataBases.AcctList.TABLENAME, null, values);

	}

	@Override
	public ArrayList<Account> getAccounts(String uid) {
		Cursor c = mDB.rawQuery("select * from acctList where id=" + "'" + uid
				+ "'", null);
		ArrayList<Account> accts = new ArrayList<Account>();
		while (c.moveToNext()) {
			accts.add(new Account(c.getString(1), c.getString(2)));
		}
		c.close();
		return accts;

	}

	@Override
	public void insertDataToAccountHistory(History hist) {
		ContentValues values = new ContentValues();
		values.put(DataBases.AccountHistory.ID, hist.getUid());
		values.put(DataBases.AccountHistory.DATE, hist.getDate());
		values.put(DataBases.AccountHistory.CATEGORY, hist.getCategory());
		values.put(DataBases.AccountHistory.ACCT, hist.getAcct());
		values.put(DataBases.AccountHistory.COST, hist.getCost());
		mDB.insert(DataBases.AccountHistory.TABLENAME, null, values);

	}

	@Override
	public void updateBalance(String uid, String acct, double amount) {
		Cursor c = mDB.rawQuery("select * from acctList where id=" + "'" + uid
				+ "'" + "AND acctname=" + "'" + acct + "'", null);
		while (c.moveToNext()) {
			amount += c.getDouble(2);
		}
		ContentValues values = new ContentValues();
		values.put("balance", amount);
		mDB.update(DataBases.AcctList.TABLENAME, values, "acctName=?",
				new String[] { acct });

	}

	@Override
	public ArrayList<History> getHistory(String uid, String acct) {
		Cursor c = mDB.rawQuery("select * from acctHistory where id=" + "'"
				+ uid + "'" + "AND acct=" + "'" + acct + "'", null);

		ArrayList<History> hists = new ArrayList<History>();
		while (c.moveToNext()) {
			hists.add(new History(c.getString(1), c.getString(2), c
					.getString(4)));

		}
		c.close();
		return hists;
	}

	public double getTotal(String uid, String category) {
		Cursor c = mDB.rawQuery("select * from acctHistory where id=" + "'"
				+ uid + "'" + "AND category=" + "'" + category + "'", null);
		double total = 0;
		while (c.moveToNext()) {
			total += c.getDouble(4);
		}
		c.close();
		return total;
	}

}
