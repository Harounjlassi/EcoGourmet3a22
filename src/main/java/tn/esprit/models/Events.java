package tn.esprit.models;

import java.io.FileInputStream;
import java.util.Date;

public class Events {
    private int event_id;
    private String name; // Changed from int to String for event name
    private Date date; // Changed from date to Date with capital D
    private String location;
    private int category_id; // Changed from String to int for category_id
    private FileInputStream photo;


    public Events(){};
    public Events(int event_id, String name, Date date, String location, int category_id, FileInputStream photo) {
        this.event_id = event_id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.category_id = category_id;
        this.photo = photo;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return  date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public FileInputStream getPhoto() {
        return photo;
    }

    public void setPhoto(FileInputStream photo) {
        this.photo = photo;
    }



    @Override
    public String toString() {
        return "Events{" +
                "event_id=" + event_id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", category_id=" + category_id +
                ", photo='" + photo + '\'' +
                '}';
    }
}
