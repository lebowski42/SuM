
public class Mouse
{

    public Mouse()
    { 
    }

    public int getX()
    {
        try
        {
            Thread.sleep(1);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (Window.topWindow != null)
            return (int) Window.topWindow.getMouseX();
        else
        {
            return this.warning();
        }
    }

    public int getY()
    {
        try
        {
            Thread.sleep(1);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (Window.topWindow != null)
            return (int) Window.topWindow.getMouseY();
        else
        {
            return this.warning();
        }
    }

    public boolean isPressed()
    {
        try
        {
            Thread.sleep(1);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (Window.topWindow  != null)
            return Window.topWindow.isMouseButtonDown;
        else
        {
            return this.warning() != -1;
        }
    }

    public boolean doubleclick()
    {
        try
        {
            Thread.sleep(1);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (Window.topWindow  != null)
            if (Window.topWindow.wasDoubleClick )
            {
                Window.topWindow.wasDoubleClick = false;
                return true;
            }
            else{
                return false;
            }
        else
        {
            return this.warning() != -1;
        }
    }

    public int mouseButton(){
        try
        {
            Thread.sleep(1);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (Window.topWindow  != null)
            return Window.topWindow.buttonNumber;
        else
        {
            return this.warning();
        }
    }

    private int warning(){
        System.err.println("Mouse without Window");
        return -1;
    }

	
    
}
