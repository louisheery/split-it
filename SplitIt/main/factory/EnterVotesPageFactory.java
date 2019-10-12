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
//          |   EnterVotesPageFactory.java - by Louis Heery   |
//          |_________________________________________________|

package main.factory;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.factory.body.ShowPageBody;
import main.factory.button.EnterVotesPageButton;
import main.factory.head.TitlePage;


public class EnterVotesPageFactory extends AbstractPageFactory{

    public EnterVotesPageFactory(Stage stage) {
        super(stage);
    }

    @Override
    public Pane createButton() {
        ShowPageBody body = (ShowPageBody) getBody();
        button = new EnterVotesPageButton(stage, body.getInputName(), body.getIsText());
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
                .setSubtitle("Please enter the name of the project you want to enter votes for:")
                .setTitle("Enter Votes"));
        return getHead().createHead();
    }

}
