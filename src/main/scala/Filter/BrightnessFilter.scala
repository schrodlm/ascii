package Filter

import Image.GrayscaleImage

/**
 * Adjusts the brightness of a Grayscale image.
 *
 * @param value The amount to adjust brightness by.
 */
class BrightnessFilter(val value: Int) extends GrayscaleImageFilter {

  /**
   * Applies a brightness adjustment to a GrayscaleImage.
   *
   * @param image The GrayscaleImage to adjust.
   * @return The brightness-adjusted GrayscaleImage.
   */
  override def apply(image: GrayscaleImage): GrayscaleImage = {
    val new_data = image.getData().map { pixel =>

      val grayscale_value = image.getGrayscale(pixel) + value
      val clampedGrayscaleValue = grayscale_value max 0 min 255

      clampedGrayscaleValue
    }

    new GrayscaleImage(image.getWidth(), image.getHeight(), new_data)
  }
}
