package Conversion

import Config.Tables.Table
import Image.{AsciiArt, GrayscaleImage}

class GrayscaleToAsciiConverter() {

  def convert(image: GrayscaleImage, table: Table): AsciiArt = {

    val ascii_data = image.getData().map { value =>
      val grayscaleValue = image.getGrayscale(value)
      table.getChar(grayscaleValue).toInt
    }

    new AsciiArt(image.getWidth(), image.getHeight(), ascii_data)
  }
}
