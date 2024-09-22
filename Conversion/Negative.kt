import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import java.awt.Color



fun main() {

    // Load the image
    val image: BufferedImage = ImageIO.read(File("resize.png"))

    val height = image.height
    val width = image.width

    // Create a matrix to hold pixel values
    val matrix = Array(height) { IntArray(width) }

    // Store the pixel values from the image
    for (y in 0 until height) {
        for (x in 0 until width) {
            matrix[y][x] = image.getRGB(x, y)
        }
    }

    // Create the output BufferedImage for the negative image
    val output = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    // Process the image to create the negative effect
    for (y in 0 until height) {
        for (x in 0 until width) {
            val rgba = matrix[y][x]
            val color = Color(rgba, true)

            // Invert the RGB values
            val red = 255 - color.red
            val green = 255 - color.green
            val blue = 255 - color.blue
            val alpha = color.alpha

            // Create the negative color and set it in the output image
            val negativeColor = Color(red, green, blue, alpha)
            output.setRGB(x, y, negativeColor.rgb)
        }
    }

    // Save the output image
    ImageIO.write(output, "png", File("negative.png"))
}
