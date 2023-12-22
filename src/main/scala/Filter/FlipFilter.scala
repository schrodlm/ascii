package Filter

import Image.{AsciiArt}


/**
 * Flips an AsciiArt image along a specified axis.
 *
 * @param axis The axis to flip the image ('x' for vertical, 'y' for horizontal).
 */
class FlipFilter(val axis: String) extends AsciiArtFilter {

  /**
   * Applies the flip filter to an AsciiArt image.
   *
   * @param image The AsciiArt image to flip.
   * @return The flipped AsciiArt image.
   * @throws IllegalArgumentException if an invalid axis is provided.
   */
  override def apply(image: AsciiArt): AsciiArt = {
    axis.toLowerCase match {
      case "x" | "vertical" => flipVertically(image)
      case "y" | "horizontal" => flipHorizontally(image)
      case _ => throw new IllegalArgumentException("Axis provided for the flip filter must be 'x' or 'y'")
    }
  }

  // Flips the image vertically.
  private def flipVertically(image: AsciiArt): AsciiArt = {
    val newData = image.getData().reverse
    new AsciiArt(image.getWidth(), image.getHeight(), newData)
  }

  // Flips the image horizontally.
  private def flipHorizontally(image: AsciiArt): AsciiArt = {
    val newData = image.getData().grouped(image.getWidth()).flatMap(_.reverse).toArray
    new AsciiArt(image.getWidth(), image.getHeight(), newData)
  }
}
