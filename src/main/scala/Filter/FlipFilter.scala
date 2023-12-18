package Filter
import Image.{AsciiArt, RGBImage}

class FlipFilter(val axis : String) extends AsciiArtFilter{
  override def apply(image: AsciiArt): AsciiArt = ???
}
