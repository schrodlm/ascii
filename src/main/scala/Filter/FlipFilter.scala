package Filter
import Image.RGBImage

class FlipFilter(val axis : String) extends Filter{
  override def apply(image: RGBImage): RGBImage = ???
}
