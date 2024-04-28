package com.example.poczta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    RadioButton pocztowka = new RadioButton();
    RadioButton list = new RadioButton();
    RadioButton paczka = new RadioButton();
    TextField kod = new TextField();
    Button sprawdz = new Button();
    Button zatwierdz = new Button();
    ToggleGroup grupa = new ToggleGroup();
    ImageView obraz = new ImageView();
    String rodzajPrzesylki = "";
    Text cena = new Text();
    Alert komunikat = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 350);
        stage.setTitle("Nadaj przesyłkę. PESEL: 00000000000");
        stage.setScene(scene);


        pocztowka = (RadioButton) scene.lookup("#pocztowka");
        list = (RadioButton) scene.lookup("#list");
        paczka = (RadioButton) scene.lookup("#paczka");
        pocztowka.setToggleGroup(grupa);
        list.setToggleGroup(grupa);
        paczka.setToggleGroup(grupa);
        obraz = (ImageView) scene.lookup("#obraz");
        obraz.setImage(new Image(String.valueOf(getClass().getResource("pocztowka.png"))));
        zatwierdz = (Button) scene.lookup("#zatwierdz");
        cena = (Text) scene.lookup("#cena");
        sprawdz = (Button) scene.lookup("#sprawdz");
        kod = (TextField) scene.lookup("#kod");

        sprawdz.setOnAction(actionEvent -> {
            rodzajPrzesylki = ((RadioButton) grupa.getSelectedToggle()).getId();
            switch (rodzajPrzesylki) {
                case "pocztowka":
                    cena.setText("Cena: 1 zł");
                    obraz.setImage(new Image(String.valueOf(getClass().getResource("pocztowka.png"))));
                    break;

                case "list":
                    cena.setText("Cena: 1,5 zł");
                    obraz.setImage(new Image(String.valueOf(getClass().getResource("list.png"))));
                    break;

                case "paczka":
                    cena.setText("Cena: 10 zł");
                    obraz.setImage(new Image(String.valueOf(getClass().getResource("paczka.png"))));
                    break;
            }

        });

        zatwierdz.setOnAction(actionEvent -> {
            String tekst = kod.getText();
            try {
                if (Integer.parseInt(tekst) >= 0 && tekst.length() == 5) {
                    komunikat.setContentText("Dane zostały wprowadzone");
                    komunikat.show();

                } else {
                    komunikat.setContentText("Nieprawidłowa liczba cyfr w kodzie pocztowym");
                    komunikat.show();
                }
            } catch (Exception e) {
                komunikat.setContentText("Kod pocztowy powinien składać się z samych cyfr");
                komunikat.show();
            }
        });


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}