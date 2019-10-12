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
//          |   Page.java - by Ziming He                      |
//          |_________________________________________________|

package main.page.basic;

import javafx.scene.layout.Pane;

public interface Page {
    
    //creating the head information of this page.
    Pane createHead();
    
    // creating the content of this page
    Pane createBody();



}
