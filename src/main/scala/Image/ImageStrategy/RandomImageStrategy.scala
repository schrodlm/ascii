package Image.ImageStrategy

import Image.ImageConverter.ImageConverter
import Image.RGBImage

import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

class RandomImageStrategy extends ImageStrategy {
  override def createImage(): RGBImage = {

    val imageUrl = "https://source.unsplash.com/random"
    val connection = new URL(imageUrl).openConnection()
    connection.setRequestProperty("User-Agent", "Mozilla/5.0")

    val inputStream = connection.getInputStream
    val image = ImageIO.read(inputStream)
    inputStream.close()

    ImageConverter.fromBufferedImage(image)
  }
}
