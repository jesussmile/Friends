package com.example.pannam.friends;

import android.content.ContentProvider;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

/**
 * Created by pannam on 2/15/2016.
 */
public class FriendProvider extends ContentProvider {
    private FriendsDatabase mOpenHelper;
    private static String Tag = FriendProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final int FRIENDS = 100;
    private static final int FRIENDS_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FriendsContract.CONTENT_AUTHORITY;
        //all
        matcher.addURI(authority, "friends", FRIENDS);
        //item
        matcher.addURI(authority, "friends/*", FRIENDS_ID);
        return matcher;

    }

    @Override
    public boolean onCreate() {
        //gets access to database
        mOpenHelper = new FriendsDatabase(getContext());
        return true;

    }


    private void deleteDatabase() {
        mOpenHelper.close();
        FriendsDatabase.deleteDatabase(getContext());
        //create new DATABASE
        mOpenHelper = new FriendsDatabase(getContext());

    }


    //check Uri is valid

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FRIENDS:
                return FriendsContract.Friends.CONTENT_TYPE;
            case FRIENDS_ID:
                return FriendsContract.Friends.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);

        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(FriendsDatabase.Tables.FRIENDS);
        switch (match) {
            case FRIENDS:
                //DO NOTHING
                break;
            case FRIENDS_ID:
                String id = FriendsContract.Friends.getFriendId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + "=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);

        }

        Cursor cursor = queryBuilder.query(db, projection,selection,selectionArgs,null,null,sortOrder);
        return null;
    }
}




