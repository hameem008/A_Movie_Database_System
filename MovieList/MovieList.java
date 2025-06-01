import com.sun.tools.javac.Main;
import jdk.jfr.consumer.RecordedEvent;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.io.*;
import java.sql.SQLOutput;
import java.sql.Struct;
import java.util.*;
import java.util.List;

class AllFunctions{
    void byMovieTitle(List<Movie> the_list){
        Scanner scn=new Scanner(System.in);
        String movie_name;
        System.out.println("Type name");
        movie_name=scn.nextLine();
        for(Movie m:the_list){
            if(movie_name.equalsIgnoreCase(m.getName())==true){
                m.printDetails();
                return;
            }
        }
        System.out.println("No such movie with this name");
        return;
    }
    void byReleaseYear(List<Movie> the_list){
        Scanner scn=new Scanner(System.in);
        int ReleaseYear;
        System.out.println("Type year");
        ReleaseYear=scn.nextInt();
        boolean result=false;
        for(Movie m:the_list){
            if(m.getReleaseYear()==ReleaseYear){
                m.printDetails();
                result=true;
            }
        }
        if(result!=true) System.out.println("No such movie with this release year");

    }
    void byGenre(List<Movie>the_list){
        Scanner scn=new Scanner(System.in);
        String Genre;
        System.out.println("Type Genre");
        Genre=scn.nextLine();
        Boolean result=false;
        for(Movie m:the_list){
            if(Genre.equalsIgnoreCase(m.getGenre1())||Genre.equalsIgnoreCase(m.getGenre2())||Genre.equalsIgnoreCase(m.getGenre3())){
                m.printDetails();
                result=true;
            }
        }
        if(result!=true) System.out.println("“No such movie with this genre");
    }
    void byProductionCompany(List<Movie>the_list){
        Scanner scn=new Scanner(System.in);
        String ProductionCompany;
        System.out.println("Type Production company");
        ProductionCompany=scn.nextLine();
        Boolean result=false;
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())){
                m.printDetails();
                result=true;
            }
        }
        if(result!=true) System.out.println("No such movie with this production company");
    }
    void byRunningTime(List<Movie>the_list){
        Scanner scn=new Scanner(System.in);
        System.out.println("Type Low");
        int low;
        low=scn.nextInt();
        System.out.println("Type High");
        int high;
        high=scn.nextInt();
        boolean result=false;
        for(Movie m:the_list){
            if(m.getRunningTime()<=high&&m.getRunningTime()>=low){
                m.printDetails();
                result=true;
            }
        }
        if(result!=true) System.out.println("No such movie with this running time range");
    }
    void Top10Movies(List<Movie>list){
        List<Movie>the_list=new ArrayList();
        for(Movie m:list){
            the_list.add(m);
        }
        Movie[] top10=new Movie[10];
        for(int i=0;i<10;i++){
            int profit=0;
            for(Movie m:the_list){
                if(profit<m.getProfit()){
                    top10[i]=m;
                    profit=m.getProfit();
                }
            }
            the_list.remove(top10[i]);
        }
        for(int i=0;i<10;i++){
            top10[i].printDetails();
        }
    }
    void MostRecentMovies(List<Movie>the_list){
        int year=0;
        Scanner scn=new Scanner(System.in);
        String ProductionCompany;
        System.out.println("Type Production Company");
        ProductionCompany=scn.nextLine();
        boolean result=false;
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true){
                result=true;
                if(m.getReleaseYear()>year) year=m.getReleaseYear();
            }
        }
        if(result!=true){
            System.out.println("No such production company with this name");
            return;
        }
        for(Movie m:the_list){
            if(m.getReleaseYear()==year&&ProductionCompany.equalsIgnoreCase(m.getProductionCompany())){
                m.printDetails();
            }
        }
    }
    void MoviesWithTheMaximumRevenue(List<Movie>the_list){
        Scanner scn=new Scanner(System.in);
        String ProductionCompany;
        System.out.println("Type Production company");
        ProductionCompany=scn.nextLine();
        boolean result=false;
        int revenue=0;
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true){
                if(revenue<m.getRevenue()) revenue=m.getRevenue();
                result=true;
            }
        }
        if(result!=true){
            System.out.println("No such production company with this name");
            return;
        }
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true&&revenue==m.getRevenue()){
                m.printDetails();
            }
        }
    }
    void TotalProfit(List<Movie>the_list){
        Scanner scn=new Scanner(System.in);
        String ProductionCompany;
        System.out.println("Type production company");
        ProductionCompany=scn.nextLine();
        boolean result=false;
        long totalprofit=0;
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true) {
                totalprofit = totalprofit + m.getProfit();
                result=true;
            }
        }
        if(result!=true){
            System.out.println("No such production company with this name");
            return;
        }
        System.out.println("Total profit of "+ProductionCompany+" is "+totalprofit);
    }
    void ListOfProductionCompaniesAndTheCountOfTheirProducedMovies(List<Movie>the_list){
        Set<String>ProductionCompany=new HashSet();
        for(Movie m:the_list){
            ProductionCompany.add(m.getProductionCompany());
        }
        for(String s:ProductionCompany){
            int MovieCount=0;
            for(Movie m:the_list){
                if(s.equalsIgnoreCase(m.getProductionCompany())==true){
                    MovieCount++;
                }
            }
            System.out.println("Production Company: "+s);
            System.out.println("Number of Movies: "+MovieCount);
        }
    }
    void AddMovie(List<Movie>the_list){
        Scanner scn=new Scanner(System.in);
        String NewMovie;
        System.out.println("Type the details of the movie in this format=>");
        System.out.println("Name,ReleaseYear,Genre1,Genre2,Genre3,RunningTime,ProductionCompany,Budget,Revenue");
        NewMovie=scn.nextLine();
        String[] out=NewMovie.split(",");
        Movie mov=new Movie(out[0],Integer.parseInt(out[1],10),out[2],out[3],out[4],Integer.parseInt(out[5],10),out[6],Integer.parseInt(out[7],10),Integer.parseInt(out[8],10));
        for(Movie m:the_list){
            if(mov.getName().equalsIgnoreCase(m.getName())==true){
                System.out.println("Movie already exist");
                return;
            }
        }
        the_list.add(mov);
    }
}

class MenuDriven{
    void MainMenu(List<Movie>the_list){
        while (true){
            System.out.println("Main Menu: ");
            System.out.println("1) Search Movies");
            System.out.println("2) Search Production Companies");
            System.out.println("3) Add Movie");
            System.out.println("4) Exit System");
            System.out.println("Enter your choice");
            int mmo;
            Scanner mmos=new Scanner(System.in);
            mmo=mmos.nextInt();
            if(mmo==1) SearchMovies(the_list);
            else if(mmo==2) SearchProductionCompanies(the_list);
            else if(mmo==3){
                AllFunctions f=new AllFunctions();
                f.AddMovie(the_list);
            }
            else if(mmo==4) break;
            else System.out.println("Wrong Choice");
        }
    }
    void SearchMovies(List<Movie>the_list){
       while(true){
           System.out.println("Movie Searching Options: ");
           System.out.println("1) By Movie Title");
           System.out.println("2) By Release Year");
           System.out.println("3) By Genre");
           System.out.println("4) By Production Company");
           System.out.println("5) By Running Time");
           System.out.println("6) Top 10 Movies");
           System.out.println("7) Back to Main Menu");
           System.out.println("Enter your choice");
           int mso;
           Scanner msos=new Scanner(System.in);
           mso=msos.nextInt();
           AllFunctions f=new AllFunctions();
           if(mso==1) f.byMovieTitle(the_list);
           else if(mso==2) f.byReleaseYear(the_list);
           else if(mso==3) f.byGenre(the_list);
           else if(mso==4) f.byProductionCompany(the_list);
           else if(mso==5) f.byRunningTime(the_list);
           else if(mso==6) f.Top10Movies(the_list);
           else if(mso==7) break;
           else System.out.println("Wrong Choice");
       }
    }
    void SearchProductionCompanies(List<Movie>the_list){
        while (true){
            System.out.println("Production Company Searching Options:");
            System.out.println("1) Most Recent Movies");
            System.out.println("2) Movies with the Maximum Revenue");
            System.out.println("3) Total Profit");
            System.out.println("4) List of Production Companies and the Count of their Produced Movies");
            System.out.println("5) Back to Main Menu");
            System.out.println("Enter your choice");
            int spco;
            Scanner spcos=new Scanner(System.in);
            spco=spcos.nextInt();
            AllFunctions f=new AllFunctions();
            if(spco==1) f.MostRecentMovies(the_list);
            else if(spco==2) f.MoviesWithTheMaximumRevenue(the_list);
            else if(spco==3) f.TotalProfit(the_list);
            else if(spco==4) f.ListOfProductionCompaniesAndTheCountOfTheirProducedMovies(the_list);
            else if(spco==5) break;
            else System.out.println("Wrong Choice");
        }
    }
}

public class MovieList{
    private static final String INPUT_FILE_NAME="movies.txt";
    private static final String OUTPUT_FILE_NAME="movies.txt";

    public static void main(String[] args)throws Exception {
        MenuDriven menu=new MenuDriven();
        List<Movie>the_list=new ArrayList();
        BufferedReader br=new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true){
            String line=br.readLine();
            if(line==null) break;
            String[] out=line.split(",");
            Movie mov=new Movie(out[0],Integer.parseInt(out[1],10),out[2],out[3],out[4],Integer.parseInt(out[5],10),out[6],Integer.parseInt(out[7],10),Integer.parseInt(out[8],10));
            the_list.add(mov);
        }
        menu.MainMenu(the_list);
        br.close();
        BufferedWriter bw=new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Movie m:the_list){
            bw.write(m.writeInFile());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}
