package sparkless101.crosshairmod.gui.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import sparkless101.crosshairmod.gui.utils.RGBA;

public class RenderManager
{
    /**
     * Toggles OpenGL stuff before rendering.
     */
    public static void preRender()
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Toggles OpenGL stuff after rendering.
     */
    public static void postRender()
    {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Draws a single line from one point to another.
     *
     * @param x1 X position of the first point.
     * @param y1 Y position of the first point.
     * @param x2 X position of the second point.
     * @param y2 Y position of the second point.
     * @param thickness Thickness of the line.
     * @param colour Colour of the line.
     * @param smooth Whether the line should be smoothed.
     */
    public static void drawLine(float x1, float y1, float x2, float y2, float thickness, RGBA colour, boolean smooth)
    {
        // Creates an array containing the start and end points of the line
        drawLines(new float[] { x1, y1, x2, y2 }, thickness, colour, smooth);
    }

    /**
     * Draws an outline of a rectangle.
     *
     * @param x1 X position of the top left point of the rectangle.
     * @param y1 Y position of the top left point of the rectangle.
     * @param x2 X position of the bottom right point of the rectangle.
     * @param y2 Y position of the top left point of the rectangle.
     * @param thickness Thickness of the line.
     * @param colour Colour of the line.
     * @param smooth Whether the line should be smoothed.
     */
    public static void drawRectangle(float x1, float y1, float x2, float y2, float thickness, RGBA colour, boolean smooth)
    {
        // Creates an array containing the sides of the rectangle
        drawLines(new float[]
        {
	        x1, y1, x2, y1,
	        x2, y1, x2, y2,
	        x1, y2, x2, y2,
	        x1, y1, x1, y2
        }, thickness, colour, smooth);
    }

    /**
     * Draws a filled rectangle with a border.
     *
     * @param x1 X position of the top left point of the rectangle.
     * @param y1 Y position of the top left point of the rectangle.
     * @param x2 X position of the bottom right point of the rectangle.
     * @param y2 Y position of the top left point of the rectangle.
     * @param borderThickness Thickness of the border.
     * @param borderColour Colour of the border
     * @param fillColour Colour of the background.
     * @param smooth Whether the border should be smoothed
     */
    public static void drawBorderedRectangle(float x1, float y1, float x2, float y2, float borderThickness, RGBA borderColour, RGBA fillColour, boolean smooth)
    {
        drawFilledRectangle(x1, y1, x2, y2, fillColour, smooth);
        drawRectangle(x1, y1, x2, y2, borderThickness, borderColour, smooth);
    }

    /**
     * Draws a sequence of connecting lines.
     *
     * @param points Array of points to draw.
     * @param thickness Thickness of the line.
     * @param colour Colour of the line.
     * @param smooth Whether the line should be smoothed.
     */
    public static void drawLines(float[] points, float thickness, RGBA colour, boolean smooth)
    {
        preRender();
        
    	glToggle(GL11.GL_LINE_SMOOTH, smooth);

        GL11.glLineWidth(thickness);
        
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getOpacity() / 255.0F);
        
        GL11.glBegin(GL11.GL_LINES);

        for (int i = 0; i < points.length; i += 2)
        {
            // Draws a lines between 2 sets of points
            GL11.glVertex2f(points[i], points[i + 1]);
        }

        GL11.glEnd();
        
        postRender();
    }

    /**
     * Draws a filled rectangle.
     *
     * @param x1 Top left X coordinate of the rectangle.
     * @param y1 Top left Y coordinate of the rectangle.
     * @param x2 Bottom right X coordinate of the rectangle.
     * @param y2 Bottom right Y coordinate of the rectangle.
     * @param colour Colour of the rectangle.
     * @param smooth Whether the rectangle should have smoothed sides.
     */
    public static void drawFilledRectangle(float x1, float y1, float x2, float y2, RGBA colour, boolean smooth)
    {
        // Creates an array containing the sides of the rectangle
        drawFilledShape(new float[]
        {
	        x1, y1,
	        x1, y2,
	        x2, y2,
	        x2, y1
        }, colour, smooth);
    }

    /**
     * Draws a filled shape.
     *
     * @param points Array of vertices to draw.
     * @param colour Colour of the shape.
     * @param smooth Whether the shape should have smoothed sides.
     */
    public static void drawFilledShape(float[] points, RGBA colour, boolean smooth)
    {
        preRender();
        
    	glToggle(GL11.GL_LINE_SMOOTH, smooth);

        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getOpacity() / 255.0F);
        
        GL11.glBegin(GL11.GL_POLYGON);

        for (int i = 0; i < points.length; i += 2)
        {
            // Draws a lines between 2 sets of points
            GL11.glVertex2f(points[i], points[i + 1]);
        }

        GL11.glEnd();
        
        postRender();
    }

    /**
     * Draws a full circle.
     *
     * @param x X position of the centre point of the circle.
     * @param y Y position of the centre point of the circle.
     * @param radius Radius of the circle.
     * @param thickness Thickness of the line.
     * @param colour Colour of the circle.
     * @param smooth Whether the line being drawn should be smoothed.
     */
    public static void drawCircle(float x, float y, float radius, float thickness, RGBA colour, boolean smooth)
    {
    	// Draws a full circle
        drawPartialCircle(x, y, radius, 0, 360, thickness, colour, smooth);
    }

    /**
     * Draws a partial circle.
     *
     * @param x X position of the centre point of the circle.
     * @param y Y position of the centre point of the circle.
     * @param radius Radius of the circle.
     * @param startAngle Angle (in degrees) which the partial circle will start. 0 being the top/ north point.
     * @param endAngle Angle (in degrees) which the partial circle will end. 0 being the top/ north point.
     * @param thickness Thickness of the line.
     * @param colour Colour of the circle.
     * @param smooth Whether the line being drawn should be smoothed.
     */
    public static void drawPartialCircle(float x, float y, float radius, int startAngle, int endAngle, float thickness, RGBA colour, boolean smooth)
    {
        preRender();

        // Check whether the start angle is smaller than the end angle
        // Performs a swap if start is less than end
        if (startAngle > endAngle)
        {
            int temp = startAngle;
            startAngle = endAngle;
            endAngle = temp;
        }

        // Limits the angles between 0 and 360
        if (startAngle < 0)
        {
            startAngle = 0;
        }

        if (endAngle > 360)
        {
            endAngle = 360;
        }
        
    	glToggle(GL11.GL_LINE_SMOOTH, smooth);

        GL11.glLineWidth(thickness);
        
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getOpacity() / 255.0F);
        
        GL11.glBegin(GL11.GL_LINE_STRIP);
        
        float ratio = (float)Math.PI / 180.F;

        for (int i = startAngle; i <= endAngle; ++i)
        {
            // i - 90 translates the angle so it will be relative to the top most point in the circle
            // Rather than being relative to the right
            float radians = (i - 90) * ratio;
            GL11.glVertex2f(x + (float)Math.cos(radians) * radius, y + (float)Math.sin(radians) * radius);
        }

        GL11.glEnd();
        postRender();
    }
    
    /**
     * Draws a torus (doughnut) shape.
     * 
     * @param x Centred X position of the torus.
     * @param y Centred Y position of the torus.
     * @param innerRadius Radius of the hole inside the torus.
     * @param outerRadius Radius of the entire torus.
     * @param colour Colour of the torus.
     * @param smooth Whether the edges of the torus should be smooth.
     */
    public static void drawTorus(int x, int y, int innerRadius, int outerRadius, RGBA colour, boolean smooth)
    {
    	preRender();

    	glToggle(GL11.GL_LINE_SMOOTH, smooth);

        GL11.glLineWidth(1.0F);
        
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getOpacity() / 255.0F);
        
        GL11.glBegin(GL11.GL_LINE_STRIP);
        
        float ratio = (float)Math.PI / 180.F;

        for (int i = 0; i < 360; ++i)
        {
            // i - 90 translates the angle so it will be relative to the top most point in the circle
            // Rather than being relative to the right
            float radians = (i - 90) * ratio;
            
            GL11.glVertex2f(x + (float)Math.cos(radians) * innerRadius, y + (float)Math.sin(radians) * innerRadius);
            GL11.glVertex2f(x + (float)Math.cos(radians) * outerRadius, y + (float)Math.sin(radians) * outerRadius);
        }

        GL11.glEnd();
        
        postRender();
    }

    /**
     * Draws text.
     *
     * @param text Text to display.
     * @param x X position of the text.
     * @param y Y position of the text.
     * @param colour Colour of the text.
     */
    public static void drawString(String text, int x, int y, RGBA colour)
    {
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getOpacity() / 255.0F);
        
        Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, 0xffffff);
    }

    /**
     * Gets the width of the given text.
     *
     * @param text Text to measure.
     */
    public static int getTextWidth(String text)
    {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
    }
    
    /**
     * Toggles an OpenGL property depending on the given flag.
     * 
     * @param property The property to toggle.
     * @param enable Whether or not to enable or disable the property.
     */
    private static void glToggle(int property, boolean enable)
    {
    	if (enable)
        {
            GL11.glEnable(property);
        }
        else
        {
            GL11.glDisable(property);
        }
    }
}
