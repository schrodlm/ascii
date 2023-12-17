package Conversion

import Config.table.Table
import Image.{AsciiArt, GrayscaleImage}

class GrayscaleToAsciiConverter(){

  def convert(image : GrayscaleImage, table: Table) : AsciiArt = {

    val ascii_data =  image.getData().map { value =>
        val grayscaleValue = value & 0xFF
        table.getChar(grayscaleValue).toInt
    }

    new AsciiArt(image.getWidth(), image.getHeight(), ascii_data)
  }
}