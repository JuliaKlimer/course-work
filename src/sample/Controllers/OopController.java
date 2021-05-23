package sample.Controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Question;

public class OopController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button csharpButton;

    @FXML
    private Button javaButton;

    @FXML
    private Button oopButton;

    @FXML
    private Button newButton;

    @FXML
    private Label labelQuestion;

    @FXML
    private Label chooseLabel;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private ToggleGroup answers;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    @FXML
    private Button answerButton;

    @FXML
    private Button mainButton;

    @FXML
    private Label labelHidden;

    private Question[] questions = new Question[] {
            new Question("OOP is short for ...", new String[]{
                    "Only oriented programming","Only object program","Orient-objective programming", "Object-oriented programming"}),
            new Question("Constructors are used to: ", new String[]{
                    "To build a user interface", "To create a sub class", "Free memory","Initialize a newly created object"}),
            new Question("Which keyword is used to access the method or member variables from the superclass?", new String[]{
                    "class", "use", "this", "super"}),
            new Question("Information Hiding can also be termed as ...", new String[]{
                    "Inheritance", "Data hiding", "Abstraction", "Encapsulation"}),
            new Question("What are the main OOP principles?", new String[]{
                    "Encapsulation and Abstraction", "Inheritance and Encapsulation", "Polymorphism, Encapsulation and Inheritance", "Encapsulation, Inheritance, Polymorphism and Abstraction"}),
            new Question("How can you call a class which cannot be instantiated?", new String[]{
                    "Both this names are wrong", "You can use both names", "Uninstallable class", "Abstract class"}),
            new Question("Name of the operator which takes tree arguments is ...", new String[]{
                    "Such kind of operator doesn't exist", "Universal operator", "Binary operator", "Ternary operator"}),
            new Question("What is ‘this’ pointer?", new String[]{
                    "\'This\' pointer doesn't exist", "\'This\' pointer refers to the current method", "\'This\' pointer refers to the current class", "\'This\' pointer refers to the current object of a class"}),
            new Question("Which OOPS concept is used as a reuse mechanism?", new String[]{
                    "Polymorphism", "Abstraction", "Encapsulation", "Inheritance"}),
            new Question("What is the correct way to create an object called myObj of MyClass (in Java/C#)?", new String[]{
                    "class MyClass = new myObj();", "new myObj = MyClass();", "class myObj = new MyClass();", "MyClass myObj = new MyClass();"})
    };

    private int currentQuestion = 0, correctAnswer;
    private String currentCorrectAnswer;

    @FXML
    void initialize() {
        csharpButton.setVisible(false);
        javaButton.setVisible(false);
        oopButton.setVisible(false);
        newButton.setVisible(false);
        labelHidden.setVisible(false);
        chooseLabel.setText("Good luck!");
        currentCorrectAnswer = questions[currentQuestion].correctAnswer();
        answerButton.setOnAction(event -> {
            //кнопка, выбранная пользователем
            RadioButton selectedRadio = (RadioButton) answers.getSelectedToggle();
            if (selectedRadio == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please, choose any option");
                alert.showAndWait();
                selectedRadio.setSelected(true);
            }else {
                //получаем текст ответа
                String toggleGroupValue = selectedRadio.getText();
                //проверяем совпадает ли выбранный ответ с правильным
                if (toggleGroupValue.equals(currentCorrectAnswer)) correctAnswer++;
            }
            if (currentQuestion + 1 != questions.length){ //если это не последний вопрос, то у величиваем номер текущего вопроса
                currentQuestion++;
                currentCorrectAnswer = questions[currentQuestion].correctAnswer();//новый номер верного ответа
                labelQuestion.setText(questions[currentQuestion].getName());//меняем текст на новый
                String [] answers = questions[currentQuestion].getAnswers();//получаем массив ответов
                List<String> stringList = Arrays.asList(answers);//преобразовываем в список
                Collections.shuffle(stringList);//сортировка в рандомном порядке
                radioButton1.setText(stringList.get(0));//передаем текст ответов в радиокнопки
                radioButton2.setText(stringList.get(1));
                radioButton3.setText(stringList.get(2));
                radioButton4.setText(stringList.get(3));
                selectedRadio.setSelected(false);//снимаем выделение пользователем
            } else {
                hideAllControls();
                labelQuestion.setText("You answered " + correctAnswer + " questions");//вывод кол-ва ответов, на которые пользователь ответил правильно
                if (correctAnswer <= 3){
                    labelHidden.setText("Sorry, you should improve your skills");
                    labelHidden.setVisible(true);
                }
                else if (correctAnswer > 3 && correctAnswer <= 7){
                    labelHidden.setText("You have good knowledge of OOP");
                    labelHidden.setVisible(true);
                }
                else if (correctAnswer > 7){
                    labelHidden.setText("You can be proud yourself!");
                    labelHidden.setVisible(true);
                }
            }
        });
        mainButton.setOnAction(actionEvent -> {
            if (currentQuestion + 1 != questions.length){
                modalWindow();
            } else {
                try {
                    mainButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/UI/sampleMain.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void modalWindow(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Pane pane = new Pane();
        Button yesButton = new Button("Yes");
        yesButton.setLayoutX(60);
        yesButton.setLayoutY(45);
        Button noButton = new Button("No");
        noButton.setLayoutX(120);
        noButton.setLayoutY(45);
        noButton.setOnAction(event -> window.close());
        yesButton.setOnAction(actionEvent -> {
            try {
                yesButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/UI/sampleMain.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pane.getChildren().addAll(yesButton,noButton);
        Scene scene = new Scene(pane,230,130);
        window.setTitle("Are you sure you want to exit?");
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }
    private void hideAllControls() {
        radioButton1.setVisible(false);//если вопросы закончились, то скрываем кнопки
        radioButton2.setVisible(false);
        radioButton3.setVisible(false);
        radioButton4.setVisible(false);
        answerButton.setVisible(false);
    }
}

