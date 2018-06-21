package sparkless101.crosshairmod.gui.components;

import org.lwjgl.opengl.GL11;

import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class Heading extends Component
{
	/**
	 * Scaling factor of the rendered text.
	 */
	private float textScaling;
	
	/**
	 * Reverse scaling factor for after text has been rendered.
	 */
	private float reverseTextScaling;
	
	public Heading(String label, int x, int y)
	{
		super(label, x, y, RenderManager.getTextWidth(label) + 2, 18);
		
		this.setTextScaling(1.5F);
		
		this.height *= this.textScaling;
	}
	
	public void drawComponent()
	{
		GL11.glScalef(this.textScaling, this.textScaling, 1);
		
		RenderManager.drawString(this.label, (int)(this.x / this.textScaling), (int)(this.y / this.textScaling) + 5, this.currentTextColour);
		
		GL11.glScalef(this.reverseTextScaling, this.reverseTextScaling, 1);
	}
	
	/**
	 * Sets the scaling for when rendering the text.<br>
	 * Also sets the reverse scale after rendering is complete.
	 * 
	 * @param scaling Scaling factor.
	 */
	public void setTextScaling(float scaling)
	{
		this.textScaling = scaling;
		
		this.reverseTextScaling = 1.0F / this.textScaling;
	}
}
