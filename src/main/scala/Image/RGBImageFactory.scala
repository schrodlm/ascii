package Image

import Config.{ImageSource, PathImageSource, RandomImageSource}
import Image.ImageLoaderStrategy.{JPEGImageLoadingStrategy, PNGImageLoadingStrategy}
import Image.ImageStrategy.RandomImageStrategy

/**
 * Factory object for creating images based on different sources.
 */
object RGBImageFactory {

  /**
   * Creates an image based on the specified image source.
   *
   * This method analyzes the type of ImageSource provided and
   * delegates the image creation to the appropriate strategy.
   *
   * @param input The source of the image.
   * @return The created Image.
   * @throws IllegalArgumentException if an unsupported image format is provided.
   */
  def createRGBImage(input: ImageSource): RGBImage = {

    input match {
      // Handle the case where the image source is a file path.
      case PathImageSource(path) => {

        val fileFormat = path.split("\\.").last

        fileFormat match {
          case "jpg" => {
            new JPEGImageLoadingStrategy(path).load()
          }
          case "png" => {
            new PNGImageLoadingStrategy(path).load()
          }
          // Throw an exception for unsupported file formats.
          case _ => {
            throw new IllegalArgumentException("Provided file with unsupported format")
          }
        }
      }
      // Handle the case where the image source is random.
      case RandomImageSource() => {
        val randomImageStrategy = new RandomImageStrategy()
        randomImageStrategy.createImage()
      }
    }


  }
}
