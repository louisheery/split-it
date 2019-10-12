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
//          |   main.page/AddVotes.java - by Ziming He        |
//          |_________________________________________________|

// Import relavent libraries.
package main.page;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Controller;
import main.entity.Project;
import main.entity.Votes;
import main.file.FileController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class AddVotes {

    public static void votes(String projectName) {
        Stage stage = new Stage();

        stage.setTitle("Split-It - Enter Votes");

        /*
        when entering the project name, it will automatically search how many people
        inside the project, and the user will need to enter the votes for each member.
        */

        // First the programme will get the Project's information and properties from the txt file
        try {
            // Extract the names of all the projects from the Txt file and store them as an array called 'projects'
            ArrayList<Project> projects = FileController.ToJava();
            //Get the exact Project information
            Project project = null;
            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getName().equals(projectName)) {
                    project = projects.get(i);
                    break;
                }
            }

            // Two seperates blocks will be generated: The votes in 1 blocks and the button in the other block
            VBox mainBox = new VBox(50);
            mainBox.setMaxWidth(700); // louishhh
            mainBox.setAlignment(Pos.CENTER);

            Label title = new Label("Enter Votes");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

            Label subtitle = new Label("Please enter the project votes for the " + project.getSize() + " team members");
            subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));


            mainBox.getChildren().addAll(title, subtitle);


            VBox votesBox = new VBox(10);


            System.out.println("project size " + project.getSize());
            for (int i = 0; i < project.getSize(); i++) {
                votesBox.getChildren().add(VotesInfo.createVotes(project.getSons().get(i).getText(), project));
            }

            VBox spBox = new VBox();
            spBox.setAlignment(Pos.CENTER);
            spBox.setMaxWidth(700);

            //Sets the position of the ScrollBar
            ScrollPane sp = new ScrollPane();
            sp.setPrefViewportWidth(50);
            sp.setVmax(400);
            sp.setPrefSize(600, 200);
            sp.setContent(votesBox);
            sp.setStyle("-fx-background-color:transparent;");

            spBox.getChildren().add(sp);
            mainBox.getChildren().add(spBox);

            // Adds the 'Save Votes' button
            VBox buttonBox = new VBox(10);
            buttonBox.setAlignment(Pos.CENTER);
            Button save = new Button("Save Votes");
            save.setDefaultButton(true);
            save.setMinWidth(200);

            // Sets the "finalProject" object to the aformentioned "project" objects properties
            Project finalProject = project;


            /* BooleanBinding which will generate the required number of Votes TextFields for the user to enter
             their votes into. And then it will save these values to the TXT file.
             */
            BooleanBinding isTrue = new BooleanBinding() {
                {
                    for (int i = 0; i < votesBox.getChildren().size(); i++) {
                        int gridPaneSize = ((GridPane) ((VBox) votesBox.getChildren().get(i)).getChildren().get(1)).getChildren().size();

                        // We need to lock every input textField in place
                        for (int j = 0; j < gridPaneSize; j = j + 3) {
                            super.bind(((TextField) ((GridPane) ((VBox) votesBox
                                    .getChildren().get(i))
                                    .getChildren().get(1))
                                    .getChildren().get(1 + j)).textProperty()
                            );
                        }
                    }
                }

                @Override
                protected boolean computeValue() {

                    boolean flag;
                    String[] flagsets = new String[votesBox.getChildren().size()];
                    for (int i = 0; i <flagsets.length ; i++) {
                        flagsets[i] = "true";
                    }

                    int mapLength = ((GridPane) ((VBox) votesBox
                            .getChildren().get(0)).getChildren().get(1)).getChildren().size();

                    Map<Integer,Boolean> check = new HashMap<>();
                    for (int i = 0; i <mapLength ; i++) {
                        check.put(i,false);
                    }

                    try {
                        for (int i = 0; i < votesBox.getChildren().size(); i++) {
                            int gridPaneSize = ((GridPane) ((VBox) votesBox
                                    .getChildren().get(i)).getChildren().get(1)).getChildren().size();

                            int temp = 0;
                            int votes = 0;
                            int value = 0;
                            String inputText = "";

                            System.out.println("****************** "+ i +" *******************");

                            // Test each input field to check the textField isn't blank
                            // Also checks whether the textFields add up to 100; else error message is displayed.
                            for (int j = 0; j < gridPaneSize; j = j + 3) {
                                try {
                                    inputText = ((TextField) ((GridPane) ((VBox) votesBox
                                            .getChildren().get(i))
                                            .getChildren().get(1))
                                            .getChildren().get(1 + j)).getText();

                                    value = Integer.parseInt(inputText);

                                    temp += value;

                                    votes++;

                                    // make sure that every input field meets the requirement 
                                    if (value != 0 && !"".equals(inputText)) {
                                        check.put(j+i,true);
                                    }else {
                                        check.put(j+i,false);
                                    }

                                    ((Label) ((GridPane) ((VBox) votesBox
                                            .getChildren().get(i))
                                            .getChildren().get(1))
                                            .getChildren().get(j + 2)).setText("");
                                } catch (NumberFormatException e) {
                                    System.out.println(e + " " + (j + 2));
                                    ((Label) ((GridPane) ((VBox) votesBox
                                            .getChildren().get(i))
                                            .getChildren().get(1))
                                            .getChildren().get(j + 2)).setText("Sum of Number must be 100 ");
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }

                            }

                            System.out.println("******display Map");
                            for (Map.Entry<Integer,Boolean> v : check.entrySet()) {
                                System.out.println("key " + v.getKey() + " value " + v.getValue());
                            }
                            System.out.println(" contains false " + check.containsValue(false));
                            System.out.println("******display Map");

                            // just to check whether flagsets[] contains true.
                            if (temp == 100 && checkInputTextMap(i,gridPaneSize,check)) {
                                ((Label) ((GridPane) ((VBox) votesBox
                                        .getChildren().get(i))
                                        .getChildren().get(1))
                                        .getChildren().get(2)).setText("");
                                flagsets[i] = "false";
                            }else{
                                ((Label) ((GridPane) ((VBox) votesBox
                                        .getChildren().get(i))
                                        .getChildren().get(1))
                                        .getChildren().get(2)).setText("no space & no 0 ");
                            }
                        }
                    } catch (Exception e) {
                        // Flagged error displays which error is detected and prints it into the Console.
                        flag = true;
                        System.out.println("Encounter error " + e);
                    }
                    System.out.println(Arrays.toString(flagsets));
                    if (Arrays.asList(flagsets).contains("true")) {
                        flag = true;
                    } else {
                        flag = false;
                    }

                    return flag;
                }
            };
            save.disableProperty().bind(isTrue);
            save.setOnAction(event -> {

                // First reads all the Project names from the TXT file to find the one which the user requires -> and updates it
                for (int i = 0; i < votesBox.getChildren().size(); i++) {
                    int gridPaneSize = ((GridPane) ((VBox) votesBox.getChildren().get(i)).getChildren().get(1)).getChildren().size();

                    int temp = 0;
                    int votes = 0;
                    int value;
                    // Test the output of each value
                    for (int j = 0; j < gridPaneSize; j = j + 3) {
                        try {
                            value = Integer.parseInt(((TextField) ((GridPane) ((VBox) votesBox
                                    .getChildren().get(i))
                                    .getChildren().get(1))
                                    .getChildren().get(1 + j)).getText());

                            temp += value;
                            //Sets each person's votes & saves
                            String str = ((Label) ((GridPane) ((VBox) votesBox
                                    .getChildren().get(i))
                                    .getChildren().get(1))
                                    .getChildren().get(j)).getText();

                            int votesNameIndexStart = 4;
                            int votesNameIndexEnd = str.lastIndexOf(":");
                            String votesName = str.substring(votesNameIndexStart, votesNameIndexEnd);

                            Votes votes1 = new Votes(votesName, value);
                            finalProject.getSons().get(i).addVotes(votes1);


                            votes++;

                            ((Label) ((GridPane) ((VBox) votesBox
                                    .getChildren().get(i))
                                    .getChildren().get(1))
                                    .getChildren().get(j + 2)).setText("");
                        } catch (NumberFormatException e) {
                            // Subsequent error message which detects if user's votes don't add up to 100.
                            System.out.println(e + " " + (j + 2));

                            ((Label) ((GridPane) ((VBox) votesBox
                                    .getChildren().get(i))
                                    .getChildren().get(1))
                                    .getChildren().get(j + 2)).setText("enter number and add up to 100");
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }

                    }
                }

                //Reads the project and updates the project object we are saving data to
                for (int i = 0; i < projects.size(); i++) {
                    if (projects.get(i).getName().equals(projectName)) {
                        projects.remove(i);
                        projects.add(finalProject);
                        break;
                    }
                }

                // Saves the project to the TXT File.
                try {
                    FileController.toTxt(projects);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
                    alert.show();
                    new Controller().start(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                System.out.println("in");
            });

            // Adds main menu button & listener setOnAction method
            Button menu = new Button("Main Menu");
            menu.setDefaultButton(true);
            menu.setMinWidth(200);
            menu.setOnAction(event -> {
                try {
                    new Controller().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            buttonBox.getChildren().add(save);
            buttonBox.getChildren().add(menu);

            mainBox.getChildren().add(buttonBox);

            // Defines the size of the GUI window
            Scene scene = new Scene(mainBox, 800, 500);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

     //Check if the input meets with the rule.
     //if any filed does not meet with the rule then return false, otherwise return true.
    public static boolean checkInputTextMap(int index, int gridPaneSize, Map<Integer, Boolean> map){
        for (int i = index; i <gridPaneSize ; i = i+3) {
            if (!map.get(i))
                return false;
        }
        return true;
    }

}
