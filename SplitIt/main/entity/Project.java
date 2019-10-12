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
//          |   main.entity/Project.java - by Louis Heery     |
//          |_________________________________________________|


// Import relavent libraries.
package main.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


 // The parent of the project
 // Some set and get stuff

public class Project implements Serializable{

    private String name;
    private ArrayList<ProjectSon> sons = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<ProjectSon> getSons() {
        return sons; // this will access the ProjectSon ArrayList.
    }

    public void addSons(ProjectSon son) {
        sons.add(son);
    }

    public void setSons(ArrayList<ProjectSon> sons) {
       this.sons = sons;
    }


     // we are able to know the number of children that are inside the parent

    public int getSize(){
        return sons.size();
    }
}
