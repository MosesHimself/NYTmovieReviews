/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmkwp5moviereviews;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Space_Craft_Trajectories_042
 */
public class ViewController implements Initializable {

    private ArrayList<NYTMovieReview> reviews;
    
    private ObservableList<String> titles;
    
    @FXML
    public TextField text;
    
    @FXML
    public ListView list;
    
    @FXML
    public AnchorPane anchor;
    
    @FXML
    public Text display_title;
    @FXML
    public Text mpaa_rating;
    @FXML
    public Text byline;
    @FXML
    public Text headline;
    @FXML
    public Text summary_short;
    @FXML
    public Text publication_date;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void ready(Scene scene)  {
        NYTReviewManager.initManager();
        
        list.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            // When the contents of the newsListView changes a situation can be created
            // where autoselection results in a new_val that is out of range of the stories.
            // The following makes sure the new_val is within the bounds of stories.
            if ((int)new_val < 0 || (int)new_val > (reviews.size() - 1)) {
                return;
            }
            NYTMovieReview review = reviews.get((int)new_val);
            loadReview(review);
        });
        
        //list.setItems(titles);
    }
    
    public void end()  {
        NYTReviewManager.clearReviews();
    }
    
    @FXML
    public void handleSearch(ActionEvent event) throws Exception  {
        
        String search = text.getText();
        if(search == null || "".equals(search))  {
            return;
        }
        
        NYTReviewManager.loadReviews(search);
        reviews = NYTReviewManager.getReviews();
        //titles.clear();
        loadData(reviews);
                
    }
    
    private void loadReview(NYTMovieReview review)  {
        if(review == null)  {
            System.out.println("You selected an invalid movie review");
            return;
        }
        
        byline.setText(review.byline);
        headline.setText(review.headline);
        summary_short.setText(review.summary_short);
        publication_date.setText(review.publication_date);
        mpaa_rating.setText(review.mpaa_rating);
        display_title.setText(review.display_title);
        
        
    }
    
    private void loadData(ArrayList<NYTMovieReview> reviews )  {
        
        if(titles != null)  {
            titles = null;
        }
        
        titles = FXCollections.observableArrayList();
        for (NYTMovieReview review : reviews)  {
            String string = review.display_title + "-" + review.byline;
            
            titles.add(string);
        }
        list.setItems(titles);
    }
    
    @FXML
    private void displayData()  {
        String string = (String)(list.getSelectionModel().getSelectedItem());
        System.out.println(string);
        
        for(NYTMovieReview review: this.reviews)  {
            if(string.equals(review.display_title + "-" + review.byline))  {
                
            }
        }
        
    }
    
}
