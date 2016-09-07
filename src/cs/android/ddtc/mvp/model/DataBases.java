/**
 * This file is just for the SQL code statement combination. 
 * We are gonna implement these methodson DbOpenHelper 
 */
package cs.android.ddtc.mvp.model;

import android.provider.BaseColumns;

/**
 * This is a DataBase class.
 * 
 * @author Team DDTC
 * 
 */

public final class DataBases {
	/**
	 * This is a UserInformation class.
	 * 
	 * 
	 */
	public static final class UserInformation implements BaseColumns {
		/**
		 * Make a static variable ID.
		 */
		public static final String ID = "id";
		/**
		 * Make a static variable PASSWORD.
		 */
		public static final String PASSWORD = "password";
		/**
		 * Make a static variable NAME.
		 */
		public static final String NAME = "name";
		/**
		 * Make a static variable _TABLENAME.
		 */
		public static final String TABLENAME = "userInfo";
		/**
		 * This is a String generator for make database table.
		 */
		public static final String CREATE = "create table " + TABLENAME + "("
				+ ID + " text not null , " + PASSWORD + " text not null , "
				+ NAME + " text not null );";
	}

	/**
 * 
 *
 *
 */
	public final class AcctList implements BaseColumns {
		/**
    	 * 
    	 */
		public static final String ID = "id";
		/**
         * 
         */
		public static final String ACCTNAME = "acctname";
		/**
         * 
         */
		public static final String BALANCE = "balance";
		/**
         * 
         */
		public static final String TABLENAME = "acctList";
		/**
 * 
 */
		public static final String CREATE = "create table " + TABLENAME + "("
				+ ID + " text not null , " + ACCTNAME + " text not null , "
				+ BALANCE + " double );";
	}

	/**
	 * 
	 * @author Sodabi
	 * 
	 */
	public final class AccountHistory implements BaseColumns {
		/**
    	 * 
    	 */
		public static final String ID = "id";
		/**
         * 
         */
		public static final String DATE = "date";
		/**
         * 
         */
		public static final String CATEGORY = "category";
		/**
         * 
         */
		public static final String ACCT = "acct";
		/**
         * 
         */
		public static final String COST = "cost";
		/**
         * 
         */
		public static final String TABLENAME = "acctHistory";

		/**
		 * Create new table.
		 */
		public static final String CREATE = "create table " + TABLENAME + "("
				+ ID + " text not null , " + DATE + " text not null , "
				+ CATEGORY + " text not null , " + ACCT + " text not null , "
				+ COST + " double );";

	}

}
