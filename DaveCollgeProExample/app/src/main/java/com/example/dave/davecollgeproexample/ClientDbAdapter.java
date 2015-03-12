package com.example.dave.davecollgeproexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dave on 3/12/2015.
 */
public class ClientDbAdapter {



   /* public static final String TABLE_NAME = "entry";
    public static final String COLUMN_NAME_ENTRY_ID = "entryid";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_ADDRESS = "address";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_NOTES = "notes";
    public static final String COLUMN_NAME_LATITUDE = "latitude";
    public static final String COLUMN_NAME_LONGITUDE = "longitude";
    public static final String COLUMN_NAME_PRICE = "price";*/

    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "ClientDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;


    private static final String CREATE_DATABASE =
            "create table clients (_id integer primary key autoincrement, "
                    + "name text not null, address text not null);";

    private static final String CREATE_DATABASE_2 =
            "create table if not exists clients (_id integer primary key autoincrement, "
                    + "name text not null, address text not null);";


    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "clients";
    private static final int DATABASE_VERSION = 2;




    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS clients");
            onCreate(db);
        }
    }

    public ClientDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public ClientDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    /**
     * Create a new client using the name and address provided. If the client is
     * successfully created return the new rowId for that client, otherwise return
     * a -1 to indicate failure.
     *
     * @param name the title of the note
     * @param address the body of the note
     * @return rowId or -1 if failed
     */
    public long createClient(String name, String address) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADDRESS, address);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the client with the given rowId
     *
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteClient(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all notes in the database
     *
     * @return Cursor over all clients
     */
    public Cursor fetchAllClients() {


        mDb.execSQL(CREATE_DATABASE_2);
        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_ADDRESS}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the client that matches the given rowId
     *
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching client, if found
     * @throws SQLException if client could not be found/retrieved
     */
    public Cursor fetchClient(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_NAME, KEY_ADDRESS}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @param rowId id of note to update
     * @param name value to set note title to
     * @param address value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateClient(long rowId, String name, String address) {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_ADDRESS, address);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}
