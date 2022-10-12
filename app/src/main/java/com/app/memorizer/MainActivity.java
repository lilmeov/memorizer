package com.app.memorizer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView totalWordsTxt, learnedWordsTxt, question;
    private int totalWordsCount, learnedWordsCount;

    private Button option1, option2, option3, option4, option5, option6, option7, nextButton;
    private Vocabulary currentlyAskedVocab;
    private String askedVocabTranslation;

    List<Vocabulary> vocabList;
    List<String> listOfTranslation;

    QuizDbHelper quizDbHelper;
    List<Vocabulary> limitedOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalWordsTxt = findViewById(R.id.total_words);
        learnedWordsTxt = findViewById(R.id.learned_words);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.ans_A);
        option2 = findViewById(R.id.ans_B);
        option3 = findViewById(R.id.ans_C);
        option4 = findViewById(R.id.ans_D);
        option5 = findViewById(R.id.ans_E);
        option6 = findViewById(R.id.ans_F);
        option7 = findViewById(R.id.ans_G);
        nextButton = findViewById(R.id.next_btn);

        quizDbHelper = new QuizDbHelper(this);
        vocabList = quizDbHelper.getAllVocabulary();
        totalWordsCount = vocabList.size();

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);
        option7.setOnClickListener(this);


        shuffleAndSetOptions();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleAndSetOptions();
            }
        });
    }

    public int countLearnedWords() {
        int learned = 0;
        for (int i = 0; i < vocabList.size(); i++) {
            if (vocabList.get(i).getRating() >= 10) {
                learned++;
            }
        }
        return learned;
    }


    public void shuffleAndSetOptions() {
        setAllButtonsWhite();

        Collections.shuffle(vocabList);
        limitedOptions = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            limitedOptions.add(vocabList.get(i));
        }

        setTextView();
        listOfTranslation = quizDbHelper.giveListOfTranslation(vocabList);

        Collections.shuffle(limitedOptions);
        option1.setText(limitedOptions.get(0).getTranslate());
        option2.setText(limitedOptions.get(1).getTranslate());
        option3.setText(limitedOptions.get(2).getTranslate());
        option4.setText(limitedOptions.get(3).getTranslate());
        option5.setText(limitedOptions.get(4).getTranslate());
        option6.setText(limitedOptions.get(5).getTranslate());
        option7.setText(limitedOptions.get(6).getTranslate());
    }

    @Override
    public void onClick(View view) {

        Button b = (Button) view;
        String chosenTranslation = b.getText().toString();
        checkCorrectness(chosenTranslation);


        if (!askedVocabTranslation.equals(chosenTranslation)) {
            b.setBackgroundColor(Color.RED);
        }

        turnGreenRightOptionOnly();
//        System.out.println("Рейтинг слова:  "+currentlyAskedVocab.getWord()+" состовляет:   "+currentlyAskedVocab.getRating());
    }

    public void turnGreenRightOptionOnly() {

        if (option1.getText().toString().equals(askedVocabTranslation)) {
            option1.setBackgroundColor(Color.GREEN);
        } else if (option2.getText().toString().equals(askedVocabTranslation)) {
            option2.setBackgroundColor(Color.GREEN);
        } else if (option3.getText().toString().equals(askedVocabTranslation)) {
            option3.setBackgroundColor(Color.GREEN);
        } else if (option4.getText().toString().equals(askedVocabTranslation)) {
            option4.setBackgroundColor(Color.GREEN);
        } else if (option5.getText().toString().equals(askedVocabTranslation)) {
            option5.setBackgroundColor(Color.GREEN);
        } else if (option6.getText().toString().equals(askedVocabTranslation)) {
            option6.setBackgroundColor(Color.GREEN);
        } else if (option7.getText().toString().equals(askedVocabTranslation)) {
            option7.setBackgroundColor(Color.GREEN);
        }
    }

    public void setAllButtonsWhite() {
        option1.setBackgroundColor(Color.WHITE);
        option2.setBackgroundColor(Color.WHITE);
        option3.setBackgroundColor(Color.WHITE);
        option4.setBackgroundColor(Color.WHITE);
        option5.setBackgroundColor(Color.WHITE);
        option6.setBackgroundColor(Color.WHITE);
        option7.setBackgroundColor(Color.WHITE);
    }

    private void setTextView() {
        Collections.shuffle(limitedOptions);
        currentlyAskedVocab = limitedOptions.get(0);
        askedVocabTranslation = currentlyAskedVocab.getTranslate();

        learnedWordsCount = countLearnedWords();
        totalWordsTxt.setText("Всего слов: " + totalWordsCount);
        learnedWordsTxt.setText("Изучено слов:" + learnedWordsCount);
        question.setText("Выберите правильный перевод:   " + currentlyAskedVocab.getWord());
    }

    public void checkCorrectness(String chosenTranslation) {
        int rating = currentlyAskedVocab.getRating();

        if (askedVocabTranslation.equals(chosenTranslation)) {
            currentlyAskedVocab.setRating(rating + 1);
            System.out.println("Correct!");
        } else {
            currentlyAskedVocab.setRating(rating - 2);
            System.out.println("Nope :(");
        }
    }


}