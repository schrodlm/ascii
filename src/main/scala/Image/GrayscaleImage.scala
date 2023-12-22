package Image

class GrayscaleImage(width: Int, height: Int, data: Array[Int]) extends BaseImage(width, height, data) {

  def getGrayscale(value: Int): Int = {
    value & 0xFF
  }

  def getGrayscale(x: Int, y: Int): Int = {
    getGrayscale(getPixel(x, y))
  }

}
