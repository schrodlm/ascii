package Filter

import Image.{Image, RGBImage}

class IdentityFilter() extends Filter[Image]
{
  override def apply(image: Image): Image = image
}
