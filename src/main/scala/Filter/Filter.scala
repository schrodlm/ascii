package Filter

import Image.Image

trait Filter {
  def apply(image: Image)
}
