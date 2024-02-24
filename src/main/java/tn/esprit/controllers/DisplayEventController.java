package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tn.esprit.models.Events;
import tn.esprit.services.ServiceEvents;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.table.TableModel;

public class DisplayEventController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TableColumn<TableModel, String> t_action;

    @FXML
    private TableColumn<?, ?> t_categorie;

    @FXML
    private TableColumn<?, ?> t_date;

    @FXML
    private TableColumn<?, ?> t_location;

    @FXML
    private TableColumn<?, ?> t_name;

    @FXML
    private TableColumn<Events, InputStream> t_photo;

    @FXML
    private TableColumn<?, ?> t_vote;

    @FXML
    private TableView<Events> table;

    public ObservableList<Events> listView = FXCollections.observableArrayList();
    //Events e = null;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

display();
    }

    @FXML
    void ajouter_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterEvents.fxml"));
        try {
            Parent root = loader.load();
            AjouterEventsController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void display() {
        listView.clear();
        ServiceEvents pcd = new ServiceEvents();
        listView.addAll(pcd.getAll());
        t_categorie.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        t_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        t_location.setCellValueFactory(new PropertyValueFactory<>("location"));

        boolean phottoColAdded = false;
        for (TableColumn<?, ?> col : table.getColumns()) {
            if (col.getText().equals("Photo")) {
                phottoColAdded = true;
                break;
            }
        }

        if (!phottoColAdded) {
            TableColumn<Events, Void> phottoCol = new TableColumn<>("Photo");

            phottoCol.setCellFactory(param -> new TableCell<Events, Void>() {
            private final Button photoButton = new Button("photo");

            {
                photoButton.getStyleClass().add("custom-button");

                photoButton.setOnAction(event -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPhotoEvent.fxml"));
                    try {
                        Events ev = getTableView().getItems().get(getIndex());

                        Parent root = loader.load();
                        AfficherPhotoEvent controller = loader.getController();

                        FileInputStream photoStream = ev.getPhoto();
                        controller.setPhoto(photoStream);

                        table.getScene().setRoot(root);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(photoButton); // Place buttons horizontally
                    buttons.setSpacing(5); // Adjust spacing between buttons if needed
                    setGraphic(buttons);
                }
            }
        });
        table.getColumns().add(phottoCol);
    }
       // t_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
//        t_photo.setCellFactory(column -> new TableCell<Events, InputStream>() {
//
//            private final ImageView imageView = new ImageView();
//
//            @Override
//            protected void updateItem(InputStream item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null) {
//                    setGraphic(null);
//                } else {
//                    try {
//                        Image image = new Image(item);
//                        imageView.setImage(image);
//                        setGraphic(imageView);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        // Handle exception
//                    }
//                }
//            }
//        });



        t_vote.setCellValueFactory(new PropertyValueFactory<>("vote_id"));
        t_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        boolean actionColAdded = false;
        for (TableColumn<?, ?> col : table.getColumns()) {
            if (col.getText().equals("Action")) {
                actionColAdded = true;
                break;
            }
        }

        if (!actionColAdded) {
            TableColumn<Events, Void> actionCol = new TableColumn<>("Action");
            actionCol.setCellFactory(param -> new TableCell<Events, Void>() {
                private final Button deleteButton = new Button("Supprimer");
                private final Button updateButton = new Button("Modifier");

                {
                    deleteButton.getStyleClass().add("custom-button");
                    updateButton.getStyleClass().add("custom-button");

                    deleteButton.setOnAction(event -> {
                        Events ev = getTableView().getItems().get(getIndex());
                        pcd.delete(ev);
                        display();
                    });
                    updateButton.setOnAction(event -> {
                        Events ev = getTableView().getItems().get(getIndex());
                        // You can uncomment this block if needed


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateEvents.fxml"));
                        try {
                            Parent root = loader.load();
                            updateEventController controller = loader.getController(); // Corrected class name to UpdateEventController
                            // Assuming your controller needs to access the selected item's name, you might want to uncomment this line:
                            // controller.setLbname(table.getSelectionModel().getSelectedItem().getName());

                            // Assuming 'Events' is the correct type for your items, get the item at the current index in the TableView

                            // Assuming 'initData' is a method in your controller to initialize data
                            controller.initData(ev);

                            // Print statements for debugging purposes
                            System.out.println(ev.getEvent_id());
                            System.out.println(3);

                            // Setting the root of the scene to the loaded FXML
                            table.getScene().setRoot(root);
                        } catch (IOException e) {
                            // Throw a more descriptive exception, capturing the root cause
                            throw new RuntimeException("Error loading updateEvents.fxml", e);

                    }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttons = new HBox(deleteButton, updateButton); // Place buttons horizontally
                        buttons.setSpacing(5); // Adjust spacing between buttons if needed
                        setGraphic(buttons);
                    }
                }
            });
            table.getColumns().add(actionCol);
        }
        table.setItems(listView);
    }
}



//
//            String requet2 = "SELECT * FROM personne";
//            Statement st = cnx2.createStatement();
//            ResultSet rs = st.executeQuery(requet2);
//            while (rs.next()) {
//                Personne p = new Personne();
//                p.setId(rs.getInt(1));
//                p.setNom(rs.getString("nom"));
//                p.setPrenom(rs.getString("prenom"));
//                listView.add(p);
//                listView.add(p);
//            }
//
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//
//        }
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        table.setItems(listView);
//    }





//}
