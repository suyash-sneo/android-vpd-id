import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class emojisGrid
{
    // Unique ID
    private static final String id = "AE8Hr/tnoH1wVpdX202dNy8Hipm0dEYTMgzPfB==";

    public static void main(String[] args)
    {
        generateImage();
    }

    private static void generateImage()
    {
        // Canvas Dimensions
        int canvasWidth = 600;
        int canvasHeight = 600;

        BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        // Background gradient color
        GradientPaint gradient = new GradientPaint(0, 0, new Color(192, 192, 192), canvasWidth, canvasHeight, new Color(240, 240, 240));
        graphics.setPaint(gradient);
        graphics.fillRect(0, 0, canvasWidth, canvasHeight);

        // Explicitly specify java.util.List here
        List<String> emojis = fetchEmojis();

        int gridSize = 2;
        int padding = 10;
        int emojiWidth = (canvasWidth - padding * (gridSize + 1)) / gridSize;
        int emojiHeight = (canvasHeight - padding * (gridSize + 1)) / gridSize;
        List<String> emojisUsed = new ArrayList<String>();

        // Seed random generator with hashed ID
        Random random = new Random(id.hashCode());

        for (int row = 0; row < gridSize; row++)
        {
            for (int col = 0; col < gridSize; col++) 
            {
                // Get emoji and ensure no repeat emoji is being used
                String emoji = "";
                while(true)
                {
                    emoji = emojis.get(random.nextInt(emojis.size()));
                    if (!emojisUsed.contains(emoji))
                    {
                        emojisUsed.add(emoji);
                        break;
                    }

                }

                try
                {
                    // Load the image from URL using ImageIO.read
                    BufferedImage img = ImageIO.read(new URL(emoji));

                    // Check if image is loaded successfully
                    if (img != null)
                    {
                        int x = padding + col * (emojiWidth + padding);
                        int y = padding + row * (emojiHeight + padding);
                        // System.out.println("Drawing emoji at coordinates: (" + x + ", " + y + ") -
                        // URL: " + emoji);
                        graphics.drawImage(img, x, y, emojiWidth, emojiHeight, null);
                    }
                    else 
                {
                        System.err.println("Failed to load image: " + emoji);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    System.err.println("Error loading image: " + emoji);
                }
            }
        }

        try
        {
            ImageIO.write(image, "png", new File("emoji_grid.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static List<String> fetchEmojis()
    {
        List<String> emojis = new ArrayList<>();
        try
        {
            URL url = new URL("https://api.github.com/emojis");
            String response = new String(url.openConnection().getInputStream().readAllBytes());
            // System.out.println("\nResponse: " + response);
            JSONObject data = new JSONObject(response);
            // System.out.println("\n\nDATA: " + data);
            for (String key : data.keySet())
            {
                // System.out.println("\n\nEmoji: " + data.getString(key));
                emojis.add(data.getString(key));
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
        return emojis;
    }
}
/*

PC:

Compile Code:
javac -cp "C:\Users\suhas\OneDrive\Documents\Code\Projects\IDtoIMG\scripts\lib\json-20240303.jar" emojisGrid.java
Run Code:
java -cp "C:\Users\suhas\OneDrive\Documents\Code\Projects\IDtoIMG\scripts\lib\json-20240303.jar;." emojisGrid

Laptop:

Compile Code:
javac -cp "C:\Users\suhas\Documents\Code\Projects\IDtoIMG\scripts\lib\json-20240303.jar" emojisGrid.java
(may get some deprecation warnings, but ignore)
Run Code:
java -cp "C:\Users\suhas\Documents\Code\Projects\IDtoIMG\scripts\lib\json-20240303.jar;." emojisGrid

 */
