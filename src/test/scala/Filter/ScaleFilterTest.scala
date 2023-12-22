package Filter

import Image.AsciiArt
import org.scalatest.{FlatSpec, Matchers}

class ScaleFilterTest extends FlatSpec with Matchers {


  val width = 4
  val height = 4
  val data: Array[Int] = Array(
    'a', 'b', 'c', 'd',
    'e', 'f', 'g', 'h',
    'i', 'j', 'k', 'l',
    'm', 'n', 'o', 'p'
  ).map(c => c.toInt) // Example data

  val asciiArt = new AsciiArt(width, height, data)

  "ScaleFilter" should "correctly scale down an AsciiArt image" in {


    val scale = 2.0
    val scaleFilter = new ScaleFilter(scale)
    val scaledArt = scaleFilter.apply(asciiArt)

    // Assertions for scale down
    scaledArt.getWidth() shouldBe (width / scale).toInt
    scaledArt.getHeight() shouldBe (height / scale).toInt
    scaledArt.getData() shouldBe Array('a', 'c', 'i', 'k')
  }

  it should "correctly scale up an AsciiArt image" in {
    val scale = 0.5
    val scaleFilter = new ScaleFilter(scale)
    val scaledArt = scaleFilter.apply(asciiArt)

    scaledArt.getWidth() shouldBe (width / scale).toInt
    scaledArt.getHeight() shouldBe (height / scale).toInt
    scaledArt.getData shouldBe Array(
      'a', 'a', 'b', 'b', 'c', 'c', 'd', 'd',
      'a', 'a', 'b', 'b', 'c', 'c', 'd', 'd',
      'e', 'e', 'f', 'f', 'g', 'g', 'h', 'h',
      'e', 'e', 'f', 'f', 'g', 'g', 'h', 'h',
      'i', 'i', 'j', 'j', 'k', 'k', 'l', 'l',
      'i', 'i', 'j', 'j', 'k', 'k', 'l', 'l',
      'm', 'm', 'n', 'n', 'o', 'o', 'p', 'p',
      'm', 'm', 'n', 'n', 'o', 'o', 'p', 'p'
    ).map(c => c.toInt)
  }

  it should "throw IllegalArgumentException for non-positive scale values" in {
    val scaleFilter = new ScaleFilter(0)

    an[IllegalArgumentException] should be thrownBy scaleFilter.apply(asciiArt)
  }
}