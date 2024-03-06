package tn.esprit.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import tn.esprit.models.Events;
import tn.esprit.services.ServiceEvents;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.esprit.test.Core;
import tn.esprit.util.Commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.table.TableModel;


public class DisplayEventController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button btn_stat;
    @FXML
    private Button openAdmin;
    @FXML
    void   openAdmi(){}










    @FXML
    private Button listeCommande;

    @FXML
    private BorderPane eventPane;
    @FXML
    private Button panier;







    @FXML

    public void openAdmin(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));
            Parent admin = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button annonce;
    @FXML
    void   comboEvents(){}
    @FXML
    private void openAjouterPersonne(ActionEvent event) {
        try {
            // Load AjouterPersonne.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Annonce.fxml"));
            Parent ajouterPersonnePane = loader.load();
            AnnonceController annonceController = new AnnonceController();

            // Instantiate AjouterAnnonceController
            AjouterAnnonceController ajouterAnnonceController = new AjouterAnnonceController();

            // Set the reference to AnnonceController in AjouterAnnonceController
            ajouterAnnonceController.setAnnonceController(annonceController);

            // Set AjouterPersonne.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(ajouterPersonnePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void openListeCommande(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeCommande.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openPanier(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panier.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void   openCommandeArchive(){
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/commandeArchive.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Decon(ActionEvent actionEvent) {
        try {
            // Load the previous page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the stage from the event source
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

            // Set the scene to the previous stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//    @FXML
//    void   openListeCommande(){}
//    @FXML
//    void   openAjouterPersonne(){}
//
//    @FXML
//    void   openListeCommande(){}







    @FXML
    private TableColumn<TableModel, String> t_action;

    @FXML
    private  TableColumn<?, ?> t_categorie;

    @FXML
    private TextField t_chercher;

    @FXML
    private  TableColumn<?, ?> t_date;





    @FXML
    private  TableColumn<?, ?> t_location;

    @FXML
    private  TableColumn<?, ?> t_name;

    @FXML
    private ComboBox<String> combo_table;
    @FXML
    private Button enligne;


    @FXML
    private ComboBox<String> triCombo;

    @FXML
    private TableColumn<Events, InputStream> t_photo;



    @FXML
    private TableView<Events> table;

    public ObservableList<Events> listView = FXCollections.observableArrayList();
    @FXML
    public void mail(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mailDisplay.fxml"));
        try {
            Parent root = loader.load();
            MailDisplay dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            t_chercher.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Events e = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList("Events", "Categories", "Votes");
        ObservableList<String> itemsTri = FXCollections.observableArrayList("Tri Ascendant", "Tri Descendante");

        triCombo.setItems(itemsTri);
        combo_table.setItems(items);

        combo_table.setOnAction(event -> {
            String selectedItem = combo_table.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Perform action based on the selected item
                switch (selectedItem) {
                    case "Events":
                        System.out.println("Events selected. Perform action for Events.");
                        loadDisplayEvent();
                        break;
                    case "Categories":
                        System.out.println("Categories selected. Perform action for Categories.");
                        // Call method or perform action for Categories
                        loadDisplayCategories();

                        break;
                    case "Votes":
                        System.out.println("Votes selected. Perform action for Votes.");
                        loadDisplayVote();
                        break;
                    default:
                        System.out.println("Unknown selection");
                }

            }
        });
        triCombo.setOnAction(event -> {
            String selectedItem = triCombo.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Perform action based on the selected item
                switch (selectedItem) {
                    case "Tri Ascendant":
                        System.out.println("sd");
                        System.out.println("Events selected. Perform action for Events.");
                        display1();

                        break;
                    case "Tri Descendante":
                        System.out.println("Categories selected. Perform action for Categories.");
                        // Call method or perform action for Categories
                        display2();

                        break;

                    default:
                        System.out.println("Unknown selection");
                }

            }
        });

        display();
    }
    void loadDisplayCategories() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCategories.fxml"));
        try {
            Parent root = loader.load();
            DisplayCategoriesController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void loadDisplayEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root = loader.load();
            DisplayEventController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void loadDisplayVote() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayVote.fxml"));
        try {
            Parent root = loader.load();
            DisplayVoteController dc = loader.getController();
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void ExportPdf(){
        ServiceEvents se = new ServiceEvents();
        ArrayList<Events> arrayList = se.getAll();
        ObservableList<Events> list = FXCollections.observableList(arrayList);
        try {
            Document document = new Document();
            // Create a PdfWriter instance with the FileOutputStream
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("events.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(5);
            table.addCell("Name");
            table.addCell("Location");
            table.addCell("Date");
            table.addCell("Category_id");
            table.addCell("Photo");
            for (Events event : list) {
                table.addCell(event.getName());
                table.addCell(event.getLocation());
                table.addCell(event.getDate().toString());
                table.addCell(String.valueOf(event.getCategory_id()));
                if (event.getPhoto() != null) {
                    try {
                        byte[] photoBytes = event.getPhoto().readAllBytes();
                        // Create an Image instance using the byte array
                        Image image = Image.getInstance(photoBytes);
                        table.addCell(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            document.add(table);
            document.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF");
            alert.setContentText("PDF created successfully!");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void exportExecl(){ServiceEvents se = new ServiceEvents();
        ArrayList<Events> arrayList = se.getAll();
        ObservableList<Events> list = FXCollections.observableList(arrayList);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("user details");
        XSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Name"); // Corrected column index
        header.createCell(1).setCellValue("Location"); // Corrected column index
        header.createCell(2).setCellValue("Date"); // Corrected column index
        header.createCell(3).setCellValue("Category_id"); // Corrected column index
        header.createCell(4).setCellValue("Photo"); // Corrected column index




        int index = 1;
        for (Events event : list) { // Using enhanced for loop
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(event.getName()); // Assuming 'name' is the attribute to retrieve
            row.createCell(1).setCellValue(event.getLocation()); // Assuming 'location' is the attribute to retrieve
            row.createCell(2).setCellValue(event.getDate().toString()); // Assuming 'date' is the attribute to retrieve
            row.createCell(3).setCellValue(event.getCategory_id());
            if (event.getPhoto() != null) {
                try {
                    InputStream inputStream = new FileInputStream(String.valueOf(event.getPhoto())); // Assuming getPhoto() returns the file path
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG); // Adjust accordingly based on the image type
                    inputStream.close();

                    CreationHelper helper = wb.getCreationHelper();
                    Drawing<?> drawing = sheet.createDrawingPatriarch();
                    ClientAnchor anchor = helper.createClientAnchor();
                    anchor.setCol1(4); // Column to place the image
                    anchor.setRow1(index); // Row to place the image
                    drawing.createPicture(anchor, pictureIdx);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle file IO exception appropriately
                }
            }

            index++;
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("user.xlsx");
            wb.write(fileOut); // Moved the write operation into the try block
            fileOut.close(); // Closing the FileOutputStream
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excel");
            alert.setContentText("Excel created successfully!");
            alert.show(); // Displaying the alert
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

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
                    photoButton.getStyleClass().add("photo-button");

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
                private final Button deleteButton;

                // Constructor or initialization block where you initialize deleteButton
                {
                    deleteButton = new Button("Supprimer");
                    deleteButton.getStyleClass().add("delete-button");
                }

                private final Button updateButton = new Button("Modifier");

                {
                    updateButton.getStyleClass().add("update-button");

                    deleteButton.setOnAction(event -> {
                        Events ev = getTableView().getItems().get(getIndex());
                        System.out.println(ev);
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
    void display1() {
        listView.clear();
        ServiceEvents pcd = new ServiceEvents();
        listView.addAll(pcd.getAllByTriAsd());
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
                    photoButton.getStyleClass().add("photo-button");

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
                private final Button deleteButton;

                // Constructor or initialization block where you initialize deleteButton
                {
                    deleteButton = new Button("Supprimer");
                    deleteButton.getStyleClass().add("delete-button");
                }

                private final Button updateButton = new Button("Modifier");

                {
                    updateButton.getStyleClass().add("update-button");

                    deleteButton.setOnAction(event -> {
                        Events ev = getTableView().getItems().get(getIndex());
                        System.out.println(ev);
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
    void display2() {
        listView.clear();
        ServiceEvents pcd = new ServiceEvents();
        listView.addAll(pcd.getAllByTriDesc());
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
                    photoButton.getStyleClass().add("photo-button");

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
                private final Button deleteButton;

                // Constructor or initialization block where you initialize deleteButton
                {
                    deleteButton = new Button("Supprimer");
                    deleteButton.getStyleClass().add("delete-button");
                }

                private final Button updateButton = new Button("Modifier");

                {
                    updateButton.getStyleClass().add("update-button");

                    deleteButton.setOnAction(event -> {
                        Events ev = getTableView().getItems().get(getIndex());
                        System.out.println(ev);
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
    @FXML
    void enligne() throws IOException {
        Stage stage = new Stage();
        // set current stage to core
        Core.instance().stage(stage);
        Core.instance().start();
        // catch on close
        stage.setOnCloseRequest((evt) -> Core.instance().close());

        // get the parent node
        Parent root = (Parent) FXMLLoader.load(
                getClass().getResource("/fxml/Main.fxml"));
        // build the default scene
        Scene scene = new Scene(root);
        // add custom styles to the scene
        scene.getStylesheets().add("/css/default.css");

        // prepare the stage
        stage.setTitle("Événements de réunion en ligne");
        // set the scene to stage
        stage.setScene(scene);

        // stage size
        stage.setWidth(920);
        stage.setHeight(650);

        // set the icon
        javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResourceAsStream("/img/logo.png"));
        stage.getIcons().add(icon);
        stage.getIcons().add(Commons.resizeImage(icon, 16, 16));
        stage.getIcons().add(Commons.resizeImage(icon, 24, 24));
        stage.getIcons().add(Commons.resizeImage(icon, 32, 32));
        stage.getIcons().add(Commons.resizeImage(icon, 48, 48));
        stage.getIcons().add(Commons.resizeImage(icon, 128, 128));
        stage.getIcons().add(Commons.resizeImage(icon, 256, 256));

        // display the stage
        //stage.setMaximized(true);
        stage.show();

    }

    @FXML
    void stat(){


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuAppWithChart.fxml"));
        try {
            Parent root = loader.load();
            MenuAppWithChartController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void chercherNomEvent(){
        if (t_chercher.getText().isEmpty()) {
            display();
            return;
        }


        listView.clear();
        ServiceEvents pcd = new ServiceEvents();
        ArrayList <Events> arrayList = pcd.getByName(t_chercher.getText());
        if (arrayList.isEmpty()) {
            Errorpop("Aucun événement trouvé avec ce nom");
            return;
        }else {
            succesPop("Evénement avec le nom "+t_chercher.getText()+" est trouvé");
            listView.addAll(arrayList);
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
                        photoButton.getStyleClass().add("photo-button");

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
                    private final Button deleteButton;

                    // Constructor or initialization block where you initialize deleteButton
                    {
                        deleteButton = new Button("Supprimer");
                        deleteButton.getStyleClass().add("delete-button");
                    }

                    private final Button updateButton = new Button("Modifier");

                    {
                        updateButton.getStyleClass().add("update-button");

                        deleteButton.setOnAction(event -> {
                            Events ev = getTableView().getItems().get(getIndex());
                            System.out.println(ev);
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
        System.out.println("khf");
    }
    public void succesPop(String s){
        Label label = new Label(s); // "Successfully added!" in French
        label.setStyle("-fx-background-color:  #7A8727;-fx-text-fill: #FFB347; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
        StackPane stackPane = new StackPane(label);
        stackPane.setPrefSize(400, 200); // Set preferred size of the StackPane

        Screen screen = Screen.getPrimary();
        double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
        double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

        Popup popup = new Popup();
        popup.getContent().add(stackPane);
        popup.setAutoHide(true);

        popup.show(t_chercher.getScene().getWindow(), centerX, centerY);
    }
    public void Errorpop(String s)
    {
        Label label = new Label(s); // "Successfully added!" in French
        label.setStyle("-fx-background-color:  #FF5733;-fx-text-fill: white; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
        StackPane stackPane = new StackPane(label);
        stackPane.setPrefSize(400, 400); // Set preferred size of the StackPane

        Screen screen = Screen.getPrimary();
        double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
        double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

        Popup popup = new Popup();
        popup.getContent().add(stackPane);
        popup.setAutoHide(true);

        popup.show(t_chercher.getScene().getWindow(), centerX, centerY);
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
