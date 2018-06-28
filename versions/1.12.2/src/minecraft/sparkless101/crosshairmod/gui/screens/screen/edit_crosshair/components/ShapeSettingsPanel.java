package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.ColourPicker;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.Slider;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class ShapeSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private ColourPicker crosshairColourPicker;
	
	private Slider crosshairWidthSlider;
	
	private Slider crosshairHeightSlider;
	
	private Slider crosshairGapSlider;
	
	private Slider crosshairThicknessSlider;
	
	private Slider crosshairRotationSlider;
	
	private Slider crosshairStyleSlider;
	
	public ShapeSettingsPanel(Screen parentScreen, int x, int y, int width, int height)
	{
		super(parentScreen, x, y, width, height);
		
		this.titleHeading = new Heading(parentScreen, "Crosshair Shape Settings", 0, 0);
		
		int maxValue = CustomCrosshairMod.INSTANCE.getCrosshair().getStyles().size();
		this.crosshairStyleSlider = new Slider(parentScreen, "Crosshair Style", 0, 0, 75, 0, maxValue - 1)
		{
			@Override
			protected String getDisplayValue(float value)
			{
				int intValue = this.valueToInt();
				
				if (intValue == 0) return "Default";
				
				if (intValue == 1) return "Cross";
				
				if (intValue == 2) return "Circle";
				
				if (intValue == 3) return "Square";
				
				return "Invalid (Defaults to Default Crosshair)";
			}
		};
		this.crosshairStyleSlider.bindProperty("crosshair_style");
		
		this.crosshairColourPicker = new ColourPicker(parentScreen, "Crosshair Colour", 0, 0);
		this.crosshairColourPicker.bindProperty("crosshair_colour");
		
		this.crosshairWidthSlider = new Slider(parentScreen, "Width", 0, 0, 150, 0, 50);
		this.crosshairWidthSlider.bindProperty("crosshair_width");
		
		this.crosshairHeightSlider = new Slider(parentScreen, "Height", 0, 0, 150, 0, 50);
		this.crosshairHeightSlider.bindProperty("crosshair_height");
		
		this.crosshairGapSlider = new Slider(parentScreen, "Gap", 0, 0, 100, 0, 50);
		this.crosshairGapSlider.bindProperty("crosshair_gap");
		
		this.crosshairThicknessSlider = new Slider(parentScreen, "Thickness", 0, 0, 40, 1, 10);
		this.crosshairThicknessSlider.bindProperty("crosshair_thickness");
		
		this.crosshairRotationSlider = new Slider(parentScreen, "Rotation", 0, 0, 180, 0, 360);
		this.crosshairRotationSlider.bindProperty("crosshair_rotation");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.crosshairStyleSlider);
		this.addComponent(this.crosshairColourPicker);
		this.addComponent(this.crosshairWidthSlider);
		this.addComponent(this.crosshairHeightSlider);
		this.addComponent(this.crosshairGapSlider);
		this.addComponent(this.crosshairThicknessSlider);
		this.addComponent(this.crosshairRotationSlider);
		
		this.packComponent();
	}
}
