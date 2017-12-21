package com.example.file;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static com.example.file.NamesReaderContract.FeedEntry.COLUMN_NAME_TITLE;
import static com.example.file.NamesReaderContract.FeedEntry.TABLE_NAME;

/**
 * Created by Иван on 31.10.2017.
 */
public final class NamesReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    public NamesReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "namesTable";
        public static final String COLUMN_NAME_TITLE = "name";
    }

    public static class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            //db.execSQL(SQL_DELETE_ENTRIES);
            //onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
        public void putName(String name)
        {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(FeedEntry.COLUMN_NAME_TITLE, name);

            db.insert(TABLE_NAME, null, values);
        }

        public String getAllNames()
        {
            SQLiteDatabase db = getReadableDatabase();

            String[] projection = {COLUMN_NAME_TITLE};

            Cursor c = db.query(TABLE_NAME, projection, "", null, null, null, "");

            String res ="";
            c.moveToFirst();
            do //
            {
                res = res.concat(c.getString(c.getColumnIndex(COLUMN_NAME_TITLE)));
                res = res.concat("\n");
            }
            while (c.moveToNext()); //
            return res;
        }
    }

}