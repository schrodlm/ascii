package Filter

import Image.GrayscaleImage

trait GrayscaleImageFilter extends Filter[GrayscaleImage]{
  def apply(image: GrayscaleImage) : GrayscaleImage
}
