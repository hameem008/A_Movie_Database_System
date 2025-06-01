package com.example.moviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Transfer {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField MovieName;
    @FXML
    private TextField ProductionCompanyName;
    @FXML
    private Label TransferStatus;
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
    public void clickOnTransfer(ActionEvent actionEvent)throws IOException {
        Socket socket=new Socket("127.0.0.1",22222);
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
        try{
            AllFunctions f=new AllFunctions();
            Movie movie=f.searchByMovieTitle(MovieDatabase.the_list, MovieName.getText());
            String s= ProductionCompanyName.getText();
            boolean isCompanyExist=f.isCompanyExist(s, MovieDatabase.company_list);
            //System.out.println(isCompanyExist);
            if(movie==null){
                TransferStatus.setText("Movie Not Found");
                socket.close();
            }
            else if(!isCompanyExist){
                TransferStatus.setText("Production Company Not Found");
                socket.close();
            }
            else{
                movie.setProductionCompany(ProductionCompanyName.getText());
                oos.writeObject("transfer");
                oos.writeObject(movie);
                socket.close();
                TransferStatus.setText("Transfer Successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
