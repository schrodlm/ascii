package Image

class Image(width: Int, height: Int, data: Array[Int]) {

  /**
   * TODO: This would be better in some kind of filter (maybe visitor pattern?)
   *
   *
   *   def rotate(): Image;
   *   def save(): Unit;
  */

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
