import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.moviedatabase.AllFunctions;
import com.example.moviedatabase.Movie;
import com.example.moviedatabase.*;

public class server {
    public static final String INPUT_FILE_NAME= "movies.txt";
    private static final String OUTPUT_FILE_NAME="movies.txt";
    static AllFunctions f=new AllFunctions();
    static List<Movie> the_list=new ArrayList();
    static Set<String> company_list=new HashSet<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader br=new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true){
            String line=br.readLine();
            if(line==null) break;
            String[] out=line.split(",");
            Movie mov=new Movie(out[0],Integer.parseInt(out[1],10),out[2],out[3],out[4],Integer.parseInt(out[5],10),out[6],Integer.parseInt(out[7],10),Integer.parseInt(out[8],10));
            the_list.add(mov);
        }
        company_list=f.listOfProductionCompany(the_list);
        ServerSocket serverSocket=new ServerSocket(22222);

        while (true){

            System.out.println("Server started...");
            Socket socket=serverSocket.accept();
            System.out.println("Client connected...");

            try{
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                Object o=ois.readObject();
                String command=(String) o;
                if(command.equalsIgnoreCase("login")||command.equalsIgnoreCase("refresh")){
                    o=ois.readObject();
                    String company=(String) o;
                    System.out.println(company);
                    if(company==null) continue;

                    AllFunctions functions=new AllFunctions();
                    List<Movie>a_list=functions.searchByProductionCompany(the_list,company);
                    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(a_list);
                    oos.writeObject(company_list);

                    if(a_list!=null) new ServerThread_1(socket,the_list,company);
                }
                else if(command.equalsIgnoreCase("transfer")){
                    o=ois.readObject();
                    Movie add=(Movie) o;
                    AllFunctions functions=new AllFunctions();
                    Movie remove=functions.searchByMovieTitle(the_list,add.getName());
                    the_list.remove(remove);
                    the_list.add(add);
                    new ServerThread_2(the_list,OUTPUT_FILE_NAME);
//                    BufferedWriter bw=new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
//                    for (Movie m:the_list){
//                        bw.write(m.writeInFile());
//                        bw.write(System.lineSeparator());
//                    }
//                    bw.close();
                }
                else if(command.equalsIgnoreCase("addmovie")){
                    o=ois.readObject();
                    Movie add=(Movie) o;
                    the_list.add(add);
                    new ServerThread_2(the_list,OUTPUT_FILE_NAME);
//                    BufferedWriter bw=new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
//                    for (Movie m:the_list){
//                        bw.write(m.writeInFile());
//                        bw.write(System.lineSeparator());
//                    }
//                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
class ServerThread_1 implements Runnable{
    Socket socket;
    Thread t;
    List<Movie>the_list;
    String company;
    ServerThread_1(Socket socket,List<Movie>the_list,String company){
        this.socket=socket;
        this.the_list=the_list;
        this.company=company;
        t= new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            while (true){
                AllFunctions functions=new AllFunctions();
                List<Movie>a_list=functions.searchByProductionCompany(the_list,company);
                oos.writeObject(a_list);
                //System.out.println("Ok");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ServerThread_2 implements Runnable{
    Thread t;
    List<Movie>the_list;
    String OUTPUT_FILE_NAME;
    ServerThread_2(List<Movie>the_list,String OUTPUT_FILE_NAME){
        this.the_list=the_list;
        this.OUTPUT_FILE_NAME=OUTPUT_FILE_NAME;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for (Movie m:the_list){
                bw.write(m.writeInFile());
                bw.write(System.lineSeparator());
            }
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}