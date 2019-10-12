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
//          |   main.page/VotesInfo.java - by Louis Heery     |
//          |_________________________________________________|

// Import relavent libraries.
package main.page;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.entity.Project;
import main.entity.ProjectSon;
import main.entity.Votes;
import main.file.FileController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class VotesInfo {

    public static VBox createVotes(String sonName, Project project) {

        VBox mainBox = new VBox(20);

       mainBox.setMaxWidth(700);

        // Beginning id = 0
        String str = "\nEnter the votes from " + sonName + ":";
        Label title = new Label(str);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setMinWidth(200);
        mainBox.getChildren().add(title);

        // Next id = 1
        GridPane addVotesInfo = new GridPane();
        addVotesInfo.setHgap(10);
        addVotesInfo.setVgap(10);

        // Assigns the getSons method of the project object to a new ArrayList called 'sons'
        ArrayList<ProjectSon> sons = project.getSons();


        // For loop which runs over all values of i upto the number of 'sons' of the project (i.e. each value)
        int temp = 0;
        for (int i = 0; i < sons.size(); i++) {

            // Find all names except for the name of himself/herself
            if (!sons.get(i).getText().equals(sonName)) {
                Label text = new Label("For " + sons.get(i).getText() + " :");
                // Label text = new Label("Points given to " + sons.get(i).getText() + ":");
                text.setMinWidth(250);
                TextField input = new TextField("");
                input.setMaxWidth(200);
                Label isTrue = new Label("");
                isTrue.setMinWidth(200);
                addVotesInfo.add(text, 0, temp);
                addVotesInfo.add(input, 1, temp);
                addVotesInfo.add(isTrue, 2, temp);
                temp++;
            }

        }

        // Adds the addVotesInfo to the main GUI window

        mainBox.getChildren().addAll(addVotesInfo);

        return mainBox;
    }
}
