import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDriven {
    void mainMenu(List<Movie> the_list){
        while (true){
            System.out.println("Main Menu: ");
            System.out.println("1) Search Movies");
            System.out.println("2) Search Production Companies");
            System.out.println("3) Add Movie");
            System.out.println("4) Exit System");
            System.out.println("Enter your choice");
            String choice;
            Scanner scn=new Scanner(System.in);
            choice=scn.nextLine();
            if(choice.equals("1")) SearchMovies(the_list);
            else if(choice.equals("2")) searchProductionCompanies(the_list);
            else if(choice.equals("3")){
                AllFunctions f=new AllFunctions();
                String NewMovie;
                System.out.println("Type the details of the movie in this format=>");
                System.out.println("Name,ReleaseYear,Genre1,Genre2,Genre3,RunningTime,ProductionCompany,Budget,Revenue");
                NewMovie=scn.nextLine();
                String[] out=NewMovie.split(",");
                Movie mov=new Movie(out[0],Integer.parseInt(out[1],10),out[2],out[3],out[4],Integer.parseInt(out[5],10),out[6],Integer.parseInt(out[7],10),Integer.parseInt(out[8],10));
                boolean a=f.addMovie(the_list,mov);
                if(a==false){
                    System.out.println("Movie already exist");
                }
                else System.out.println("Movie added");
            }
            else if(choice.equals("4")) break;
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
            String choice;
            Scanner scn=new Scanner(System.in);
            choice=scn.nextLine();
            AllFunctions f=new AllFunctions();
            if(choice.equals("1")){
                String movie_name;
                System.out.println("Type name");
                movie_name=scn.nextLine();
                if(f.searchByMovieTitle(the_list,movie_name)!=null){
                    f.searchByMovieTitle(the_list,movie_name).printDetails();
                }
                else System.out.println("No such movie with this name");
            }
            else if(choice.equals("2")){
                int ReleaseYear;
                System.out.println("Type year");
                ReleaseYear=scn.nextInt();
                List<Movie> a_list;
                a_list=f.searchByReleaseYear(the_list,ReleaseYear);
                if(a_list!=null){
                    for(Movie m: a_list){
                        m.printDetails();
                    }
                }
                else System.out.println("No such movie with this release year");
            }
            else if(choice.equals("3")){
                String Genre;
                System.out.println("Type Genre");
                Genre=scn.nextLine();
                List<Movie> a_list;
                a_list=f.searchByGenre(the_list,Genre);
                if(a_list!=null){
                    for(Movie m: a_list){
                        m.printDetails();
                    }
                }
                else System.out.println("“No such movie with this genre");
            }
            else if(choice.equals("4")){
                String ProductionCompany;
                System.out.println("Type Production company");
                ProductionCompany=scn.nextLine();
                List<Movie> a_list;
                a_list=f.searchByProductionCompany(the_list,ProductionCompany);
                if(a_list!=null){
                    for(Movie m:a_list){
                        m.printDetails();
                    }
                }
                else System.out.println("No such movie with this production company");
            }
            else if(choice.equals("5")){
                System.out.println("Type Low");
                int low;
                low=scn.nextInt();
                System.out.println("Type High");
                int high;
                high=scn.nextInt();
                List<Movie> a_list;
                a_list=f.searchByRunningTime(the_list,low,high);
                if(a_list!=null){
                    for(Movie m:a_list){
                        m.printDetails();
                    }
                }
                else System.out.println("No such movie with this running time range");
            }
            else if(choice.equals("6")){
                for(Movie m: f.top10Movies(the_list)){
                    m.printDetails();
                }
            }
            else if(choice.equals("7")) break;
            else System.out.println("Wrong Choice");
        }
    }
    void searchProductionCompanies(List<Movie>the_list){
        while (true){
            System.out.println("Production Company Searching Options:");
            System.out.println("1) Most Recent Movies");
            System.out.println("2) Movies with the Maximum Revenue");
            System.out.println("3) Total Profit");
            System.out.println("4) List of Production Companies and the Count of their Produced Movies");
            System.out.println("5) Back to Main Menu");
            System.out.println("Enter your choice");
            String choice;
            Scanner scn=new Scanner(System.in);
            choice=scn.nextLine();
            AllFunctions f=new AllFunctions();
            if(choice.equals("1")){
                String ProductionCompany;
                System.out.println("Type Production Company");
                ProductionCompany=scn.nextLine();
                List<Movie> a_list;
                a_list=f.mostRecentMovies(the_list,ProductionCompany);
                if(a_list!=null){
                    for(Movie m:a_list){
                        m.printDetails();
                    }
                }
                else System.out.println("No such production company with this name");
            }
            else if(choice.equals("2")){
                String ProductionCompany;
                System.out.println("Type Production company");
                ProductionCompany=scn.nextLine();
                List<Movie> a_list;
                a_list=f.moviesWithTheMaximumRevenue(the_list,ProductionCompany);
                if(a_list!=null){
                    for(Movie m:a_list){
                        m.printDetails();
                    }
                }
                else System.out.println("No such production company with this name");
            }
            else if(choice.equals("3")){
                String ProductionCompany;
                System.out.println("Type production company");
                ProductionCompany=scn.nextLine();
                long TotalProfit=f.totalProfit(the_list,ProductionCompany);
                if(TotalProfit!=0){
                    System.out.println("Total profit of "+ProductionCompany+" is "+TotalProfit);
                }
                else System.out.println("No such production company with this name");
            }
            else if(choice.equals("4")) f.listOfProductionCompaniesAndTheCountOfTheirProducedMovies(the_list);
            else if(choice.equals("5")) break;
            else System.out.println("Wrong Choice");
        }
    }
}
