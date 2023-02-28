package com.esprit.veltun.GUI.reclamationn.create;

import com.esprit.veltun.model.Reclamation;
import com.esprit.veltun.GUI.reclamationn.view.ReclamationDetailsController;

import com.esprit.veltun.services.ReclamationService;
import com.esprit.veltun.services.impl.ReclamationServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.sql.Date.valueOf;

public class ReclamationCreateController implements Initializable {
    public Button cancelbutton;
    private ReclamationService reclamationService = new ReclamationServiceImpl();

    public TextField fxtype;
    public TextField fxdescription;
    public DatePicker date_reclamation;

    public TextField fxobjet;

    @FXML
    private Label discountedPriceLabel;
    private  Label errorA;
    @FXML
    private TextField discountCodeTextField;


    public void saveEvent(ActionEvent actionEvent) {

        String objet= fxobjet.getText();
        String description= fxdescription.getText();
        String type= fxtype.getText();
    String date_reclamationn = String.valueOf(date_reclamation.getValue());

        Reclamation a= new Reclamation();

        a.setObjet(objet);
        a.setDescription(description);
        a.setType(type);
        a.setDate_reclamation(Date.valueOf(date_reclamationn));

        a = reclamationService.save(a);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/details.fxml"));

        try {
            Parent root = fxmlLoader.load();

            ReclamationDetailsController cont = fxmlLoader.getController();
            cont.setReclamation(a);

            fxtype.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("../search/search.fxml"));

       /* try {
            Parent root = Loader.load();

            ReclamationDetailsController cont = fxmlLoader.getController();
            cont.setReclamation(a);

           fxtype.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }*/
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

    }
    public void cancel(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../search/search.fxml"));

        try {
            Parent root = fxmlLoader.load();
            fxtype.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}