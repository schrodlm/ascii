package Image

/**
 * Represents a grayscale image.
 *
 * Extends `BaseImage` to provide specific functionality for grayscale images.
 *
 * @param width Width of the image.
 * @param height Height of the image.
 * @param data Array containing grayscale pixel data.
 */
class GrayscaleImage(width: Int, height: Int, data: Array[Int]) extends BaseImage(width, height, data) {

  /**
   * Extracts the grayscale value from an integer.
   *
   * Assumes the value is in a format where the grayscale value is in the lowest 8 bits.
   *
   * @param value Integer containing grayscale value.
   * @return Grayscale value extracted from the integer.
   */
  def getGrayscale(value: Int): Int = {
    value & 0xFF
  }

  /**
   * Gets the grayscale value of the pixel at (x, y).
   *
   * @param x X-coordinate of the pixel.
   * @param y Y-coordinate of the pixel.
   * @return Grayscale value of the pixel.
   */
  def getGrayscale(x: Int, y: Int): Int = {
    getGrayscale(getPixel(x, y))
  }

}
