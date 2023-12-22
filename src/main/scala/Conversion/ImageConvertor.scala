package Conversion

import Image.Image

trait ImageConvertor[T <: Image] {

  def convert() : T
}
