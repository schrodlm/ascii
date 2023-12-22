package Image

import org.scalatest.{FlatSpec, Matchers}

class GrayscaleImageTest extends FlatSpec with Matchers{

  "GrayscaleImage" should "correctly extract grayscale values from pixel data" in {
    val width = 2
    val height = 2
    val data = Array(0x00000000, 0xFF010101, 0xFF000001, 0x000000FF) // Example grayscale data
    val grayscaleImage = new GrayscaleImage(width, height, data)

    grayscaleImage.getGrayscale(0) shouldBe 0
    grayscaleImage.getGrayscale(1) shouldBe 1
    grayscaleImage.getGrayscale(1) shouldBe 1
    grayscaleImage.getGrayscale(255) shouldBe 255
  }

  it should "correctly extract grayscale values using x and y coordinates" in {
    val grayscaleImage = new GrayscaleImage(2, 2, Array(0xFF000000, 0xFF010101, 0xFF010202, 0x00000003))

    grayscaleImage.getGrayscale(0, 0) shouldBe 0
    grayscaleImage.getGrayscale(1, 0) shouldBe 1
    grayscaleImage.getGrayscale(0, 1) shouldBe 2
    grayscaleImage.getGrayscale(1, 1) shouldBe 3
  }
}
