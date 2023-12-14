package Image.ImageLoaderStrategy

import Image.ImageConverter.ImageConverter
import Image.RGBImage

import java.io.File
import javax.imageio.ImageIO

class ImageIOLoadingStrategy(path : String) extends LoadingImageStrategy {

  override def load(): RGBImage = {
    val bufferedImage = ImageIO.read(new File(path))

    ImageConverter.fromBufferedImage(bufferedImage)
  }
}
