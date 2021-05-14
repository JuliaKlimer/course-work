package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.Question;

public class CsharpController {

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

    private Question[] questions = new Question[] {
            new Question("What is a correct syntax to output \"Hello World\" in C#?", new String[]{
                    "print(\"Hello World\")","cout << \"Hello World\";","console.log(\"Hello World\");","Console.WriteLine(\"Hello World\");"}),
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
                    "get", "void", "break", "return"
            })
    };

    private int currentQuestion = 0, correctAnswer;

    private String currentCorrectAnswer;

    @FXML
    void initialize() {
        currentCorrectAnswer = questions[currentQuestion].correctAnswer();
        answerButton.setOnAction(event -> {
            RadioButton selectedRadio = (RadioButton) answers.getSelectedToggle();
            if (selectedRadio != null) {
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
                radioButton1.setVisible(false);
                radioButton2.setVisible(false);
                radioButton3.setVisible(false);
                radioButton4.setVisible(false);
                answerButton.setVisible(false);
                labelQuestion.setText("You answered " + correctAnswer + " questions");
            }
        });

        mainButton.setOnAction(actionEvent -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/UI/sampleMain.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}

