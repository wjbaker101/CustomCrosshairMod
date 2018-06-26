package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.CheckBox;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.screens.Screen;

public class VisibilitySettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private CheckBox isVisibleDefaultCheckBox;
	
	private CheckBox isVisibleHiddenGUICheckBox;
	
	private CheckBox isVisibleDebugCheckBox;
	
	private CheckBox isVisibleThirdPersonCheckBox;
	
	private CheckBox isVisibleSpectatorCheckBox;
	
	public VisibilitySettingsPanel(Screen parentScreen, int x, int y, int width, int height)
	{
		super(parentScreen, x, y, width, height);
		
		this.titleHeading = new Heading(parentScreen, "Visibility Settings", 0, 0);
		
		this.isVisibleDefaultCheckBox = new CheckBox(parentScreen, "Visible by Default", 0, 0);
		this.isVisibleDefaultCheckBox.bindProperty("visible_default");
		
		this.isVisibleHiddenGUICheckBox = new CheckBox(parentScreen, "Visible Hidden GUI", 0, 0);
		this.isVisibleHiddenGUICheckBox.bindProperty("visible_hiddengui");
		
		this.isVisibleDebugCheckBox = new CheckBox(parentScreen, "Visible Debug GUI", 0, 0);
		this.isVisibleDebugCheckBox.bindProperty("visible_debug");
		
		this.isVisibleThirdPersonCheckBox = new CheckBox(parentScreen, "Visible in Third Person", 0, 0);
		this.isVisibleThirdPersonCheckBox.bindProperty("visible_thirdperson");
		
		this.isVisibleSpectatorCheckBox = new CheckBox(parentScreen, "Visible in Spectator Mode", 0, 0);
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
