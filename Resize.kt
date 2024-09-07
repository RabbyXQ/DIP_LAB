

import java.awt.image.BufferedImage
import java.awt.Image
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.File

fun main(){
    val image: BufferedImage = ImageIO.read(File("image.png"))

    val resizedImage = BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB)
    val canvas: Graphics2D = resizedImage.createGraphics()
    canvas.drawImage(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH), 0, 0, null)
    canvas.dispose()

    ImageIO.write(resizedImage, "png", File("resized.png"))
}