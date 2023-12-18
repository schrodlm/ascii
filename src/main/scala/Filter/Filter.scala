package Filter

import Image.{Image, RGBImage}

trait Filter[T <: Image]{
  def apply(image: T) : T
}
