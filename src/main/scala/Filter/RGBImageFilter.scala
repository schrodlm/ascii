package Filter

import Image.RGBImage


/**
 * Trait for filters applicable to RGB images.
 *
 * Defines a contract for implementing filters specific to RGBImage.
 */
trait RGBImageFilter extends Filter[RGBImage] {

  /**
   * Applies the filter to an RGBImage.
   *
   * @param image The RGBImage to apply the filter to.
   * @return The filtered RGBImage.
   */
  def apply(image: RGBImage): RGBImage
}