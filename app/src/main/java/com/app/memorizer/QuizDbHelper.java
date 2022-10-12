package com.app.memorizer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.memorizer.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_WORD + " TEXT, " +
                QuestionsTable.COLUMN_TRANSLATE + " TEXT, " +
                QuestionsTable.COLUMN_RATING + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillVocabularyTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void fillVocabularyTable() {
        Vocabulary v1 = new Vocabulary(1, "Урок", "Lesson", 0);
        addVocabulary(v1);
        Vocabulary v2 = new Vocabulary(2, "Задание", "Task", 0);
        addVocabulary(v2);
        Vocabulary v3 = new Vocabulary(3, "Таблица", "Grid", 0);
        addVocabulary(v3);
        Vocabulary v4 = new Vocabulary(4, "Шаблон", "Template", 0);
        addVocabulary(v4);
        Vocabulary v5 = new Vocabulary(5, "Цвет", "Color", 0);
        addVocabulary(v5);
        Vocabulary v6 = new Vocabulary(6, "Направление", "Trend", 0);
        addVocabulary(v6);
        Vocabulary v7 = new Vocabulary(7, "Ссылка", "Link", 0);
        addVocabulary(v7);
        Vocabulary v8 = new Vocabulary(8, "Рисунок", "Painting", 0);
        addVocabulary(v8);
        Vocabulary v9 = new Vocabulary(9, "Помощь", "Help", 0);
        addVocabulary(v9);
        Vocabulary v10 = new Vocabulary(10, "Файл", "File", 0);
        addVocabulary(v10);
    }

    private void addVocabulary(Vocabulary vocabulary) {
        ContentValues cv = new ContentValues();

        cv.put(QuestionsTable.COLUMN_WORD, vocabulary.getWord());
        cv.put(QuestionsTable.COLUMN_TRANSLATE, vocabulary.getTranslate());
        cv.put(QuestionsTable.COLUMN_RATING, vocabulary.getRating());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<String> giveListOfTranslation(List<Vocabulary> allVocab) {
        List<String> list = new ArrayList<>();

        for (Vocabulary vocabulary : allVocab) {
            list.add(vocabulary.getTranslate());
        }
        return list;
    }


    @SuppressLint("Range")
    public List<Vocabulary> getAllVocabulary() {
        List<Vocabulary> vocabList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setWord(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_WORD)));
                vocabulary.setTranslate(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_TRANSLATE)));
                vocabulary.setRating(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_RATING)));
                vocabulary.setId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ID)));

                vocabList.add(vocabulary);
            } while (c.moveToNext());
        }
        c.close();
        return vocabList;
    }
}
