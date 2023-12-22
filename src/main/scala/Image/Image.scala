package Image

/**
 * Trait for basic image operations.
 *
 * Defines common operations for image processing, applicable to various image types.
 */
trait Image {
  def getPixel(x: Int, y: Int): Int

  def getWidth(): Int

  def getHeight(): Int
}
