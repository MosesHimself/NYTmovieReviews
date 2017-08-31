/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmkwp5moviereviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class NYTReviewManager {
    private static ArrayList<NYTMovieReview> reviews;
    private static int numResults;
    
    private static final String baseUrlString = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    private static final String apiKey = "cacc7a03f66148c6a15b57b2cfa44bf6";
    private static String searchString = "Space Jam";
    
    
    private static URL url;
    
    public static void initManager()  {
        reviews = new ArrayList<>();
        numResults = 0;
    }
    
    
    public static void loadReviews(String searchString) throws Exception  {
        
        clearReviews();
        
        
        if(searchString == null || "".equals(searchString))  {
            throw new Exception("The search string was empty.");
        }
        
        //NYTReviewManager.searchString = searchString;
        
        // create the urlString
        String encodedSearchString;
        try {
            // searchString must be URL encoded to put in URL
            encodedSearchString = URLEncoder.encode(searchString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw ex;
        }
        
        String urlString = baseUrlString + "?query=" + encodedSearchString + "&api-key=" + apiKey;
        System.out.println(urlString);
        try {
            url = new URL(urlString);
        } catch(MalformedURLException muex) {
           throw muex;
        }
        
        String jsonString = "";
        try {
            BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                jsonString += inputLine;
            in.close();
        } catch (IOException ioex) {
            throw ioex;
        }
        
        try {
            parseJsonString(jsonString);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public static void clearReviews()  {
        reviews.clear();
        numResults = 0;
    }
    
    private static void parseJsonString(String jsonString) throws Exception {
        
        // start with clean list
        clearReviews();
        
        if (jsonString == null || "".equals(jsonString)) return;
        
        JSONObject jsonObj;
        try {
            jsonObj = (JSONObject)JSONValue.parse(jsonString);
        } catch (Exception ex) {
            throw ex;
        }
        
        if (jsonObj == null) return;
        
        String status = "";
        try {
            status = (String)jsonObj.get("status");
        } catch (Exception ex) {
            throw ex;
        }
        
        if (status == null || !status.equals("OK")) {
            throw new Exception("Status returned from API was not OK.");
        }
        
        
        JSONArray results;
        try {
            results = (JSONArray)jsonObj.get("results");
        } catch (Exception ex) {
            throw ex;
        }
      
        for (Object result : results) {
            try {
                JSONObject review = (JSONObject)result;
                
                
                String display_title = (String)review.getOrDefault("display_title", "");
                String mpaa_rating = (String)review.getOrDefault("mpaa_rating", "");
                //int critics_pick = (int)review.getOrDefault("critics_pick", 0);
                String byline = (String)review.getOrDefault("byline", "");
                String headline = (String)review.getOrDefault("headline", "");
                String summary_short = (String)review.getOrDefault("summary_short", "");
                String publication_date = (String)review.getOrDefault("publication_date", "");
                String opening_date = (String)review.getOrDefault("opening_date", "");
                String date_updated = (String)review.getOrDefault("date_updated", "");
                
                System.out.println("title: " + display_title + "\n");
                System.out.println("rating: " + mpaa_rating + "\n");
                //System.out.println("pick: " + critics_pick + "\n");
                System.out.println("byline: " + byline + "\n");
                System.out.println("headline: " + headline + "\n");
                System.out.println("summary_short: " + summary_short + "\n");
                System.out.println("publication_date: " + publication_date + "\n");
                System.out.println("opening_date: " + opening_date + "\n");
                System.out.println("date_updated: " + date_updated + "\n");
                System.out.println("------------------------------------------------------\n");
                
                NYTMovieReview movieReview = new NYTMovieReview(display_title, mpaa_rating, 0, byline,
                          headline, summary_short, publication_date, opening_date,
                          date_updated);
                
                reviews.add(movieReview);
                numResults++;
                
            } catch (Exception ex) {
                throw ex;
            }
            
        }
        
    }
    
    public static int getNumResults()  {
        return numResults;
    }
    
    public static ArrayList<NYTMovieReview> getReviews() {
        return reviews;
    }
}
