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
//          |   FileUtil.java - by Ziming He                  |
//          |_________________________________________________|

package main.utils;

import main.entity.Project;
import main.entity.ProjectSon;
import main.file.FileController;

import java.io.IOException;
import java.util.ArrayList;
import main.entity.Votes;

public class FileUtil {

    public static Project checkProjectByName(String projectName) throws IOException, ClassNotFoundException {
        Project needProject = null;
        ArrayList<Project> projects = FileController.ToJava();
        if (projects != null) {
            for (Project project : projects) {
                //If there is one on our text file
                if (project.getName().equals(projectName.replaceAll("\\s+",""))) {
                    needProject = project;
                    break;
                }
            }
        }
        return needProject;
    }

    public static void deleteProjectVotes(Project project) throws IOException, ClassNotFoundException {
        ArrayList<Project> projects = FileController.ToJava();

        // remove this project
        assert projects != null;
        for (Project p:projects) {
            if (p.getName().equals(project.getName())){
                projects.remove(p);
                break;
            }
        }

        //update votes
        ArrayList<ProjectSon> sons = project.getSons();
        for (ProjectSon son : sons) {
            ArrayList<Votes> list = new ArrayList<>();
            son.setVotes(list);
        }
        project.setSons(sons);

        projects.add(project);
        FileController.toTxt(projects);
    }

}
