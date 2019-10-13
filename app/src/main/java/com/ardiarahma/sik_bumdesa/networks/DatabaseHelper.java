package com.ardiarahma.sik_bumdesa.networks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ardiarahma.sik_bumdesa.networks.models.ClassAccount;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/6/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_bumdes";
    private static final int DB_VERSION = 1;

    //tabel parent_akun
    private static final String PARENT_ACCOUNTS = "parent_akun";
    private static final String PARENT_ACCOUNT_ID = "id";
    private static final String PARENT_ACCOUNT_NAME = "name";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PARENT_ACCOUNTS_TABLE = "CREATE TABLE "
                + PARENT_ACCOUNTS + "("
                + PARENT_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PARENT_ACCOUNT_NAME + " TEXT"
                + ")";
        Log.d("Data", "onCreate : " + CREATE_PARENT_ACCOUNTS_TABLE);
        db.execSQL(CREATE_PARENT_ACCOUNTS_TABLE);
//        CREATE_PARENT_ACCOUNTS_TABLE = "INSERT INTO PA"
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //upgrade database
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + CLASS_ACCOUNTS);
//        onCreate(db);
//    }

    //================ CLASS ACCOUNTS ===============
    //create
//    public void addClassAccount(ClassAccount classAccount){
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_CLASS_ACCOUNT_NAME, classAccount.getName());
//
//        db.insert(CLASS_ACCOUNTS, null, values);
//        db.close();
//    }

    //read
    //one record
//    public ClassAccount getClassAccounts(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(CLASS_ACCOUNTS,
//                new String[] { KEY_CLASS_ACCOUNT_ID, KEY_CLASS_ACCOUNT_NAME }, KEY_CLASS_ACCOUNT_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//
//        if (cursor != null){
//            cursor.moveToFirst();
//        }
//
//        ClassAccount classAccount = new ClassAccount(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
//        return classAccount;
//    }
    //get all record
    public ArrayList<ClassAccount> getAllClassAccount(){
        ArrayList<ClassAccount> classAccounts = new ArrayList<ClassAccount>();
        //select all query
//        String selectClass = "SELECT * FROM " + CLASS_ACCOUNTS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectClass, null);

//        if (cursor.moveToFirst()){
//            do {
//                ClassAccount classAccount = new ClassAccount();
//
//                classAccount.setId(Integer.parseInt(cursor.getString(0)));
//                classAccount.setName(cursor.getString(1));
//
//                classAccounts.add(classAccount);
//            }while (cursor.moveToNext());
//        }
        return classAccounts;
    }

    //update record
//    public int updateClassAccount(ClassAccount classAccount){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_CLASS_ACCOUNT_NAME, classAccount.getName());
//
//        //updating row
//        return db.update(CLASS_ACCOUNTS, values, KEY_CLASS_ACCOUNT_ID + "=?",
//                new String[] { String.valueOf(classAccount.getId()) });
//    }

    //delete record
    public void deleteClassAccount(ClassAccount classAccount){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(CLASS_ACCOUNTS, KEY_CLASS_ACCOUNT_ID + "=?",
//                new String[] { String.valueOf(classAccount.getId()) });
//        db.close();
    }



}