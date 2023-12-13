package Image.ImageLoaderStrategy

import Image.{Image, ImageConverter}

import java.io.File
import javax.imageio.ImageIO

class ImageIOLoadingStrategy(path : String) extends LoadingImageStrategy {

  override def load(): Image = {
    val bufferedImage = ImageIO.read(new File(path))

    ImageConverter.fromBufferedImage(bufferedImage)
  }
}
