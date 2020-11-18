package ch.comstock.progre21.viewmodel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

import ch.comstock.progre21.model.Point;


public class PointListViewCell extends ListCell<Point> {

    @FXML
    private Label lbl_nr;

    @FXML
    private Label lbl_gr;

    @FXML
    private Label lbl_kl;

    @FXML
    private Label lbl_h;
    
    @FXML
    private Label lbl_name;

    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Point pt, boolean empty) {
        super.updateItem(pt, empty);

        if(empty || pt == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/views/PointListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lbl_nr.setText(String.valueOf(pt.getNr()));
            lbl_gr.setText(String.valueOf(pt.getGr()));
            lbl_kl.setText(String.valueOf(pt.getKl()));
            lbl_h.setText(String.valueOf(pt.getH()));
            lbl_name.setText(pt.getName());

            setText(null);
            setGraphic(gridPane);
        }

    }
}