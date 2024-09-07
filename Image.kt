import java.io.File
import java.awt.image.BufferedImage
import java.awt.Image
import java.awt.Graphics2D
import javax.imageio.ImageIO

fun main() {
    val imagePath = "image.png"
    val imageFile = File(imagePath)

    if (!imageFile.exists()) {
        println("Image doesn't exist")
        return
    }

    val originalImage: BufferedImage = ImageIO.read(imageFile)

    val resizedImage = BufferedImage(128, 128, BufferedImage.TYPE_BYTE_GRAY)
    val g: Graphics2D = resizedImage.createGraphics()
    g.drawImage(originalImage.getScaledInstance(128, 128, Image.SCALE_SMOOTH), 0, 0, null)
    g.dispose() 

    val outputImageFile = File("mImage.png")
    ImageIO.write(resizedImage, "png", outputImageFile)  

    println("Resized image saved as mImage.png")

    val width = resizedImage.width
    val height = resizedImage.height

    val pixelMatrix = Array(height) { IntArray(width) }

    for (y in 0 until height) {
        for (x in 0 until width) {
            val pixel = resizedImage.getRGB(x, y)
            pixelMatrix[y][x] = pixel
        }
    }

    
    val matrixImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    for (y in 0 until height) {
        for (x in 0 until width) {
            matrixImage.setRGB(x, y, pixelMatrix[y][x])
        }
    }

    val matrixImageFile = File("matrixImage.png")
    ImageIO.write(matrixImage, "png", matrixImageFile)

   
}
