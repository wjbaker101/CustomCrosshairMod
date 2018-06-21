package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;

public class DotSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isDotEnabledCheckBox;
	
	public DotSettingsPanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.titleHeading = new Heading("Dot Settings", 0, 0);
		
		this.isDotEnabledCheckBox = new CheckBox("Enable Dot", 0, 0);
		this.isDotEnabledCheckBox.bindProperty("dot_enabled");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isDotEnabledCheckBox);
		
		this.packComponent();
	}
}
