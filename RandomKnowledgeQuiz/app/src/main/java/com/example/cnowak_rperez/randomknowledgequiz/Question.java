package com.example.cnowak_rperez.randomknowledgequiz;

import java.io.Serializable;

/**
 * Created by Rafael on 11/18/2015.
 */
public class Question implements Serializable{
    String question;
    String answer;
    String[] possible_answers = new String[4];
    int order;
    enum Category {Animals, Sports, Misc, Images, Geography, History};
    public Category category;

    public Question(String question, String answer, String a1, String a2, String a3, String a4, int order, Category category){
        this.question = question;
        this.answer = answer;
        this.possible_answers[0] = a1;
        this.possible_answers[1] = a2;
        this.possible_answers[2] = a3;
        this.possible_answers[3] = a4;
        this.order = order;
        this.category = category;
    }
    public String getQuestion(){return question;}
    public String getAnswer(){return answer;}
    public String[] getPossible_answers(){return possible_answers;}
    public int getOrder(){return order;}
    public Category getCategory(){return category;}

    public int[] getImageResources(Category category, int order){
        int[] resources = new int[4];
        if(!category.equals(Category.Images)){
            return resources;
        }
        switch (order){
            //AND IMAGES TO DRAWABLE SO I CAN ADD LOGIC HERE
        }
        return resources;
    }
}

