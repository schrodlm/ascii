package Conversion

import Config.Tables.Table
import Image.GrayscaleImage
import org.scalatest.{FlatSpec, Matchers}

class GrayscaleToAsciiConverterTest extends FlatSpec with Matchers {
  "GrayscaleToAsciiConverter" should "correctly convert a GrayscaleImage to AsciiArt" in {
    val width = 10
    val height = 10
    val grayscaleData = Array.fill(width * height)(128) // Example grayscale data
    val grayscaleImage = new GrayscaleImage(width, height, grayscaleData)

    val mockTable = new Table {
      override def getChar(value: Int): Char = {
        // Simplified character mapping for testing
        (value / 25 + 65).toChar // Maps grayscale values to ASCII characters
      }
    }

    val converter = new GrayscaleToAsciiConverter()
    val asciiArt = converter.convert(grayscaleImage, mockTable)

    // Assertions
    asciiArt.getWidth() shouldBe width
    asciiArt.getHeight() shouldBe height

    // Check ASCII values
    asciiArt.getData().foreach { asciiValue =>
      asciiValue shouldBe 'F'.toInt // Based on the mock mapping
    }
  }
}
