package Filter
import Image.{GrayscaleImage, RGBImage}

class BrightnessFilter(val value : Int) extends GrayscaleImageFilter {
  override def apply(image: GrayscaleImage): GrayscaleImage = ???
}
