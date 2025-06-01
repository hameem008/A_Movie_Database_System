package com.example.moviedatabase;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MostRecentMovies implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Movie> tableView;
    @FXML
    private TableColumn<Movie, String> MovieName;
    @FXML
    private TableColumn<Movie, String> Genre1;
    @FXML
    private TableColumn<Movie, String> Genre2;
    @FXML
    private TableColumn<Movie, String> Genre3;
    @FXML
    private TableColumn<Movie, Integer> Budget;
    @FXML
    private TableColumn<Movie, Integer> ReleaseYear;
    @FXML
    private TableColumn<Movie, Integer> RunningTime;
    @FXML
    private TableColumn<Movie, Integer>Revenue;
    @FXML
    private Label CompanyName;

    public void setCompanyName(String companyName) {
        CompanyName.setText(companyName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MovieName.setCellValueFactory(new PropertyValueFactory<Movie,String >("Name"));
        Genre1.setCellValueFactory(new PropertyValueFactory<Movie,String>("Genre1"));
        Genre2.setCellValueFactory(new PropertyValueFactory<Movie,String>("Genre2"));
        Genre3.setCellValueFactory(new PropertyValueFactory<Movie,String>("Genre3"));
        Budget.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("Budget"));
        ReleaseYear.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("ReleaseYear"));
        RunningTime.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("RunningTime"));
        Revenue.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("Revenue"));

        //System.out.println(MovieDatabase.the_list);

        tableView.setItems(FXCollections.observableList(MovieDatabase.temp_list));
    }

    public void clickOnBack(ActionEvent actionEvent)throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Menu.fxml"));
        //root= FXMLLoader.load(getClass().getResource("Menu.fxml"));
        root=loader.load();
        //root= FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();

        Menu m=loader.getController();
        m.setCompanyName(MovieDatabase.company);
    }
}
