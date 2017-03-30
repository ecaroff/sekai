package emeline.mysekai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.country;

/**
 * Created by emeli on 21/02/2017.
 */

    public class DBHandler extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 7;
        // Database Name
        public static final String DATABASE_NAME = "sekai.db";

        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE_COUNTRIES = "CREATE TABLE " + Country.TABLE + "("
                    + Country.KEY_ID + " TEXT," + Country.KEY_NAME + " TEXT," +  Country.KEY_LANGUAGE + " TEXT" + ")";

            db.execSQL(CREATE_TABLE_COUNTRIES);

            String CREATE_TABLE_USERS = "CREATE TABLE " + User.TABLE + "("
            + User.KEY_ID + " TEXT," + User.KEY_LAST_NAME + " TEXT," +  User.KEY_FIRST_NAME + " TEXT,"
            + User.KEY_EMAIL + " TEXT," + User.KEY_COUNTRY + " TEXT" + ")";

            db.execSQL(CREATE_TABLE_USERS);

            CountryRepo.insertCountries(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + Country.TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        // Creating tables again
            onCreate(db);
        }
    }

