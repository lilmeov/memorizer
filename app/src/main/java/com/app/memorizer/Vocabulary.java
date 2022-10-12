package com.app.memorizer;

public class Vocabulary {
    private int id;
    private String word;
    private String translate;
    private int rating;

    public Vocabulary(int id, String word, String translate, int rating) {
        this.id = id;
        this.word = word;
        this.translate = translate;
        this.rating = rating;
    }

    public Vocabulary() {
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                ", rating=" + rating +
                '}';
    }


    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
