package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.screens.Screen;

public class DotSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isDotEnabledCheckBox;
	
	public DotSettingsPanel(Screen parentScreen, int x, int y, int width, int height)
	{
		super(parentScreen, x, y, width, height);
		
		this.titleHeading = new Heading(parentScreen, "Dot Settings", 0, 0);
		
		this.isDotEnabledCheckBox = new CheckBox(parentScreen, "Enable Dot", 0, 0);
		this.isDotEnabledCheckBox.bindProperty("dot_enabled");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isDotEnabledCheckBox);
		
		this.packComponent();
	}
}
