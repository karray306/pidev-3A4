package com.esprit.veltun.gui.rackStation.rackvelo.update;

import com.esprit.veltun.model.RackVelo;
import com.esprit.veltun.services.RackVeloService;
import com.esprit.veltun.services.impl.RackVeloImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

    public class RackveloUpdateController implements Initializable {
        public Button cancelbutton;
        private RackVeloService rackVeloService = new RackVeloImpl();
        private RackVelo rackVelo ;


        public TextField fxrefRv;
        public TextField fxidS;
        public TextField fxCapacity;
        ;


        public void setFxrefRv(String refRV) {
            this.fxrefRv.setText(refRV);
        }

        public void setFxCap(String cap) {
            this.fxCapacity.setText(cap);
        }

        public void setFxidS(String idS) {
            this.fxidS.setText(idS);
        }


        public void setRackVelo(RackVelo rackVelo) {
            this.rackVelo = rackVelo;
            setFxrefRv(String.valueOf(rackVelo.getRefRack()));
            setFxCap(String.valueOf(rackVelo.getCapacite()));
            setFxidS(String.valueOf(rackVelo.getId_station()));


        }

        public void updateRackvelo(ActionEvent actionEvent) {
            String refRV = fxrefRv.getText();
            String cap = fxCapacity.getText();
            String idS = fxidS.getText();


            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update the data?");
            Optional<ButtonType> result = confirm.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                RackVelo rv = new RackVelo();
                rv.setRefRack(Integer.parseInt(refRV));
                rv.setId_station(Integer.parseInt(idS));
                rv.setCapacite(Integer.parseInt(cap));


                rackVelo = rackVeloService.update(rackVelo);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/details.fxml"));

                try {
                    Parent root = fxmlLoader.load();

                    com.esprit.veltun.gui.rackStation.rackvelo.view.RackveloDetailsController cont = fxmlLoader.getController();
                    cont.setRackvelo(rackVelo);

                    fxrefRv.getScene().setRoot(root);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
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

            //tfheuredebut.setTextFormatter(formatter);
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

        public void updateRV(ActionEvent event) {
            String refRV = fxrefRv.getText();
            String cap = fxCapacity.getText();
            String idS = fxidS.getText() ;


            rackVelo.setRefRack(Integer.parseInt(refRV));
            rackVelo.setCapacite(Integer.parseInt(cap));
            rackVelo.setId_station(Integer.parseInt(idS));



            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update the data?");
            Optional<ButtonType> result = confirm.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {



                RackVeloImpl rackvelo = new RackVeloImpl() ;
                rackVelo= rackvelo.update(rackVelo);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("update.fxml"));

                try {
                    Parent root = fxmlLoader.load();

                    com.esprit.veltun.gui.rackStation.rackvelo.view.RackveloDetailsController cont = fxmlLoader.getController();
                    cont.setRackvelo(rackVelo);

                    fxrefRv.getScene().setRoot(root);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }


        public void switchToRacksFromUpdate(ActionEvent event) {

        }
    }