package Image

/**
 * Abstract base class for image representations.
 *
 * Provides common functionality for different image types.
 *
 * @param width Width of the image.
 * @param height Height of the image.
 * @param data Array storing the image's pixel data.
 */
abstract class BaseImage(width: Int, height: Int, data: Array[Int]) extends Image {

  def getPixel(x: Int, y: Int): Int = {
    data(y * getWidth() + x)
  }

  def getWidth(): Int = {
    width
  }

  def getHeight(): Int = {
    height
  }

  def getData(): Array[Int] = {
    data
  }
}
