package Conversion
import Config.{LinearTable, NonLinearTable, Table}
import Image.{AsciiArt, GrayscaleImage, Image}

class GrayscaleToAsciiConverter(){

  def convert(image : GrayscaleImage, table: LinearTable) : AsciiArt = {

    val width = image.getWidth()
    val height = image.getHeight()

    val bars = table.chars.length

    val ascii_data =  image.getData().map { value =>
        val grayscaleValue = value & 0xFF
        table.getChar(grayscaleValue).toInt

    }

    new AsciiArt(width, height, ascii_data)
  }

  def convert(image: GrayscaleImage, table: NonLinearTable): AsciiArt = {

    val width = image.getWidth()
    val height = image.getHeight()

    val bars = table.chars.length

    val ascii_data = image.getData().map { value =>
      val grayscaleValue = value & 0xFF
      table.getChar(grayscaleValue).toInt


    }

    new AsciiArt(width, height, ascii_data)
  }

  protected def convertWithLinearTable(): Unit =
  {

  }
}
