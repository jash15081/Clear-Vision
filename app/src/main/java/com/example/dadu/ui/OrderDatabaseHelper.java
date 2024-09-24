package com.example.eyespecsapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eyespecsapp.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "order_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ORDERS = "orders";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ORDER_DATE = "order_date";
    private static final String COLUMN_IS_COMPLETED = "is_completed";
    private static final String COLUMN_IS_PAYMENT_PENDING = "is_payment_pending";
    private static final String COLUMN_EYE_CAPACITY = "eye_capacity";

    public OrderDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ORDER_DATE + " TEXT,"
                + COLUMN_IS_COMPLETED + " INTEGER,"
                + COLUMN_IS_PAYMENT_PENDING + " INTEGER,"
                + COLUMN_EYE_CAPACITY + " INTEGER" + ")";
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_DATE, order.getOrderDate());
        values.put(COLUMN_IS_COMPLETED, order.isOrderCompleted() ? 1 : 0);
        values.put(COLUMN_IS_PAYMENT_PENDING, order.isPaymentPending() ? 1 : 0);
        values.put(COLUMN_EYE_CAPACITY, order.getEyeCapacity());

        db.insert(TABLE_ORDERS, null, values);
        db.close();
    }

    public List<Order> getPendingOrders() {
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDERS, new String[]{COLUMN_ORDER_DATE, COLUMN_IS_COMPLETED, COLUMN_IS_PAYMENT_PENDING, COLUMN_EYE_CAPACITY},
                COLUMN_IS_COMPLETED + "=?", new String[]{"0"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String orderDate = cursor.getString(0);
                boolean isCompleted = cursor.getInt(1) == 1;
                boolean isPaymentPending = cursor.getInt(2) == 1;
                int eyeCapacity = cursor.getInt(3);

                Order order = new Order(orderDate, isCompleted, isPaymentPending, eyeCapacity);
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;
    }
}
