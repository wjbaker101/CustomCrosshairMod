package sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.components;

import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.Slider;

public class ShapeSettingsPanel extends Panel
{
	private Heading titleHeading;
	
	private Slider crosshairWidthSlider;
	
	private Slider crosshairHeightSlider;
	
	private Slider crosshairGapSlider;
	
	private Slider crosshairThicknessSlider;
	
	private Slider crosshairRotationSlider;
	
	public ShapeSettingsPanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.titleHeading = new Heading("Crosshair Shape Settings", 0, 0);
		
		this.crosshairWidthSlider = new Slider("Width", 0, 0, 100, 0, 50);
		this.crosshairWidthSlider.bindProperty("crosshair_width");
		
		this.crosshairHeightSlider = new Slider("Height", 0, 0, 100, 0, 50);
		this.crosshairHeightSlider.bindProperty("crosshair_height");
		
		this.crosshairGapSlider = new Slider("Gap", 0, 0, 100, 0, 50);
		this.crosshairGapSlider.bindProperty("crosshair_gap");
		
		this.crosshairThicknessSlider = new Slider("Thickness", 0, 0, 40, 1, 10);
		this.crosshairThicknessSlider.bindProperty("crosshair_thickness");
		
		this.crosshairRotationSlider = new Slider("Rotation", 0, 0, 180, 0, 360);
		this.crosshairRotationSlider.bindProperty("crosshair_rotation");
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.crosshairWidthSlider);
		this.addComponent(this.crosshairHeightSlider);
		this.addComponent(this.crosshairGapSlider);
		this.addComponent(this.crosshairThicknessSlider);
		this.addComponent(this.crosshairRotationSlider);
		
		this.packComponent();
	}
}
