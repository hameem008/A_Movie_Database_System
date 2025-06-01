class Movie{
    private String Name;
    private int ReleaseYear;
    private String Genre1,Genre2,Genre3;
    private int RunningTime;
    private String ProductionCompany;
    private int Budget;
    private int Revenue;
    private int Profit;
    Movie(Movie m){
        Name=m.getName();
        ReleaseYear=m.getReleaseYear();
        Genre1=m.getGenre1();
        Genre2=m.getGenre2();
        Genre3=m.getGenre3();
        RunningTime=m.getRunningTime();
        ProductionCompany=m.getProductionCompany();
        Budget=m.getBudget();
        Revenue=m.getRevenue();
        Revenue=m.getRevenue();
        Profit=m.getProfit();
    }
    Movie(String Name,int ReleaseYear,String Genre1,String Genre2,String Genre3,int RunningTime,String ProductionCompany,int Budget,int Revenue){
        this.Name=Name;
        this.ReleaseYear=ReleaseYear;
        this.Genre1=Genre1;
        this.Genre2=Genre2;
        this.Genre3=Genre3;
        this.RunningTime=RunningTime;
        this.ProductionCompany=ProductionCompany;
        this.Budget=Budget;
        this.Revenue=Revenue;
        this.Profit=Revenue-Budget;
    }
    String getName(){ return Name; }
    int getReleaseYear(){ return ReleaseYear; }
    String getGenre1(){ return Genre1; }
    String getGenre2(){ return Genre2; }
    String getGenre3(){ return Genre3; }
    int getRunningTime(){ return RunningTime; }
    String getProductionCompany(){ return ProductionCompany; }
    int getBudget(){ return Budget; }
    int getRevenue(){ return Revenue; }
    int getProfit(){ return Profit; }
    void printDetails(){
        System.out.println("Name: "+Name);
        System.out.println("Release year: "+ReleaseYear);
        if(Genre2.length()==0&&Genre3.length()==0) System.out.println("Genre1: "+Genre1);
        else if(Genre3.length()==0) System.out.println("Genre12: "+Genre1+","+Genre2);
        else System.out.println("Genre123: "+Genre1+","+Genre2+","+Genre3);
        System.out.println("Running Time: "+RunningTime);
        System.out.println("Production company: "+ProductionCompany);
        System.out.println("Budget: "+Budget);
        System.out.println("Revenue: "+Revenue);
        System.out.println();
    }
    String writeInFile(){
        String s=Name+","+ReleaseYear+","+Genre1+","+Genre2+","+Genre3+","+RunningTime+","+ProductionCompany+","+Budget+","+Revenue;
        return s;
    }
}