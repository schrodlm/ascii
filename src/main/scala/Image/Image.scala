package Image

trait Image {

  protected val width: Int
  protected val height: Int
  protected val data: Array[Int]
  def rotate(): Image;
  def save(): Unit;

  def getPixel(x: Int, y: Int): Int
  def getWidth(): Int
  def getHeight(): Int

}
