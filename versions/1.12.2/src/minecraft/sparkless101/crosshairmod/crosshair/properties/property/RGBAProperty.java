package sparkless101.crosshairmod.crosshair.properties.property;

import sparkless101.crosshairmod.gui.utils.RGBA;

public class RGBAProperty extends Property<RGBA>
{
	public RGBAProperty(RGBA value)
	{
		super(value);
	}
	
	@Override
	public String toString()
	{
		return this.value.toString();
	}

	@Override
	public Property setValue(String value)
	{
		String[] split = value.split("/");
		
		int red = Integer.parseInt(split[0]);
		int green = Integer.parseInt(split[1]);
		int blue = Integer.parseInt(split[2]);
		int opacity = Integer.parseInt(split[3]);
		
		return new RGBAProperty(new RGBA(red, green, blue, opacity));
	}
}
