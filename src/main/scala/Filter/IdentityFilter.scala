package Filter

import Image.Image

class IdentityFilter() extends Filter
{
  override def apply(image: Image): Image =
  {
    image
  }
}
