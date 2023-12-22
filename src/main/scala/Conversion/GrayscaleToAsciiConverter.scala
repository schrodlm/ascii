package Conversion

import Config.Tables.Table
import Image.{AsciiArt, GrayscaleImage}

/**
 * Converts a GrayscaleImage to AsciiArt.
 *
 * @param image The GrayscaleImage to convert.
 * @param table The character table to use for conversion.
 */
class GrayscaleToAsciiConverter(val image: GrayscaleImage,val table: Table) extends ImageConvertor[AsciiArt] {

  /**
   * Converts the GrayscaleImage to AsciiArt.
   *
   * Maps grayscale values to ASCII characters based on the provided table.
   *
   * @return The converted AsciiArt.
   */
  def convert(): AsciiArt = {

    val ascii_data = image.getData().map { value =>
      val grayscaleValue = image.getGrayscale(value)
      table.getChar(grayscaleValue).toInt
    }

    new AsciiArt(image.getWidth(), image.getHeight(), ascii_data)
  }
}
