package com.example.moviedatabase;

import java.sql.Struct;
import java.util.*;
public class AllFunctions {
    Movie searchByMovieTitle(List<Movie> the_list,String movie_name){
        for(Movie m:the_list){
            if(movie_name.equalsIgnoreCase(m.getName())==true){
                return m;
            }
        }
        return null;
    }
    List<Movie> searchByReleaseYear(List<Movie> the_list,int ReleaseYear){
        boolean result=false;
        List<Movie> a_list=new ArrayList<>();
        for(Movie m:the_list){
            if(m.getReleaseYear()==ReleaseYear){
                a_list.add(m);
                result=true;
            }
        }
        if(result==true) return a_list;
        else return null;
    }
    List<Movie> searchByGenre(List<Movie>the_list,String Genre){
        Boolean result=false;
        List<Movie> a_list=new ArrayList<>();
        for(Movie m:the_list){
            if(Genre.equalsIgnoreCase(m.getGenre1())||Genre.equalsIgnoreCase(m.getGenre2())||Genre.equalsIgnoreCase(m.getGenre3())){
                a_list.add(m);
                result=true;
            }
        }
        if(result==true) return a_list;
        else return null;
    }
    List<Movie> searchByProductionCompany(List<Movie>the_list,String ProductionCompany){
        Boolean result=false;
        List<Movie> a_list=new ArrayList<>();
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())){
                a_list.add(m);
                result=true;
            }
        }
        if(result==true) return a_list;
        else return null;
    }
    List<Movie> searchByRunningTime(List<Movie>the_list,int low,int high){
        boolean result=false;
        List<Movie> a_list=new ArrayList<>();
        for(Movie m:the_list){
            if(m.getRunningTime()<=high&&m.getRunningTime()>=low){
                a_list.add(m);
                result=true;
            }
        }
        if(result==true) return a_list;
        else return null;
    }
    Movie[] top10Movies(List<Movie>list){
        List<Movie>the_list=new ArrayList<>();
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
        return top10;
    }
    List<Movie> mostRecentMovies(List<Movie>the_list,String ProductionCompany){
        int year=0;
        List<Movie> a_list=new ArrayList<>();
        boolean result=false;
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true){
                result=true;
                if(m.getReleaseYear()>year) year=m.getReleaseYear();
            }
        }
        if(result!=true){
            return null;
        }
        for(Movie m:the_list){
            if(m.getReleaseYear()==year&&ProductionCompany.equalsIgnoreCase(m.getProductionCompany())){
                a_list.add(m);
            }
        }
        return a_list;
    }
    List<Movie> moviesWithTheMaximumRevenue(List<Movie>the_list,String ProductionCompany){
        boolean result=false;
        int revenue=0;
        List<Movie> a_list=new ArrayList<>();
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true){
                if(revenue<m.getRevenue()) revenue=m.getRevenue();
                result=true;
            }
        }
        if(result!=true){
            return null;
        }
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true&&revenue==m.getRevenue()){
                a_list.add(m);
            }
        }
        return a_list;
    }
    long totalProfit(List<Movie>the_list,String ProductionCompany){
        boolean result=false;
        long totalprofit=0;
        for(Movie m:the_list){
            if(ProductionCompany.equalsIgnoreCase(m.getProductionCompany())==true) {
                totalprofit = totalprofit + m.getProfit();
                result=true;
            }
        }
        if(result!=true){
            return 0;
        }
        return totalprofit;
    }
    void listOfProductionCompaniesAndTheCountOfTheirProducedMovies(List<Movie>the_list){
        Set<String> ProductionCompany=new HashSet<>();
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
    public boolean isCompanyExist(String company,Set<String>company_list){
        for(String s:company_list){
            if(s.equalsIgnoreCase(company)) return true;
        }
        return false;
    }
    boolean addMovie(List<Movie>the_list,Movie mov){
        for(Movie m:the_list){
            if(mov.getName().equalsIgnoreCase(m.getName())==true){
                return false;
            }
        }
        the_list.add(mov);
        return true;
    }
}
