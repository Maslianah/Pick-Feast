package my.maslianah.com.pickfeastfyp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "STORAGE";
        public static final String TABLE_STORAGE = "storage";
        public static final String col1 = "pack_id";
        public static final String col2 = "charity_id";
        public static final String col3 = "status";



        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 3);
            SQLiteDatabase db = this.getWritableDatabase();

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(" CREATE TABLE " + TABLE_STORAGE + " (pack_id TEXT, charity_id TEXT, status INTEGER);");
                Log.e("msg", "table created");
            }
            catch (Exception e)
            {
                Log.e("msg", "hey something wrong here");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            // Upgrade the existing database to conform to the new version.
            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORAGE);
            // Create tables again
            onCreate(db);
        }

        public boolean insertData()
        {
            SQLiteDatabase db = this.getWritableDatabase();

            try {
                ContentValues cv = new ContentValues();
                cv.put(col1, "P001");
                cv.put(col2, "");
                cv.put(col3, 0);
                long result = db.insert(TABLE_STORAGE, null, cv);
            }
            catch (Exception e)
            {
                Log.e("msg", "cannot insertData");
            }
            return true;
        }

        public Cursor getAllData()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * from "+TABLE_STORAGE,  null);
            return res;
        }

        public  boolean updatePackID(String id)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col1, id);
            db.update(TABLE_STORAGE, cv, null, null);

            return true;
        }
        public  boolean updateCharityID(String id)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col2, id);
            db.update(TABLE_STORAGE, cv, null, null);

            return true;
        }
        public  boolean updateTheme(int id)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col3, id);
            db.update(TABLE_STORAGE, cv, null, null);

            return true;
        }
    }











