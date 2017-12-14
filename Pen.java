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
    protected boolean isUp = true;
    protected double angel = 0;

    protected Window myWindow;
    protected int currentNode;
    protected Paint color=Color.BLACK;
    protected double linewidth = 1;

    protected static final int NORMALMODE = 0;
    protected static final int ERASEMODE = 1;
    protected static final int XORMODE = 2;

    /**
     * Konstruktor für Objekte der Klasse Pen
     */
    public Pen()
    {
        this(Window.topWindow);
    }

    public Pen(Window pWindow){
        myWindow = pWindow;
    }

    protected void drawLine(double x1, double y1, double x2, double y2) 
    {
        GraphicsContext g = myWindow.g();
        if (g != null)
        {          
            this.setState(g);       
            g.strokeLine(x1,y1,x2,y2);
        }
    }

    protected void setState(GraphicsContext g){
        if(this.currentNode == NORMALMODE){
            g.setStroke(this.color);
            g.setLineWidth(this.linewidth);
        }
        if(this.currentNode == ERASEMODE){
            g.setStroke(this.myWindow.getBgColor());
            g.setLineWidth(this.linewidth*2.5);
        }

    }

    public void normal() 
    {
        this.currentNode = this.NORMALMODE;
    }
    
    public void erase() 
    {
        this.currentNode = this.ERASEMODE; 
    }

    public void changeMode() 
    {
        this.currentNode = this.XORMODE;
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }

    protected void setStandard(){
        this.x = 0;
        this.y = 0;
        this.isUp = true;
        this.angel = 0;
        this.currentNode = this.NORMALMODE;
        this.normal();
    }

}
