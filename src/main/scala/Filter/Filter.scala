package Filter

import Image.Image

trait Filter[T <: Image]{
  def apply(image: T) : T
}
