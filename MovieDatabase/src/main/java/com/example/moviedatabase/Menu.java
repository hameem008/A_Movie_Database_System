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
import java.util.List;

public class Menu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label CompanyName;
    public static AllMovies m;
    public void setCompanyName(String companyName) {
        CompanyName.setText(companyName);
    }

    public void clickOnLogOut(ActionEvent actionEvent)throws IOException {
        Main.socket.close();
        root= FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void clickOnAllMovies(ActionEvent actionEvent)throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AllMovies.fxml"));
        //root= FXMLLoader.load(getClass().getResource("AllMovies.fxml"));
        root=loader.load();
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();

        m=loader.getController();
        m.setCompanyName(MovieDatabase.company);
    }
    public void clickOnMostRecentMovies(ActionEvent actionEvent)throws IOException{
        AllFunctions f=new AllFunctions();
        MovieDatabase.temp_list=f.mostRecentMovies(MovieDatabase.the_list,MovieDatabase.company);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MostRecentMovies.fxml"));
        //root= FXMLLoader.load(getClass().getResource("MostRecentMovies.fxml"));
        root =loader.load();
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();

        MostRecentMovies m= loader.getController();
        m.setCompanyName(MovieDatabase.company);
    }
    public void clickOnMoviesWithMaximumRevenue(ActionEvent actionEvent)throws IOException {
        AllFunctions f=new AllFunctions();
        MovieDatabase.temp_list=f.moviesWithTheMaximumRevenue(MovieDatabase.the_list,MovieDatabase.company);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MoviesWithMaximumRevenue.fxml"));
        //root= FXMLLoader.load(getClass().getResource("MoviesWithMaximumRevenue.fxml"));
        root= loader.load();
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();

        MoviesWithMaximumRevenue m= loader.getController();
        m.setCompanyName(MovieDatabase.company);
    }

    public void clickOnTotalProfit(ActionEvent actionEvent)throws IOException {
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

    public void clickOnTransfer(ActionEvent actionEvent)throws IOException {
        root= FXMLLoader.load(getClass().getResource("Transfer.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void clickOnAddMovies(ActionEvent actionEvent)throws IOException {
        root= FXMLLoader.load(getClass().getResource("AddMovies.fxml"));
        stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
