package tn.esprit.models;


public class Votes {
    private int id;
    private String commentaire	;
    private int vote_value;
    private int user_id;
    private int event_id;

    public Votes() {
    }

    public Votes(int id, String commentaire, int vote_value, int user_id, int event_id) {
        this.id = id;
        this.commentaire = commentaire;
        this.vote_value = vote_value;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getVote_value() {
        return vote_value;
    }

    public void setVote_value(int vote_value) {
        this.vote_value = vote_value;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "Votes{" +
                "id=" + id +
                ", commentaire='" + commentaire + '\'' +
                ", vote_value=" + vote_value +
                ", user_id=" + user_id +
                ", event_id=" + event_id +
                '}';
    }
}
