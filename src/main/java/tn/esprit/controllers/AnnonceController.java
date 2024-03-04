package tn.esprit.controllers;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.DialogPane;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import tn.esprit.models.Annonce;
import tn.esprit.models.Commentaire;
import tn.esprit.services.ServiceAnnonce;
import tn.esprit.services.panierService;
import tn.esprit.models.Panier;
import tn.esprit.services.ServiceCommentaire;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AnnonceController {
    @FXML
    public Button plusButton;
    @FXML
    public Button openChatbotButton;
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
    private BorderPane AP;
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
    private TilePane tilePane;
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
    private HBox currentHBOX;
    @FXML
    private final ServiceAnnonce sa = new ServiceAnnonce();
    private final ServiceCommentaire comment = new ServiceCommentaire();
    private int currentPage = 0;
    private int itemsPerPage = 8;
private panierService panierService;
panierService p =new panierService();

    List<String> badWords = Arrays.asList("fuck", "dick", "zebi");

    @FXML
    public void ajouterAnnonce(ActionEvent actionEvent) {
    }

//@FXML
//    public void handleAnnouncementAdded() {
//
//        populateTilePane();
//
//
//    }
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
    private void displayNutritionalInfo(String dishName) {
        String apiKey = "G44c2zitJ9rVo8ztYU5RpsXC7KGQIvT8U8xokA4O"; // Replace "YOUR_API_KEY" with your actual API key
        String url = "https://api.nal.usda.gov/fdc/v1/foods/search?query=" + dishName + "&api_key=" + apiKey;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonElement jsonResponse = JsonParser.parseString(response.toString());
                if (jsonResponse.isJsonObject()) {
                    JsonObject jsonObject = jsonResponse.getAsJsonObject();
                    JsonElement foodsElement = jsonObject.get("foods");
                    if (foodsElement.isJsonArray()) {
                        JsonArray foodsArray = foodsElement.getAsJsonArray();
                        if (!foodsArray.isEmpty()) {
                            JsonObject firstFood = foodsArray.get(0).getAsJsonObject();
                            String description = firstFood.get("description").getAsString();
                            JsonArray foodNutrients = firstFood.getAsJsonArray("foodNutrients");

                            // Construct a string containing nutritional information
                            StringBuilder nutritionalInfo = new StringBuilder();
                            for (JsonElement nutrient : foodNutrients) {
                                JsonObject nutrientObj = nutrient.getAsJsonObject();
                                String nutrientName = nutrientObj.get("nutrientName").getAsString();
                                String nutrientValue = nutrientObj.get("value").getAsString();
                                nutritionalInfo.append(nutrientName).append(": ").append(nutrientValue).append("\n");
                            }

                            showAlert(Alert.AlertType.INFORMATION, "Nutritional Information",
                                    "Nutritional Information for " + description, nutritionalInfo.toString());
                        } else {
                            showAlert(Alert.AlertType.WARNING, "Nutritional Information",
                                    "No nutritional information found", "No nutritional information found for the specified dish.");
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Invalid JSON Response",
                                "The 'foods' field in the JSON response is not a JSON array.");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid JSON Response",
                            "The JSON response from the API is not valid.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch nutritional information",
                        "Failed to fetch nutritional information from the API. Please try again later.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "IO Exception",
                    "An error occurred while fetching nutritional information. Please try again later.");
        }

    }




    @FXML
    private void displayAnnonceInfo(Annonce annonce) {
        // Create a dialog to display Annonce information
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Annonce Information");
        dialog.setHeaderText("Annonce Information");

        // Get the dialog pane
        DialogPane dialogPane = dialog.getDialogPane();

        // Set the background color
        dialogPane.setStyle("-fx-background-color: #7a8727;"); // Green background color

        VBox content = new VBox();
        content.getStyleClass().add("custom-dialog-content"); // Add custom style class

        content.getChildren().add(new Label("Nom: " + annonce.getNom_du_plat()));
        content.getChildren().add(new Label("Description: " + annonce.getDescription_du_plat()));
        content.getChildren().add(new Label("Prix: " + annonce.getPrix()));
        content.getChildren().add(new Label("Ingrédients: " + annonce.getIngredients()));
        content.getChildren().add(new Label("Catégorie: " + annonce.getCategorie_de_plat()));
        content.getChildren().add(new Label("Quantité: " + annonce.getQuantite())); // Add "quantite" field

        // Button to display nutritional info
        Button nutritionalInfoButton = new Button("Nutritional Info");
        nutritionalInfoButton.setOnAction(event -> {
            // Call a method to fetch and display nutritional info based on the name of the dish
            displayNutritionalInfo(annonce.getNom_du_plat());
        });

        // Add the nutritional info button to an HBox
        HBox buttonBox = new HBox();

        buttonBox.getChildren().add(nutritionalInfoButton);
        content.getChildren().add(buttonBox);
        Button addToCartButton = new Button("Ajouter au panier");
        addToCartButton.setOnAction(event -> {
            // Call a method to add the current dish to the cart

            p.ajouterAnnonceAuPanier(loginController.id, annonce.getId_Annonce());

        });

// Add the "Ajouter au panier" button to the buttonBox
        buttonBox.getChildren().add(addToCartButton);

        dialogPane.setContent(content);

        dialogPane.getScene().getWindow().setOnCloseRequest(event -> {
            dialog.close();
        });
        int X=annonce.getUserID();
        // Show the dialog
        dialog.showAndWait();
        System.out.print(X);
    }

    List<Annonce> sortedAnnonces = new ArrayList<>();

    @FXML
    private void sortAnnonces() {
        String selectedSort = Tri.getValue();

        if (selectedSort != null && !selectedSort.isEmpty()) {
            List<Annonce> annonces = sa.getAll(); // Assuming sa is your service to retrieve announcements

            // Sorting logic...
            switch (selectedSort) {
                case "Ascendant":
                    sortedAnnonces = annonces.stream()
                            .sorted(Comparator.comparingDouble(tn.esprit.models.Annonce::getPrix))
                            .collect(Collectors.toList());
                    break;
                case "Descendant":
                    sortedAnnonces = annonces.stream()
                            .sorted(Comparator.comparingDouble(tn.esprit.models.Annonce::getPrix).reversed())
                            .collect(Collectors.toList());
                    break;
                case "Par defaut":
                    // Set sortedAnnonces to null to indicate the default display
                    sortedAnnonces = annonces;
                    break;
                case "Likes Ascendant":
                    sortedAnnonces = sortAnnoncesByLikes(annonces, true);
                    break;
                case "Likes Descendant":
                    sortedAnnonces = sortAnnoncesByLikes(annonces, false);
                    break;
                default:
                    // Handle other cases as needed
                    sortedAnnonces = annonces; // Assign the unsorted list
                    break;
            }

            // Update your UI to reflect the sorted list or default display
            if (sortedAnnonces != null) {
                populateTilePane(); // Call populateTilePane without passing arguments
            } else {
                // Display the default list (unsorted)
                populateTilePaneWithDefault();
            }
        }
    }

    private List<Annonce> sortAnnoncesByLikes(List<Annonce> annonces, boolean ascending) {
        return annonces.stream()
                .sorted((a1, a2) -> {
                    int likesCount1 = 0;
                    try {
                        likesCount1 = comment.getCommentsForAnnouncement(a1.getId_Annonce()).stream()
                                .mapToInt(Commentaire::getLikesCount).sum();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    int likesCount2 = 0;
                    try {
                        likesCount2 = comment.getCommentsForAnnouncement(a2.getId_Annonce()).stream()
                                .mapToInt(Commentaire::getLikesCount).sum();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return ascending ? Integer.compare(likesCount1, likesCount2) : Integer.compare(likesCount2, likesCount1);
                })
                .collect(Collectors.toList());
    }

    // Method to populate tile pane with default display (unsorted)
    private void populateTilePaneWithDefault() {
        // Clear existing children from the tile pane
        tilePane.getChildren().clear();

        // Populate tile pane with default display logic here...
    }

    @FXML
    private void populateTilePane() {
        // Clear existing children from the tile pane
        String searchText = searchField.getText().toLowerCase();
        tilePane.getChildren().clear();

        // Get all announcements
        List<Annonce> allAnnonces = sa.getAll();

        // Apply search filter
        List<Annonce> filteredAnnonces = sortedAnnonces.stream()
                .filter(annonce ->
                        annonce.getNom_du_plat().toLowerCase().contains(searchText) || // Filter by name
                                annonce.getIngredients().toLowerCase().contains(searchText)) // Filter by ingredients
                .collect(Collectors.toList());

        // Create tile panes for each announcement
        for (Annonce annonce : filteredAnnonces) {
            try {
                // Create ImageView for the announcement image
                ImageView imageView = new ImageView(new Image(new File(annonce.getImage_plat()).toURI().toString()));
                imageView.setFitWidth(150); // Adjust image width
                imageView.setFitHeight(150); // Adjust image height
                imageView.setOnMouseClicked(event -> displayAnnonceInfo(annonce));

                // Create VBox to hold image, comments, and text field
                VBox tileContent = new VBox();
                tileContent.getChildren().add(imageView);
                tileContent.setPadding(new Insets(5)); // Set padding for vertical spacing
                VBox.setMargin(imageView, new Insets(0, 0, 10, 0));
                HBox buttonBox = new HBox(); // Create an empty HBox
                buttonBox.setAlignment(Pos.CENTER); // Align buttons to the center
                tileContent.getChildren().add(buttonBox); // Add the button box to the tile content

                // Add event listeners for showing buttons on hover
                tileContent.setOnMouseEntered(e -> showButtons(buttonBox, annonce));
                tileContent.setOnMouseExited(e -> hideButtons(buttonBox));

                // Set user data to associate the announcement with the tile content
                tileContent.setUserData(annonce);

                // Fetch and display comments for the current announcement
                List<Commentaire> comments = comment.getCommentsForAnnouncement(annonce.getId_Annonce());
                for (Commentaire comment : comments) {
                    // Create HBox for each comment
                    HBox commentBox = new HBox();
                    commentBox.getStyleClass().add("comment-box");
                    // Create a label for the comment with the user's name
                    ServiceCommentaire C = new ServiceCommentaire();

                    String nomComplet = C.getUserName(comment.getUserId());
                    Label nameLabel = new Label(nomComplet + " : ");

                    // Create a label for the comment
                    Label commentLabel = new Label(comment.getComment());
                    commentLabel.getStyleClass().add("comment-label");  // Adjust font size as needed

                    // Add like button
                    ToggleButton likeButton = new ToggleButton("♡");
                    likeButton.getStyleClass().add("like-button");
                    likeButton.setStyle("-fx-font-size: 1.5em;");

                    // Like count label
                    Label likeCountLabel = new Label(String.valueOf(comment.getLikesCount()));
                    likeCountLabel.getStyleClass().add("like-count-label");

                    // Delete button
                    Button deleteButton = new Button("X");
                    deleteButton.getStyleClass().add("delete-button");

                    // Add components to the commentBox
                    commentBox.getChildren().addAll(nameLabel, commentLabel, likeButton, likeCountLabel, deleteButton);
                    commentBox.setSpacing(10); // Adjust the spacing as needed

// Align all components vertically centered
                    commentBox.setAlignment(Pos.CENTER_LEFT);

                    // Add like button and count to the comment box
                    HBox.setMargin(likeButton, new Insets(0, 5, 0, 5));
                    HBox.setMargin(likeCountLabel, new Insets(0, 5, 0, 0));

                    // Add event listeners for like button and delete button
                    likeButton.setOnAction(event -> {
                        try {
                            if (likeButton.isSelected()) {
                                // Check if the current user has already liked this comment
                                if (!C.hasUserLikedComment(comment.getCommentaireId(), loginController.id)) {
                                    // Increment likes count and update UI
                                    C.incrementLikesCount(comment.getCommentaireId());
                                    likeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                                    handleAnnouncementAdded();
                                } else {
                                    // If the user has already liked the comment, set the button back to unselected
                                    likeButton.setSelected(false);
                                }
                            } else {
                                // Decrement likes count and update UI
                                C.decrementLikesCount(comment.getCommentaireId());
                                likeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
                                handleAnnouncementAdded();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });

                    deleteButton.setOnAction(event -> {
                        // Handle delete comment action
                        try {
                            ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                            serviceCommentaire.deleteComment(comment.getCommentaireId()); // Implement deleteComment method in ServiceCommentaire class
                            // Refresh the tile pane to remove the deleted comment
                            handleAnnouncementAdded();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                    // Add double-click event handling to the comment label
                    commentLabel.setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                            // Double-click detected, enable editing if the comment belongs to the current user
                            if (comment.getUserId() == loginController.id || Objects.equals(loginController.role, "admin")) {
                                TextField updateField = new TextField(comment.getComment());
                                commentBox.getChildren().set(1, updateField); // Replace label with text field

                                // Add event listener for Enter key to save update
                                updateField.setOnKeyPressed(event -> {
                                    if (event.getCode() == KeyCode.ENTER) {
                                        String updatedComment = updateField.getText().trim();
                                        if (!updatedComment.isEmpty()) {
                                            comment.setComment(updatedComment);
                                            try {
                                                // Update the comment in the database
                                                ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                                                serviceCommentaire.updateComment(comment); // Implement updateComment method in ServiceCommentaire class
                                                // Refresh the tile pane to display the updated comment
                                                handleAnnouncementAdded();
                                            } catch (SQLException ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                });

                                // Request focus on the text field for editing
                                updateField.requestFocus();
                            }
                        }
                    });

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
                            commentaire.setUserId(loginController.id); // Set user ID for the comment
                            commentaire.setid_Annonce(annonce.getId_Annonce());
                            commentaire.setComment(commentText);
                            try {
                                // Add comment to the database
                                comment.addComment(commentaire);
                                // Clear the comment text field
                                commentField.clear();
                                // Refresh the tile pane to display the new comment
                                handleAnnouncementAdded();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                // Add comment field to the tile content
                tileContent.getChildren().add(commentField);

                // Add event listeners for showing update and delete buttons on hover
                tileContent.setOnMouseEntered(e -> showButtons(buttonBox, annonce));
                tileContent.setOnMouseExited(e -> hideButtons(buttonBox));

                // Add the tile content to the tile pane
                tilePane.getChildren().add(tileContent);

            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                updatePaginationButtons();
            }
        }
    }


    @FXML
    private void showButtons(HBox buttonBox, Annonce annonce) {
        // Check if the logged-in user is a chef and if the announcement is associated with them
        if (Objects.equals(loginController.role, "chef") && annonce.getUserID() == loginController.id) {
            Button updateButton = new Button("Update");
            Button deleteButton = new Button("Delete");

            // Add event listener for update button
            updateButton.setOnAction(event -> {
                Optional<Annonce> result = showUpdateDialog(annonce); // Open the update dialog
                result.ifPresent(updatedAnnonce -> {
                    sa.update(updatedAnnonce); // Update the announcement in the database
                    populateTilePane(); // Refresh the TilePane after updating
                });
            });

            // Add event listener for delete button
            deleteButton.setOnAction(event -> {
                sa.delete(annonce); // Delete the announcement
                tilePane.getChildren().remove(buttonBox.getParent()); // Remove the tile content from the tile pane
            });

            // Add buttons to the button box
            buttonBox.getChildren().addAll(updateButton, deleteButton);
        } else if (Objects.equals(loginController.role, "admin")) {
            Button updateButton = new Button("Update");
            Button deleteButton = new Button("Delete");

            // Add event listener for update button
            updateButton.setOnAction(event -> {
                Optional<Annonce> result = showUpdateDialog(annonce); // Open the update dialog
                result.ifPresent(updatedAnnonce -> {
                    sa.update(updatedAnnonce); // Update the announcement in the database
                    populateTilePane(); // Refresh the TilePane after updating
                });
            });

            // Add event listener for delete button
            deleteButton.setOnAction(event -> {
                sa.delete(annonce); // Delete the announcement
                tilePane.getChildren().remove(buttonBox.getParent()); // Remove the tile content from the tile pane
            });

            // Add buttons to the button box
            buttonBox.getChildren().addAll(updateButton, deleteButton);
        }
    }



    @FXML
    private void hideButtons(HBox buttonBox) {
        // Clear the button box
        buttonBox.getChildren().clear();
    }


    @FXML
    void initialize() {
       // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Tri.setItems(FXCollections.observableArrayList("Par defaut", "Ascendant", "Descendant", "Likes Ascendant", "Likes Descendant"));
        Tri.setValue("Par defaut"); // Set "Par default" as the default value


        sortedAnnonces = sa.getAll();
        if (Objects.equals(loginController.role, "client")) {
            // If the user is a client, hide the button
            plusButton.setVisible(false);
        } else {
            // If the user is not a client, show the button
            plusButton.setVisible(true);
        }


        populateTilePane();
    }
    private Button createUpdateButton(Annonce annonce) {
        Button button = new Button("Update");
        button.setOnAction(event -> {
            showUpdateDialog(annonce);
        });
        return button;
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
        TextField quantiteField = new TextField(Integer.toString(annonce.getQuantite())); // New field for quantité
        TextField adresseField = new TextField(annonce.getAdresse()); // New field for adresse

        // Add labels and fields to the dialog
        dialog.getDialogPane().setContent(new VBox(8,
                new Label("Nom du plat:"), nomField,
                new Label("Description du plat:"), descriptionField,
                new Label("Prix:"), prixField,
                new Label("Ingredients:"), ingredientsField,
                new Label("Categorie de plat:"), categorieField,
                new Label("Quantité:"), quantiteField, // Add label and field for quantité
                new Label("Adresse:"), adresseField // Add label and field for adresse
        ));

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
        quantiteField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });
        adresseField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });

        // Convert the result to a person object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                annonce.setNom_du_plat(nomField.getText());
                annonce.setDescription_du_plat(descriptionField.getText());
                annonce.setPrix(Float.parseFloat(prixField.getText()));
                annonce.setIngredients(ingredientsField.getText());
                annonce.setCategorie_de_plat(categorieField.getText());
                annonce.setQuantite(Integer.parseInt(quantiteField.getText())); // Update quantité
                annonce.setAdresse(adresseField.getText()); // Update adresse
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

    // Method to show update and delete buttons on hover

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

        previousPageButton.setDisable(currentPage <= 0); // Disable if already at the first page
        nextPageButton.setDisable(currentPage >= totalPages - 1); // Disable if already at the last page
    }


    @FXML
    void searchTextChanged(KeyEvent event) {
        String searchText = searchField.getText().toLowerCase();
        populateTilePane();
    }

    private int userId;

    // Setter method for userId
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @FXML
    void openAjouterAnnonce() {
        try {
            // Load the FXML file for ajouterAnnonce
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterAnnonce.fxml"));
            Parent root = loader.load();

            // Instantiate AjouterAnnonceController
            AjouterAnnonceController ajouterAnnonceController = loader.getController();

            // Pass the reference to AnnonceController to AjouterAnnonceController
            ajouterAnnonceController.setAnnonceController(this); // 'this' refers to AnnonceController


            // Create a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openChatbot(ActionEvent event) {
        try {
            // Load the FXML file for the chatbot interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chatbot.fxml"));
            Parent root = loader.load();

            // Create a new stage for the chatbot interface
            Stage stage = new Stage();
            stage.setTitle("Chatbot");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleAnnouncementAdded() {
        populateTilePane();
    }
}





