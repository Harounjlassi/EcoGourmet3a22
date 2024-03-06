package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.models.User;
import tn.esprit.services.UserService;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class adminController {
    @FXML
    public Tab adminaff;
    public Tab Chefaff;
    public Tab livreuraff;
    public Tab clientaff;
    public TextField Nom;
    public TextField Prenom;
    Connection connection;
    UserService uti = new UserService();

    public TextField name;
    public TextField prenom;
    public TextField Email;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> t_name;

    @FXML
    private TableColumn<User, String> t_prenom;

    @FXML
    private TableColumn<User, String> t_num;

    @FXML
    private TableColumn<User, String> t_email;

    @FXML
    private TableColumn<User, String> t_action;

    @FXML
    private TableColumn<User, String> t_UserID;

    @FXML
    private TableColumn<User,String> delete;

    public PasswordField mdp;
    public ToggleButton showPasswordToggleButton;
    public SplitMenuButton Role;
    public MenuItem client;
    public MenuItem livreur;
    public MenuItem chef;
    public MenuItem admin;
    public TextField tel;

    @FXML
    private TableView<User> table11;

    @FXML
    private TableColumn<User, String> t_name11;

    @FXML
    private TableColumn<User, String> t_prenom11;

    @FXML
    private TableColumn<User, String> t_num11;

    @FXML
    private TableColumn<User, String> t_email11;

    @FXML
    private TableColumn<User, String> t_action11;

    @FXML
    private TableColumn<User, String> t_UserID111;



    @FXML
    private TableView<User> table111;

    @FXML
    private TableColumn<User, String> t_name111;

    @FXML
    private TableColumn<User, String> t_prenom111;

    @FXML
    private TableColumn<User, String> t_num111;

    @FXML
    private TableColumn<User, String> t_email111;

    @FXML
    private TableColumn<User, String> t_action111;

    @FXML
    private TableColumn<User, String> t_UserID1111;

    User user;

    @FXML
    private TableView<User> table1;

    @FXML
    private TableColumn<User, String> t_name1;

    @FXML
    private TableColumn<User, String> t_prenom1;

    @FXML
    private TableColumn<User, String> t_num1;


    @FXML
    private TableColumn<User, String> t_email1;

    @FXML
    private TableColumn<User, String> t_action1;

    @FXML
    private TableColumn<User,String> t_UserID11;

    private final UserService userService = new UserService();



    @FXML
    public void initialize() {
        // Initialize column properties
        connection = MyDataBase.getInstance().getCnx();


        // Afficher la table des clients dès le lancement de l'application
        List<User> clients = getUsersByRole("client");
        table.getItems().setAll(clients);
        // Load data into TableView when the corresponding tabs are selected

        // Configure event handlers for tab selection
        clientaff.setOnSelectionChanged(event -> {
            if (clientaff.isSelected()) {
                List<User> client = getUsersByRole("client");
                table.getItems().setAll(client);
            }
        });

        livreuraff.setOnSelectionChanged(event -> {
            if (livreuraff.isSelected()) {
                List<User> livreur = getUsersByRole("livreur");
                table1.getItems().setAll(livreur);
            }
        });

        Chefaff.setOnSelectionChanged(event -> {
            if (Chefaff.isSelected()) {
                List<User> chef = getUsersByRole("chef");
                table11.getItems().setAll(chef);
            }
        });

        adminaff.setOnSelectionChanged(event -> {
            if (adminaff.isSelected()) {
                List<User> admin = getUsersByRole("admin");
                table111.getItems().setAll(admin);
            }
        });


        t_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        t_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        t_num.setCellValueFactory(new PropertyValueFactory<>("numero"));
        t_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        t_action.setCellValueFactory(new PropertyValueFactory<>("action"));

        t_name1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        t_prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        t_email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        t_num1.setCellValueFactory(new PropertyValueFactory<>("numero"));
        t_action1.setCellValueFactory(new PropertyValueFactory<>("action"));

        t_name11.setCellValueFactory(new PropertyValueFactory<>("nom"));
        t_prenom11.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        t_email11.setCellValueFactory(new PropertyValueFactory<>("email"));
        t_num11.setCellValueFactory(new PropertyValueFactory<>("numero"));
        t_action11.setCellValueFactory(new PropertyValueFactory<>("action"));

        t_name111.setCellValueFactory(new PropertyValueFactory<>("nom"));
        t_name111.setCellValueFactory(new PropertyValueFactory<>("UserID"));

        t_prenom111.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        t_email111.setCellValueFactory(new PropertyValueFactory<>("email"));
        t_num111.setCellValueFactory(new PropertyValueFactory<>("numero"));
        t_action111.setCellValueFactory(new PropertyValueFactory<>("action"));
        // Ajouter une colonne pour les actions
        // Ajouter une colonne pour les actions
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, String> call(final TableColumn<User, String> param) {
                final TableCell<User, String> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Supprimer");
                    private final Button editButton = new Button("Modifier");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            user = getTableView().getItems().get(getIndex());

                            userService.supprimer(user.getUserID());

                            // Rafraîchir la TableView
                            getTableView().getItems().remove(user);
                        });

                        editButton.setOnAction((ActionEvent event) -> {
                            // Récupérer les données de l'utilisateur sélectionné
                            user = getTableView().getItems().get(getIndex());


                            // Afficher le formulaire de modification
                            try {
                                showEditForm(user);

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            // Rafraîchir la TableView
                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox actionContainer = new HBox(deleteButton, editButton);
                            actionContainer.setSpacing(5);
                            setGraphic(actionContainer);
                        }
                    }
                };
                return cell;
            }
        };


        t_action.setCellFactory(cellFactory);
        t_action1.setCellFactory(cellFactory);
        t_action11.setCellFactory(cellFactory);
        t_action111.setCellFactory(cellFactory);





    }private void showEditForm(User user) throws SQLException {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Modifier Utilisateur");

        // Créer des champs de texte pour les données utilisateur
        TextField nomField = new TextField(user.getNom());
        TextField idField = new TextField(String.valueOf(user.getUserID()));

        connection = MyDataBase.getInstance().getCnx();

        String req = "select Password,Role from user where UserID = '" + user.getUserID() + "'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        if (rs.next()) {
            String password = rs.getString("Password");
            String role = rs.getString("Role");
            System.out.println(password + role);


            TextField prenomField = new TextField(user.getPrenom());
            TextField emailField = new TextField(user.getEmail());
            TextField numeroField = new TextField(user.getNumero());
            TextField passwordField = new TextField(password);
            TextField roleField = new TextField(role);

            // Ajouter les champs de texte au contenu de la boîte de dialogue
            GridPane grid = new GridPane();
            grid.add(new Label("ID Utilisateur:"), 0, 0);
            grid.add(idField, 1, 0);
            grid.add(new Label("Nom:"), 0, 1);
            grid.add(nomField, 1, 1);
            grid.add(new Label("Prénom:"), 0, 2);
            grid.add(prenomField, 1, 2);
            grid.add(new Label("Email:"), 0, 3);
            grid.add(emailField, 1, 3);
            grid.add(new Label("Numéro:"), 0, 4);
            grid.add(numeroField, 1, 4);
            grid.add(new Label("Mot de passe:"), 0, 5);
            grid.add(passwordField, 1, 5);
            grid.add(new Label("Rôle:"), 0, 6);
            grid.add(roleField, 1, 6);
            dialog.getDialogPane().setContent(grid);

            // Ajouter des boutons Modifier et Annuler
            ButtonType modifierButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

            // Attendre que l'utilisateur clique sur Modifier ou Annuler, puis récupérer les données du formulaire
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == modifierButtonType) {
                    user.setUserID(Integer.parseInt(idField.getText()));
                    user.setNom(nomField.getText());
                    user.setPrenom(prenomField.getText());
                    user.setEmail(emailField.getText());
                    user.setNumero(numeroField.getText());
                    user.setPassword(passwordField.getText());
                    user.setRole(roleField.getText());

                    return user;
                }
                return null;
            });

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            dialog.showAndWait().ifPresent(updatedUser -> {
                // Mettre à jour les données de l'utilisateur dans la base de données
                UserService userService = new UserService();
                userService.modifier(updatedUser);
                List<User> updatedAdminList = getUsersByRole(updatedUser.getRole());
                table.getItems().setAll(updatedAdminList);
                table1.getItems().setAll(updatedAdminList);
                table11.getItems().setAll(updatedAdminList);
                table111.getItems().setAll(updatedAdminList);

            });
        }
    }
    private List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM user WHERE Role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String nom = resultSet.getString("Nom");
                String prenom = resultSet.getString("Prenom");
                String email = resultSet.getString("Email");
                String numero = resultSet.getString("Numero");



                users.add(new User(id,nom, prenom, email, numero));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }



    public void togglePasswordVisibility(ActionEvent actionEvent) {
        if (showPasswordToggleButton.isSelected()) {
            // Show the password
            mdp.setPromptText(mdp.getText());
            mdp.setText("");
            mdp.setDisable(true);
        } else {
            // Hide the password
            mdp.setText(mdp.getPromptText());
            mdp.setPromptText("");
            mdp.setDisable(false);
        }

    }

    public void chef(ActionEvent actionEvent) {
        Role.setText("chef");
    }

    public void client(ActionEvent actionEvent) {
        Role.setText("client");
    }

    public void livreur(ActionEvent actionEvent) {
        Role.setText("livreur");
    }


    public void admin(ActionEvent actionEvent) {
        Role.setText("admin");

    }

    public void Ajouter(ActionEvent actionEvent) {
        // Basic input validation
        if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Email.getText().isEmpty() || mdp.getText().isEmpty() || tel.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Veuillez remplir tous les champs.");
            return;
        }

        // Validate email format
        if (!isValidEmail(Email.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Veuillez entrer une adresse email valide.");
            return;
        }

        // Validate password strength
        if (!isValidPassword(mdp.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule et un chiffre.");
            return;
        }

        // Validate phone number
        if (!isValidTel(tel.getText())) {
            return;
        }

        connection = MyDataBase.getInstance().getCnx();
        User u = new User();
        u.setEmail(Email.getText());
        u.setNom(Nom.getText());
        u.setPrenom(Prenom.getText());
        u.setRole(Role.getText());
        u.setPassword(mdp.getText());
        u.setNumero(tel.getText());
        uti.add(u);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Bien ajouté");



    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
    private boolean isValidEmail(String email) {
        // Use a simple regular expression for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least 8 characters, one uppercase letter, one lowercase letter, and one digit
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(passwordRegex);
    }

    private boolean isValidTel(String tel) {
        // Validate phone number length
        if (tel.length() != 8) {
            showAlert(Alert.AlertType.ERROR, "Error", "Le numéro de téléphone doit contenir exactement 8 chiffres.");
            return false;
        }
        return true;
    }


}
