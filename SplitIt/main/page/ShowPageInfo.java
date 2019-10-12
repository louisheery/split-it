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
//          |   main.page/ShowPageInfo.java                   |
//          |   by Louis Heery & Ziming He                    |
//          |_________________________________________________|

// Import relavent libraries.
package main.page;

import java.text.DecimalFormat;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Controller;
import main.entity.Project;
import main.entity.ProjectSon;
import main.entity.Votes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.FontWeight;

public class ShowPageInfo {

    public static void showProjectInfo(Project project) {
        //This page has two section
        //1.Show how many projectSons
        //2.Show relevant information
        //Use VBox and TextArea
        Stage stage = new Stage();

        VBox main = new VBox();
        main.setSpacing(20);
        main.setAlignment(Pos.CENTER);

        //Set Title
        Label title = new Label("Show Project");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setAlignment(Pos.CENTER);
        title.setMinWidth(200);

        // Set Subtitle
        int number = project.getSons().size();
        Label subtitle = new Label("The " + number + " participants in the project received the following points allocation:\n\n\n");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        subtitle.setMinWidth(200);

        //Using this box for text field
        HBox txtBox = new HBox();
        txtBox.setAlignment(Pos.CENTER);
        //Set text field
        TextArea projectInfo = new TextArea();
        projectInfo.setMaxSize(200,200);
        projectInfo.setEditable(false);

        //Set typeface
        Font font = new Font("Arial",20);
        projectInfo.setFont(font);
        txtBox.getChildren().addAll(projectInfo);

        double[] FinalScore = new double[number];

        //Calculate the votes
        ArrayList<ProjectSon> sons = project.getSons();

        ArrayList<Votes> votes = new ArrayList<>();

        // For each loop,take out each element in the Arraylist ProjectSon (son is the name to store each element)
        // There is a tutorial in https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html
        for (ProjectSon son : sons) {
            //Get all votes' information
            for (int i = 0; i < son.getVotes().size(); i++) {
               votes.add(son.getVotes().get(i));
            }
        }


        //A Map is an object that maps keys to values, which is convient to put and get information from a particular key.
        //The form for HasMap is : Map<Object,Object> name = new HashMap<Object, Object>();
        Map<String, Double> info = new HashMap<>();

        //For each loop,take out each element in the Arraylist Votes
        //Print votes1 in each loop
        for (Votes votes1: votes) {
            Double temp  = info.get(votes1.getName());
            System.out.println(votes1.getName() + " : " + votes1.getNumber());
            //If this vote has been added in map, then we process addition for it.
            if (temp != null){
                temp += votes1.getNumber();
                info.put(votes1.getName(),temp);
            }else {
                info.put(votes1.getName(), (double) votes1.getNumber());
            }
        }

        double totalScore = 0.0;

        double[] EachNumber = new double[number];
        String [] showText = new String[number];
        int k = 0;
        double sum = 0.0;
        for (Map.Entry<String, Double> entry : info.entrySet()) {
            double tempNumber = entry.getValue()/(number-1)*1.0;
            for ( int i = k; i < k +1; i++) {
                EachNumber[i] = tempNumber;
                showText[i] = entry.getKey();
                sum += EachNumber[i];
            }
            k++;
        }
        String showTextString = "";

        // For the number of three algorithm.

        DecimalFormat formmater = new DecimalFormat("0");

        int PersonOnePercent = 0;
        int PersonTwoPercent = 0;
        int PersonThreePercent = 0;

        if(number == 3 && !votes.isEmpty()){
            // Generate a matrix
           double[][] For3x3Matrix = {
               {0.0,(double)votes.get(0).getNumber()/(double)votes.get(1).getNumber(),(double)votes.get(1).getNumber()/(double)votes.get(0).getNumber()},
               {(double)votes.get(2).getNumber()/(double)votes.get(3).getNumber(),0.0,(double)votes.get(3).getNumber()/(double)votes.get(2).getNumber()},
               {(double)votes.get(4).getNumber()/(double)votes.get(5).getNumber(),(double)votes.get(5).getNumber()/(double)votes.get(4).getNumber(),0.0}
           };
           double VoteForThirdPerson = 1/(1+For3x3Matrix[0][1] + For3x3Matrix[1][0]);
           double VoteForSecondPerson = 1/(1+For3x3Matrix[0][2] + For3x3Matrix[2][0]);
           double VoteForFirstPerson = 1/(1+For3x3Matrix[1][2] + For3x3Matrix[2][1]);
           showTextString = votes.get(2).getName() + " = " + ((int) (VoteForFirstPerson * 100)) + "\n"
                   + votes.get(0).getName() + " = " + ((int) (VoteForSecondPerson * 100)) + "\n" +
                   votes.get(1).getName() + " = " + ((int) (VoteForThirdPerson * 100)) + "\n";

           PersonOnePercent = (int) (VoteForFirstPerson * 100);
           PersonTwoPercent = (int) (VoteForSecondPerson * 100);
           PersonThreePercent = (int) (VoteForThirdPerson * 100);
        }else if(number >= 4 && !votes.isEmpty()){    // For the number of 4 or more algorithm.
           for(int z = 0; z < number; z++){
           EachNumber[z] = EachNumber[z]*100/sum;
           showTextString += showText[z] + ":" + formmater.format(EachNumber[z]) + "\n";
           }
        }
        projectInfo.setText(showTextString);

        //////// BAR CHART /////////

        //Defining the axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Team Member");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score (%)");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);


        //Setting the data to bar chart
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Team Member");

        //if there are 3 participants in project, we use algorithm given in the project brief
        if (number == 3 && !votes.isEmpty()) {

            double TotalPoints = PersonOnePercent + PersonTwoPercent + PersonThreePercent;
            XYChart.Data<String, Number> one = new XYChart.Data<>(votes.get(2).getName(), (int) (PersonOnePercent * 100 / TotalPoints));
            series1.getData().add(one);

            XYChart.Data<String, Number> two = new XYChart.Data<>(votes.get(0).getName(), (int) (PersonTwoPercent * 100 / TotalPoints));
            series1.getData().add(two);

            XYChart.Data<String, Number> three = new XYChart.Data<>(votes.get(1).getName(), (int) (PersonThreePercent * 100 / TotalPoints));
            series1.getData().add(three);

        }

        // if there are 4 or more participants in project, we use a geometric average to calculate votes of each participant
        else if (number >= 4 && !votes.isEmpty()) {

            for(int z = 0; z < number; z++){

                EachNumber[z] = EachNumber[z] * 100 / sum;
                String tmpString = showText[z];
                Number tmpValue = EachNumber[z];
                XYChart.Data<String, Number> d = new XYChart.Data<>(tmpString, tmpValue);
                series1.getData().add(d);
            }

        }

         barChart.getData().addAll(series1);

         VBox ChartWindow = new VBox();

         // ChartWindow.setAlignment(Pos.RIGHT);
         //Set text field
         ChartWindow.setMaxSize(400,200);

         //Set typeface
         if (number >= 3){
           ChartWindow.getChildren().addAll(barChart);
         }

          // main menu button
             Button menue = new Button("Main Menu");
             menue.setDefaultButton(true);
             menue.setMinWidth(200);
             menue.setOnAction(event -> {
                 try {
                     new Controller().start(stage);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             });

             //Using this box for text field
             HBox groupBox = new HBox();
             groupBox.setAlignment(Pos.CENTER);

             // Add items to stage
             groupBox.getChildren().addAll(txtBox,ChartWindow);

             main.getChildren().addAll(title,subtitle,groupBox,menue); // add all items to main group

             Scene scene = new Scene(main, 800, 500); // set size of window
             stage.setScene(scene);  // add textField and Barchart scene to stage
             stage.show();

    }
}
