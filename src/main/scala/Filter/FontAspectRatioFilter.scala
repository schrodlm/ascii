package Filter
import Image.{AsciiArt, RGBImage}

class FontAspectRatioFilter(val fontAspect : Int, val pixelAspect : Int) extends AsciiArtFilter {
  override def apply(image: AsciiArt): AsciiArt = ???
}
