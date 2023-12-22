package Filter

import Image.Image

class IdentityFilter() extends Filter[Image]
{
  override def apply(image: Image): Image = image
}
