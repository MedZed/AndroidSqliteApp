package com.example.med.sqlite.sql;

/**
 * Created by med on 18-Mar-18.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.med.sqlite.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_CIN = "user_cin";
    private static final String COLUMN_USER_ROLE = "user_role";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_SURNAME = "user_surname";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +COLUMN_USER_CIN + " INTEGER ," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_SURNAME + " TEXT,"+ COLUMN_USER_ROLE + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_CIN, user.getCin());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_SURNAME, user.getSurname());
        values.put(COLUMN_USER_ROLE, user.getRole());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUserIsAdmin(String email){
    Boolean isAdmin = false;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT user_role FROM user where user_email ='"+email+"'", null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                    String Role = c.getString(c.getColumnIndex("user_role"));
                    if (Role == "Admin" ) isAdmin = true;
            }

        }else{isAdmin = false;}
        c.close();
        db.close();
        return isAdmin;

    }

    public String returnRole(String email){
        String Role = "nothing";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT user_role FROM user where user_email ='"+email+"'", null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                Role = c.getString(c.getColumnIndex("user_role"));
            }

        }else{Role = "Still nothing";}
        return Role;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }



    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM user ORDER BY user_id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setEmail(cursor.getString(cursor.getColumnIndex("user_email")));
                users.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return users;
    }

}
