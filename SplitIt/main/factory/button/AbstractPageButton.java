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
//          |   AbstractPageButton.java - by Ziming He        |
//          |_________________________________________________|

package main.factory.button;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Controller;
import main.entity.Project;
import main.page.ShowPageInfo;

public abstract class AbstractPageButton implements Button{

    //the button of a current stage
    protected Stage stage;

    private Project project;

    public AbstractPageButton(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Pane createButton() {

        VBox button = new VBox();
        button.setSpacing(10);
        button.setAlignment(Pos.CENTER);

        javafx.scene.control.Button nextButton = new javafx.scene.control.Button("Next");
        nextButton.setAlignment(Pos.CENTER);
        nextButton.setMinWidth(200);
        nextButton.setDefaultButton(true);

        nextButton.disableProperty().bind(getBind());
        nextButton.setOnAction(event -> {
            //To the next page
            nextPage();
        });

        //Main meue button
        javafx.scene.control.Button menuButton = new javafx.scene.control.Button("Main Menu");
        menuButton.setAlignment(Pos.CENTER);
        menuButton.setMinWidth(200);
        menuButton.setDefaultButton(true);

        menuButton.setOnAction(event -> {
            try {
                new Controller().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        button.getChildren().addAll(nextButton, menuButton);

        return button;
    }

    // To see if the input is corresponding to the rule that we set.
    public abstract BooleanBinding getBind();

    
     //To set a platform which appears after clicking on the button
         public abstract void nextPage();

}
