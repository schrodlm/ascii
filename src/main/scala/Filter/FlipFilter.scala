package Filter
import Image.RGBImage

class FlipFilter(val axis : String) extends AsciiArtFilter{
  override def apply(image: RGBImage): RGBImage = ???
}
