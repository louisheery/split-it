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
//          |   PageFactory.java - by Louis Heery             |
//          |_________________________________________________|

package main.factory;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface PageFactory {

    Pane createButton();

    Pane createBody();

    Pane createHead();

    void show();

}
