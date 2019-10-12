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
//          |   ShowPageFactory.java - by Louis Heery         |
//          |_________________________________________________|

package main.factory;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.factory.body.ShowPageBody;
import main.factory.button.ShowPageButton;
import main.factory.head.TitlePage;

public class ShowPageFactory extends AbstractPageFactory {

    public ShowPageFactory(Stage stage) {
        super(stage);
    }

    @Override
    public Pane createButton() {
        ShowPageBody body = (ShowPageBody) getBody();
        button = new ShowPageButton(stage, body.getInputName(), body.getIsText());
        return button.createButton();
    }

    @Override
    public Pane createBody() {
        setBody(new ShowPageBody());
        return getBody().createBody();
    }

    @Override
    public Pane createHead() {
        setHead(new TitlePage()
                .setSubtitle("Which Project would you like to view?\n\n\n\n\n")
                .setTitle("Show Project"));
        return getHead().createHead();
    }

}
