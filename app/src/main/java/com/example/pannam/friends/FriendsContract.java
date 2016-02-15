package com.example.pannam.friends;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by pannam on 2/15/2016.
 */
//contains fixed data it basically helps provides information ,COLUMN NAME ETC ..BEST
// PRACTICE TO SEPEARTE THIS
public class FriendsContract {

    //contains column friends table
    interface FriendsColumns {
        String FRIENDS_NAME = "friends_name";
        String FRIENDS_EMAIL = "friends_email";
        String FRIENDS_PHONE = "friends_phone";
    }

    public static final String CONTENT_AUTHORITY = "org.example.pannam.friends.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_FRIENDS = "friends";
    public static final String[] TOP_LEVEL_PATHS = {
            PATH_FRIENDS
    };

    //basecolumn is from android itself
    public static class Friends implements FriendsColumns, BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_FRIENDS).build();
      //defining access more than one data (more than one friend)
       //MIME TYPE multiple type
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+CONTENT_AUTHORITY + ".friends";
       //single type
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+CONTENT_AUTHORITY + ".friends";

        //building Uri to access a particular record
        public static Uri buildFriendUri(String friendId)
        {

            return CONTENT_URI.buildUpon().appendEncodedPath(friendId).build();

        }
        public static String getFriendUri(Uri uri){
            return uri.getPathSegments().get(1);

        }
    }


}
