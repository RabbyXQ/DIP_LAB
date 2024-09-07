

import java.awt.image.BufferedImage
import java.awt.Image
import javax.imageio.ImageIO
import java.io.File

fun main(){
    val image: BufferedImage = ImageIO.read(File("resize.png"))

    val matrix = Array(image.height) { IntArray(image.width)   }


    for(y in 0 until(image.height)){
        for(x in 0 until(image.width)){
            matrix[y][x] = image.getRGB(x, y)
        }
    }

    val output = BufferedImage(image.width, image.width, BufferedImage.TYPE_INT_ARGB)

    for(y in 0 until(image.height)){
        for(x in 0 until(image.width)){
            output.setRGB(x, y, matrix[y][x])
        }
    }

    ImageIO.write(output, "png", File("matrix.png"))

}