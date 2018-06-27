package sparkless101.crosshairmod.gui.screens.screen.edit_colour.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import sparkless101.crosshairmod.crosshair.properties.property.RGBAProperty;
import sparkless101.crosshairmod.gui.components.Button;
import sparkless101.crosshairmod.gui.components.Heading;
import sparkless101.crosshairmod.gui.components.Panel;
import sparkless101.crosshairmod.gui.components.Slider;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class EditorPanel extends Panel
{
	private Heading titleHeading;
	
	private Slider redSlider;
	
	private Slider greenSlider;
	
	private Slider blueSlider;
	
	private Slider opacitySlider;
	
	private Button confirmButton;
	
	public EditorPanel(Screen parentScreen, String propertyName, int x, int y, int width, int height)
	{
		super(parentScreen, x, y, width, height);
		
		RGBA originalColour = (RGBA)CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().get(propertyName).getValue();
		
		this.titleHeading = new Heading(parentScreen, "Colour Settings", 0, 0);
		
		this.redSlider = new Slider(parentScreen, "Red", 0, 0, 255, 0, 255);
		this.redSlider.setValue(originalColour.getRed());
		this.redSlider.setThumbColours(new RGBA(235, 0, 0, 255), new RGBA(190, 0, 0, 255));
		
		this.greenSlider = new Slider(parentScreen, "Green", 0, 0, 255, 0, 255);
		this.greenSlider.setValue(originalColour.getGreen());
		this.greenSlider.setThumbColours(new RGBA(0, 235, 0, 255), new RGBA(0, 190, 0, 255));
		
		this.blueSlider = new Slider(parentScreen, "Blue", 0, 0, 255, 0, 255);
		this.blueSlider.setValue(originalColour.getBlue());
		this.blueSlider.setThumbColours(new RGBA(0, 0, 235, 255), new RGBA(0, 0, 190, 255));
		
		this.opacitySlider = new Slider(parentScreen, "Opacity", 0, 0, 255, 0, 255);
		this.opacitySlider.setValue(originalColour.getOpacity());
		this.opacitySlider.setThumbColours(new RGBA(235, 235, 235, 255), new RGBA(190, 190, 190, 255));
		
		this.confirmButton = new Button(parentScreen, "Confirm", 0, 0, 75, 25)
		{
			@Override
			public void onMouseUp(int mouseX, int mouseY)
			{
				super.onMouseUp(mouseX, mouseY);
				
				RGBA newColour = new RGBA(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue());
				
				CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().set(propertyName, new RGBAProperty(newColour));
				
				Minecraft.getMinecraft().displayGuiScreen(parentScreen.getParentScreen());
			}
		};
		
		this.addComponent(this.titleHeading);
		this.addComponent(this.redSlider);
		this.addComponent(this.greenSlider);
		this.addComponent(this.blueSlider);
		this.addComponent(this.opacitySlider);
		this.addComponent(this.confirmButton);
		
		this.packComponent();
	}
}
