package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair;

import sparkless101.crosshairmod.crosshair.properties.config.Config;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.ScrollPanel;
import sparkless101.crosshairmod.gui.components.Slider;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.DotSettingsPanel;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.DynamicCrosshairPanel;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.OutlineSettingsPanel;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.RainbowSettingsPanel;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.ShapeSettingsPanel;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components.VisibilitySettingsPanel;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.Theme;

public class EditCrosshairScreen extends Screen
{
	private ScrollPanel mainPanel;
	
	private ShapeSettingsPanel shapeSettingsPanel;
	
	private VisibilitySettingsPanel visibilitySettingsPanel;
	
	private OutlineSettingsPanel outlineSettingsPanel;
	
	private DotSettingsPanel dotSettingsPanel;
	
	private DynamicCrosshairPanel dynamicCrosshairPanel;
	
	private RainbowSettingsPanel rainbowSettingsPanel;
	
	public EditCrosshairScreen()
	{
		int panelWidth = 250;
		
		RGBA panelBackgroundColour = Theme.BLACK.setOpacity(200);
		
		this.mainPanel = new ScrollPanel(0, this.getHeaderHeight() + 1, 1000, 1000);
		
		this.mainPanel.setBackgroundColour(Theme.TRANSPARENT);
		this.mainPanel.setBorderColour(Theme.TRANSPARENT);
		
		this.shapeSettingsPanel = new ShapeSettingsPanel(0, 0, panelWidth, 0);
		this.shapeSettingsPanel.setBackgroundColour(panelBackgroundColour);
		this.shapeSettingsPanel.hoverBackgroundColour = panelBackgroundColour;
		
		this.visibilitySettingsPanel = new VisibilitySettingsPanel(0, 0, panelWidth, 0);
		this.visibilitySettingsPanel.setBackgroundColour(panelBackgroundColour);
		this.visibilitySettingsPanel.hoverBackgroundColour = panelBackgroundColour;
		
		this.outlineSettingsPanel = new OutlineSettingsPanel(0, 0, panelWidth, 0);
		this.outlineSettingsPanel.setBackgroundColour(panelBackgroundColour);
		this.outlineSettingsPanel.hoverBackgroundColour = panelBackgroundColour;
		
		this.dotSettingsPanel = new DotSettingsPanel(0, 0, panelWidth, 0);
		this.dotSettingsPanel.setBackgroundColour(panelBackgroundColour);
		this.dotSettingsPanel.hoverBackgroundColour = panelBackgroundColour;
		
		this.dynamicCrosshairPanel = new DynamicCrosshairPanel(0, 0, panelWidth, 0);
		this.dynamicCrosshairPanel.setBackgroundColour(panelBackgroundColour);
		this.dynamicCrosshairPanel.hoverBackgroundColour = panelBackgroundColour;
		
		this.rainbowSettingsPanel = new RainbowSettingsPanel(0, 0, panelWidth, 0);
		this.rainbowSettingsPanel.setBackgroundColour(panelBackgroundColour);
		this.rainbowSettingsPanel.hoverBackgroundColour = panelBackgroundColour;

		this.mainPanel.addComponent(this.shapeSettingsPanel);
		this.mainPanel.addComponent(this.visibilitySettingsPanel);
		this.mainPanel.addComponent(this.outlineSettingsPanel);
		this.mainPanel.addComponent(this.dotSettingsPanel);
		this.mainPanel.addComponent(this.dynamicCrosshairPanel);
		this.mainPanel.addComponent(this.rainbowSettingsPanel);
		
		this.mainPanel.packComponent();
		
		this.components.add(this.mainPanel);
	}
	
	@Override
	public void updateScreen()
	{
		this.mainPanel.setWidth(this.width - 1);
		this.mainPanel.setHeight(this.height - this.getHeaderHeight() - 2);
	}
	
	@Override
	protected void onMouseScrollUp()
	{
		this.mainPanel.incrementScroll(-getScrollIncrement());
	}
	
	@Override
	protected void onMouseScrollDown()
	{
		this.mainPanel.incrementScroll(getScrollIncrement());
	}
	
	/**
	 * Calculates how much to increment the scroll panel by when the wheel is scrolled.
	 * 
	 * @return Absolute value of the amount to scroll by.
	 */
	private int getScrollIncrement()
	{
		return this.mainPanel.getHeight() / 7;
	}
}
