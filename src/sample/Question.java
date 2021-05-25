package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String[] answers;
    public Question(String name, String[] answers) {
        this.name = name;
        this.answers = answers;
    }
    public Question(){
        this.name = "Some question";
        this.answers = null;

    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getAnswers() {
        return this.answers;
    }
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
    //правильный ответ - последний элмент массива
    public String correctAnswer(){
        return this.answers[answers.length-1];
    }

}
