package Image

class JPGImage(protected val width: Int, protected val height: Int, protected val data: Array[Int]) extends Image{



  override def rotate: Image = ???;

  override def save(): Unit = ???;
  override def getHeight(): Int = {
    println(f"Height: ${this.height}")
    this.height
  }

  override def getWidth(): Int = {
    println(f"Width: ${this.width}")
    this.width
  }

  override def getPixel(x: Int, y: Int): Int = {
    this.data(x*y)
  }
}
