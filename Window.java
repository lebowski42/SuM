// Swing (we need a JFrame)
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Everything in the JFrame ist from JavaFX
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
// JavaFX events
import javafx.event.*;
import javafx.scene.input.*;

// Utils
import java.util.ArrayList;

/**
 * Beschreiben Sie hier die Klasse Window.
 * 
 * @author Martin Schulte
 * @version 8.0
 */
public class Window
{
    // Attribute
    public static Window firstWindow;
    public static Window topWindow;
    protected static int windowID;

    private JFrame jFrame ;

    private Scene scene;
    private Canvas canvas;

    private static String defaultFrameTitle = "SuM-Window";

    // Mouse, Keyboard
    private double mouseX, mouseY;
    boolean isMouseButtonDown = false;
    int buttonNumber;
    boolean wasDoubleClick = false;
    private ArrayList<Character> keybuffer;

    private boolean useDoubleBuffering;
    private Color bgColor = Color.WHITE;

    KeyEvent e;
    String s;

    ///////////////////////////////////////////////////////////
    /////////// private classes for event handeling ///////////
    ///////////////////////////////////////////////////////////

    public Window()
    {
        this(0, 0, -1, -1, defaultFrameTitle+" " + (windowID + 1), false);
    }

    public Window(boolean pMitDoubleBuffering)
    {
        this(0, 0, -1, -1, defaultFrameTitle+" " + (windowID + 1), pMitDoubleBuffering);
    }

    public Window(int pLeft, int pTop, int pWidth, int pHeight){
        this(pLeft, pTop, pWidth, pHeight, defaultFrameTitle+" " + (windowID + 1), false);
    }

    public Window(int pLeft, int pTop, int pWidth, int pHeight, boolean pUseDoubleBuffering){
        this(pLeft, pTop, pWidth, pHeight, defaultFrameTitle+" " + (windowID + 1), pUseDoubleBuffering);
    }

    public Window(int pWidth, int pHeight){
        this(0, 0, pWidth, pHeight, defaultFrameTitle+" " + (windowID + 1), false);
    }

    public Window(int pWidth, int pHeight, boolean pUseDoubleBuffering){
        this(0, 0, pWidth, pHeight, defaultFrameTitle+" " + (windowID + 1), pUseDoubleBuffering);
    }

    protected Window(int pLeft, int pTop, int pWidth, int pHeight, String pName, boolean pUseDoubleBuffering){
        if (firstWindow == null)
            firstWindow = this;
        useDoubleBuffering = pUseDoubleBuffering;
        // Init JFrame, then init FX
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    initSwing(pLeft, pTop, pWidth, pHeight, pName);
                }
            });

        /*hatPanel = (JPanel) this.getContentPane();
        hatPanel.setLayout(null); //dann funktioniert setSize und setLocation
        hatPanel.setOpaque(true);*/
        /*this.addWindowListener(new FensterTester());
        this.addComponentListener(new GroesseTester());
        hatPanel.addMouseMotionListener(new MausBeweger());
        hatPanel.addMouseListener(new MausTaster());
        hatPanel.addKeyListener(new TastenTester());
        hatPanel.addFocusListener(new FokusReaktor());*/
        /*if (pBreite == -1) // ganzer Bildschirm
        {
        Dimension dimension = this.getToolkit().getScreenSize();
        pBreite = dimension.width - 20;
        pHoehe = dimension.height - 60;
        }*/

        //Scene

        //hatTastaturpuffer = new Vector();
        //this.fenstergroesseAnpassen();
        //this.setSize(this.getWidth() - this.breite() + pBreite, this.getHeight() - this.hoehe() + pHoehe);
        /*if (zMitDoubleBuffering)
        {
        dbImage = createImage(this.getSize().width, this.getSize().height);
        dbGraphics = (Graphics2D) dbImage.getGraphics();
        }*/
        windowID++;
        //this.init2DGraphics();

        //this.zeichneDich();
        //this.bearbeiteFokusErhalten();
        this.delay(500);

    }

    // For internal use
    private void initSwing(int pLeft, int pTop, int pWidth, int pHeight, String pName){
        jFrame = new JFrame(pName);
        // Fullscreen
        if (pWidth == -1){
            java.awt.Dimension dimension = jFrame.getToolkit().getScreenSize();
            pWidth = dimension.width;
            pHeight = dimension.height;
        }
        jFrame.setBounds(pLeft, pTop, pWidth, pHeight);
        jFrame.setVisible(true);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set JFrame events
        jFrame.addWindowListener(new java.awt.event.WindowAdapter(){
                public void windowClosing(java.awt.event.WindowEvent e)
                {
                    closeWindow();
                }
            });
        JFXPanel jFXPanel = new JFXPanel();
        jFXPanel.setOpaque(true);
        jFrame.add(jFXPanel);


        Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    initFx(jFXPanel);
                }
            });

    }

    private void initFx(JFXPanel pPanel){
        pPanel.setOpaque(false); //non Transparent
        Pane root = new Pane();
        canvas = new Canvas(jFrame.getWidth(), jFrame.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRoundRect(110, 60, 30, 30, 10, Math.random()*10);
        scene = new Scene(root, jFrame.getWidth(), jFrame.getHeight());
        this.setColor(this.bgColor);
        // set JavaFX events
        // Mouse
        scene.setOnMousePressed(new EventHandler<MouseEvent>(){
                public void handle (MouseEvent mouseEvent) {
                    setMousePosition(mouseEvent);
                    isMouseButtonDown = true;
                }
            });
        scene.setOnMouseReleased(new EventHandler<MouseEvent>(){
                public void handle (MouseEvent mouseEvent) {
                    setMousePosition(mouseEvent);   
                    isMouseButtonDown = false;
                    if(mouseEvent.getClickCount() > 1){
                        wasDoubleClick = true;
                    }else{
                        wasDoubleClick = false;
                    }
                }
            });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle (MouseEvent mouseEvent) {
                    setMousePosition(mouseEvent);
                    isMouseButtonDown = false;
                }
            });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>(){
                public void handle (MouseEvent mouseEvent) {
                    setMousePosition(mouseEvent);
                    isMouseButtonDown = true;
                }
            });
        scene.setOnMouseMoved(new EventHandler<MouseEvent>(){
                public void handle (MouseEvent mouseEvent) {
                    setMousePosition(mouseEvent);
                    isMouseButtonDown = false;
                }
            });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent keyEvent){
                    s = keyEvent.getCharacter();
                    char c =  s.charAt(0);
                    e=keyEvent;
                    System.out.println("Es wurde folgende Taste gedrückt:\t" + Character.getNumericValue(c));                }
            });
        Circle circ = new Circle(40, 40, 30);
        Circle circ1 = new Circle(70, 50, 30);
        //((Pane)scene.getRoot()).getChildren().add(circ);
        ((Pane)scene.getRoot()). getChildren().add(canvas);
        // ((Pane)scene.getRoot()).getChildren().add(circ1);
        pPanel.setScene(scene);
    }

    private void delay(long pMilliseconds)
    {
        try
        {
            Thread.sleep(pMilliseconds);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    //////////////////////////////////
    //// called from Eventhandler ////
    //////////////////////////////////

    // close window
    private void closeWindow()
    {
        jFrame.dispose();
        if (this.equals(firstWindow))
            System.exit(0);
    }

    ///////////////////////////////
    //// set- and get-Methods /////
    ///////////////////////////////   

    public int getHeight(){
        return jFrame.getHeight();
    }

    public int getWidth(){
        return jFrame.getWidth();
    }

    protected double getMouseX(){
        return this.mouseX;
    }

    protected double getMouseY(){
        return this.mouseY;
    }

    protected void setMousePosition(MouseEvent pMouseEvent){
        this.mouseX = pMouseEvent.getX();
        this.mouseY = pMouseEvent.getY();
        this.buttonNumber = 1;
        if(pMouseEvent.getButton() == MouseButton.MIDDLE) this.buttonNumber = 2;
        if(pMouseEvent.getButton() == MouseButton.SECONDARY) this.buttonNumber = 3;
    }

    public void setColor(Color pColor){
        canvas.setStyle("-fx-background-color: white;");
        ((Pane)scene.getRoot()).setBackground(new Background(new BackgroundFill(pColor, CornerRadii.EMPTY, Insets.EMPTY)));
        this.bgColor = pColor;
    }

    public Color getColor(){
        return this.bgColor;
    }

    public void test(){
        int a=100000;
        while(a>0){
            //canvas.getGraphicsContext2D().strokeLine(Math.random()*getWidth(), Math.random()*getHeight(), Math.random()*getWidth(), Math.random()*getHeight());
            double x = Math.random()*getWidth();
            double y = Math.random()*getHeight();
            canvas.getGraphicsContext2D().strokeOval(x, y,20,20);
            canvas.getGraphicsContext2D().setStroke(Color.WHITE);
            canvas.getGraphicsContext2D().strokeOval(x, y,20,20);
            canvas.getGraphicsContext2D().setStroke(Color.BLACK);

            a--;
        }
    }
    
    public void test2(int p){
        canvas.getGraphicsContext2D().setFontSmoothingType(null);
        double lw = 1;
        if(p==1){
            canvas.getGraphicsContext2D().setStroke(Color.WHITE);
            canvas.getGraphicsContext2D().setLineWidth(lw);
        
        }
            
        else  {
            
            canvas.getGraphicsContext2D().setStroke(Color.BLACK);
            canvas.getGraphicsContext2D().setLineWidth(lw);
        }

        canvas.getGraphicsContext2D().strokeOval(50, 50,60,60);
        //canvas.getGraphicsContext2D().strokeLine(20,20,100,100);
        
    }

}
