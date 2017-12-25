
/**
 * Beschreiben Sie hier die Klasse Test.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Test
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private Window bild;
    private Pen stift;
    /**
     * Konstruktor für Objekte der Klasse Test
     */
    public Test()
    {bild = new Window(500,600);
        stift = new Pen();
        stift.moveTo(250,300);
        stift.down();
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void beispielMethode(int y)
    {
        int dec = 1;
        y=25;
        int i=0;
        while(true){i++;
            stift.erase();
            //stift.drawCircle(50);
            this.zeichnung();
            bild.clearWindow();
           // bild.clearWindow();
            stift.forward(dec);
            stift.normal();
           // stift.drawCircle(50);
            this.zeichnung();
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){} 
            
            y=y+dec;
            if(y>=450||y<=25)dec=-1*dec;
            
            System.out.println(stift.getX());
        }
    }
    
    
    public void zeichnung(){
            double x = stift.getX();
            double y = stift.getY();
            double a = stift.getAngle();
            int i = 10;
            while(i>0){
                stift.forward(10);
                stift.turn(100.0*i/100);
                i--;
        }
        stift.moveTo(x,y);
        stift.setAngle(a);
            
        
    }
}
