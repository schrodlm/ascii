package Conversion

import Config.{ImageSource, PathImageSource, RandomImageSource}
import Image.ImageLoaderStrategy.{ImageLoadingStrategy, JPEGImageLoadingStrategy, PNGImageLoadingStrategy, RandomImageLoadingStrategy}
import Image.RGBImage


/**
 * Converts an image source to an RGBImage.
 *
 * Determines and uses an appropriate loading strategy based on the input source.
 *
 * @param input           The source of the image.
 * @param loadingStrategy Optional custom strategy for loading the image.
 */
class SourceToRgbImage(input: ImageSource, var loadingStrategy: Option[ImageLoadingStrategy] = None) extends ImageConvertor[RGBImage] {

  /**
   * Converts the input source to an RGBImage.
   *
   * Automatically selects a loading strategy based on the input type if not provided.
   *
   * @return The converted RGBImage.
   * @throws IllegalArgumentException if the file format is unsupported.
   */
  def convert(): RGBImage = {
    if (loadingStrategy.isEmpty) {
      loadingStrategy = input match {
        case PathImageSource(path) =>
          val fileFormat = path.split("\\.").last
          fileFormat match {
            case "jpg" => Some(new JPEGImageLoadingStrategy(path, bufferedImage => {
              new BufferedToRgbConverter(bufferedImage).convert()
            }))
            case "png" => Some(new PNGImageLoadingStrategy(path, bufferedImage => {
              new BufferedToRgbConverter(bufferedImage).convert()
            }))
            case _ => throw new IllegalArgumentException("Provided file with unsupported format")
          }

        case RandomImageSource() =>
          Some(new RandomImageLoadingStrategy(bufferedImage => {
            new BufferedToRgbConverter(bufferedImage).convert()
          }))
      }
    }
    loadingStrategy.get.load()
  }
}
