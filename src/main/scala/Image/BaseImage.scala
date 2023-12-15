package Image

abstract class BaseImage(width: Int, height: Int, data: Array[Int]) extends Image {

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
