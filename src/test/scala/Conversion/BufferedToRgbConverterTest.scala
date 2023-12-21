package Conversion

import org.scalatest.{FlatSpec, Matchers}

import java.awt.Color
import java.awt.image.BufferedImage

class BufferedToRgbConverterTest extends FlatSpec with Matchers{
  "BufferedToRgbConverter" should "correctly convert a BufferedImage to an RGBImage" in {
    val width = 10
    val height = 10
    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    // Fill the BufferedImage with test data
    for (x <- 0 until width; y <- 0 until height) {
      bufferedImage.setRGB(x, y, new Color(x % 256, y % 256, (x + y) % 256).getRGB)
    }

    val rgbImage = BufferedToRgbConverter.convert(bufferedImage)

    // Assertions
    rgbImage.getWidth() shouldBe width
    rgbImage.getHeight() shouldBe height

    // Check pixel values
    for (x <- 0 until width; y <- 0 until height) {
      val expectedColor = new Color(x % 256, y % 256, (x + y) % 256)
      val pixel = rgbImage.getPixel(x, y)
      val color = new Color(pixel, true)

      color.getRed shouldBe expectedColor.getRed
      color.getGreen shouldBe expectedColor.getGreen
      color.getBlue shouldBe expectedColor.getBlue
    }
  }
}
