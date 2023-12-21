package Image

import org.scalatest.{FlatSpec, Matchers}

class RGBImageTest extends FlatSpec with Matchers
{
  "RGBImage" should "correctly extract color components from pixel data" in {
    val width = 2
    val height = 2
    val data = Array(
      0xFF112233,
      0xFF445566,
      0xFF778899,
      0xFFAABBCC
    )
    val rgbImage = new RGBImage(width, height, data)

    // Test getPixelColor with x, y coordinates
    rgbImage.getPixelColor(0, 0) shouldBe(0xFF, 0x11, 0x22, 0x33)
    rgbImage.getPixelColor(1, 0) shouldBe(0xFF, 0x44, 0x55, 0x66)
    rgbImage.getPixelColor(0, 1) shouldBe(0xFF, 0x77, 0x88, 0x99)
    rgbImage.getPixelColor(1, 1) shouldBe(0xFF, 0xAA, 0xBB, 0xCC)

    // Test getPixelColor with pixel value
    rgbImage.getPixelColor(data(0)) shouldBe(0xFF, 0x11, 0x22, 0x33)
    rgbImage.getPixelColor(data(1)) shouldBe(0xFF, 0x44, 0x55, 0x66)
    rgbImage.getPixelColor(data(2)) shouldBe(0xFF, 0x77, 0x88, 0x99)
    rgbImage.getPixelColor(data(3)) shouldBe(0xFF, 0xAA, 0xBB, 0xCC)
  }
}
