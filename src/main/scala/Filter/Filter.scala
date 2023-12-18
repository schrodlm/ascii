package Filter

import Image.RGBImage

trait Filter {
  def apply(image: RGBImage) : RGBImage
}
