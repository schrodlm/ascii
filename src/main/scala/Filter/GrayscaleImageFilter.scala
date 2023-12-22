package Filter

import Image.GrayscaleImage

/**
 * Trait for filters applicable to Grayscale images.
 *
 * Defines a contract for implementing filters specific to GrayscaleImage.
 */
trait GrayscaleImageFilter extends Filter[GrayscaleImage] {

  /**
   * Applies the filter to a GrayscaleImage.
   *
   * @param image The GrayscaleImage to apply the filter to.
   * @return The filtered GrayscaleImage.
   */
  def apply(image: GrayscaleImage): GrayscaleImage
}