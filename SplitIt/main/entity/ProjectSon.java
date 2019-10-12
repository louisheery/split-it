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
//          |   main.entity/ProjectSon.java                   |
//          |   by Louis Heery & Ziming He                    |
//          |_________________________________________________|

// Import relavent libraries.
package main.entity;

import java.io.Serializable;
import java.util.ArrayList;


public class ProjectSon implements Serializable {

    private int id;
    // Assigns an id (identification) to each team member in the project
    private String text;// just a text
    //An ArrayList which will store the values of each team member's votes
    private ArrayList<Votes> votes = new ArrayList<>();

    public ProjectSon(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Votes> getVotes() {
        return votes; // This will access the Votes ArrayList
    }

    public void addVotes(Votes votes) {
        this.votes.add(votes);
    }
    public void setVotes(ArrayList<Votes> votes) {
        this.votes = votes;
    }
}
