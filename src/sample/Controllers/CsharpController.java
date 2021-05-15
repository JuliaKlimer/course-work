package sample.Controllers;

import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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

    @FXML
    private Label labelHidden;
    //масссив вопросов и ответов
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
                    "get", "void", "break", "return"})
    };
    //номер текущего вопроса, кол-во правильных ответов
    private int currentQuestion = 0, correctAnswer;
    //текущий правильный ответ
    private String currentCorrectAnswer;

    @FXML
    void initialize() {
        labelHidden.setVisible(false);
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
                    labelHidden.setText("Good");
                    labelHidden.setVisible(true);
                }
                else if (correctAnswer > 7){
                    labelHidden.setText("You have excellent knowledge of C#");
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
    private static void modalWindow(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Pane pane = new Pane();
        Label alertLabel = new Label();
        alertLabel.setText("Are you sure you want to exit?");
        alertLabel.setVisible(true);
        Button yesButton = new Button("Yes");
        yesButton.setLayoutX(60);
        yesButton.setLayoutY(100);
        Button noButton = new Button("No");
        noButton.setLayoutX(120);
        noButton.setLayoutY(100);
        noButton.setOnAction(event -> window.close());
        yesButton.setOnAction(event -> {
            try {
                noButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(CsharpController.class.getResource("/sample/UI/sampleMain.fxml"));
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
        Scene scene = new Scene(pane,200,200);
        window.setTitle("Alert");
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

