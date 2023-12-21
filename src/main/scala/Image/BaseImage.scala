package Image

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
