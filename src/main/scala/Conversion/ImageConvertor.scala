package Conversion

import Image.Image

/**
 * Trait defining a generic image converter.
 *
 * @tparam T The type of image to convert to, extending the Image trait.
 */
trait ImageConvertor[T <: Image] {

  /**
   * Converts to the specified image type.
   *
   * @return The converted image of type T.
   */
  def convert() : T
}
