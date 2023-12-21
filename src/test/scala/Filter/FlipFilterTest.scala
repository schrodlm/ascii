package Filter

import Image.AsciiArt
import org.scalatest.{FlatSpec, Matchers}

class FlipFilterTest extends FlatSpec with Matchers{

  val width = 2
  val height = 3
  val data: Array[Int] = Array('a', 'b', 'c', 'd', 'e', 'f').map(c => c.toInt) // Example data

  val asciiArt = new AsciiArt(width, height, data)


  "FlipFilter" should "correctly flip an AsciiArt image vertically" in {

    val flipFilter = new FlipFilter("x")
    val flippedArt = flipFilter.apply(asciiArt)

    // Assertions
    flippedArt.getWidth() shouldBe width
    flippedArt.getHeight() shouldBe height
    flippedArt.getData() shouldBe Array('f', 'e', 'd', 'c', 'b', 'a')
  }

  it should "correctly flip an AsciiArt image horizontally" in {
    val flipFilter = new FlipFilter("y")
    val flippedArt = flipFilter.apply(asciiArt)

    flippedArt.getData() shouldBe Array('b', 'a', 'd', 'c', 'f', 'e')
  }

  it should "throw IllegalArgumentException for invalid axis" in {
    val flipFilter = new FlipFilter("z")

    an[IllegalArgumentException] should be thrownBy flipFilter.apply(asciiArt)
  }
}
