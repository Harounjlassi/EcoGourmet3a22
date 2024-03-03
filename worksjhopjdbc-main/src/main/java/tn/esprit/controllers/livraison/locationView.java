package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class locationView implements Initializable {

    @FXML
    private WebView webView;
    static float lat;
    static float lon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(getGoogleMapsHTML());


    }

    private String getGoogleMapsHTML() {
        String sh = "var latlng = new google.maps.LatLng(" + lat + "," + lon + ");";
        System.out.println(sh);

        String s = """
                        <!DOCTYPE html>
                        <html>
                        
                        <head>
                            <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
                            <style type="text/css">
                                html, body {
                                    height: 100%;
                                    margin: 0;
                                    padding: 0;
                                    overflow: hidden; /* Add this line to prevent scrolling */
                                }
                        
                                #map_canvas {
                                    height: 100%;
                                    background-color: #666970;
                                    margin: 0;
                                    padding: 0;
                                }
                        
                                #coordinates {
                                    position: absolute;
                                    top: 10px;
                                    left: 10px;
                                    background: white;
                                    padding: 5px;
                                }
                            </style>
                            <script type="text/javascript"
                                src="http://maps.google.com/maps/api/js?key=AIzaSyAgpCwkkTAaRSVoP6_UfKM7-IFspmpOTuA&sensor=false"></script>
                            <script type="text/javascript">
                                var map, geocoder, pickingMode = true;
                        
                                function initialize() {
                                    // Get user's location using IPGeolocation API
                                    fetch('https://api.ipgeolocation.io/ipgeo?apiKey=071613bbdf0149c89ee9ea39d5a287f3')
                                        .then(response => response.json())
                                        .then(data => {
                                            var lat = data.latitude;
                                            var lon = data.longitude;
                                            var latlng = new google.maps.LatLng(lat, lon);
                        
                                            var myOptions = {
                                                zoom: 14,
                                                center: latlng,
                                                mapTypeId: google.maps.MapTypeId.ROADMAP,
                                                mapTypeControl: false,
                                                navigationControl: false,
                                                streetViewControl: false,
                                                backgroundColor: "#666970"
                                            };
                        
                                            geocoder = new google.maps.Geocoder();
                                            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
                        
                                            // Place marker at the user's location
                                            var marker = new google.maps.Marker({
                                                position: latlng,
                                                map: map,
                                                title: 'Your Location',
                                                icon: {
                                                    url: 'https://maps.google.com/mapfiles/ms/micons/blue-dot.png', // Customize the marker icon
                                                    scaledSize: new google.maps.Size(30, 30) // Adjust the size of the marker
                                                }
                                            });
                        
                                            map.addListener('click', function (event) {
                                                if (pickingMode) {
                                                    placeMarker(event.latLng);
                                                }
                                            });
                                        })
                                        .catch(error => console.error('Error fetching location:', error));
                                }
                        
                                function placeMarker(location) {
                                    var marker = new google.maps.Marker({
                                        position: location,
                                        map: map
                                    });
                        
                                    // Update coordinates display
                                    document.getElementById("coordinates").innerHTML = "Latitude: " + location.lat() +
                                        "<br>Longitude: " + location.lng();
                        
                                    // Set the latitude and longitude values to hidden divs for later retrieval
                                    document.getElementById("latitude").innerHTML = location.lat();
                                    document.getElementById("longitude").innerHTML = location.lng();
                                }
                        
                                // Function to retrieve latitude
                                function getLatitude() {
                                    return document.getElementById("latitude").innerHTML;
                                }
                        
                                // Function to retrieve longitude
                                function getLongitude() {
                                    return document.getElementById("longitude").innerHTML;
                                }
                        
                                function setMapLocation(latitude, longitude) {
                                    var newLatLng = new google.maps.LatLng(latitude, longitude);
                                    map.setCenter(newLatLng);
                                }
                            </script>
                        </head>
                        
                        <body onload="initialize()" style="margin: 0; padding: 0;">
                            <div id="map_canvas" style="width:100%; height:100%;"></div>
                            <div id="coordinates"></div>
                            <div id="latitude" style="display: none;"></div>
                            <div id="longitude" style="display: none;"></div>
                        </body>
                        
                        </html>
                        
                """;
        return s;
    }
}