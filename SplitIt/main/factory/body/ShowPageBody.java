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
//          |   ShowPageBody.java - by Ziming He              |
//          |_________________________________________________|

package main.factory.body;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ShowPageBody implements Body{

    private Label isText;//Create a reminder for project name.

    private  TextField inputName;// TextField for project name

    @Override
    public Pane createBody() {

        HBox body = new HBox();
        body.setSpacing(10);
        body.setAlignment(Pos.CENTER);

        //Make a Label and set its postion also its size
        Label name = new Label("Enter Project Name: ");
        name.setMaxWidth(200);

        inputName = new TextField();
        inputName.setMaxWidth(400);

        isText = new Label("");
        isText.setMinWidth(150);

        body.getChildren().addAll(name,inputName,isText);

        return body;
    }

    public Label getIsText() {
        return isText;
    }

    public void setIsText(Label isText) {
        this.isText = isText;
    }

    public TextField getInputName() {
        return inputName;
    }

    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }
}
