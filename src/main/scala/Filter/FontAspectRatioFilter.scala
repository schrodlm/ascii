package Filter
import Image.Image

class FontAspectRatioFilter(val fontAspect : Int, val pixelAspect : Int) extends Filter{
  override def apply(image: Image): Unit = ???
}
