package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.Slider;

public class DynamicCrosshairPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isDynamicAttackIndicatorEnabledCheckBox;
	
	private CheckBox isDynamicBowEnabledCheckBox;
	
	public DynamicCrosshairPanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.titleHeading = new Heading("Dynamic Crosshair Settings", 0, 0);
		
		this.isDynamicAttackIndicatorEnabledCheckBox = new CheckBox("Enable Dynamic Attack Indicator", 0, 0);
		this.isDynamicAttackIndicatorEnabledCheckBox.bindProperty("dynamic_attackindicator_enabled");
		
		this.isDynamicBowEnabledCheckBox = new CheckBox("Enable Dynamic Bow", 0, 0);
		this.isDynamicBowEnabledCheckBox.bindProperty("dynamic_bow_enabled");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isDynamicAttackIndicatorEnabledCheckBox);
		this.addComponent(this.isDynamicBowEnabledCheckBox);
		
		this.packComponent();
	}
}
