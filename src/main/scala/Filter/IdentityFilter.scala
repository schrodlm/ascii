package Filter

import Image.RGBImage

class IdentityFilter() extends Filter
{
  override def apply(image: RGBImage): RGBImage =
  {
    image
  }
}
