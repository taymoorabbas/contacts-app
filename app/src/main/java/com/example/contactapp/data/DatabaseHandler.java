package com.example.contactapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contactapp.R;
import com.example.contactapp.model.Contact;
import com.example.contactapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //we create our table here
    //using SQL commands
    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY, " +
                Util.KEY_NAME + " TEXT, " +
                Util.KEY_PHONE + " TEXT" + ")";

        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        String DROP_TABLE = String.valueOf(R.string.drop_table);
        database.execSQL(DROP_TABLE, new String[]{Util.TABLE_NAME});
        onCreate(database);
    }

    /* CRUD - CREATE, READ, UPDATE, DELETE */

    public void addContact(Contact contact){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getContactName());
        values.put(Util.KEY_PHONE, contact.getPhoneNumber());

        database.insert(Util.TABLE_NAME, null, values);
        database.close();
    }

    public Contact getContact(int id){

        SQLiteDatabase database = this.getReadableDatabase();

        String SELECT_FROM = "SELECT " + Util.KEY_ID + ", " + Util.KEY_NAME + ", " + Util.KEY_PHONE +
                " FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_ID + " = " + id;

        Cursor cursor = database.rawQuery(SELECT_FROM, null);

        if(cursor != null){

            cursor.moveToFirst();
        }

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setContactName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    public List<Contact> getAllContacts(){

        SQLiteDatabase database = this.getReadableDatabase();
        List<Contact> contactList = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = database.rawQuery(SELECT_ALL,null);

        if(cursor.moveToFirst()){

            do{

                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setContactName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contactList.add(contact);

            }while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateContact(Contact contact){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getContactName());
        values.put(Util.KEY_PHONE,contact.getPhoneNumber());

        return database.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){

        SQLiteDatabase database = this.getWritableDatabase();
        String DELETE_FROM = "DELETE FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_ID + " = " + contact.getId();

        database.execSQL(DELETE_FROM);
        database.close();
    }

    public int getCount(){

        SQLiteDatabase database = this.getReadableDatabase();
        String SELECT_ALL = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = database.rawQuery(SELECT_ALL,null);

        return cursor.getCount();
    }
}
