package Image

trait Image {
  def getPixel(x: Int, y: Int): Int

  def getWidth(): Int

  def getHeight(): Int
}
