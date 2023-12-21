package Filter
import Image.{AsciiArt, RGBImage}

class RotationFilter(val rotation: Int) extends AsciiArtFilter {
  override def apply(image: AsciiArt): AsciiArt = ???
}
