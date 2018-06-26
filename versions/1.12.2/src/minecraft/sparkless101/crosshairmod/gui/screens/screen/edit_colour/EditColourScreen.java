package sparkless101.crosshairmod.gui.screens.screen.edit_colour;

import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.screens.screen.edit_colour.components.EditorPanel;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.ShapeSettingsPanel;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.Theme;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class EditColourScreen extends Screen
{
	private Panel mainPanel;
	
	private EditorPanel editorPanel;
	
	public EditColourScreen(String propertyName)
	{
		int panelWidth = 350;
		
		RGBA panelBackgroundColour = Theme.BLACK.setOpacity(200);
		
		this.mainPanel = new Panel(0, this.getHeaderHeight() + 1, 1000, 1000);
		
		this.mainPanel.setBackgroundColour(Theme.TRANSPARENT);
		this.mainPanel.setBorderColour(Theme.TRANSPARENT);
		this.mainPanel.hoverBorderColour = Theme.TRANSPARENT;
		
		this.editorPanel = new EditorPanel(propertyName, 0, 0, panelWidth, 0);
		this.editorPanel.setBackgroundColour(panelBackgroundColour);
		this.editorPanel.hoverBackgroundColour = panelBackgroundColour;
		
		this.mainPanel.addComponent(this.editorPanel);
		
		this.mainPanel.packComponent();
		
		this.components.add(this.mainPanel);
	}
	
	@Override
	public void updateScreen()
	{
		this.mainPanel.setWidth(this.width - 1);
		this.mainPanel.setHeight(this.height - this.getHeaderHeight() - 2);
	}
}
