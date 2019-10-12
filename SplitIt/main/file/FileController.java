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
//          |   main.file/FileController.java - by Ziming He  |
//          |_________________________________________________|

// Import relavent libraries.
package main.file;

import main.entity.Project;
import main.entity.Votes;
import java.io.*;
import java.util.ArrayList;


public class FileController {

    // Defines the paths to which the user's data is stored.
    private static final String ProjectPath = "D:\\test.txt";
    private static final String VotesPath = "D:\\votes.txt";

    // Add information to the TXT file
    public static void toTxt(ArrayList<Project> projects) throws IOException {
        File file = new File(ProjectPath);

        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

        os.writeObject(projects);
    }


    // Method which extracts the project name value and the project from the txt file into the project
    public static ArrayList<Project> ToJava() throws IOException, ClassNotFoundException {
        File file = new File(ProjectPath);
        if (!file.exists()) {
            return null;
        }

        ObjectInputStream is = null;

        // Tests whether the project has been read corrected or if it hasn't been read correctly
        try {
            is = new ObjectInputStream(new FileInputStream(file));
            Object ob = is.readObject();
            System.out.println("Readed!");
            return (ArrayList<Project>) ob;
        } catch (Exception e) {
            System.out.println("Error!");
            return new ArrayList<>();
        }
    }

    // Method which saves the project values & information to the TXT file
    public static void VotestoTxt(ArrayList<Votes> projects) throws IOException {
        File file = new File(VotesPath);

        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

        os.writeObject(projects);
    }

     //We can use VotesToJava to extract the vote information in project
    public static ArrayList<Votes> VotesToJava() {
        File file = new File(VotesPath);
        if (!file.exists()) {
            return null;
        }
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(file));
            Object ob = is.readObject();
            System.out.println("Readed!");
            return (ArrayList<Votes>) ob;
        } catch (Exception e) {
            System.out.println("Error!");
            return new ArrayList<>();
        }
    }
}
