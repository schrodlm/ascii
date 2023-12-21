package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import Image.RGBImage

import java.net.URL
import javax.imageio.ImageIO

class RandomImageLoadingStrategy extends ImageLoadingStrategy {
  override def load(): RGBImage = {

    val imageUrl = "https://source.unsplash.com/random"
    val connection = new URL(imageUrl).openConnection()
    connection.setRequestProperty("User-Agent", "Mozilla/5.0")

    val inputStream = connection.getInputStream
    val image = ImageIO.read(inputStream)
    inputStream.close()

    new BufferedToRgbConverter(image).convert()
  }
}
