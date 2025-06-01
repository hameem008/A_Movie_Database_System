import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class movie_database {
    private static final String INPUT_FILE_NAME="movies.txt";
    private static final String OUTPUT_FILE_NAME="movies.txt";

    public static void main(String[] args)throws Exception {
        MenuDriven menu=new MenuDriven();
        List<Movie> the_list=new ArrayList();
        BufferedReader br=new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true){
            String line=br.readLine();
            if(line==null) break;
            String[] out=line.split(",");
            Movie mov=new Movie(out[0],Integer.parseInt(out[1],10),out[2],out[3],out[4],Integer.parseInt(out[5],10),out[6],Integer.parseInt(out[7],10),Integer.parseInt(out[8],10));
            the_list.add(mov);
        }
        menu.mainMenu(the_list);
        br.close();
        BufferedWriter bw=new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Movie m:the_list){
            bw.write(m.writeInFile());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}
