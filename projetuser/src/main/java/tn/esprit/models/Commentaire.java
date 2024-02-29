package tn.esprit.models;

public class Commentaire {
    private int commentaireId;
    private int userId;
    private int annonceId;
    private String comment;

    public Commentaire() {
    }

    public Commentaire(int commentaireId, int userId, int annonceId, String comment) {
        this.commentaireId = commentaireId;
        this.userId = userId;
        this.annonceId = annonceId;
        this.comment = comment;
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

    public int getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(int annonceId) {
        this.annonceId = annonceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "commentaireId=" + commentaireId +
                ", userId=" + userId +
                ", annonceId=" + annonceId +
                ", comment='" + comment + '\'' +
                '}';
    }

}
