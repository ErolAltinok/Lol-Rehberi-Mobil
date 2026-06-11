package com.example.ErolMobil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LolRehberiDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favori_taktikler";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CHAMPION = "sampiyon";
    private static final String COLUMN_TACTIC = "taktik";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CHAMPION + " TEXT, " +
                COLUMN_TACTIC + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Arayüzden gelen LoL şampiyonu ve taktiğini veritabanına ekler
    public boolean addTactic(String champion, String tactic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHAMPION, champion);
        values.put(COLUMN_TACTIC, tactic);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    // Widget için veritabanından rastgele bir taktik getirir
    public String getRandomTactic() {
        SQLiteDatabase db = this.getReadableDatabase();
        String resultText = "Henüz taktik eklenmedi. Vadi'ye dön!";

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_CHAMPION + ", " + COLUMN_TACTIC + " FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);

        if (cursor.moveToFirst()) {
            String champ = cursor.getString(0);
            String tac = cursor.getString(1);
            resultText = champ + " İpucu: " + tac;
        }
        cursor.close();
        return resultText;
    }

    // Favoriler için kayıtlı tüm taktikleri liste olarak getirir
    public ArrayList<HashMap<String, String>> getAllTactics() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> tacticList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("sampiyon", cursor.getString(1));
                map.put("taktik", cursor.getString(2));
                tacticList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tacticList;
    }

    // Tablodaki tüm taktikleri tek seferde siler
    public void deleteAllTactics() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}