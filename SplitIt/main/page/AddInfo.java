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
//          |   main.page/AddInfo.java - by Louis Heery       |
//          |_________________________________________________|

// Import relavent libraries.
package main.page;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

// Method which allows user to enter the names of each participant.
// This method can be run as many times as required, and will be run upto the number of
// participants in the project.
public class AddInfo {

    public static HBox setInfo(int id){
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        // Outputting label which says which participant numebr the user should enter the name of.
        Label pr = new Label("\tName of Participant " + id + ": ");
        pr.setMinWidth(160);

        // Setting size of the text field and label.
        TextField input = new TextField();
        input.setMinWidth(200);

        Label isText = new Label();
        isText.setMinWidth(160);

        hBox.getChildren().addAll(pr,input,isText);
        return hBox;
    }
}
