import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class Test{
    
     Canvas canvas;
     JFrame jFrame ;
    
    public Test(){
         SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                initSwingLater();
            }
            
        });
    }
    
    
    private void initFxLater(JFXPanel pPanel){
        Pane root = new Pane();
        canvas = new Canvas(jFrame.getWidth(), jFrame.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRoundRect(110, 60, 30, 30, 10, Math.random()*10);
        Scene scene = new Scene(root, jFrame.getWidth(), jFrame.getHeight());
        Circle circ = new Circle(40, 40, 30);
        Circle circ1 = new Circle(70, 50, 30);
        ((Pane)scene.getRoot()).getChildren().add(circ);
        ((Pane)scene.getRoot()).getChildren().add(canvas);
        ((Pane)scene.getRoot()).getChildren().add(circ1);
        pPanel.setScene(scene);
    }
    
    private void initSwingLater(){
        jFrame = new JFrame("- JFrame -");
        jFrame.setSize(540, 210);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFXPanel jFXPanel = new JFXPanel();
        jFrame.add(jFXPanel);
        
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                initFxLater(jFXPanel);
            }
        });
        
    }
}