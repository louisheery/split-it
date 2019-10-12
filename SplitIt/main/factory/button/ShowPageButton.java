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
//          |   ShowPageButton.java - by Ziming He            |
//          |_________________________________________________|

package main.factory.button;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entity.Project;
import main.page.ShowPageInfo;
import main.utils.FileUtil;

import java.io.IOException;

public class ShowPageButton extends AbstractPageButton {

    private Project project;

    private TextField inputProjectName;

    private Label prompt;

    public ShowPageButton(Stage stage , TextField inputProjectName , Label prompt ) {
        super(stage);
        this.inputProjectName = inputProjectName;
        this.prompt = prompt;
    }

    @Override
    public BooleanBinding getBind() {

        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(inputProjectName.textProperty());
            }
            @Override
            protected boolean computeValue() {
                try {
                    //get the text by the input
                    String inputName = inputProjectName.getText();
                    project = FileUtil.checkProjectByName(inputName);
                    if (project == null) {
                        //If we can't find one matched
                        prompt.setText("We can't find that project");
                        return true;
                    } else {
                        prompt.setText("");
                        return false;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };

        return binding;
    }

    @Override
    public void nextPage() {
        stage.close();
        ShowPageInfo.showProjectInfo(project);
    }
}
