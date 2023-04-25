package Utils;

public class Review {
    private String name_of_reviewer;
    private String comment;
    private double star_Rating;

    public Review(String name_of_reviewer, String comment, double star_Rating){
        this.name_of_reviewer = name_of_reviewer;
        this.comment = comment;
        this.star_Rating = star_Rating;
    }

    public String getName_of_reviewer(){
        return this.name_of_reviewer;
    }

    public String getComment(){
        return this.comment;
    }

    public double getStar_Rating(){
        return this.star_Rating;
    }


    public String toString(){
        return "Reviewer:" + this.name_of_reviewer + "\n comment: " + this.comment + "\n starRating: " + this.star_Rating ;
    }
}
