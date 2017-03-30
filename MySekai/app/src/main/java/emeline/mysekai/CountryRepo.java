package emeline.mysekai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by emeli on 28/03/2017.
 */

public class CountryRepo {

    private DBHandler dbHandler;

    public CountryRepo(Context context)
    {
        dbHandler = new DBHandler(context);
    }

    public long insert(Country country)
    {
        //Open connection to write data
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Country.KEY_ID, country.id);
        values.put(Country.KEY_NAME, country.name);
        values.put(Country.KEY_LANGUAGE, country.language);

        //Inserting row
        long country_Id=db.insert(Country.TABLE, null, values);
        db.close();
        return country_Id;
    }

    public void delete(long country_Id)
    {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_ID + "= ?", new String[] { String.valueOf(country_Id) });
        db.close(); // Closing database connection
    }

    public void update(Country country)
    {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Country.KEY_ID, country.id);
        values.put(Country.KEY_NAME, country.name);
        values.put(Country.KEY_LANGUAGE, country.language);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Country.TABLE, values, Country.KEY_ID + "= ?", new String[] { String.valueOf(country.id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getCountryList() {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Country.KEY_ID + "," +
                Country.KEY_NAME + "," +
                Country.KEY_LANGUAGE +
                " FROM " + Country.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> countryList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> country = new HashMap<String, String>();
                country.put("id", cursor.getString(cursor.getColumnIndex(Country.KEY_ID)));
                country.put("name", cursor.getString(cursor.getColumnIndex(Country.KEY_NAME)));
                country.put("language", cursor.getString(cursor.getColumnIndex(Country.KEY_LANGUAGE)));
                countryList.add(country);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        System.out.println("CountryList : " + countryList);
        return countryList;
    }


    public Country getCountryById(long Id){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Country.KEY_ID + "," +
                Country.KEY_NAME + "," +
                Country.KEY_LANGUAGE +
                " FROM " + Country.TABLE
                + " WHERE " +
                Country.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Country country = new Country();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                country.id =cursor.getInt(cursor.getColumnIndex(Country.KEY_ID));
                country.name =cursor.getString(cursor.getColumnIndex(Country.KEY_NAME));
                country.language =cursor.getString(cursor.getColumnIndex(Country.KEY_LANGUAGE));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return country;
    }

    public static long insertCountries(SQLiteDatabase db)
    {
        //Open connection to write data
        ContentValues values = new ContentValues();

        values.put(Country.KEY_ID, "1");
        values.put(Country.KEY_NAME, "France");
        values.put(Country.KEY_LANGUAGE, "French");

        //Inserting row
        long country_Id=db.insert(Country.TABLE, null, values);

        values.put(Country.KEY_ID, 2);
        values.put(Country.KEY_NAME, "England");
        values.put(Country.KEY_LANGUAGE, "English");

        //Inserting row
        country_Id=db.insert(Country.TABLE, null, values);

        values.put(Country.KEY_ID, 3);
        values.put(Country.KEY_NAME, "Germany");
        values.put(Country.KEY_LANGUAGE, "German");

        //Inserting row
        country_Id=db.insert(Country.TABLE, null, values);

        values.put(Country.KEY_ID, 4);
        values.put(Country.KEY_NAME, "Spain");
        values.put(Country.KEY_LANGUAGE, "Spanish");

        //Inserting row
        country_Id=db.insert(Country.TABLE, null, values);

        return country_Id;
    }

}
