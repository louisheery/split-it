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
//          |   EnterVotesPageButton.java - by Ziming He      |
//          |_________________________________________________|

package main.factory.button;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.entity.Project;
import main.entity.ProjectSon;
import main.file.FileController;
import main.page.AddVotes;
import main.utils.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class EnterVotesPageButton extends AbstractPageButton {

    private TextField inputProjectName;

    private Label prompt;

    private Project project;

    public EnterVotesPageButton(Stage stage, TextField inputProjectName, Label prompt) {
        super(stage);
        this.inputProjectName = inputProjectName;
        this.prompt = prompt;
    }

    @Override
    public BooleanBinding getBind() {

        // BooleanBinding which requires the user to NOT enter a number for the project name.
        // If the user enters a number then they will not be able to click the 'Next' button
        // and they will see an error message.
        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(inputProjectName.textProperty());
            }

            @Override
            protected boolean computeValue() {
                // Checks if the input is true.
                try {
                    //a is String，b is an integer，b=Integer.Parseint(a). This associates variable a to the variable b.
                    Integer.parseInt(inputProjectName.getText());
                    prompt.setText("The project name can't be a number");
                    return true;
                } catch (Exception e) {
                    // If project name isn't text, then error message disappears.
                    prompt.setText("");
                }
                //See if we have this project in our background txt
                try {
                    project = FileUtil.checkProjectByName(inputProjectName.getText());
                    if (project != null) {
                        prompt.setText("");
                        return false;
                    } else {
                        prompt.setText("We can't find that project");
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

            int votesNumber = project.getSons().get(0).getVotes().size();
            //see if the vote has been made
            if (votesNumber != 0) {
                // Alert message which appear if the user is trying to overwrite votes data for a project which already has votes entered for it.
                Alert alertName2 = new Alert(Alert.AlertType.WARNING, "", ButtonType.YES, ButtonType.NO);  //new alert object
                alertName2.setTitle("Warning");  //warning box title
                alertName2.setHeaderText(null);// Header
                alertName2.setContentText("You have already entered votes for this project!\nDo you want to overwrite the existing votes?"); //Discription of warning
                alertName2.getDialogPane().setPrefSize(300, 200); //sets size of alert box
                Optional<ButtonType> result = alertName2.showAndWait();

                if (result.get() == ButtonType.YES) {
                    //re-enter the vote page.
                    try{
                        FileUtil.deleteProjectVotes(project);
                    }catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    AddVotes.votes(inputProjectName.getText());
                    stage.close();
                } else {
                    // ... user chose NO or closed the dialog
                }
            } else {
                AddVotes.votes(inputProjectName.getText());
                stage.close();
            }
        
    }
    public TextField getInputProjectName() {
        return inputProjectName;
    }

    public void setInputProjectName(TextField inputProjectName) {
        this.inputProjectName = inputProjectName;
    }

    public Label getPrompt() {
        return prompt;
    }

    public void setPrompt(Label prompt) {
        this.prompt = prompt;
    }
}
