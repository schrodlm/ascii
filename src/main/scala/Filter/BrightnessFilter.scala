package Filter
import Image.{GrayscaleImage, RGBImage}

class BrightnessFilter(val value : Int) extends GrayscaleImageFilter {
  override def apply(image: GrayscaleImage): GrayscaleImage = {
    val new_data = image.getData().map{pixel =>

      val grayscale_value = image.getGrayscale(pixel) + value

      val clampedGrayscaleValue = grayscale_value max 0 min 255

      (pixel << 24)| (clampedGrayscaleValue << 16) | (clampedGrayscaleValue << 8) | clampedGrayscaleValue
    }

    new GrayscaleImage(image.getWidth(), image.getHeight(),new_data)
  }
}
