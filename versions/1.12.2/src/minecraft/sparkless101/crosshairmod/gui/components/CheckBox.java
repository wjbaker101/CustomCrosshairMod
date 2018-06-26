package sparkless101.crosshairmod.gui.components;

import java.awt.Cursor;

import sparkless101.crosshairmod.crosshair.properties.property.BooleanProperty;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class CheckBox extends Component
{
	private RGBA backgroundColour;
	
	private boolean isChecked;
	
	public CheckBox(String label, int x, int y)
	{
		super(label, x, y, 11, 11);
		
		this.backgroundColour = Theme.PRIMARY;
		
		this.isChecked = false;
	}
	
	public void drawComponent()
	{
		RenderManager.drawBorderedRectangle(this.x, this.y, this.x + this.width, this.y + this.height, 1.0F, Theme.DARK_GREY, this.backgroundColour, true);
		
		if (this.isChecked)
		{
			RenderManager.drawBorderedRectangle(this.x + 2, this.y + 2, this.x + this.width - 2, this.y + this.height - 2, 1.0F, Theme.SUCCESS, Theme.SUCCESS, true);
		}
		
		RenderManager.drawString(this.label, this.x + this.width + 4, this.y + (this.height / 2) - 3, Theme.WHITE);
	}
	
	public void onMouseUp(int mouseX, int mouseY)
	{
		this.toggleChecked();
	}
	
	/**
	 * Toggles between whether the CheckBox is checked or not.
	 */
	public void toggleChecked()
	{
		this.setChecked(!this.isChecked);
	}
	
	/**
	 * Sets whether the CheckBox is checked or not.
	 * 
	 * @param isChecked New checked value.
	 */
	public void setChecked(boolean isChecked)
	{
		this.isChecked = isChecked;
		
		// Checks whether there is a bound property
		// Updates the value of the bound property
		if (this.boundProperty != null)
		{
			CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().set(this.boundProperty, new BooleanProperty(this.isChecked));
		}
	}
	
	@Override
	public void bindProperty(String property)
	{
		super.bindProperty(property);
		
		this.setChecked((Boolean)CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().get(this.boundProperty).getValue());
	}
	
	public boolean isChecked()
	{
		return this.isChecked;
	}
}
