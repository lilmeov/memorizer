package com.app.memorizer;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){};

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "vocabulary";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_TRANSLATE = "translate";
        public static final String COLUMN_RATING = "rating";
    }

}
