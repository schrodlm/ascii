package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import Image.RGBImage

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
/**
 * Loads images using ImageIO and converts them.
 *
 * @param path Path to the image file.
 * @param converter Converts BufferedImage to RGBImage.
 */
class ImageIOLoadingStrategy(path: String, converter: BufferedImage => RGBImage) extends ImageLoadingStrategy {

  /**
   * Loads and converts an image from the specified path.
   *
   * @return Converted RGBImage.
   */
  override def load(): RGBImage = {
    val bufferedImage = readImage(path)
    converter(bufferedImage)
  }

  /**
   * Reads an image from a file path.
   *
   * @param str Path to the image file.
   * @return BufferedImage read from the file.
   */
  protected def readImage(str: String): BufferedImage = {
    ImageIO.read(new File(path))
  }
}
