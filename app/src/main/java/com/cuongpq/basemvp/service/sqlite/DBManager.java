package com.cuongpq.basemvp.service.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.model.User;

import java.util.ArrayList;
import java.util.List;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "user_manager";
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String AVATAR = "avatar";
    //car
    private static final String TABLE_CAR = "car";
    private static final String ID_CAR = "id_car";
    private static final String Name_Car = "name_car";
    private static final String Name_Person = "name_person";

    //race
    private static final String TABLE_RACE = "race";
    private static final String ID_Race = "id_race";
    private static final String Name_Race = "name_race";
    private static final String Date_Race = "date_race";

    private static final int VERSION = 3;
    private Context context;
    private final String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            EMAIL + " TEXT, " +
            FIRST_NAME + " TEXT, " +
            LAST_NAME + " TEXT, " +
            AVATAR + " TEXT)";
    private final String CREATE_TABLE_CAR = "CREATE TABLE " +  TABLE_CAR + " ("+
            ID_CAR + " integer primary key,"+
            Name_Car + " TEXT, " +
            Name_Person + "TEXT)";
    private final String CREATE_TABLE_RACE = "CREATE TABLE " +  TABLE_RACE + " ("+
            ID_Race + " integer primary key,"+
            Name_Race + " TEXT, " +
            Date_Race + "TEXT)";
    public static final DBManager getInstance(Context context){
        DBManager db = new DBManager(context);
        return db;
    }
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, user.getEmail());
        values.put(FIRST_NAME, user.getFirstName());
        values.put(LAST_NAME, user.getLastName());
        values.put(AVATAR, user.getAvatar());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void insertListUser(List<User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users) {
            ContentValues values = new ContentValues();
            values.put(EMAIL, user.getEmail());
            values.put(FIRST_NAME, user.getFirstName());
            values.put(LAST_NAME, user.getLastName());
            values.put(AVATAR, user.getAvatar());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }
    public void insertListCar(List<Car> cars) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Car car : cars) {
            ContentValues values = new ContentValues();
            values.put(ID_CAR, car.getIdCar());
            values.put(Name_Car,car.getNameCar());
            values.put(Name_Person,car.getNamePerson());
            db.insert(TABLE_CAR, null, values);
        }
        db.close();
    }
    public List<Car> getCars() {
        List<Car> listCars = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null,null);
        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setIdCar(cursor.getInt(0));
                car.setNameCar(cursor.getString(1));
                car.setNamePerson(cursor.getString(2));
                listCars.add(car);
            } while (cursor.moveToNext());
        }
        db.close();
        return listCars;
    }
    public void insertListRace(List<Race> races) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Race race : races) {
            ContentValues values = new ContentValues();
            values.put(ID_Race, race.getIdRace());
            values.put(Name_Race,race.getNameRace());
            values.put(Date_Race,race.getDateRace());
            db.insert(TABLE_RACE, null, values);
        }
        db.close();
    }
    public List<Race> getRaces() {
        List<Race> listRaces = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RACE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null,null);
        if (cursor.moveToFirst()) {
            do {
                Race race = new Race();
                race.setIdRace(cursor.getInt(0));
                race.setNameRace(cursor.getString(1));
                race.setDateRace(cursor.getString(2));
                listRaces.add(race);
            } while (cursor.moveToNext());
        }
        db.close();
        return listRaces;
    }
//    public List<User> getUsers() {
//        List<User> listUsers = new ArrayList<>();
//        String selectQuery = "SELECT * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                User user = new User();
//                user.setId(cursor.getInt(0));
//                user.setEmail(cursor.getString(1) + "");
//                user.setFirstName(cursor.getString(2));
//                user.setLastName(cursor.getString(3));
//                user.setAvatar(cursor.getString(4));
//                listUsers.add(user);
//            } while (cursor.moveToNext());
//        }
//        db.close();
//        return listUsers;
//    }

    public int deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(CREATE_TABLE_CAR);
        sqLiteDatabase.execSQL(CREATE_TABLE_RACE);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
            onCreate(sqLiteDatabase);
        }
        Log.d(TAG, "onUpgrade: ");
    }

    public Context getContext() {
        return context;
    }
}
