package Image

import org.w3c.dom.css.RGBColor

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

  def getPixelColor(x : Int, y : Int) : (Int, Int, Int, Int) = {
    val pixel = getPixel(x,y)

    val alpha = (pixel >> 24) & 0xff
    val red   = (pixel >> 16) & 0xff
    val green = (pixel >> 8) & 0xff
    val blue  = pixel & 0xff

    (alpha, red, green, blue)
  }

}
