package sparkless101.crosshairmod.crosshair.properties.property;

public class BooleanProperty extends Property<Boolean>
{
	public BooleanProperty(boolean value)
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
		return new BooleanProperty(Boolean.parseBoolean(value));
	}
}