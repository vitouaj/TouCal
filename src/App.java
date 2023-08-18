import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public String num1 = "";
    public String num2 = "";
    public String operator = "";

    public boolean onNum1 = true;



    Stage window;
     @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setWidth(500);
        window.setHeight(600);
        window.setResizable(false);

        window.setTitle("calculator");

        TextField input = new TextField();
        input.setStyle("-fx-font-size: 20px;"); // Set text size using CSS
        input.setPrefSize(500, 40);
        List<Button> bList = new ArrayList<>();


        for (int i = 0; i <10; i++) {
            Button b = new Button(String.valueOf(i));
            b.setMinSize(80, 60);
            b.setOnAction(e -> {

                if (onNum1) {
                    num1 += b.getText();
                    input.setText(num1);
                } else {
                    num2 += b.getText();
                    input.setText(num2);
                }
                
                
                
            });
            bList.add(b);
        }

        String[] ops = {"+", "-", "x", "/"};
        for (var op : ops) {
            Button o = new Button(op);
            o.setMinSize(80, 60);
            o.setOnAction(e-> {
                operator = o.getText();
                input.clear();
                onNum1 = !onNum1;
            });
            bList.add(o);
        }





        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(12);
        flowPane.setVgap(12);
        flowPane.setPrefWrapLength(400); // Preferred width for wrapping

        // Add buttons to the flow pane
        flowPane.getChildren().add(input);
        flowPane.getChildren().addAll(bList);

        Button equal = new Button("=");
        equal.setMinSize(80, 60);

        equal.setOnAction(e-> {
            double result = calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
            input.setText(String.valueOf(result));
        });

        Button ccbtn = new Button("CC");
        ccbtn.setMinSize(80, 60);

        ccbtn.setOnAction(e-> {
            num1 = "";
            num2 = "";
            operator = "";
            onNum1 = true;
            input.clear();
        });

        flowPane.getChildren().add(ccbtn);
        flowPane.getChildren().add(equal);



        Scene scene = new Scene(flowPane);

        window.setScene(scene);
        window.show();
    }

    public double calculate(double num1, double num2, String op) {
        Calculator calculator = new Calculator();
        if (op == "+") {
            return calculator.add(num1, num2);
        } else if (op == "-") {
            return calculator.sub(num1, num2);
        } else if (op == "x") {
            return calculator.mul(num1, num2);
        } else if (op == "/") {
            return calculator.div(num1, num2);
        }
        return 0;
    }

}
