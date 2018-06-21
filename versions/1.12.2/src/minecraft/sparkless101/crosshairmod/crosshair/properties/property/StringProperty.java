package sparkless101.crosshairmod.crosshair.properties.property;

public class StringProperty extends Property<String>
{
	public StringProperty(String value)
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
		return new StringProperty(value);
	}
}
