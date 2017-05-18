package ni.com.debugon.notificationinfo.ni.com.debugon.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ni.com.debugon.notificationinfo.ni.com.debugon.DatabaseClasses.Keyword;

/**
 * Created by luisr on 26/03/2017.
 */

//Referência: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

public class KeywordDatabaseHandler extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "SearchManager";
    //Database table name
    private static final String TABLE_KEYWORDS = "keywords";

    //Keywords Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_KEYWORD = "keyword";
    private static final String KEY_USER = "user";
    private static final String CREATE = "create_at";
    private static final String UPDATE = "update_at";

    public KeywordDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public KeywordDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public KeywordDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_KEYWORDS_TABLE = "CREATE TABLE " + TABLE_KEYWORDS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                KEY_KEYWORD + " TEXT NOT NULL, " +
                KEY_USER + " TEXT, " + //Por enquanto pode ser null, mudar para NOT NULL
                CREATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                UPDATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(CREATE_KEYWORDS_TABLE);

    }

    //Updating Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEYWORDS);
        onCreate(db);
    }

    // Adding new keyword
    public void addKeyword(Keyword keyword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_KEYWORD, keyword.get_keyword()); // Keyword Name
        values.put(KEY_USER, keyword.get_user()); // Keyword User

        // Inserting Row
        db.insert(TABLE_KEYWORDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single keyword
    public Keyword getKeyword(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KEYWORDS, new String[] { KEY_ID,
                        KEY_KEYWORD, KEY_USER }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Keyword keyword = new Keyword(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return contact
        return keyword;
    }

    // Getting All Keywords
    public List<Keyword> getAllKeyword() {
        List<Keyword> keywordList = new ArrayList<Keyword>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KEYWORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Keyword keyword = new Keyword();
                keyword.set_id(Integer.parseInt(cursor.getString(0)));
                keyword.set_keyword(cursor.getString(1));
                keyword.set_user(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                keywordList.add(keyword);
            } while (cursor.moveToNext());
        }

        // return contact list
        return keywordList;
    }

    // Getting keyword Count
    public int getKeywordsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_KEYWORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single keyword
    public int updateKeyword(Keyword keyword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_KEYWORD, keyword.get_keyword());
        values.put(KEY_USER, keyword.get_user());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        values.put(UPDATE, dateFormat.format(date));

        // updating row
        return db.update(TABLE_KEYWORDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(keyword.get_id()) });
    }

    // Deleting single keyword
    public void deleteKeyword(Keyword keyword) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KEYWORDS, KEY_ID + " = ?",
                new String[] { String.valueOf(keyword.get_id()) });
        db.close();
    }
}