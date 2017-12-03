import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class SuMApplication extends Application
{
	@Override
	public void start(Stage pStage)
	{
	     Pane root = new Pane();
        Circle circ = new Circle(40, 40, 30);
        Scene scene = new Scene(root, 200, 300);
        root.getChildren().add(circ);
        pStage.setScene(scene);
		pStage.show();
	}
	
	public static void main(String[] parameters) {       
              launch(parameters);   
       } 
}