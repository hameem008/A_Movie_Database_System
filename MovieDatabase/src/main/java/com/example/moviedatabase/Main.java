package com.example.moviedatabase;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class Main {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField text;
    //public static String company;
    public static Socket socket;
    public void clickOnLogIn(ActionEvent actionEvent)throws IOException {
        socket=new Socket("127.0.0.1",22222);
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
        try{
            oos.writeObject("login");
            MovieDatabase.company=(String) text.getText();
            //System.out.println(MovieDatabase.company);
            oos.writeObject(MovieDatabase.company);
            //sent to server
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            Object cMsg=ois.readObject();
            Object cMsg2=ois.readObject();
            //socket.close();
            MovieDatabase.the_list=(List<Movie>) cMsg;
            MovieDatabase.company_list=(Set<String>) cMsg2;
            System.out.println(MovieDatabase.company_list);
            if(MovieDatabase.the_list!=null){
                new ClientThread(socket);
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
            else{
                System.out.println("Error");
                FXMLLoader fxmlLoader = new FXMLLoader(MovieDatabase.class.getResource("Main.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Movie Database");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class ClientThread implements  Runnable{
    Socket socket;
    Thread t;
    ClientThread(Socket socket){
        this.socket=socket;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try{
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            while (true){
                Object cMsg=ois.readObject();
                int s=MovieDatabase.the_list.size();
                MovieDatabase.the_list=(List<Movie>) cMsg;
                if(MovieDatabase.the_list.size()!=s&&Menu.m!=null){
                    Menu.m.tableView.setItems(FXCollections.observableList(MovieDatabase.the_list));
                }

                //System.out.println("Ok");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}