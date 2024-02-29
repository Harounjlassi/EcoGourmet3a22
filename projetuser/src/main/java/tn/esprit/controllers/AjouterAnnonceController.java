package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import tn.esprit.models.Annonce;
import tn.esprit.models.Commentaire;
import tn.esprit.services.ServiceAnnonce;
import tn.esprit.services.ServiceCommentaire;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AjouterAnnonceController {
    private Annonce annonce;
    @FXML
    private Button Annonce;
    @FXML
    private TextField CategorieTF;
    @FXML
    private ComboBox<String> Tri;
    @FXML
    private TilePane annonceTilePane;
    @FXML
    private VBox tilePaneContainer;
    @FXML
    private BorderPane annoncePane;
    @FXML
    private TextField IngredientTF;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField chefTF;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Annonce, Button> actionCol;
    @FXML
    private TableColumn<Annonce, Button> modcol;
    @FXML
    private Button importImageButton;
    @FXML
    private Button previousPageButton;
    @FXML
    private Button nextPageButton;
    @FXML
    private final ServiceAnnonce sa = new ServiceAnnonce();
    private final ServiceCommentaire comment = new ServiceCommentaire();
    private int currentPage = 0;
    private int itemsPerPage = 4;
    List<String> badWords = Arrays.asList("fuck", "badword2", "badword3");
    @FXML
    public void ajouterAnnonce(ActionEvent actionEvent) {
    }

    @FXML
    private void ajouterAnnonce(String imagePath) {
        // Get the values from the text fields
        String nomPlat = nomtf.getText();
        String descriptionPlat = prenomTF.getText();
        String prixText = ageTF.getText();
        String chefIdText = chefTF.getText();
        String ingredients = IngredientTF.getText();
        String categorie = CategorieTF.getText();

        // Validate input fields
        if (nomPlat.isEmpty() || descriptionPlat.isEmpty() || prixText.isEmpty() || chefIdText.isEmpty() || ingredients.isEmpty() || categorie.isEmpty()) {
            // Show an error message indicating that all fields are required
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "All fields are required.", "Please fill in all the fields.");
            return;
        }

        float prix;
        int chefId;

        try {
            prix = Float.parseFloat(prixText);
        } catch (NumberFormatException e) {
            // Show an error message indicating that prix must be a valid number
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "Invalid Prix", "Prix must be a valid number.");
            return;
        }

        try {
            chefId = Integer.parseInt(chefIdText);
        } catch (NumberFormatException e) {
            // Show an error message indicating that chef ID must be a valid integer
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "Invalid Chef ID", "Chef ID must be a valid integer.");
            return;
        }

        // Create an Annonce object and set its attributes
        Annonce annonce = new Annonce();
        annonce.setNom_du_plat(nomPlat);
        annonce.setDescription_du_plat(descriptionPlat);
        annonce.setPrix(prix);
        annonce.setID_du_chef(chefId);
        annonce.setIngredients(ingredients);
        annonce.setCategorie_de_plat(categorie);
        annonce.setImagePlat(imagePath); // Set the image path

        // Add the Annonce to the database using the service
        sa.add(annonce);
        populateTilePane();
    }

    // Method to show an alert dialog
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();

        // Schedule the alert to close after the specified duration
    }


    @FXML
    private void displayAnnonceInfo(Annonce annonce) {
        // Create a dialog to display Annonce information
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Annonce Information");
        dialog.setHeaderText("Annonce Information");

        // Add the Annonce information to the dialog content
        VBox content = new VBox();
        content.getChildren().add(new Label("Nom: " + annonce.getNom_du_plat()));
        content.getChildren().add(new Label("Description: " + annonce.getDescription_du_plat()));
        content.getChildren().add(new Label("Prix: " + annonce.getPrix()));
        content.getChildren().add(new Label("Ingrédients: " + annonce.getIngredients()));
        content.getChildren().add(new Label("Catégorie: " + annonce.getCategorie_de_plat()));

        // Add delete and update buttons
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            sa.delete(annonce); // Delete the annonce
            populateTilePane(); // Refresh the TilePane after deletion
            dialog.close(); // Close the dialog after deletion
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> {
            Optional<Annonce> result = showUpdateDialog(annonce); // Open the update dialog
            result.ifPresent(updatedAnnonce -> {
                sa.update(updatedAnnonce); // Update the annonce in the database
                populateTilePane(); // Refresh the TilePane after updating
                dialog.close(); // Close the dialog after updating
            });
        });

        content.getChildren().addAll(deleteButton, updateButton);
        dialog.getDialogPane().setContent(content);

        // Set a handler for the 'X' button
        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
            dialog.close();
        });

        // Show the dialog
        dialog.showAndWait();
    }




    @FXML
    private void populateTilePane() {
        tilePaneContainer.getChildren().clear();

        final List<tn.esprit.models.Annonce>[] annonces = new List[]{sa.getAll()};
        List<Annonce> allAnnonces = sa.getAll();

        // Apply search filter
        String searchText = searchField.getText().toLowerCase();
        List<Annonce> filteredAnnonces = allAnnonces.stream()
                .filter(annonce -> annonce.getNom_du_plat().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        // Update total items count
        int totalItems = filteredAnnonces.size();

        // Calculate pagination indices
        int start = currentPage * itemsPerPage;
        int end = Math.min(start + itemsPerPage, totalItems);

        // Get announcements for the current page
        List<Annonce> currentPageAnnonces = filteredAnnonces.subList(start, end);
        TilePane currentTilePane = new TilePane();
        currentTilePane.setPrefColumns(3);
        currentTilePane.setHgap(10);

        for (int i = start; i < end; i++) {
            Annonce annonce = annonces[0].get(i);
            try {
                ImageView imageView = new ImageView(new Image(new File(annonce.getImagePlat()).toURI().toString()));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setOnMouseClicked(event -> displayAnnonceInfo(annonce));

                // Create a VBox to hold image, comments, and text field
                VBox tileContent = new VBox();
                tileContent.getChildren().add(imageView);

                // Fetch and display comments for the current announcement
                List<Commentaire> comments = comment.getCommentsForAnnouncement(annonce.getId_Annonce());
                for (Commentaire comment : comments) {
                    HBox commentBox = new HBox();

                    // Create a label for the comment
                    Label commentLabel = new Label();

                    // Filter bad words and replace them with stars
                    String commentText = comment.getComment();
                    for (String badWord : badWords) {
                        if (commentText.contains(badWord)) {
                            String stars = "*".repeat(badWord.length());
                            commentText = commentText.replaceAll(badWord, stars);
                        }
                    }
                    commentLabel.setText(commentText);
                    commentLabel.setStyle("-fx-font-size: 14px;"); // Adjust font size as needed

                    // Add event handler for double-click on the comment label
                    String finalCommentText = commentText;
                    commentLabel.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                            // Double-click detected, enable editing
                            commentLabel.setVisible(false); // Hide the label
                            TextField editField = new TextField(finalCommentText); // Create a text field with the current comment text
                            editField.setPrefWidth(commentLabel.getWidth()); // Set the width of the text field
                            editField.setOnKeyPressed(keyEvent -> {
                                if (keyEvent.getCode() == KeyCode.ENTER) {
                                    // If Enter key is pressed, save the edited comment
                                    String editedComment = editField.getText().trim();
                                    if (!editedComment.isEmpty()) {
                                        // Update the comment in the database
                                        comment.setComment(editedComment);
                                        try {
                                            ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                                            // Call the updateComment method from the service
                                            serviceCommentaire.updateComment(comment);
                                            populateTilePane(); // Refresh the TilePane
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    // Remove the text field and show the label again
                                    tileContent.getChildren().remove(editField);
                                    commentLabel.setText(editedComment);
                                    commentLabel.setVisible(true);
                                }
                            });
                            tileContent.getChildren().add(editField); // Add the text field to the VBox
                        }
                    });

                    // Create the delete button
                    Button deleteButton = new Button("❌"); // Unicode symbol for 'cross mark'
                    deleteButton.setStyle("-fx-font-size: 10px; -fx-padding: 2px 5px;"); // Adjust size and padding

                    // Add event handler to delete the comment when the button is clicked
                    deleteButton.setOnAction(event -> {
                        // Prompt confirmation before deleting the comment
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Comment");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to delete this comment?");

                        // Get the user's response
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            // User confirmed deletion, delete the comment from the database
                            try {
                                ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                                serviceCommentaire.deleteComment(comment.getCommentaireId());
                                populateTilePane(); // Refresh the TilePane after deletion
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    // Add the delete button to the HBox
                    commentBox.getChildren().addAll(commentLabel, deleteButton);

                    // Add the comment box to the tile content
                    tileContent.getChildren().add(commentBox);
                }

                // Create a TextField for adding comments
                TextField commentField = new TextField();
                commentField.setPromptText("Add a comment...");

                // Add event listener to add comment when Enter key is pressed
                commentField.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        String commentText = commentField.getText().trim();
                        if (!commentText.isEmpty()) {
                            Commentaire commentaire = new Commentaire();
                            commentaire.setUserId(10); // Assuming currentUser is the current user object
                            commentaire.setAnnonceId(annonce.getId_Annonce());
                            commentaire.setComment(commentText);
                            try {
                                comment.addComment(commentaire); // Add comment to the database
                                // Clear the comment text field
                                commentField.clear();
                                // Refresh the tile pane to display the new comment
                                populateTilePane();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                tileContent.getChildren().add(commentField);
                currentTilePane.getChildren().add(tileContent);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
            }
        }

        tilePaneContainer.getChildren().add(currentTilePane);
        updatePaginationButtons();
    }



    @FXML
    void initialize() {
        assert ageTF != null : "fx:id=\"ageTF\" was not injected: check your FXML file 'AjouterPersonnes.fxml'.";
        assert prenomTF != null : "fx:id=\"prenomTF\" was not injected: check your FXML file 'AjouterPersonnes.fxml'.";
        //CategorieTF.getItems().addAll("Vegan", "Gluten Free", "Vegetarian");
        Tri.setItems(FXCollections.observableArrayList("Ascendant", "Descendant"));
        populateTilePane();
    }
    @FXML
    private void sortAnnonces() {
        String selectedSort = Tri.getValue();
        switch (selectedSort) {
            case "Ascendant":
                // Call function to sort annonces by price in ascending order
                // populateTilePane(sortedAnnonces);
                break;
            case "Descendant":
                // Call function to sort annonces by price in descending order
                // populateTilePane(sortedAnnonces);
                break;
            default:
                break;
        }
    }



    private Button createUpdateButton(Annonce annonce) {
        Button button = new Button("Update");
        button.setOnAction(event -> {
            showUpdateDialog(annonce);
        });
        return button;
    }
    @FXML
    void importImageClicked(ActionEvent event) {
        // Open a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(importImageButton.getScene().getWindow());

        // If a file is selected, get its path and add it to the database
        if (selectedFile != null) {
            String imagePath = selectedFile.getAbsolutePath();
            ajouterAnnonce(imagePath); // Method to add Annonce with image to the database
        }
    }


    @FXML
    private Optional<Annonce> showUpdateDialog(Annonce annonce) {
        // Create a dialog for the user to input new values
        Dialog<Annonce> dialog = new Dialog<>();
        dialog.setTitle("Update Annonce");
        dialog.setHeaderText("Update Annonce");

        // Set the button types
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create text fields and labels for each attribute
        TextField nomField = new TextField(annonce.getNom_du_plat());
        TextField descriptionField = new TextField(annonce.getDescription_du_plat());
        TextField prixField = new TextField(Float.toString(annonce.getPrix()));
        TextField ingredientsField = new TextField(annonce.getIngredients());
        TextField categorieField = new TextField(annonce.getCategorie_de_plat());


        // Add labels and fields to the dialog
        dialog.getDialogPane().setContent(new VBox(8,
                new Label("Nom du plat:"), nomField,
                new Label("Description du plat:"), descriptionField,
                new Label("Prix:"), prixField,
                new Label("Ingredients:"), ingredientsField,
                new Label("Categorie de plat:"), categorieField));

        // Enable/Disable update button depending on whether a text field is empty
        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(true);

        // Listen for text changes and enable/disable the update button accordingly
        nomField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });
        descriptionField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });
        prixField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });
        ingredientsField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });
        categorieField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue == null || newValue.trim().isEmpty());
        });

        // Convert the result to a person object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                annonce.setNom_du_plat(nomField.getText());
                annonce.setDescription_du_plat(descriptionField.getText());
                annonce.setPrix(Float.parseFloat(prixField.getText()));
                annonce.setIngredients(ingredientsField.getText());
                annonce.setCategorie_de_plat(categorieField.getText());
                return annonce;
            }
            return null;
        });

        Optional<Annonce> result = dialog.showAndWait();

        // If an update was made, refresh the TableView
        result.ifPresent(updatedAnnonce -> {
            sa.update(updatedAnnonce);
            populateTilePane();
        });
        return result;
    }

    private Button createDeleteButton(Annonce annonce) {
        Button button = new Button("Delete");
        button.setOnAction(event -> {
            // Handle delete button click
            sa.delete(annonce); // Assuming you have a delete method in your ServiceAnnonce
            populateTilePane(); // Refresh TableView after deletion

        });
        return button;
    }



    @FXML
    void showPreviousPage() {
        if (currentPage > 0) {
            currentPage--;
            populateTilePane();
        }
    }
    @FXML
    void showNextPage() {
        int totalItems = sa.getAll().size();
        int totalPages = (totalItems + itemsPerPage - 1) / itemsPerPage;
        if (currentPage < totalPages - 1) {
            currentPage++;
            populateTilePane();
        }
    }
    @FXML
    private void updatePaginationButtons() {
        int totalItems = sa.getAll().size();
        int totalPages = (totalItems + itemsPerPage - 1) / itemsPerPage;
        previousPageButton.setDisable(currentPage <= 0);
        nextPageButton.setDisable(currentPage >= totalPages - 1);
    }

    @FXML
    private void updateTilePane(String searchQuery) {
        tilePaneContainer.getChildren().clear();

        List<Annonce> annonces = sa.getAll();

        int start = currentPage * itemsPerPage;
        int end = Math.min(start + itemsPerPage, annonces.size());

        TilePane currentTilePane = new TilePane();
        currentTilePane.setPrefColumns(3);
        currentTilePane.setHgap(10);

        for (int i = start; i < end; i++) {
            Annonce annonce = annonces.get(i);

            // Check if the nom_plat contains the search query
            if (annonce.getNom_du_plat().toLowerCase().contains(searchQuery.toLowerCase())) {
                try {
                    ImageView imageView = new ImageView(new Image(new File(annonce.getImagePlat()).toURI().toString()));
                    imageView.setFitWidth(200);
                    imageView.setFitHeight(200);
                    imageView.setOnMouseClicked(event -> displayAnnonceInfo(annonce));
                    currentTilePane.getChildren().add(imageView);
                } catch (Exception e) {
                    System.err.println("Error loading image: " + e.getMessage());
                }
            }
        }

        tilePaneContainer.getChildren().add(currentTilePane);
        updatePaginationButtons();
    }

    @FXML
    void searchTextChanged(KeyEvent event) {
        String searchQuery = searchField.getText();
        updateTilePane(searchQuery);
    }

    public void sortAnnonces(ActionEvent actionEvent) {
    }
}




