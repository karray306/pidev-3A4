package com.esprit.veltun.gui.rackvelo.create;
import com.esprit.veltun.gui.rackvelo.view.RackveloDetailsController;
import com.esprit.veltun.services.RackVeloService;
import com.esprit.veltun.services.impl.RackVeloImpl;
import com.esprit.veltun.model.RackVelo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RackveloCreateController implements Initializable {
    public Button cancelbutton;
    private RackVeloService RackVeloService = new RackVeloImpl();

    public TextField fxrefRv;
    public TextField fxidS;
    public TextField fxCap;;
    @FXML
    public ChoiceBox<Integer> modeleFX  ;
    private Integer[] modele = {1,2,3} ;

    public void saveRackvelo(ActionEvent actionEvent) {
        String idS= fxidS.getText();
        String refRV=fxrefRv.getText();
        String cap =fxCap.getText();


//Les controles de saisies !!
        if (idS.isEmpty() || refRV.isEmpty() || cap.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the form .");
            alert.showAndWait();
            return;
        }
/*
        if(!nomS.matches("[a-zA-Z]+"))  {
            // Si le nom contient autre chose que des lettres et des espaces, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the name field with letters only .");
            alert.showAndWait();
            return;
        }*/

        if(cap.matches("[a-zA-Z]+") || refRV.matches("[a-zA-Z]+") || idS.matches("[a-zA-Z]+"))  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the capacity , reference and stationId with numbers only .");
            alert.showAndWait();
            return;
        }


        RackVelo rv = new RackVelo();
        rv.setId_station(Integer.parseInt(idS));
        rv.setRefRack(Integer.parseInt(refRV));
        rv.setCapacite(Integer.parseInt(cap));
        rv.setModele(modeleFX.getValue());


        rv = RackVeloService.save(rv);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/details.fxml"));

        try {
            Parent root = fxmlLoader.load();

            RackveloDetailsController cont = fxmlLoader.getController();
            cont.setRackvelo(rv);

            fxrefRv.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("../search/search.fxml"));

        try {
            Parent root = Loader.load();

            RackveloDetailsController cont = fxmlLoader.getController();
            cont.setRackvelo(rv);

            fxrefRv.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void getChoice(ChoiceBox<Integer> choiceBox) {
        Integer modeleFX = choiceBox.getValue() ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final Pattern pattern = Pattern.compile("\\d{2}\\:\\d{2}\\:\\d{2}");
        TextFormatter<?> formatter = new TextFormatter<>(change -> {
            if (pattern.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                // todo: add error message/markup
                return null; // prevent change
            }
        });
        //tfheurefin.setTextFormatter(formatter);
        modeleFX.getItems().addAll(modele);

    }
    public void cancel(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../search/search.fxml"));

        try {
            Parent root = fxmlLoader.load();
            fxrefRv.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private Stage stage ;
    private Scene scene ;
    private Parent root ;


    public void switchToStation(java.awt.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("station/create/create.fxml")) ;
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

}