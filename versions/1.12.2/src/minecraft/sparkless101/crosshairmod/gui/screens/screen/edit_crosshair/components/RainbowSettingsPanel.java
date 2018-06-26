package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.Slider;
import sparkless101.crosshairmod.gui.screens.Screen;

public class RainbowSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isRainbowEnabledCheckBox;
	
	private Slider rainbowSpeedSlider;
	
	public RainbowSettingsPanel(Screen parentScreen, int x, int y, int width, int height)
	{
		super(parentScreen, x, y, width, height);
		
		this.titleHeading = new Heading(parentScreen, "Rainbow Settings", 0, 0);
		
		this.isRainbowEnabledCheckBox = new CheckBox(parentScreen, "Enable Rainbow", 0, 0);
		this.isRainbowEnabledCheckBox.bindProperty("rainbow_enabled");
		
		this.rainbowSpeedSlider = new Slider(parentScreen, "Rainbow Speed", 0, 0, 100, 1, 1000)
		{
			@Override
			protected String getDisplayValue(float value)
			{
				if (value < 333) return "Slow";
				
				if (value < 666) return "Medium";
				
				return "Fast";
			}
		};
		this.rainbowSpeedSlider.bindProperty("rainbow_speed");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isRainbowEnabledCheckBox);
		this.addComponent(this.rainbowSpeedSlider);
		
		this.packComponent();
	}
}
