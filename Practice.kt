

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.Image
import javax.imageio.ImageIO
import java.io.File

fun main(){
    val image: BufferedImage = ImageIO.read(File("image.png"))

    val resizedImage = BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB)
    val canvas: Graphics2D = resizedImage.createGraphics()
    canvas.drawImage(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH), 0, 0, null)
    canvas.dispose()

    val height = resizedImage.height
    val width = resizedImage.width

    val matrix = Array(height) { IntArray(width) }

    for(y in 0 until height){
        for(x in 0 until width){
            matrix[y][x] = resizedImage.getRGB(x, y)
        }
    }

    val outputImageFile = BufferedImage(200, 200, BufferedImage.TYPE_BYTE_GRAY)

    for(y in 0 until(height)){
        for(x in 0 until(width)){
            outputImageFile.setRGB(x, y, matrix[y][x])
        }
    }

    ImageIO.write(outputImageFile, "png", File("save.png"))
}