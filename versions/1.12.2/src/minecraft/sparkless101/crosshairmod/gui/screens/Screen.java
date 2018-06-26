package sparkless101.crosshairmod.gui.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.crosshair.config.Config;
import sparkless101.crosshairmod.gui.components.Component;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public abstract class Screen extends GuiScreen
{
	/**
	 * A list of components to display on the screen.
	 */
	protected List<Component> components;
	
	/**
	 * Whether or not the header should be drawn onto the screen.
	 */
	protected boolean isHeaderVisible = true;
	
	/**
	 * Height of the header for all screens.
	 */
	private int headerHeight;
	
	/**
	 * Stores whether the mouse if currently being pressed or not.
	 */
	private boolean isMouseDown;
	
	/**
	 * Length of time the mouse have been pressed for.
	 */
	private long mouseDragTime;
	
	/**
	 * Stores the starting X position when the mouse is being dragged.
	 */
	private int dragStartX;
	
	/**
	 * Stores the starting Y position when the mouse is being dragged.
	 */
	private int dragStartY;
	
	/**
	 * Initialise the screen.
	 */
	public Screen()
	{
		this.components = new ArrayList<Component>();
		
		this.headerHeight = 35;
		
		this.isMouseDown = false;
	}
	
	public void updateScreen()
	{
		if (this.isMouseDown)
		{
			this.mouseDragTime++;
		}
	}
	
	/**
	 * Draw the screen.
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
        GL11.glColor3f(1.0F, 1.0F, 1.0F); // Reset colour
        
        this.drawBackground();
		
		this.components.forEach((component) -> component.drawComponent());
		
		if (this.isHeaderVisible) this.drawScreenHeader();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	/**
	 * Draws the header.<br>
	 * Contains the Mod name and version, as well as the settings button.
	 */
	private void drawScreenHeader()
	{
		RenderManager.drawFilledRectangle(0, 0, this.width, this.headerHeight - 1, Theme.PRIMARY, true);
		RenderManager.drawLine(0, this.headerHeight - 1, this.width, this.headerHeight - 1, 1.0F, Theme.DARK_GREY, true);
		
		String title = CustomCrosshairMod.MOD_NAME + " v" + CustomCrosshairMod.MOD_VERSION;
		
		RenderManager.drawString(title, 10, 13, Theme.WHITE);
	}
	
	/**
	 * Draws the dimmed background.
	 */
	private void drawBackground()
	{
		RenderManager.drawFilledRectangle(0, 0, this.width, this.height, Theme.BLACK.setOpacity(140), true);
	}

	/**
	 * Called when the mouse is depressed.
	 * 
	 * @param mouseX The X position of the mouse.
	 * @param mouseY The Y position of the mouse.
	 */
	protected void onMouseUp(int mouseX, int mouseY)
	{
		this.components.forEach((component) ->
		{
			if (!component.isWithinBounds(mouseX, mouseY)) return;
			
			component.onMouseUp(mouseX, mouseY);
		});
		
		//System.out.println("onMouseUp");
	}

	/**
	 * Called when the mouse is pressed.
	 * 
	 * @param mouseX The X position of the mouse.
	 * @param mouseY The Y position of the mouse.
	 */
	protected void onMouseDown(int mouseX, int mouseY)
	{
		this.mouseDragTime = 0L;
		this.dragStartX = mouseX;
		this.dragStartY = mouseY;
		
		this.components.forEach((component) ->
		{
			if (!component.isWithinBounds(mouseX, mouseY)) return;
			
			component.onMouseDown(mouseX, mouseY);
		});
		
		//System.out.println("onMouseDown");
	}
	
	/**
	 * Called when the mouse is moving.
	 * 
	 * @param mouseX The X position of the mouse.
	 * @param mouseY The Y position of the mouse.
	 */
	protected void onMouseMove(int mouseX, int mouseY)
	{
		this.components.forEach((component) ->
		{
			component.setMouseOver(false);
			
			if (component.isWithinBounds(mouseX, mouseY))
			{
				component.setMouseOver(true);
				
				component.onMouseMove(mouseX, mouseY);
			}
		});
		
		//System.out.println("onMouseMove");
	}
	
	protected void onMouseDrag(int startX, int startY, int mouseX, int mouseY, long dragTime)
	{
		this.components.forEach((component) ->
		{
			component.onMouseDrag(startX, startY, mouseX, mouseY, dragTime);
		});
		
		//System.out.println("onMouseDrag " + startX + " " + startY + " " + mouseX + " " + mouseY + " " + dragTime);
	}
	
	protected void onMouseScrollUp()
	{
		//System.out.println("onMouseScrollUp");
	}
	
	protected void onMouseScrollDown()
	{
		//System.out.println("onMouseScrollDown");
	}
	
	/**
	 * Handles the mouse movement and buttons presses.
	 * Override default mouse input handling, only handling movement and left clicks.
	 */
	public void handleMouseInput()
	{
		int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        
        // Gets which button was pressed (or if the mouse was moved)
        // -1 = Mouse Move
        //  0 = Left Click
        //  1 = Right Click
        //  2 = Middle Click
        int buttonIndex = Mouse.getEventButton();
        
        // Checks whether the mouse button pressed is left click
        // Or if the mouse is moved
        if (buttonIndex == 0)
        {
        	if (this.isMouseDown)
            	this.onMouseUp(mouseX, mouseY);
            else
            	this.onMouseDown(mouseX, mouseY);
        	
        	this.isMouseDown = !this.isMouseDown;
        }
        else if (this.isMouseDown && buttonIndex == -1)
        {
        	this.onMouseDrag(this.dragStartX, this.dragStartY, mouseX, mouseY, this.mouseDragTime);
        }
        else if (buttonIndex == -1)
        {
        	this.onMouseMove(mouseX, mouseY);
        }
        
        this.handleMouseScrollEvents();
	}
	
	/**
	 * Handles the events of the mouse scroll wheel.
	 */
	private void handleMouseScrollEvents()
	{
		// Gets the movement direction of the wheel
        int mouseWheelScroll = Mouse.getDWheel();
        
        // Make sure the scroll wheel has scrolled
        if (mouseWheelScroll == 0) return;
        
        if (mouseWheelScroll > 0)
        {
        	this.onMouseScrollUp();
        }
        else
        {
        	this.onMouseScrollDown();
        }
	}
	
	/**
	 * Called when a key is released.
	 * 
	 * @param keyCode Code of the key released.
	 * @param keyChar Character of the key released.
	 */
	public void onKeyUp(int keyCode, char keyChar)
	{
		//System.out.println("onKeyUp " + keyCode + " " + keyChar);
	}

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode Code of the key pressed.
	 * @param keyChar Character of the key pressed.
	 */
	public void onKeyDown(int keyCode, char keyChar)
	{
		//System.out.println("onKeyDown " + keyCode + " " + keyChar);
	}
	
	public void handleKeyboardInput() throws IOException
	{
		// Stores information about the key event
		char keyChar = Keyboard.getEventCharacter();
		int keyCode = Keyboard.getEventKey();
		
		// Checks whether the keyboard event is press or release
		// True = Down
		// False = Up
		if (Keyboard.getEventKeyState())
		{
			this.onKeyDown(keyCode, keyChar);
		}
		else
		{
			this.onKeyUp(keyCode, keyChar);
		}
		
		super.handleKeyboardInput();
	}
	
	@Override
	public void onGuiClosed()
	{
		// Updates the config file with current crosshair properties
		Config.writeToFile();
	}
	
	/**
	 * Gets the height of the header.
	 * 
	 * @return Height of the header.
	 */
	public int getHeaderHeight()
	{
		return this.headerHeight;
	}
}
