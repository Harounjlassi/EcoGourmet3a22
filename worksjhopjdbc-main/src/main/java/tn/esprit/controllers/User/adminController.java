package tn.esprit.controllers.User;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.User.User;
import tn.esprit.services.User.UserService;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class adminController implements Serializable {
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

    private UserService userService = new UserService();



    @FXML
    public void initialize() {
        // Initialize column properties
        connection = MyDataBase.getInstance().getCnx();

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
        t_prenom111.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        t_email111.setCellValueFactory(new PropertyValueFactory<>("email"));
        t_num111.setCellValueFactory(new PropertyValueFactory<>("numero"));
        t_action111.setCellValueFactory(new PropertyValueFactory<>("action"));

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


                users.add(new User(nom,prenom,email,numero));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void handleAddUser(ActionEvent actionEvent) {

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


    public void handleModifier(ActionEvent actionEvent) {
    }

    public void handleSupprimer(ActionEvent actionEvent) {
    }
}

