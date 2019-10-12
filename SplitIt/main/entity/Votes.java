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
//          |   main.entity/Votes.java - by Ziming He         |
//          |_________________________________________________|

// Import relavent libraries.
package main.entity;

import java.io.Serializable;


public class Votes implements Serializable {
    private String name;
    private int number;

    // we store project member's name and vote number inside.
    // we can extract these names and their corresponding votes.
    // We will extract these names and their corresponding votes with the "getName" and "getNumber" methods.

    public Votes(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
