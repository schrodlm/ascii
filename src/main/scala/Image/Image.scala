package Image

/**
 * Represents an image with a specified width, height, and pixel data.
 *
 * @param width  The width of the image.
 * @param height The height of the image.
 * @param data   An array containing pixel data.
 */
class Image(width: Int, height: Int, data: Array[Int]) {

  def getPixel(x: Int, y: Int): Int = {
    data(x * y)
  }
  def getWidth(): Int = {
    width
  }
  def getHeight(): Int = {
    height
  }

}
