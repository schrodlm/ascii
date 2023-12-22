package Filter

import Image.Image

/**
 * Trait defining a generic filter for images.
 *
 * @tparam T The type of image the filter is applicable to, extending the Image trait.
 */
trait Filter[T <: Image] {

  /**
   * Applies the filter to an image.
   *
   * @param image The image to apply the filter to.
   * @return The filtered image of type T.
   */
  def apply(image: T): T
}