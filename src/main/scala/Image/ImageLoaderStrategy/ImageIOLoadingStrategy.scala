package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import Image.RGBImage

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageIOLoadingStrategy(path : String, converter: BufferedImage => RGBImage) extends ImageLoadingStrategy {

  override def load(): RGBImage = {
    val bufferedImage = readImage(path)

    converter(bufferedImage)
  }

  protected def readImage(str: String): BufferedImage = {
    ImageIO.read(new File(path))
  }
}
