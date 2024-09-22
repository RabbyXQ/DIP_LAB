

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File



fun main(){

    val image: BufferedImage = ImageIO.read(File("resize.png"))

    val height = image.height
    val width = image.width

    val matrix = Array(height) { IntArray(width) }

    for(y in 0 until(height)){
        for(x in 0 until(width)){
            matrix[y][x] = image.getRGB(x, y)
        }
    }

    val output = BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)

    for(y in 0 until(height)){
        for(x in 0 until(width)){
            output.setRGB(x, y, matrix[y][x])
        }
    }

    ImageIO.write(output, "png", File("output.png"))
    

}