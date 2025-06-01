/*import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class client {
    public static List<com.example.moviedatabase.Movie>the_list;
    public static void main(String[] args) throws IOException {
        System.out.println("CLient started...");
        Socket socket=new Socket("127.0.0.1",22222);
        System.out.println("Client connected...");


            //ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

            try{
                //sent to server
                Object cMsg=ois.readObject();
                the_list=(List<Movie>) cMsg;
                int i=0;
                for(Movie m:the_list){
                    m.printDetails();
                    i++;
                    if(i==10) break;
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }

    }
}
*/