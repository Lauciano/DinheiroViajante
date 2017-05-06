package com.example.lauciano.dinheiroviajante.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.lauciano.dinheiroviajante.model.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lauciano on 5/5/2017.
 */

public class TransacationDAO extends SQLiteOpenHelper {

    public TransacationDAO(Context context) {
        super(context, "Transacations", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableTransacations =
                "CREATE TABLE Transacations (" +
                        "id INTEGER PRIMARY KEY," +
                        "value REAL NOT NULL," +
                        "date TEXT NOT NULL," +
                        "info TEXT)";
        db.execSQL(sqlCreateTableTransacations);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NOTHING CURRENTLY
    }

    public void create(Transaction transaction) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues transactionValues = getContentValues(transaction);
        database.insert("Transacations", null, transactionValues);
    }

    public List<Transaction> readAll() {
        SQLiteDatabase database = getReadableDatabase();
        String sqlReadTransactions = "SELECT * FROM Transactions";
        Cursor cursorReadTransactions = database.rawQuery(sqlReadTransactions, null);

        List<Transaction> transactions = new ArrayList<Transaction>();
        while(cursorReadTransactions.moveToNext()){
            Transaction transaction = new Transaction();
            transaction.setId(cursorReadTransactions.getLong(
                    cursorReadTransactions.getColumnIndex("id")));
            transaction.setValue(cursorReadTransactions.getDouble(
                    cursorReadTransactions.getColumnIndex("value")));
            transaction.setDateFromString(cursorReadTransactions.getString(
                    cursorReadTransactions.getColumnIndex("date")), "yyyy/MM/dd HH:mm:ss");
            transaction.setInfo(cursorReadTransactions.getString(
                    cursorReadTransactions.getColumnIndex("info")));
            transactions.add(transaction);
        }

        cursorReadTransactions.close();

        return transactions;
    }

    public Transaction findTransactionById(Long id) {
        SQLiteDatabase database = getReadableDatabase();
        String sqlReadTransactions = "SELECT * FROM Transactions WHERE id = " + id.toString();

        Cursor cursorReadTransactions = database.rawQuery(sqlReadTransactions, null);
        cursorReadTransactions.moveToNext();

        Transaction transaction = new Transaction();
        transaction.setId(cursorReadTransactions.getLong(
                cursorReadTransactions.getColumnIndex("id")));
        transaction.setValue(cursorReadTransactions.getDouble(
                cursorReadTransactions.getColumnIndex("value")));
        transaction.setDateFromString(cursorReadTransactions.getString(
                cursorReadTransactions.getColumnIndex("date")), "yyyy/MM/dd HH:mm:ss");
        transaction.setInfo(cursorReadTransactions.getString(
                cursorReadTransactions.getColumnIndex("info")));

        return transaction;
    }

    public void delete(Long id) {
        SQLiteDatabase database = getWritableDatabase();
        String[] params = {id.toString()};
        database.delete("Transactions", "id = ?", params);
    }

    public void update(Transaction transaction) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues transactionValues = getContentValues(transaction);
        String[] params = {transaction.getId().toString()};
        database.update("Transactions", transactionValues, "id = ?", params);
    }

    @NonNull
    private ContentValues getContentValues(Transaction transaction) {
        ContentValues transacationValues = new ContentValues();
        transacationValues.put("value", transaction.getSignedValue());
        transacationValues.put("date", transaction.getDateAsString("yyyy/MM/dd HH:mm:ss"));
        transacationValues.put("info", transaction.getInfo());
        return transacationValues;
    }
}
