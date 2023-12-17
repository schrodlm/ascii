package Image

import org.w3c.dom.css.RGBColor

/**
 * Represents an image with a specified width, height, and pixel data.
 *
 * @param width  The width of the image.
 * @param height The height of the image.
 * @param data   An array containing pixel data.
 */
class RGBImage(width: Int, height: Int, data: Array[Int]) extends BaseImage(width,height,data) {

  def getPixelColor(x : Int, y : Int) : (Int, Int, Int, Int) = {
    getPixelColor(getPixel(x,y))
  }

  def getPixelColor(pixel: Int): (Int, Int, Int, Int) = {

    val alpha = (pixel >> 24) & 0xff
    val red = (pixel >> 16) & 0xff
    val green = (pixel >> 8) & 0xff
    val blue = pixel & 0xff

    (alpha, red, green, blue)
  }

}
