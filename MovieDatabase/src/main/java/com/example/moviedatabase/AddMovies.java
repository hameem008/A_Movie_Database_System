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
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AddMovies {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField Name;
    @FXML
    private TextField ReleaseYear;
    @FXML
    private TextField Genre1;
    @FXML
    private TextField Genre2;
    @FXML
    private TextField Genre3;
    @FXML
    private TextField RunningTime;
    @FXML
    private TextField Budget;
    @FXML
    private TextField Revenue;
    @FXML
    private Label AddStatus;
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

    public void clickOnAdd(ActionEvent actionEvent)throws IOException {
        AllFunctions functions=new AllFunctions();
        String name= Name.getText();
        String releaseyear= ReleaseYear.getText();
        String genre1= Genre1.getText();
        String genre2= Genre2.getText();
        String genre3= Genre3.getText();
        String runningtime= RunningTime.getText();
        String budget= Budget.getText();
        String revenue= Revenue.getText();
        if(genre2.length()<=1) genre2=null;
        if(genre3.length()<=1) genre3=null;
        Movie movie=functions.searchByMovieTitle(MovieDatabase.the_list, name);
        if(movie!=null){
            //movie already exist
            AddStatus.setText("Movie already exist");
        }
        else if(name.length()<=1||releaseyear.length()<=1||genre1.length()<=1||runningtime.length()<=1||budget.length()<=1||revenue.length()<=1){
            //movie cannot be added
            AddStatus.setText("Movie cannot be added");
        }
        else if(movie==null){
            //Successfully added
            AddStatus.setText("Successfully added");
            Movie add=new Movie(Name.getText(),
                    Integer.parseInt(releaseyear),
                    genre1,
                    genre2,
                    genre3,
                    Integer.parseInt(runningtime),
                    MovieDatabase.company,
                    Integer.parseInt(budget),
                    Integer.parseInt(revenue));
            MovieDatabase.the_list.add(add);
            Socket socket=new Socket("127.0.0.1",22222);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            try{
                oos.writeObject("addmovie");
                oos.writeObject(add);
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
