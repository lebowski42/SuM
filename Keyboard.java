import javafx.scene.input.KeyEvent;
/**
 * Beschreiben Sie hier die Klasse Keyboard.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Keyboard
{
   
    public Keyboard()
    {
       
    }

    /*public void getChar(){
        try
        {
            Thread.sleep(1);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (Window.firstWindow != null)
        {
            
            
            
            if (!zGetestet)
            {
                Warnung warnung = new Warnung(Bildschirm.hatPrivatschirm,
                        "Die Tastatur wurde nicht getestet.");
                if (warnung.istOk())
                    System.exit(0);
                return (char) 0;
            }
            else
            {
                Integer merke = (Integer) Bildschirm.hatPrivatschirm.hatTastaturpuffer
                        .elementAt(0);
                return (char) merke.intValue();
            }
        }
        else
        {
            Warnung warnung = new Warnung(Bildschirm.hatPrivatschirm,
                    "Der Bildschirm wurde nicht erzeu   gt.");
            if (warnung.istOk())
                System.exit(0);
            return (char) 0;
        }
        
    }*/
    
    
    private static char KeyEventToChar(KeyEvent e){
        // Letters and Numbers
       /* String s = e.getText();
        char character;
        if(s.length() >0){
            character =  s.charAt(0);
        }else{
            KeyCode keyCode = e.getCode();
           // of
        }
        */
        return 0;
    }
}
