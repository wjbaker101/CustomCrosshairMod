package sparkless101.crosshairmod.crosshair.properties.property;

public class FloatProperty extends Property<Float>
{
	public FloatProperty(Float value)
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
		return new FloatProperty(Float.parseFloat(value));
	}
}
