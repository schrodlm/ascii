package Conversion

import Config.{ImageSource, PathImageSource, RandomImageSource}
import Image.ImageLoaderStrategy.{ImageLoadingStrategy, JPEGImageLoadingStrategy, PNGImageLoadingStrategy, RandomImageLoadingStrategy}
import Image.RGBImage

import java.awt.image.BufferedImage

class SourceToRgbImage(input: ImageSource, var loadingStrategy: Option[ImageLoadingStrategy] = None) extends Convertor[RGBImage] {

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
          Some(new RandomImageLoadingStrategy())
      }
    }
    loadingStrategy.get.load()
  }
}
