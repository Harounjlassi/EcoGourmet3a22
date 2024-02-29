package tn.esprit.services;

import tn.esprit.models.Annonce.Commentaire;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceCommentaire {
    private Connection cnx;

    // Constructor to initialize the connection
    public ServiceCommentaire() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    // Method to add a new comment
    public void addComment(Commentaire commentaire) throws SQLException {
        String query = "INSERT INTO commentaire (id_Annonce, comment) VALUES (?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            //statement.setInt(1, commentaire.getUserId());
            statement.setInt(1, commentaire.getAnnonceId());
            statement.setString(2, commentaire.getComment());
            statement.executeUpdate();
        }
    }

    // Method to update an existing comment
    public void updateComment(Commentaire commentaire) throws SQLException {
        String query = "UPDATE commentaire SET comment = ? WHERE commentaireId = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, commentaire.getComment());
            statement.setInt(2, commentaire.getCommentaireId());
            statement.executeUpdate();
        }
    }

    // Method to delete a comment
    public void deleteComment(int commentId) throws SQLException {
        String query = "DELETE FROM commentaire WHERE commentaireId = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        }
    }

    // Method to retrieve all comments for a specific announcement
    public List<Commentaire> getCommentsForAnnouncement(int annonceId) throws SQLException {
        List<Commentaire> comments = new ArrayList<>();
        String query = "SELECT * FROM commentaire WHERE id_Annonce = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, annonceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Commentaire commentaire = new Commentaire();
                    commentaire.setCommentaireId(resultSet.getInt("CommentaireId"));
                    commentaire.setUserId(resultSet.getInt("UserId"));
                    commentaire.setAnnonceId(resultSet.getInt("id_Annonce"));
                    commentaire.setComment(resultSet.getString("comment"));
                    comments.add(commentaire);
                }
            }
        }
        return comments;
    }
}

