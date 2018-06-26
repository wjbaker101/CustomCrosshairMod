package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.ColourPicker;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.screens.Screen;

public class OutlineSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isOutlineEnabledCheckBox;
	
	private ColourPicker outlineColourPicker;
	
	public OutlineSettingsPanel(Screen parentScreen, int x, int y, int width, int height)
	{
		super(parentScreen, x, y, width, height);
		
		this.titleHeading = new Heading(parentScreen, "Outline Settings", 0, 0);
		
		this.isOutlineEnabledCheckBox = new CheckBox(parentScreen, "Enable Outline", 0, 0);
		this.isOutlineEnabledCheckBox.bindProperty("outline_enabled");
		
		this.outlineColourPicker = new ColourPicker(parentScreen, "Outline Colour", 0, 0);
		this.outlineColourPicker.bindProperty("outline_colour");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isOutlineEnabledCheckBox);
		this.addComponent(this.outlineColourPicker);
		
		this.packComponent();
	}
}
