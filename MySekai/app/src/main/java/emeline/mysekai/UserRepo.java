package emeline.mysekai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by emeli on 27/03/2017.
 */

public class UserRepo {
    private DBHandler dbHandler;

    public UserRepo(Context context)
    {
        dbHandler = new DBHandler(context);
    }

    public long insert(User user)
    {
        //Open connection to write data
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_ID, user.id);
        values.put(User.KEY_LAST_NAME, user.last_name);
        values.put(User.KEY_FIRST_NAME, user.first_name);
        values.put(User.KEY_EMAIL, user.email);
        values.put(User.KEY_COUNTRY, user.home_country);

        //Inserting row
        long user_Id=db.insert(User.TABLE, null, values);
        db.close();
        return user_Id;
    }

    public void delete(long user_Id)
    {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_ID + "= ?", new String[] { String.valueOf(user_Id) });
        db.close(); // Closing database connection
    }

    public void update(User user)
    {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_ID, user.id);
        values.put(User.KEY_LAST_NAME, user.last_name);
        values.put(User.KEY_FIRST_NAME, user.first_name);
        values.put(User.KEY_EMAIL, user.email);
        values.put(User.KEY_COUNTRY, user.home_country);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(User.TABLE, values, User.KEY_ID + "= ?", new String[] { String.valueOf(user.id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getUserList() {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String selectQuery =  "SELECT " +
                User.KEY_ID + "," +
                User.KEY_LAST_NAME + "," +
                User.KEY_FIRST_NAME + "," +
                User.KEY_EMAIL + "," +
                User.KEY_COUNTRY +
                " FROM " + User.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(User.KEY_ID)));
                user.put("last_name", cursor.getString(cursor.getColumnIndex(User.KEY_LAST_NAME)));
                user.put("first_name", cursor.getString(cursor.getColumnIndex(User.KEY_FIRST_NAME)));
                user.put("email", cursor.getString(cursor.getColumnIndex(User.KEY_EMAIL)));
                user.put("country", cursor.getString(cursor.getColumnIndex(User.KEY_COUNTRY)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        System.out.println("UserList : " + userList);
        return userList;
    }

    public ArrayList<HashMap<String, String>>  getUserListByCountry(long country_Id) {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String selectQuery =  "SELECT " +
                User.KEY_LAST_NAME + "," +
                User.KEY_FIRST_NAME + "," +
                " FROM " + User.TABLE +
                " WHERE " + User.KEY_COUNTRY + "=?";

        //Student student = new Student();
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(User.KEY_ID)));
                user.put("last_name", cursor.getString(cursor.getColumnIndex(User.KEY_LAST_NAME)));
                user.put("first_name", cursor.getString(cursor.getColumnIndex(User.KEY_FIRST_NAME)));
                user.put("email", cursor.getString(cursor.getColumnIndex(User.KEY_EMAIL)));
                user.put("home_country", cursor.getString(cursor.getColumnIndex(User.KEY_COUNTRY)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    public User getStudentById(long Id){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String selectQuery =  "SELECT " +
                User.KEY_ID + "," +
                User.KEY_LAST_NAME + "," +
                User.KEY_FIRST_NAME + "," +
                User.KEY_EMAIL + "," +
                User.KEY_COUNTRY +
                " FROM " + User.TABLE
                + " WHERE " +
                User.KEY_ID + "=" + Long.toString(Id);// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        User user = new User();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                user.id =cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                user.last_name =cursor.getString(cursor.getColumnIndex(User.KEY_LAST_NAME));
                user.first_name =cursor.getString(cursor.getColumnIndex(User.KEY_FIRST_NAME));
                user.email =cursor.getString(cursor.getColumnIndex(User.KEY_EMAIL));
                user.home_country = cursor.getInt(cursor.getColumnIndex(User.KEY_COUNTRY));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }

    public boolean test(long Id)
    {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        User user = new User();

        Cursor cursor = db.rawQuery("SELECT id, first_name FROM users WHERE id = ?", new String[] {Long.toString(Id)});

        if(cursor.getCount()>0) {
            if (cursor.moveToFirst()) {
                do {
                    user.id = cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                    user.first_name = cursor.getString(cursor.getColumnIndex(User.KEY_FIRST_NAME));
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();

        if(user.id == 0) return false;
        else return true;
    }
}