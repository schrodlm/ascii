package Filter
import Image.RGBImage

class FontAspectRatioFilter(val fontAspect : Int, val pixelAspect : Int) extends Filter{
  override def apply(image: RGBImage): RGBImage = ???
}
