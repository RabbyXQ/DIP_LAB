import java.awt.Color
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

fun main() {
    val imagePath = "image.png"
    val imageFile = File(imagePath)

    if (!imageFile.exists()) {
        println("Image does not exist")
        return
    }

    // Read the original image
    val originalImage: BufferedImage = ImageIO.read(imageFile)

    // Resize the image
    val resizedImage = BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB)
    val canvas: Graphics2D = resizedImage.createGraphics()
    canvas.drawImage(originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH), 0, 0, null)
    canvas.dispose()

    // Convert to grayscale
    val grayImage = BufferedImage(200, 200, BufferedImage.TYPE_BYTE_GRAY)
    val graphics: Graphics2D = grayImage.createGraphics()
    graphics.drawImage(resizedImage, 0, 0, null)
    graphics.dispose()

    // Save the grayscale image
    val grayImageFile = File("gray.png")
    ImageIO.write(grayImage, "png", grayImageFile)

    println("Grayscale image saved as gray.png")

    val height = grayImage.height
    val width = grayImage.width

    val pixelMatrix = Array(height) { IntArray(width) }

    // Populate the pixel matrix
    for (y in 0 until height) {
        for (x in 0 until width) {
            pixelMatrix[y][x] = resizedImage.getRGB(x, y)
        }
    }

    // Create an image to store pixel matrix
    val matrixImage: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    // Populate the matrix image with the pixel matrix
    for (y in 0 until height) {
        for (x in 0 until width) {
            matrixImage.setRGB(x, y, pixelMatrix[y][x])
        }
    }

    // Save the output image
    val outputImageFile = File("output.png")
    ImageIO.write(matrixImage, "png", outputImageFile)

    println("Matrix image saved as output.png")
}
