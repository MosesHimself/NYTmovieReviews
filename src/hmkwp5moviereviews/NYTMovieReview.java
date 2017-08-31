/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmkwp5moviereviews;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class NYTMovieReview {
    
    public String display_title;
    public String mpaa_rating;
    public int critics_pick;
    public String byline;
    public String headline;
    public String summary_short;
    public String publication_date;
    public String opening_date;
    public String date_updated;
    
    public String link_type;
    public String link_url;
    public String link_suggested_text;
    
    public String mmType;
    public String mmSrc;
    public int mmHeight;
    public int mmWidth;
    
    public NYTMovieReview(String display_title, String mpaa_rating, int critics_pick, String byline,
                          String headline, String summary_short, String publication_date, String opening_date,
                          String date_updated)  {
        
        this.display_title = display_title;
        this.mpaa_rating = mpaa_rating;
        this.critics_pick = critics_pick;
        this.byline = byline;
        this.headline = headline;
        this.summary_short = summary_short;
        this.publication_date = publication_date;
        this.opening_date = opening_date;
        this.date_updated = date_updated;
        
    }
}
