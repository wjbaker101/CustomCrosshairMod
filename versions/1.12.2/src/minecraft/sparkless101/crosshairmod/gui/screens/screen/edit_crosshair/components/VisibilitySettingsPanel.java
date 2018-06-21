package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.crosshair.properties.property.BooleanProperty;
import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.Slider;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class VisibilitySettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isVisibleDefaultCheckBox;
	
	private CheckBox isVisibleHiddenGUICheckBox;
	
	private CheckBox isVisibleDebugCheckBox;
	
	private CheckBox isVisibleThirdPersonCheckBox;
	
	private CheckBox isVisibleSpectatorCheckBox;
	
	public VisibilitySettingsPanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.titleHeading = new Heading("Visibility Settings", 0, 0);
		
		this.isVisibleDefaultCheckBox = new CheckBox("Visible by Default", 0, 0);
		this.isVisibleDefaultCheckBox.bindProperty("visible_default");
		
		this.isVisibleHiddenGUICheckBox = new CheckBox("Visible Hidden GUI", 0, 0);
		this.isVisibleHiddenGUICheckBox.bindProperty("visible_hiddengui");
		
		this.isVisibleDebugCheckBox = new CheckBox("Visible Debug GUI", 0, 0);
		this.isVisibleDebugCheckBox.bindProperty("visible_debug");
		
		this.isVisibleThirdPersonCheckBox = new CheckBox("Visible in Third Person", 0, 0);
		this.isVisibleThirdPersonCheckBox.bindProperty("visible_thirdperson");
		
		this.isVisibleSpectatorCheckBox = new CheckBox("Visible in Spectator Mode", 0, 0);
		this.isVisibleSpectatorCheckBox.bindProperty("visible_spectator");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.isVisibleDefaultCheckBox);
		this.addComponent(this.isVisibleHiddenGUICheckBox);
		this.addComponent(this.isVisibleDebugCheckBox);
		this.addComponent(this.isVisibleThirdPersonCheckBox);
		this.addComponent(this.isVisibleSpectatorCheckBox);
		
		this.packComponent();
	}
}
