/*package com.capstone.istakk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Delete {
    private MyDatabaseHelper databaseHelper;

    public Delete(Context context) {
        databaseHelper = new MyDatabaseHelper(context);
    }

    public boolean deleteItem(String itemId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String selection = MyDatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {itemId};
        int rowsAffected = db.delete(MyDatabaseHelper.TABLE_NAME, selection, selectionArgs);
        return rowsAffected > 0;
    }
}
*/