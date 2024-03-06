package tn.esprit.services;

import tn.esprit.interfaces.IEService;
import tn.esprit.interfaces.IService;
import tn.esprit.models.Events;
import tn.esprit.utils.MyDataBase;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ServiceEvents implements IEService<Events> {
    private Connection cnx;

    public ServiceEvents() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Events e) {

        String qry = "INSERT INTO `Events`(`name`, `date`, `location`,`photo`,`category_id`) VALUES (?,?,?,?,?)";
        try {
            java.util.Date utilDate = e.getDate();
            Date sqlDate = new Date(utilDate.getTime());
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, e.getName());
            stm.setDate(2, sqlDate);
            stm.setString(3, e.getLocation());
            //FileInputStream fs= new FileInputStream(e.getPhoto());
            stm.setBinaryStream(4, e.getPhoto());
            stm.setInt(5, e.getCategory_id());



            stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }
    public ArrayList<Events> getAllByTriDesc() {

        ArrayList<Events> eventsArray = new ArrayList();
        String qry ="SELECT * FROM `events` ORDER BY name DESC";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Events e = new Events();

                e.setEvent_id(rs.getInt(1));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate(3));
                e.setLocation(rs.getString("location"));
                e.setCategory_id(rs.getInt("category_id"));

                // Retrieve the BLOB data as an InputStream

                InputStream inputStream = rs.getBinaryStream("photo");
                if (inputStream != null) {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] photoBytes = outputStream.toByteArray();

                        // Now you have the photo data as byte array, you can create a ByteArrayInputStream from it
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);

                        // Convert ByteArrayInputStream to FileInputStream
                        File tempFile = File.createTempFile("temp_photo", ".tmp");
                        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                            byte[] byteArray = new byte[1024];
                            int len;
                            while ((len = byteArrayInputStream.read(byteArray)) > 0) {
                                fos.write(byteArray, 0, len);
                            }
                        }

                        // Set the FileInputStream to your Events object
                        FileInputStream fileInputStream = new FileInputStream(tempFile);
                        e.setPhoto(fileInputStream);
                    } catch (IOException xe) {
                        xe.printStackTrace(); // Handle the exception appropriately
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace(); // Handle the exception appropriately
                        }
                    }
                }
                eventsArray.add(e);


                // Add 'e' to your collection or do whatever you need to do with it
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        System.out.println("rtrt");
        System.out.println(eventsArray);
        return eventsArray;
    }
    public ArrayList<Events> getAllByTriAsd() {

        ArrayList<Events> eventsArray = new ArrayList();
        String qry ="SELECT * FROM `events` ORDER BY name ASC";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Events e = new Events();

                e.setEvent_id(rs.getInt(1));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate(3));
                e.setLocation(rs.getString("location"));
                e.setCategory_id(rs.getInt("category_id"));

                // Retrieve the BLOB data as an InputStream

                InputStream inputStream = rs.getBinaryStream("photo");
                if (inputStream != null) {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] photoBytes = outputStream.toByteArray();

                        // Now you have the photo data as byte array, you can create a ByteArrayInputStream from it
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);

                        // Convert ByteArrayInputStream to FileInputStream
                        File tempFile = File.createTempFile("temp_photo", ".tmp");
                        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                            byte[] byteArray = new byte[1024];
                            int len;
                            while ((len = byteArrayInputStream.read(byteArray)) > 0) {
                                fos.write(byteArray, 0, len);
                            }
                        }

                        // Set the FileInputStream to your Events object
                        FileInputStream fileInputStream = new FileInputStream(tempFile);
                        e.setPhoto(fileInputStream);
                    } catch (IOException xe) {
                        xe.printStackTrace(); // Handle the exception appropriately
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace(); // Handle the exception appropriately
                        }
                    }
                }
                eventsArray.add(e);


                // Add 'e' to your collection or do whatever you need to do with it
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        System.out.println("rtrt");
        System.out.println(eventsArray);
        return eventsArray;
    }
    @Override
    public ArrayList<Events> getAll() {

        ArrayList<Events> eventsArray = new ArrayList();
        String qry = "SELECT * FROM `events`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Events e = new Events();

                e.setEvent_id(rs.getInt(1));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate(3));
                e.setLocation(rs.getString("location"));
                e.setCategory_id(rs.getInt("category_id"));

                // Retrieve the BLOB data as an InputStream

                InputStream inputStream = rs.getBinaryStream("photo");
                if (inputStream != null) {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] photoBytes = outputStream.toByteArray();

                        // Now you have the photo data as byte array, you can create a ByteArrayInputStream from it
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);

                        // Convert ByteArrayInputStream to FileInputStream
                        File tempFile = File.createTempFile("temp_photo", ".tmp");
                        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                            byte[] byteArray = new byte[1024];
                            int len;
                            while ((len = byteArrayInputStream.read(byteArray)) > 0) {
                                fos.write(byteArray, 0, len);
                            }
                        }

                        // Set the FileInputStream to your Events object
                        FileInputStream fileInputStream = new FileInputStream(tempFile);
                        e.setPhoto(fileInputStream);
                    } catch (IOException xe) {
                        xe.printStackTrace(); // Handle the exception appropriately
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace(); // Handle the exception appropriately
                        }
                    }
                }
                eventsArray.add(e);


                // Add 'e' to your collection or do whatever you need to do with it
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        System.out.println("rtrt");
        System.out.println(eventsArray);
        return eventsArray;
    }

    @Override
    public void update(Events e) {
        try {
            java.util.Date utilDate = e.getDate();
            Date sqlDate = new Date(utilDate.getTime());
            String req = "UPDATE `events` SET `name` = ?, `date` = ?, `category_id` = ?, `location` = ?, `photo` = ? WHERE `events`.`event_id` = ?";
            PreparedStatement stmt = cnx.prepareStatement(req);

// Assuming `stmt` is your PreparedStatement object
            stmt.setString(1, e.getName());
            stmt.setDate(2, sqlDate); // Assuming sqlDate is a java.sql.Date object
            stmt.setInt(3, e.getCategory_id());
            stmt.setString(4, e.getLocation());
            stmt.setBinaryStream(5, e.getPhoto());
            stmt.setInt(6, e.getEvent_id());



// Execute the update statement
            stmt.executeUpdate();
            if (stmt.executeUpdate(req) > 0) {
                System.out.println("Events updated successfully!");
            } else {
                System.out.println("No Events updated!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean delete(Events events) {
        try {
            String req = "DELETE FROM `events` WHERE event_id = " + events.getEvent_id();
            ;
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Events deleted successfully!");
            } else {
                System.out.println("No Events deleted!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return false;

    }

    public  String fetchEventNameFromDatabase(int eventId) {
        String eventName = null;
        try {

            String sql = "SELECT name FROM events WHERE event_id = ?";
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setInt(1, eventId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                eventName = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return eventName;
        }

    }


    public int getIdByName(Object value) {
        int id = 0;
        String qry = "SELECT event_id FROM `events` WHERE name = '" + value + "'";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while ( rs.next() ){
                id = rs.getInt("event_id");
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public ArrayList<String>  getAllName() {

        ArrayList<String> categorieArray = new ArrayList();
        String qry = "SELECT name FROM `events`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                // e.setName(rs.getString("name"));




                //e.setButton(new Button("Supprimer"));


                categorieArray.add(rs.getString("name"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return categorieArray;
    }
    public ArrayList<Events>  getByName(String n) {

        ArrayList<Events> eventsArray = new ArrayList();
        String qry = "SELECT * FROM `events`WHERE name = '" + n + "'";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Events e = new Events();

                e.setEvent_id(rs.getInt(1));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate(3));
                e.setLocation(rs.getString("location"));
                e.setCategory_id(rs.getInt("category_id"));

                // Retrieve the BLOB data as an InputStream

                InputStream inputStream = rs.getBinaryStream("photo");
                if (inputStream != null) {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] photoBytes = outputStream.toByteArray();

                        // Now you have the photo data as byte array, you can create a ByteArrayInputStream from it
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(photoBytes);

                        // Convert ByteArrayInputStream to FileInputStream
                        File tempFile = File.createTempFile("temp_photo", ".tmp");
                        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                            byte[] byteArray = new byte[1024];
                            int len;
                            while ((len = byteArrayInputStream.read(byteArray)) > 0) {
                                fos.write(byteArray, 0, len);
                            }
                        }

                        // Set the FileInputStream to your Events object
                        FileInputStream fileInputStream = new FileInputStream(tempFile);
                        e.setPhoto(fileInputStream);
                    } catch (IOException xe) {
                        xe.printStackTrace(); // Handle the exception appropriately
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace(); // Handle the exception appropriately
                        }
                    }
                }
                eventsArray.add(e);


                // Add 'e' to your collection or do whatever you need to do with it
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        System.out.println("rtrt");
        System.out.println(eventsArray);
        return eventsArray;
    }
}