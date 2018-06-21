package sparkless101.crosshairmod.crosshair.properties.property;

/**
 * Base class for a crosshair property.
 * 
 * @author Sparkless101
 *
 * @param <T> The object type of the property.
 */
public abstract class Property<T>
{
	/**
	 * Stores the value of the property.
	 */
	protected final T value;
	
	public Property(T value)
	{
		this.value = value;
	}
	
	/**
	 * Gets the value of the property.
	 * 
	 * @return Value of the property.
	 */
	public T getValue()
	{
		return this.value;
	}
	
	/**
	 * Gets the formatted string version of the value.<br>
	 * This will then be written to the config file.
	 */
	public abstract String toString();
	
	/**
	 * Sets the value of the property, depending on the object type of the property.
	 * 
	 * @param value String value to be converted into the type of the property.
	 * @return A new Property with the given value.
	 */
	public abstract Property setValue(String value);
}
