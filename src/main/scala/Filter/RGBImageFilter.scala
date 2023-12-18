package Filter

import Image.RGBImage

trait RGBImageFilter extends Filter[RGBImage {
  def apply(image: RGBImage) : RGBImage
}
