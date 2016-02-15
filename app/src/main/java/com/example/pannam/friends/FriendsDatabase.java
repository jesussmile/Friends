package com.example.pannam.friends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by pannam on 2/15/2016.
 */
//add SQLiteopenhelper is  a class that activates database

public class FriendsDatabase extends SQLiteOpenHelper {
    //Logging
    private static final String TAG = FriendsDatabase.class.getSimpleName();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.FRIENDS + "{" + BaseColumns._ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FriendsContract.FriendsColumns.FRIENDS_NAME + "TEXT NOT NULL,"
                + FriendsContract.FriendsColumns.FRIENDS_EMAIL + "TEXT NOT NULL,"
                + FriendsContract.FriendsColumns.FRIENDS_PHONE + "TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //INCASE THE USER UPGRADES THE APP ADD MORE FUNCTIONS ETC..
        int version = oldVersion;
        if (version == 1 ) {
            //Add some extra fields to the database without deleting existing data

            version =2 ;

        }
        if (version != DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS "+ Tables.FRIENDS);
            onCreate(db);
        }

    }

    //friends.db is the data base that will get stored in android device
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 2;
    private final Context mContext;

    interface Tables {
        String FRIENDS = "friends";

    }

    //constructor


    public FriendsDatabase(Context context) {
        super(context, DATABASE_NAME, null
                , DATABASE_VERSION);
        mContext = context;
    }


    //delete database
    public static void deleteDatabase (Context context){
        context.deleteDatabase(DATABASE_NAME);

    }

}


