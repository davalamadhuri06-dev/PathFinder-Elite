package com.example.pathfinder2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PathFinderElite.db";
    
    // User Table
    public static final String TABLE_USERS = "users_table";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PASS = "PASSWORD";
    public static final String COL_AGE = "AGE";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_LOCATION = "LOCATION";
    public static final String COL_DREAM = "DREAM_CAREER";
    public static final String COL_SURVEY = "SURVEY_DONE";
    public static final String COL_RECOMMENDATION = "RECOMMENDED_PATH";
    public static final String COL_LAST_LOGIN = "LAST_LOGIN";

    // Application Table
    public static final String TABLE_APPLICATIONS = "applications_table";
    
    // Resume Table
    public static final String TABLE_RESUMES = "resumes_table";
    public static final String RES_COL_EMAIL = "EMAIL";
    public static final String RES_COL_CONTENT = "RESUME_DATA";

    // Feedback Table
    public static final String TABLE_FEEDBACK = "feedback_results";
    public static final String FEED_COL_ID = "ID";
    public static final String FEED_COL_EMAIL = "EMAIL";
    public static final String FEED_COL_USEFUL = "IS_USEFUL";
    public static final String FEED_COL_FAV = "FAV_FEATURE";
    public static final String FEED_COL_SUGGEST = "SUGGESTIONS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, AGE INTEGER, PHONE TEXT, LOCATION TEXT, " + COL_DREAM + " TEXT, SURVEY_DONE INTEGER DEFAULT 0, RECOMMENDED_PATH TEXT, " + COL_LAST_LOGIN + " TEXT)");
        db.execSQL("create table " + TABLE_APPLICATIONS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENT_NAME TEXT, COURSE_NAME TEXT, STATUS TEXT)");
        db.execSQL("create table " + TABLE_RESUMES + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, RESUME_DATA TEXT)");
        db.execSQL("create table " + TABLE_FEEDBACK + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, IS_USEFUL TEXT, FAV_FEATURE TEXT, SUGGESTIONS TEXT)");
        // Legacy support
        db.execSQL("create table feedback_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENT_NAME TEXT, Q1_AWARENESS TEXT, Q2_DREAM TEXT, Q3_INTERNET TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPLICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESUMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS feedback_table");
        onCreate(db);
    }

    public boolean registerUser(String name, String email, String pass, int age, String phone, String location, String dream) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PASS, pass);
        cv.put(COL_AGE, age);
        cv.put(COL_PHONE, phone);
        cv.put(COL_LOCATION, location);
        cv.put(COL_DREAM, dream);
        long result = db.insert(TABLE_USERS, null, cv);
        return result != -1;
    }

    public boolean checkUser(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_USERS + " where EMAIL=? and PASSWORD=?", new String[]{email, pass});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void updateLastLogin(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_LAST_LOGIN, new java.util.Date().toString());
        db.update(TABLE_USERS, cv, "EMAIL=?", new String[]{email});
    }

    public String getUserName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select NAME from " + TABLE_USERS + " where EMAIL=?", new String[]{email});
        String name = "Explorer";
        if (cursor.moveToFirst()) name = cursor.getString(0);
        cursor.close();
        return name;
    }

    public String getRecommendation(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select RECOMMENDED_PATH from " + TABLE_USERS + " where EMAIL=?", new String[]{email});
        String rec = "Explorer";
        if (cursor.moveToFirst()) rec = cursor.getString(0);
        cursor.close();
        return (rec == null || rec.isEmpty()) ? "Explorer" : rec;
    }

    public void updateRecommendation(String email, String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_RECOMMENDATION, path);
        cv.put(COL_SURVEY, 1); // Mark survey as done
        db.update(TABLE_USERS, cv, "EMAIL=?", new String[]{email});
    }

    public Cursor getAllFeedback() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from " + TABLE_FEEDBACK, null);
    }

    public boolean saveResume(String email, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RES_COL_EMAIL, email);
        cv.put(RES_COL_CONTENT, content);
        long result = db.insert(TABLE_RESUMES, null, cv);
        return result != -1;
    }

    public boolean saveFeedback(String email, String useful, String fav, String suggest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FEED_COL_EMAIL, email);
        cv.put(FEED_COL_USEFUL, useful);
        cv.put(FEED_COL_FAV, fav);
        cv.put(FEED_COL_SUGGEST, suggest);
        long result = db.insert(TABLE_FEEDBACK, null, cv);
        return result != -1;
    }

    public boolean hasGivenFeedback(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_FEEDBACK + " where EMAIL=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Cursor getApplicationsForStudent(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from " + TABLE_APPLICATIONS + " where STUDENT_NAME=?", new String[]{name});
    }

    public boolean isCourseMentorshipApproved(String studentName, String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_APPLICATIONS + " where STUDENT_NAME=? and COURSE_NAME=? and STATUS=?", new String[]{studentName, courseName, "✅ APPROVED"});
        boolean approved = cursor.getCount() > 0;
        cursor.close();
        return approved;
    }

    public boolean applyForCourse(String student, String course, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUDENT_NAME", student);
        cv.put("COURSE_NAME", course);
        cv.put("STATUS", status);
        long result = db.insert(TABLE_APPLICATIONS, null, cv);
        return result != -1;
    }

    public boolean updateApplicationStatus(String id, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STATUS", status);
        int result = db.update(TABLE_APPLICATIONS, cv, "ID=?", new String[]{id});
        return result > 0;
    }

    public String getApplicationStatus(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select STATUS from " + TABLE_APPLICATIONS + " where ID=?", new String[]{id});
        String status = "";
        if (cursor.moveToFirst()) status = cursor.getString(0);
        cursor.close();
        return status;
    }

    public boolean deleteUser(String input) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_USERS, "EMAIL=?", new String[]{input});
        if (rows == 0) {
            try {
                rows = db.delete(TABLE_USERS, "ID=?", new String[]{input});
            } catch (Exception e) {}
        }
        return rows > 0;
    }

    public boolean deleteApplication(String input) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_APPLICATIONS, "ID=?", new String[]{input});
        return rows > 0;
    }

    public void wipeAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USERS);
        db.execSQL("DELETE FROM " + TABLE_APPLICATIONS);
        db.execSQL("DELETE FROM " + TABLE_RESUMES);
        db.execSQL("DELETE FROM " + TABLE_FEEDBACK);
        db.execSQL("DELETE FROM feedback_table");
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from " + TABLE_USERS, null);
    }

    public Cursor getAllApplications() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from " + TABLE_APPLICATIONS, null);
    }

    public Cursor getAllSurveyResults() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from feedback_table", null);
    }

    public boolean insertSurvey(String name, String q1, String q2, String q3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUDENT_NAME", name);
        cv.put("Q1_AWARENESS", q1);
        cv.put("Q2_DREAM", q2);
        cv.put("Q3_INTERNET", q3);
        long result = db.insert("feedback_table", null, cv);
        return result != -1;
    }
}
