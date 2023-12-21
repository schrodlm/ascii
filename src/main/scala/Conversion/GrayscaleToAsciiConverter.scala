package Conversion

import Config.Tables.Table
import Image.{AsciiArt, GrayscaleImage}

class GrayscaleToAsciiConverter(val image: GrayscaleImage,val table: Table) extends Convertor[AsciiArt] {

  def convert(): AsciiArt = {

    val ascii_data = image.getData().map { value =>
      val grayscaleValue = image.getGrayscale(value)
      table.getChar(grayscaleValue).toInt
    }

    new AsciiArt(image.getWidth(), image.getHeight(), ascii_data)
  }
}
