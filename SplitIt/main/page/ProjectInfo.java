//           _________________________________________________
//          |   COMP210P Final Project - "Split-It"           |
//          |   Deliverables 3 -- Submitted: 20th April 2018  |
//          |   COMP210P Final Project - "Split-It"           |
//          |    By LOUIS HEERY and ZIMING HE                 |
//          |                                                 |
//          |   Split-It is an Java application that lets     |
//          |   you allocate the marks which each team member |
//          |   should be awarded for a project!              |
//          |                                                 |
//          |   main.page/ProjectInfo.java - by Louis Heery   |
//          |_________________________________________________|

// Import relavent libraries.
package main.page;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Controller;
import main.entity.Project;
import main.entity.ProjectSon;
import main.file.FileController;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class ProjectInfo {


    public static void setProjectInfo(String name, int number) {
        Stage stage = new Stage();

        stage.setTitle("Split-It - Create Project"); // Set title of the window.

        //  Generates a projectSon, which is the sub-object of the main project object which we will assign names & values to
        Project project = new Project(name);
        for (int i = 1; i < number + 1; i++) {
            ProjectSon son = new ProjectSon(i, "");
            project.addSons(son);
        }


        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(700);
        mainBox.setAlignment(Pos.CENTER);

        Label title = new Label("Create Project");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setAlignment(Pos.CENTER);
        Label subtitleLabel = new Label("Please Enter the Names of the Participants:");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        mainBox.getChildren().addAll(title, subtitleLabel);

        //Make a box contain contain Name of participant1, Name of participant2, Name of participant3 etc..
        HBox scro = new HBox();
        scro.setMinWidth(500);
        scro.setAlignment(Pos.CENTER);



//AddInfo is in the main.page
        VBox pro = new VBox(10);
        for (int i = 1; i < number + 1; i++) {
            HBox hBox = AddInfo.setInfo(i);
            pro.getChildren().add(hBox);
        }
        //Add ScrollBar to the window
        ScrollPane sp = new ScrollPane();
        sp.setPrefViewportWidth(50);
        sp.setVmax(440);
        sp.setPrefSize(600, 150);
        sp.setContent(pro);
        sp.setStyle("-fx-background-color:transparent;");
        // Apply ScrollBar to the Box
        scro.getChildren().add(sp);
        mainBox.getChildren().addAll(scro);

        // Get the name of each Participant from the input TextFields
        ArrayList<TextField> inputs = new ArrayList<>();
        ArrayList<Label> texts = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            inputs.add((TextField) (
                    (HBox) pro.getChildren().get(i))
                    .getChildren().get(1));
            texts.add((Label) (
                    (HBox) pro.getChildren().get(i))
                    .getChildren().get(2));
        }
        // Make sure that each textField has had text entered into it
        BooleanBinding isTrue = new BooleanBinding() {
            {
                for (int i = 0; i < number; i++) {
                    super.bind(inputs.get(i).textProperty());
                }
            }

            @Override
            protected boolean computeValue() {

                boolean flag = false;
                boolean right = false;

                for (int i = 0; i < number; i++) {
                    try {
                        for (int j = i - 1 ; j <= i && j >= 0; j--){
                            if(inputs.get(i).getText().equals(inputs.get(j).getText())) {
                                right = true;
                                flag = true;
                            }
                        }
                        if (inputs.get(i).getText().equals("")) {
                            texts.get(i).setText("");
                            flag = true;
                        } else if(right == true){
                            // Error message which is displayed if two participant names are equal to each other.
                            texts.get(i).setText("Name can not be the same");
                        }else {
                            // Error message which is displayed if one of the participant names is a number
                            Integer.parseInt(inputs.get(i).getText());
                            texts.get(i).setText("Name can not be numbers.");
                            flag = true;
                        }
                    } catch (Exception e) {
                        texts.get(i).setText("");
                    }
                }
                return flag;
            }
        };


        // Place Save Project and Main menu button onto the Scene
        VBox buttons = new VBox(10);
        buttons.setAlignment(Pos.CENTER);

        Button save = new Button("Save Project");
        save.setDefaultButton(true);
        save.setMinWidth(200);

        //Save the text that the user enter into the TextFields
        save.disableProperty().bind(isTrue);
        save.setOnAction(event -> {

            ArrayList<ProjectSon> projectSon = project.getSons();
            for (int i = 0; i < number; i++) {
                projectSon.get(i).setText(inputs.get(i).getText());
            }

            //
            try {
                ArrayList<Project> projects = FileController.ToJava();
                if (projects==null){
                    projects = new ArrayList<>();
                    projects.add(project);
                }else {
                    projects.add(project);
                }
                FileController.toTxt(projects);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Make an Alert which tells the user that all the Project Info has been saved
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Added!");
            alert.show();

            try {
                new Controller().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Create a Main Menu button
        Button Menu = new Button("Main Menu");
        Menu.setDefaultButton(true);
        Menu.setMinWidth(200);
        Menu.setOnAction(event -> {
            try {
                new Controller().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttons.getChildren().addAll(save, Menu);

        mainBox.getChildren().addAll(buttons);
       //Show Scene which is inside the Stage.
        Scene scene = new Scene(mainBox, 800, 500);
        stage.setScene(scene);
        stage.show();


    }


}
