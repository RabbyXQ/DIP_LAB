import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import java.awt.Color

fun main() {

    // Load the image
    val image: BufferedImage = ImageIO.read(File("resize.png"))

    val height = image.height
    val width = image.width

    // Create the output BufferedImage for the grayscale image
    val output = BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)

    // Process the image to create the grayscale effect
    for (y in 0 until height) {
        for (x in 0 until width) {
            val rgba = image.getRGB(x, y)
            val color = Color(rgba, true)

            val red = color.red
            val green = color.green
            val blue = color.blue

            // Sum the RGB values
            val total = red + green + blue

            // Prevent division by zero and compute grayValue based on the normalized RGB values
            val grayValue = if (total > 0) {
                (red.toDouble() / total * red + green.toDouble() / total * green + blue.toDouble() / total * blue).toInt()
            } else {
                0 // If the RGB sum is 0 (black), set the grayscale value to 0
            }

            // Clamp the gray value between 0 and 255
            val clampedGrayValue = grayValue.coerceIn(0, 255)

            // Create the grayscale color and set it to the output image
            val gray = Color(clampedGrayValue, clampedGrayValue, clampedGrayValue, color.alpha).rgb
            output.setRGB(x, y, gray)
        }
    }

    // Save the output image
    ImageIO.write(output, "png", File("rgbsum.png"))
}
