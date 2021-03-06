import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
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
    protected double angle = 0;

    protected Window myWindow;
    protected int currentMode;
    protected Paint color=Color.BLACK;
    protected double linewidth = 1;

    protected static final int NORMALMODE = 0;
    protected static final int ERASEMODE = 1;
    protected static final int XORMODE = 2;
    protected int delay = 10;

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
            this.x = x2;
            this.y = y2;
        }
    }

    protected void setState(GraphicsContext g){
        if(this.currentMode == NORMALMODE){
            g.setStroke(this.color);
            g.setLineWidth(this.linewidth);
        }
        if(this.currentMode == ERASEMODE){
            g.setStroke(this.myWindow.getBgColor());
            g.setLineWidth(this.linewidth*2.5);
        }

    }

    public void moveTo(double pX, double pY){
        if(!this.isUp){
            this.drawLine(this.x, this.y, pX, pY);
        }else{
            this.x = pX;
            this.y = pY;
        }
    }

    public void forward(double pDistance){
        double angleRad = this.angle * Math.PI/180;
        double xNew = this.x + pDistance*Math.cos(angleRad);
        double yNew = this.y - pDistance*Math.sin(angleRad);
        if(!this.isUp){
            this.drawLine(this.x, this.y, xNew, yNew);
        }else{
            this.x = xNew;
            this.y = yNew;
        }
    }

    public void setAngle(double pAngle){
        this.angle = pAngle%360;
        if(this.angle <0){
            this.angle = this.angle+360;       
        }
    }

    public void turn(double pAngle){
        this.angle = this.angle + pAngle%360;
        if(this.angle <0){
            this.angle = this.angle+360;       
        }
        this.angle = this.angle%360;
    }

    public void turnTo(double pX, double pY){
        if(pX != this.x || pY != this.y){
            if(pX == this.x){
                if(pY > this.y){
                    this.angle = 270;
                }else{
                    this.angle = 90;
                }
            }else{
                if(pY == this.y){
                    if(pX > this.x){
                        this.angle = 0;
                    }else{
                        this.angle = 180;
                    }
                }else{
                    this.angle = Math.atan((pY - this.y) / (this.x - pX)) * 180 / Math.PI;
                    if(pX < this.x){
                        this.angle = this.angle + 180;
                    }
                    if(this.angle <0){
                        this.angle = this.angle+360;       
                    }
                }
            }
        }
    }
    
    public void drawRect(double pWidth, double pHeight){
        GraphicsContext g = myWindow.g();
        if (g != null)
        {          
            this.setState(g);       
            g.strokeRect(this.x,this.y,pWidth,pHeight);
        }
        
    }
    
     public void drawCircle(double pRadius){
        GraphicsContext g = myWindow.g();
        if (g != null)
        {          
            this.setState(g);       
            g.strokeOval(this.x,this.y,pRadius, pRadius);
        }
        
    }
    
    
    public void write(String pText){
        GraphicsContext g = myWindow.g();
        if (g != null)
        {          
            this.setState(g);       
            Text text = new Text(pText);
            //https://stackoverflow.com/questions/13015698/how-to-calculate-the-pixel-width-of-a-string-in-javafx
            g.strokeText(pText, this.x, this.y);
            this.x = this.x+text.getLayoutBounds().getWidth();
        }
    }
    
     public void write(double pNumber){
         this.write(""+pNumber);
     }
     
      public void write(int pNumber){
         this.write(""+pNumber);
     }
    
     
      public void write(long pNumber){
         this.write(""+pNumber);
     }
     
      public void write(byte pNumber){
         this.write(""+pNumber);
     }
     
      public void write(char pCharacter){
         this.write(""+pCharacter);
     }
     

    public void up(){
        this.isUp = true;
    }
    
    public void down(){
        this.isUp = false;
    }
    
    public void normal() 
    {
        this.currentMode = this.NORMALMODE;
    }

    public void erase() 
    {
        this.currentMode = this.ERASEMODE;
    }

    public void changeMode() 
    {
        this.currentMode = this.XORMODE;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getAngle(){
        return this.angle;
    }   

    protected void setStandard(){
        this.x = 0;
        this.y = 0;
        this.isUp = true;
        this.angle = 0;
        this.currentMode = this.NORMALMODE;
        this.normal();
    }
    
    public void setDelay(int pMilliSeconds){
        this.delay = pMilliSeconds;
    }

}
