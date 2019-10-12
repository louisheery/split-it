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
//          |   AbstractPageFactory.java - by Louis Heery     |
//          |_________________________________________________|

package main.factory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.factory.body.Body;
import main.factory.button.Button;
import main.factory.head.Head;


public abstract class AbstractPageFactory implements PageFactory{

    protected Stage stage;

    private Body body;

    protected Button button;

    private Head head;

    public AbstractPageFactory(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Pane pane){
        Scene scene = new Scene(pane,800,500);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void show() {
        VBox main = new VBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(20);
        main.getChildren().addAll(createHead(),createBody(),createButton());
        setScene(main);
    }

    Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }
}
