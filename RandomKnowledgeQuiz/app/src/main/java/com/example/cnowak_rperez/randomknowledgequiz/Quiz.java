package com.example.cnowak_rperez.randomknowledgequiz;

import java.io.Serializable;

/**
 * Created by Rafael on 11/18/2015.
 */
public class Quiz implements Serializable{
    String name;
    String description;
    Question questions[] = new Question[10];
    enum Category {Animals, Sports, Misc, Images, Geography, History};
    public Category category;

    //Quiz constructor
    public Quiz(String name, Category category ,String description,Question[] questions){
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.category = category;
    }
    public String getName(){return name;}
    public String getDescription(){return description;}
    public Question getQuestion(int index){return questions[index];}
    public Category getCategory(){return category;}
    public static int getIconResource(Quiz.Category category){

        switch(category){
            case History: return R.drawable.history;
            case Animals: return R.drawable.animal;
            case Misc: return R.drawable.misc;
            case Geography: return R.drawable.geography;
            case Sports: return R.drawable.sport;
        }
        return -1;
    }

}

