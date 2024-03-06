package tn.esprit.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import tn.esprit.services.ServiceVotes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author spangsberg
 */
public class MenuAppWithChartController implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setCenter(buildPieChart());
    }

    @FXML
    private void handleShowBarChart() {

        borderPane.setCenter(buildBarChart());
    }

    @FXML
    private void handleShowPieChart() {
        borderPane.setCenter(buildPieChart());
    }

    private BarChart buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Statistique des votes");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("# of developers x 1000");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Statistique des votes");

        ServiceVotes sv= new ServiceVotes();
        ArrayList<Integer> arVote= sv.getAllByVoteVal();
        System.out.println(arVote);
        int votes_0_2 = 0;
        int votes_3_4 = 0;
        int votes_4_5 = 0;
        for (int vote : arVote) {
            if (vote >= 0 && vote <= 2) {
                votes_0_2++;
            } else if (vote >= 3 && vote <= 4) {
                votes_3_4++;
            } else if (vote >= 4 && vote <= 5) {
                votes_4_5++;
            }
        }
        System.out.println("votes_0_2"+votes_0_2+"votes_3_4"+votes_3_4+"votes_4_5"+votes_4_5);

        dataSeries1.getData().add(new XYChart.Data("vote ente 0 et 2", votes_0_2));
        dataSeries1.getData().add(new XYChart.Data("vote ente 3 et 4", votes_3_4));
        dataSeries1.getData().add(new XYChart.Data("vote ente 4 et 5", votes_4_5));

        barChart.getData().add(dataSeries1);

        return barChart;
    }

    private PieChart buildPieChart() {
        ServiceVotes sv= new ServiceVotes();
        ArrayList<Integer> arVote= sv.getAllByVoteVal();
        System.out.println(arVote);
        int votes_0_2 = 0;
        int votes_3_4 = 0;
        int votes_4_5 = 0;
        for (int vote : arVote) {
            if (vote >= 0 && vote <= 2) {
                votes_0_2++;
            } else if (vote >= 3 && vote <= 4) {
                votes_3_4++;
            } else if (vote >= 4 && vote <= 5) {
                votes_4_5++;
            }
        }
        System.out.println("votes_0_2"+votes_0_2+"votes_3_4"+votes_3_4+"votes_4_5"+votes_4_5);



        //Preparing ObservbleList object
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("vote ente 0 et 2",votes_0_2 ),
                new PieChart.Data("vote ente 3 et 4",votes_3_4 ),
                new PieChart.Data("vote ente 4 et 5", votes_4_5));


        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart

        //attach tooltips
        createToolTips(pieChart);

        pieChart.setTitle("Statistique des votes"); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data
        pieChart.setLabelLineLength(50); //Setting the length of the label line
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(180);

        //bind value and label on each pie slice to reflect changes
        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " ")
                ));


        ContextMenu contextMenu = new ContextMenu(); //create context menu
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);


        //Add event handler to display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }
                    }
                });


        //Before Java 8
        //Add event handler to change chart type (anonymous inner class)
        miSwitchToBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                borderPane.setCenter(buildBarChart());
            }
        });


        //Java 8 and newer (lambda expression)
        miSwitchToBarChart.setOnAction(event -> { borderPane.setCenter(buildBarChart()); });


        return pieChart;
    }

    /**
     *
     */
    @FXML
    private void handleClose() {
        System.exit(0);
    }
    @FXML
    private void handleBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root = loader.load();
            DisplayEventController dc = loader.getController();
            //dc.se(nomtf.getText());
            borderPane.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    /**
     *
     */
    @FXML
    private void handleUpdatePieData() {
        Node node = borderPane.getCenter();

        if (node instanceof PieChart)
        {
            PieChart pc = (PieChart) node;
            double value = pc.getData().get(2).getPieValue();
            pc.getData().get(2).setPieValue(value * 1.10);
            createToolTips(pc);
        }
    }


    /**
     * Creates tooltips for all data entries
     * @param pc
     */
    private void createToolTips(PieChart pc) {

        for (PieChart.Data data: pc.getData()) {
            String msg = Double.toString(data.getPieValue());

            Tooltip tp = new Tooltip(msg);
            tp.setShowDelay(Duration.seconds(0));

            Tooltip.install(data.getNode(), tp);

            //update tooltip data when changed
            data.pieValueProperty().addListener((observable, oldValue, newValue) ->
            {
                tp.setText(newValue.toString());
            });
        }
    }
}
