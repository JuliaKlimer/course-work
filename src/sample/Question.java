package sample;

import java.util.ArrayList;

public class Question {
    private String name;
    private String[] answers;
    public Question(String name, String[] answers) {
        this.name = name;
        this.answers = answers;
    }
    //правильный ответ - последний элмент массива
    public String correctAnswer(){
        return this.answers[answers.length-1];
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
