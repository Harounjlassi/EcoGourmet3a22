package tn.esprit.models;

public class Commentaire {
    private int commentaireId;
    private int userId;
    private int id_Annonce;
    private String comment,userName,userPrenom;

    private int likesCount; // New attribute for storing the number of likes

    public Commentaire() {
    }

    public Commentaire(int commentaireId, int userId, int id_Annonce, String comment) {
        this.commentaireId = commentaireId;
        this.userId = userId;
        this.id_Annonce = id_Annonce;
        this.comment = comment;
    }

    public String getUserPrenom() {
        return userPrenom;
    }

    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCommentaireId() {
        return commentaireId;
    }

    public void setCommentaireId(int commentaireId) {
        this.commentaireId = commentaireId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getid_Annonce() {
        return id_Annonce;
    }

    public void setid_Annonce(int id_Annonce) {
        this.id_Annonce = id_Annonce;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "commentaireId=" + commentaireId +
                ", userId=" + userId +
                ", annonceId=" + id_Annonce +
                ", comment='" + comment + '\'' +
                ", likesCount=" + likesCount +
                '}';
    }
}
