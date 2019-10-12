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
//          |   CreateProject.java - by Louis Heery           |
//          |_________________________________________________|

// Import relavent libraries.
package main;

import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.Controller;
import main.entity.Project;
import main.file.FileController;
import main.entity.ProjectSon;
import main.entity.Votes;
import main.page.ProjectInfo;


public class CreateProject {

    public static void createProjectStage(){
        Stage stage = new Stage();

        stage.setTitle("Split-It - Create Project"); // Set title of the window.

        //Title text Using VBox layout to display buttons

         VBox mainBox = new VBox(50);
         mainBox.setAlignment(Pos.CENTER);
         mainBox.setMaxWidth(700);

         // Add create project title
        Label title = new Label("Create Project");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label subtitleLabel = new Label("Please enter the project details below:");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        mainBox.getChildren().add(title);
        mainBox.getChildren().add(subtitleLabel);


        //Project name label
        Label createpageHeaderName = new Label("Project Name:");
        //Number of participants label
        Label createpageHeaderNumber = new Label("Number of Participants:");

        //The size of Text field for Project name to be entered into
        TextField nameInput = new TextField();
        nameInput.setMinSize(300,20);


        //The size of Text field which user can enter the "Number of participants"
        TextField numberInput = new TextField();
        numberInput.setMinSize(300,20);

        Label isText = new Label();
        isText.setMinWidth(200);
        Label isNumber = new Label();
        isNumber.setMinWidth(200);

        // Layout of the "Create project" page, which defines the spacing of each grid which elements are arranged in
        GridPane input = new GridPane();
        input.setHgap(10);
        input.setVgap(10);
        input.setAlignment(Pos.CENTER);

        //when doing the coding , we use it to test the position of grid.
        input.setGridLinesVisible(false);
        //add all the visual GUI elements to the page and sets their position in the GUI window.
        input.add(createpageHeaderName,0,0);
        input.add(createpageHeaderNumber,0,1);
        input.add(nameInput,1,0);
        input.add(numberInput,1,1);
        input.add(isText,2,0);
        input.add(isNumber,2,1);
        mainBox.getChildren().add(input);

        // Adds a next button to allow user to go to next page where they can enter participant names.
        Button nextButton = new Button("Next");
        nextButton.setDefaultButton(true);
        nextButton.setMinWidth(200);

        // This booleanBinding requires that only if the conditions are met can the user click the button and proceed to the next step.
        // These conditions are that: The project name isn't blank, and the project make doesn't only contain numbers.
        // Only when all these conditions are satisfied can the user go to the next step.
        BooleanBinding isTrue = new BooleanBinding() {
            {
                super.bind(nameInput.textProperty());
                super.bind(numberInput.textProperty());
            }
            @Override
            protected boolean computeValue() {
                try {
                    // Warning message which allears if the user has entered only numbers into the project name textField.
                    Integer.parseInt(nameInput.getText());
                    isText.setText("Project name must contain letters");
                    return true;
                }catch (Exception e){

                    if ("".equals(nameInput.getText().trim())){
                        // Warning message which allears if the user hasn't entered any text into the Textfield.
                        isText.setText("The project can't be empty");
                        return true;
                    }
                    // Error message disappears if the aformentioned conditions are satisfied.
                    isText.setText("");
                }

                try {
                    // Error message is hidden if numbers are entered.
                    Integer.parseInt(numberInput.getText());
                    isNumber.setText("");
                    return false;
                }catch (Exception e){
                    // Error message which is displayed if the user hasn't entered a number of participants.
                    isNumber.setText("Please enter a number");
                    return true;
                }
            }
        };
        nextButton.disableProperty().bind(isTrue);

        // Warning Message to detect if User is overwriting an Existing project
        nextButton.setOnAction(event -> {
            boolean type = true;
            try {
                ArrayList<Project> projectsName = FileController.ToJava();
                if (projectsName != null) {
                    for (int i = 0; i < projectsName.size(); i++) {
                        if (projectsName.get(i).getName().equals(nameInput.getText().replaceAll("\\s+",""))) {
                            Alert alertName = new Alert(Alert.AlertType.WARNING, "", ButtonType.YES, ButtonType.NO);  //new alert object
                            alertName.setTitle("Warning!");  //warning box title
                            alertName.setHeaderText("WARNING");// Header
                            alertName.setContentText("A project with this name already exists. Do you want to overwrite the existing project?"); //Discription of warning
                            alertName.getDialogPane().setPrefSize(300, 200); //sets size of alert box
                            Optional<ButtonType> result = alertName.showAndWait();
                            if (result.get() == ButtonType.YES) {
                                projectsName.remove(i);
                                FileController.toTxt(projectsName);
                                ProjectInfo.setProjectInfo(nameInput.getText(), Integer.parseInt(numberInput.getText()));
                                stage.close();
                            } else {
                                // ... user chose NO or closed the dialog
                            }
                            type = false;
                            break;
                        }
                    }
                }
            }catch (Exception E){

            }
                if(type){
                    ProjectInfo.setProjectInfo(nameInput.getText().replaceAll("\\s+",""),Integer.parseInt(numberInput.getText()));
                    stage.close();
                }
        });

        //Back Button which returns user to the main menu
        Button aboutButton = new Button("Main Menu");
        aboutButton.setDefaultButton(true);
        aboutButton.setMinWidth(200);

        //back to the main menu
        aboutButton.setOnAction(event -> {
            try {
                new Controller().start(stage);
            } catch (Exception e) {
                e.printStackTrace();// To catch error,and print Stack Trace
            }
        });

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(nextButton,aboutButton);

        mainBox.getChildren().add(buttonBox);

    //Create Stage for this scene.
        Scene scene = new Scene(mainBox,800,500);
        stage.setScene(scene);
        stage.show();
    }

}
