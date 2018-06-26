package sparkless101.crosshairmod.crosshair.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map.Entry;

import sparkless101.crosshairmod.crosshair.properties.property.Property;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class Config
{
	/**
	 * File name of the config file.
	 */
	private static final String SAVE_LOCATION = "crosshair_config.ccmcfg";
	
	/**
	 * Character at the the start of the line to denote a comment.<br>
	 * (Reader will ignore the line.)
	 */
	private static final String COMMENT = "#";
	
	/**
	 * Stores the lines to add as a header to the config file.
	 */
	private static final String[] HEADER = new String[]
	{
		String.format("%s %s v%s", COMMENT, CustomCrosshairMod.MOD_NAME, CustomCrosshairMod.MOD_VERSION),
		String.format("%s Made by Sparkless101", COMMENT),
		String.format("%s ------------------------------------", COMMENT),
		String.format("%s %s", COMMENT, CustomCrosshairMod.MC_FORUMS),
		String.format("%s %s", COMMENT, CustomCrosshairMod.CURSEFORGE),
		String.format("%s ------------------------------------", COMMENT),
		String.format("%s This config file contains the properties of the crosshair and mod settings. Feel free to edit them.", COMMENT),
		String.format("%s ------------------------------------", COMMENT),
	};
	
	/**
	 * Reads the config file.<br>
	 * Sets properties of the crosshair and mod settings depending on the values read.
	 * 
	 * @return Whether or not the config file was successfully read.
	 */
	public static boolean readFromFile()
	{
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SAVE_LOCATION)))
		{
			// Stores the value of the current line in the file
			String currentLine = null;
			
			// Stores the alias and value separated by the ":"
			String[] lineSplit;
			
			// Stores the alias and value from the line into their own variable
			String alias, value;
			
			while ((currentLine = bufferedReader.readLine()) != null)
			{
				// Ignore the line if it starts with a comment
				if (currentLine.startsWith(COMMENT)) continue;
				
				// Gets the alias and value from either side of the ":"
				lineSplit = currentLine.split(":");
				
				// Ignore if line is incorrectly formatted
				if (lineSplit.length == 0) continue;
				
				// Gets the alias and value of the line to store in the property
				// Removes any unnecessary trailing spaces and converts to lower case
				alias = lineSplit[0].trim().toLowerCase();
				value = lineSplit[1].trim().toLowerCase();
				
				// Gets the current property object
				// Associated from the alias found on the current line
				Property property = CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().get(alias);
				
				// Checks whether there is a property with the current alias
				if (property != null)
				{
					// Updates the property value with the new value from the config file
					CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().set(alias, property.setValue(value));
				}
			}
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Writes the config file for the mod.<br>
	 * Writes the values of the properties of the crosshair.
	 * 
	 * @return Whether or not the config file was successfully saved.
	 */
	public static boolean writeToFile()
	{
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SAVE_LOCATION)))
		{
			// Write lines from the header
			// Writes information about the mod
			for (String line : HEADER)
			{
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			
			// Writes lines for each of the properties
			// Formats the key and value of the properties separated by a ":"
			for (Entry entry: CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().getList())
			{
				bufferedWriter.write(String.format("%s:%s", entry.getKey(), entry.getValue()));
				bufferedWriter.newLine();
			}
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
