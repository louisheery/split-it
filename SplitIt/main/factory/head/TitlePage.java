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
//          |   TitlePage.java - by Louis Heery               |
//          |_________________________________________________|

package main.factory.head;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitlePage implements Head{

    //Title
    private String Title;
    //Subtitle
    private String Subtitle;

    public TitlePage() {
    }

    public TitlePage(String title, String subtitle) {
        Title = title;
        Subtitle = subtitle;
    }

    @Override
    public Pane createHead() {

        VBox head = new VBox();
        head.setSpacing(10);
        head.setAlignment(Pos.CENTER);

        //Set Title
        Label title = new Label(Title);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setMinWidth(200);

        //Set Subtitle
        Label subtitle = new Label(Subtitle);
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        subtitle.setMinWidth(200);

        head.getChildren().addAll(title,subtitle);
        return head;
    }

    public String getTitle() {
        return Title;
    }

    public TitlePage setTitle(String title) {
        Title = title;
        return this;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public TitlePage setSubtitle(String subtitle) {
        Subtitle = subtitle;
        return this;
    }
}
