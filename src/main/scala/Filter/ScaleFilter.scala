package Filter
import Image.AsciiArt

class ScaleFilter(val scale : Int) extends AsciiArtFilter {
  override def apply(image: AsciiArt): AsciiArt = {
    if (scale <= 0) throw new IllegalArgumentException("Scale must be positive")

    val newWidth = image.getWidth() / scale
    val newHeight = image.getHeight() / scale
    val newData = Array.ofDim[Int](newWidth * newHeight)

    for {
      y <- 0 until newHeight
      x <- 0 until newWidth
    } {
      val originalX = x * scale
      val originalY = y * scale
      newData(y * newWidth + x) = image.getData()(originalY * image.getWidth() + originalX)
    }

    new AsciiArt(newWidth, newHeight, newData)
  }
}
