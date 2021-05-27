package sample.Controllers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class NewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label chooseLabel;

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

    private static final String FILE_NAME_NEW = "Test4.xml";

    private int currentQuestion = 0, correctAnswer;

    private String currentCorrectAnswer;

    private Question[] questions = new Question[] {
            new Question("What is a correct syntax to output \"Hello World\" in Java?", new String[]{
                    "print(\"Hello World\")","echo(\"Hello World\")","Console.WriteLine(\"Hello World\");", "System.out.println(\"Hello World\");"}),
            new Question("How do you create a variable with the floating number 2.8?", new String[]{
                    "x = 2.8f;", "byte x = 2.8f;", "float x = 2.8;","float x = 2.8f;"}),
            new Question("Which method can be used to return a string in upper case letters?", new String[]{
                    "tuc", "UpperCase", "touppercase", "toUpperCase"}),
            new Question("To declare an array in Java, define the variable type with:", new String[]{
                    "{}", "()", "None of this signs", "[]"}),
            new Question("What is the parent of Error and Exception classes?", new String[]{
                    "MainError", "MainException", "Catchable", "Throwable"}),
            new Question("How to declare class in program?", new String[]{
                    "new class MyClass {}", "MyClass extends Class {}", "select * from class MyClass {}", "class MyClass {}"}),
            new Question("How do you start writing an if statement in Java?", new String[]{
                    "if x > y then:", "if x > y:", "(x > y) if", "if (x > y)"}),
            new Question("Which method can be used to find the highest value of x and y?", new String[]{
                    "Math.maxNum(x,y)", "Math.maximum(x,y)", "Math.largest(x,y)", "Math.max(x,y)"}),
            new Question("What is the correct way to create an object called myObj of MyClass?", new String[]{
                    "class MyClass = new myObj();", "new myObj = MyClass();", "class myObj = new MyClass();", "MyClass myObj = new MyClass();"}),
            new Question("Which operator can be used to compare two values?", new String[]{
                    "=", "<>", "><","=="}),
            new Question("Which data type is used to create a variable that should store text?", new String[]{
                    "myString", "str", "Txt", "string"}),
            new Question("How do you create a variable with the numeric value 5?", new String[]{
                    "num x = 5", "x = 5;", "double x = 5;", "int x = 5;"}),
            new Question("Which property can be used to find the length of a string?", new String[]{
                    "length", "length()", "getLength()", "Length"}),
            new Question("Which keyword is used to create a class in C#?", new String[]{
                    "className", "MyClass", "class()", "class"}),
            new Question("How do you create a method in C#?", new String[]{
                    "MyMethod.", "myMethod[]", "(MyMethod)", "MyMethod()"}),
            new Question("How do you start writing a while loop in C#?", new String[]{
                    "while x > y {", "x > y while {", "while x > y:", "while (x > y)"}),
            new Question("Which statement is used to stop a loop?", new String[]{
                    "return", "stop", "exit", "break"}),
            new Question("Which keyword is used to return a value inside a method?", new String[]{
                    "get", "void", "break", "return"}),
            new Question("OOP is short for ...", new String[]{
                    "Only oriented programming","Only object program","Orient-objective programming", "Object-oriented programming"}),
            new Question("Constructors are used to: ", new String[]{
                    "To build a user interface", "To create a sub class", "Free memory","Initialize a newly created object"}),
            new Question("Which keyword is used to access the method from the superclass?", new String[]{
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
                    "Polymorphism", "Abstraction", "Encapsulation", "Inheritance"})
    };

    @FXML
    void initialize() throws Exception {
        serialize(questions);
        deserialize();
        csharpButton.setVisible(false);
        javaButton.setVisible(false);
        oopButton.setVisible(false);
        newButton.setVisible(false);
        chooseLabel.setText("Good luck!");
        labelHidden.setVisible(false);
        List<Question> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);
        currentCorrectAnswer = questions[currentQuestion].correctAnswer();
        answerButton.setOnAction(event -> {
            RadioButton selectedRadio = (RadioButton) answers.getSelectedToggle();
            if (selectedRadio == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please, choose any option");
                alert.showAndWait();
                selectedRadio.setSelected(true);
            }else {
                String toggleGroupValue = selectedRadio.getText();
                if (toggleGroupValue.equals(currentCorrectAnswer)) correctAnswer++;
            }
            if (currentQuestion + 1 != questions.length){
                currentQuestion++;
                currentCorrectAnswer = questions[currentQuestion].correctAnswer();
                labelQuestion.setText(questions[currentQuestion].getName());
                String [] answers = questions[currentQuestion].getAnswers();
                List<String> stringList = Arrays.asList(answers);
                Collections.shuffle(stringList);
                radioButton1.setText(stringList.get(0));
                radioButton2.setText(stringList.get(1));
                radioButton3.setText(stringList.get(2));
                radioButton4.setText(stringList.get(3));
                selectedRadio.setSelected(false);
            } else {
                hideAllControls();
                labelQuestion.setText("You answered " + correctAnswer + " questions");
                if (correctAnswer <= 10){
                    labelHidden.setText("Sorry, you should improve your skills");
                    labelHidden.setVisible(true);
                }
                else if (correctAnswer > 10 && correctAnswer <= 20){
                    labelHidden.setText("You have good knowledge of programming");
                    labelHidden.setVisible(true);
                }
                else if (correctAnswer > 20){
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
                newButton.getScene().getWindow().hide();
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/sample/UI/sampleMain.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Simple Testing");
                stage.setScene(new Scene(root));
                stage.show();
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
        radioButton1.setVisible(false);
        radioButton2.setVisible(false);
        radioButton3.setVisible(false);
        radioButton4.setVisible(false);
        answerButton.setVisible(false);
    }
    public void serialize(Question[] questions) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME_NEW);
        XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
        xmlEncoder.writeObject(questions);
        xmlEncoder.close();
        fileOutputStream.close();
    }
    public Question[] deserialize() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(FILE_NAME_NEW);
        XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(fileInputStream));
        xmlDecoder.readObject();
        xmlDecoder.close();
        fileInputStream.close();
        return questions;
    }
}