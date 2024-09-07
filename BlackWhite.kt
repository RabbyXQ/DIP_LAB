import java.io.File
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO

fun main() {
    val imagePath = "image.png"
    val imageFile = File(imagePath)

    if (!imageFile.exists()) {
        println("Image doesn't exist")
        return
    }

    // Read the original image
    val originalImage: BufferedImage = ImageIO.read(imageFile)

    // Resize the image to 128x128
    val resizedImage = BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB)
    val g: Graphics2D = resizedImage.createGraphics()
    g.drawImage(originalImage.getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH), 0, 0, null)
    g.dispose() // Clean up

    // Convert to black and white
    val threshold = 128 // Set the threshold for black-and-white conversion

    for (y in 0 until resizedImage.height) {
        for (x in 0 until resizedImage.width) {
            val rgb = resizedImage.getRGB(x, y)

            // Extract the red, green, and blue components
            val red = (rgb shr 16) and 0xFF
            val green = (rgb shr 8) and 0xFF
            val blue = rgb and 0xFF

            // Calculate the brightness or luminance (use average)
            val brightness = (red + green + blue) / 3

            // Determine if the pixel should be black or white
            val bwRgb = if (brightness >= threshold) 0xFFFFFF else 0x000000

            // Set the pixel to black or white, preserving alpha
            val alpha = rgb.toInt() and 0xFF000000.toInt()
            resizedImage.setRGB(x, y, (bwRgb or alpha).toInt())        
        }
    }

    // Save the black and white image as "mImage.png"
    val outputImageFile = File("mImage.png")
    ImageIO.write(resizedImage, "png", outputImageFile)  // Saving the black-and-white image

    println("Black and white image saved as mImage.png")

    // Initialize the matrix to store pixel values
    val width = resizedImage.width
    val height = resizedImage.height
    val pixelMatrix = Array(height) { IntArray(width) }

    // Populate the matrix with pixel values
    for (y in 0 until height) {
        for (x in 0 until width) {
            pixelMatrix[y][x] = resizedImage.getRGB(x, y)
        }
    }

    // Convert the matrix back to an image
    val matrixImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    for (y in 0 until height) {
        for (x in 0 until width) {
            matrixImage.setRGB(x, y, pixelMatrix[y][x])
        }
    }

    // Save the matrix image as "mmImage.png"
    val matrixImageFile = File("mmImage.png")
    ImageIO.write(matrixImage, "png", matrixImageFile)

    println("Matrix image saved as mmImage.png")
}
