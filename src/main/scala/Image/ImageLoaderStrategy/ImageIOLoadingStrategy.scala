package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import Image.RGBImage

import java.io.File
import javax.imageio.ImageIO

class ImageIOLoadingStrategy(path : String) extends LoadingImageStrategy {

  override def load(): RGBImage = {
    val bufferedImage = ImageIO.read(new File(path))

    BufferedToRgbConverter.convert(bufferedImage)
  }
}
