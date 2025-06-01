package com.example.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TotalProfit {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label TotalProfit;
    @FXML
    private Label TotalMovies;
    public void setTotalMovies(String totalMovies) {TotalMovies.setText(totalMovies);}
    @FXML
    private Label CompanyName;
    public void setTotalProfit(String totalProfit) {
        TotalProfit.setText(totalProfit);
    }

    public void setCompanyName(String companyName) {
        CompanyName.setText(companyName);
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

    public void clickOnRefresh(ActionEvent actionEvent)throws IOException {
        AllFunctions f=new AllFunctions();
        MovieDatabase.totalProfit=f.totalProfit(MovieDatabase.the_list,MovieDatabase.company);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TotalProfit.fxml"));
        //root= FXMLLoader.load(getClass().getResource("TotalProfit.fxml"));
        root = loader.load();
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();

        TotalProfit t = loader.getController();
        t.setTotalProfit(MovieDatabase.totalProfit +"$");
        t.setCompanyName(MovieDatabase.company);
        t.setTotalMovies(MovieDatabase.the_list.size()+"");
    }
}
