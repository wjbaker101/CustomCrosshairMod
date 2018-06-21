package sparkless101.crosshairmod.crosshair.properties.property;

public class IntegerProperty extends Property<Integer>
{
	public IntegerProperty(Integer value)
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
		return new IntegerProperty(Integer.parseInt(value));
	}
}