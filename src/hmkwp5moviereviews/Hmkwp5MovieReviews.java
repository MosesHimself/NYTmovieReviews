/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmkwp5moviereviews;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class Hmkwp5MovieReviews extends Application {
    
    private ViewController controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewFXML.fxml"));
        Parent root = loader.load();
        System.out.println("Loading done");
        controller = loader.getController();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        controller.ready(scene);
        
        
        
        
    }
    
    @Override
    public void stop(){
        System.out.println("Stage is closing from stop");
        
        controller.end();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
