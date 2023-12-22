package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import Image.RGBImage

import java.awt.image.BufferedImage
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

class RandomImageLoadingStrategy(imageConverter: BufferedImage => RGBImage) extends ImageLoadingStrategy {

  private val imageUrl = "https://source.unsplash.com/random"

  override def load(): RGBImage = {

    val connection = new URL(imageUrl).openConnection()
    connection.setRequestProperty("User-Agent", "Mozilla/5.0")

    val inputStream = connection.getInputStream
    try {
      val image = readImage(inputStream)
      imageConverter(image)
    }finally {
      inputStream.close()
    }

  }

  protected def readImage(inputStream: InputStream): BufferedImage = {
    ImageIO.read(inputStream)
  }
}
