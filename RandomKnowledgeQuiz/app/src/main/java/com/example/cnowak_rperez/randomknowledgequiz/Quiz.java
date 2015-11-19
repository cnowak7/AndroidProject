package com.example.cnowak_rperez.randomknowledgequiz;

/**
 * Created by Rafael on 11/18/2015.
 */
public class Quiz {
    String name;
    String description;
    Question questions[] = new Question[10];
    enum Category {Animals, Sports, Misc, Images, Geography};

    //Quiz constructor
    public Quiz(String name, String description,Question[] questions){
        this.name = name;
        this.description = description;
        this.questions = questions;
    }
    public String getName(){return name;}
    public String getDescription(){return description;}
    public Question getQuestion(int index){return questions[index];}

}

