package Conversion
import Config.Table
import Image.{AsciiArt, GrayscaleImage, Image}

class GrayscaleToAsciiConverter(){

  def convert(image : GrayscaleImage, table: Table) : AsciiArt = {

    val width = image.getWidth()
    val height = image.getHeight()

    val bars = table.chars.length

    val ascii_data =  image.getData().map { value =>
        val grayscaleValue = (value & 0xFF)
        val index = (bars-1) * ( grayscaleValue / 255.0)
        table.chars(index.toInt).toInt
    }

    new AsciiArt(width, height, ascii_data)
  }
}
