import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
/**
 * Beschreiben Sie hier die Klasse Pen.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Pen
{
    protected double x,y;
    protected boolean penIsUp = true;
    protected double angel = 0;
    
    protected Window myWindow;
    

    /**
     * Konstruktor für Objekte der Klasse Pen
     */
    public Pen()
    {
       x=0;
       y=0;
       myWindow = Window.firstWindow;
    }

    
    protected void drawLine(double x1, double y1, double x2, double y2) 
    {
        GraphicsContext g = myWindow.g();
        if (g != null)
        {
            System.out.println("Draw");
           g.setStroke(Color.BLACK);
            this.setState(g);       
            g.strokeLine(x1,y1,x2,y2);
        }
    }
    
    protected void setState(GraphicsContext g){
        
    }
    
}
