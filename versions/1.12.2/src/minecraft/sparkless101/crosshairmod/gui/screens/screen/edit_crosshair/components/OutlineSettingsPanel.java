package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.ColourPicker;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;

public class OutlineSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isOutlineEnabledCheckBox;
	
	private ColourPicker outlineColourPicker;
	
	public OutlineSettingsPanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.titleHeading = new Heading("Outline Settings", 0, 0);
		
		this.isOutlineEnabledCheckBox = new CheckBox("Enable Outline", 0, 0);
		this.isOutlineEnabledCheckBox.bindProperty("outline_enabled");
		
		this.outlineColourPicker = new ColourPicker("Outline Colour", 0, 0);
		this.outlineColourPicker.bindProperty("outline_colour");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isOutlineEnabledCheckBox);
		this.addComponent(this.outlineColourPicker);
		
		this.packComponent();
	}
}
