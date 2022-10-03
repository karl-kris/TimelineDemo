/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package timelinedemo;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author usu2dam
 */
public class TimelineDemo extends Application {
    public static double ballDirectionX = 1;
    public static double ballDirectionY = 1;
    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();
        Circle Ball = new Circle(10);
        Ball.setTranslateX(300 * 0.5);
        Ball.setTranslateY(250 * 0.5);
        root.getChildren().addAll(Ball);
        
        Label fpsTracker = new Label();
        fpsTracker.setTranslateX(10);
        fpsTracker.setTranslateY(10);
        root.getChildren().addAll(fpsTracker);
        
        Scene scene = new Scene(root, 300, 250);
        
        EventHandler<ActionEvent> eH = e->{
            
            PerformanceTracker perfTracker=
            PerformanceTracker.getSceneTracker(scene);
            fpsTracker.setText("FPS (Timeline) = "+perfTracker.getInstantFPS());

            if(Ball.getTranslateX()< Ball.getRadius() || Ball.getTranslateX()> scene.getWidth() - Ball.getRadius()){
                ballDirectionX*=-1;
            }
            if(Ball.getTranslateY()< Ball.getRadius() || Ball.getTranslateY()> scene.getHeight() - Ball.getRadius()){
                ballDirectionY*=-1;
            }
            Ball.setTranslateX(Ball.getTranslateX()+ballDirectionX);
            Ball.setTranslateY(Ball.getTranslateY()+ballDirectionY);
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5),eH));
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        primaryStage.setTitle("Timeline Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
