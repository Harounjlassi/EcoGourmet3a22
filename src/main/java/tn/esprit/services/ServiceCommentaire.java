package tn.esprit.services;

import tn.esprit.models.Commentaire;
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
        String query = "INSERT INTO commentaire (id_Annonce, comment, userId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commentaire.getid_Annonce());
            statement.setString(2, commentaire.getComment());
            statement.setInt(3, commentaire.getUserId());
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
    public List<Commentaire> getCommentsForAnnouncement(int id_Annonce) throws SQLException {
        List<Commentaire> comments = new ArrayList<>();
        String query = "SELECT * FROM commentaire WHERE id_Annonce = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id_Annonce);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Commentaire commentaire = new Commentaire();
                    commentaire.setCommentaireId(resultSet.getInt("commentaireId"));
                    commentaire.setUserId(resultSet.getInt("userId"));
                    commentaire.setid_Annonce(resultSet.getInt("id_Annonce"));
                    commentaire.setComment(resultSet.getString("comment"));
                   commentaire.setLikesCount(resultSet.getInt("likes_count")); // Retrieve likes count from database
                    comments.add(commentaire);
                }
            }
        }
        return comments;
    }

    // Method to increment likes count for a comment
    public void incrementLikesCount(int commentId) throws SQLException {
        String query = "UPDATE commentaire SET likes_count = likes_count + 1 WHERE commentaireId = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        }
    }

    // Method to decrement likes count for a comment
    public void decrementLikesCount(int commentId) throws SQLException {
        String query = "UPDATE commentaire SET likes_count = likes_count - 1 WHERE commentaireId = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        }
    }
    // Method to check if the current user has already liked the comment
    public boolean hasUserLikedComment(int commentId, int userId) throws SQLException {
        String query = "SELECT likes_count FROM commentaire WHERE commentaireId = ? AND userId = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commentId);
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int likeCount = resultSet.getInt("likes_count");
                    return likeCount > 0;
                }
            }
        }
        return false;
    }
    public String getUserName(int userId) throws SQLException {
        String userName = null;
        String userPrenom=null;
        String fullName = null;
        String query = "SELECT nom ,prenom FROM user WHERE userId = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userName = resultSet.getString("Nom");
                    userPrenom=resultSet.getString("Prenom");
                }
                fullName = userName + " " + userPrenom;
            }
        }
        return fullName;
    }


}
