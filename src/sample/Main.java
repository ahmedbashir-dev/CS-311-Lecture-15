package sample;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Scanner;

public class Main extends Application {

    private RadioButton redRb, greenRb, blueRb;
    private FlowPane root;
    private ColorPicker colorPicker;
    private DatePicker dobPicker;
    @Override
    public void start(Stage primaryStage) throws Exception{

        root = new FlowPane();
        colorPicker = new ColorPicker();
        dobPicker = new DatePicker();
        redRb = new RadioButton("Red");
        RadioButtonHandler rbh = new RadioButtonHandler();
        redRb.setOnAction(rbh);
        greenRb = new RadioButton("Green");
        greenRb.setOnAction(rbh);
        blueRb = new RadioButton("Blue");
        blueRb.setOnAction(rbh);
        blueRb.setSelected(true);
        colorPicker.setOnAction(new ColorPickerHandler());
        ToggleGroup toggleGroup = new ToggleGroup();
        redRb.setToggleGroup(toggleGroup);
        greenRb.setToggleGroup(toggleGroup);
        blueRb.setToggleGroup(toggleGroup);
        root.getChildren().addAll(redRb,greenRb,blueRb,colorPicker,dobPicker);
        dobPicker.setEditable(false);
        dobPicker.setOnAction(e->{
            LocalDate ld = dobPicker.getValue();
            String date = dobPicker.getValue().toString();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Date of Birth");
            alert.setTitle("User DOB");
            alert.setContentText(date);
            alert.showAndWait();
        });
        root.setHgap(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Background Demo");
        primaryStage.show();

    }

    private class RadioButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent e) {
            RadioButton sRb = (RadioButton) e.getSource();
            if (sRb == redRb){
                BackgroundFill fill = new BackgroundFill(Color.RED, CornerRadii.EMPTY,Insets.EMPTY);
                Background bg = new Background(fill);
                root.setBackground(bg);
            }
            else if(sRb == greenRb){
                root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY,Insets.EMPTY)));
            }
            else if (sRb == blueRb){
                root.setBackground(new Background(new BackgroundFill(Color.BLUE,CornerRadii.EMPTY,Insets.EMPTY)));
            }

        }
    }

    private class ColorPickerHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Color c = colorPicker.getValue();
            root.setBackground(new Background(new BackgroundFill(c,CornerRadii.EMPTY,Insets.EMPTY)));

        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
