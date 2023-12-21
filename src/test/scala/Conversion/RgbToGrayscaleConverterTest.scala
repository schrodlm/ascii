package Conversion

import Image.RGBImage
import org.scalatest.{FlatSpec, Matchers}

class RgbToGrayscaleConverterTest extends FlatSpec with Matchers {
  "RgbToGrayscaleConverter" should "correctly convert an RGBImage to a GrayscaleImage" in {
    val width = 10
    val height = 10
    val rgbData = Array.fill(width * height)(0xff123456) // Example RGB data
    val rgbImage = new RGBImage(width, height, rgbData)

    val converter = new RgbToGrayscaleConverter(rgbImage)
    val grayscaleImage = converter.convert()

    // Assertions
    grayscaleImage.getWidth() shouldBe width
    grayscaleImage.getHeight() shouldBe height

    // Check grayscale values
    grayscaleImage.getData().foreach { grayscaleValue =>
      // Calculate expected grayscale value based on the RGB data used
      val red = (0xff123456 >> 16) & 0xff
      val green = (0xff123456 >> 8) & 0xff
      val blue = 0xff123456 & 0xff
      val expectedGrayscale = (0.3 * red + 0.59 * green + 0.11 * blue).toInt

      grayscaleValue shouldBe expectedGrayscale
    }
  }
}
