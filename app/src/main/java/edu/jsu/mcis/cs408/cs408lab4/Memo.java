package edu.jsu.mcis.cs408.cs408lab4;

import androidx.annotation.NonNull;

public class Memo {

    private int id;
    private final String text;

    public Memo(int id, String text) {

        this.id = id;
        this.text = text;

    }

    public Memo(String name) {

        this.text = name;

    }

    public int getId() { return id; }

    public String getText() {
        return text;
    }

    @NonNull
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append(text);
        return s.toString();

    }

}