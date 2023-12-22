package InputParser

import Config.Tables.CustomTable
import Config.{ConsoleImageOutput, PathImageOutput, PathImageSource, RandomImageSource}
import Filter.{BrightnessFilter, FlipFilter, ScaleFilter}
import org.scalatest.{FlatSpec, Matchers}

class CommandLineParserTest extends FlatSpec with Matchers {

  "CommandLineParser" should "correctly parse image source and output options" in {

    val args = Array("--image", "/path/to/image.jpg", "--output-console")
    val parser = new CommandLineParser(args)
    val configOption = parser.parse()

    configOption.isDefined shouldBe true
    val config = configOption.get
    config.imageSource shouldBe a[PathImageSource]
    config.imageOutput shouldBe a[ConsoleImageOutput]
  }

  it should "handle custom table option" in {
    val args = Array("--image-random", "--output-file", "test.txt", "--custom-table", ".*+")
    val parser = new CommandLineParser(args)
    val configOption = parser.parse()

    configOption.isDefined shouldBe true
    val config = configOption.get
    config.imageSource shouldBe a[RandomImageSource]
    config.imageOutput shouldBe a[PathImageOutput]
    config.table shouldBe a[CustomTable]

    config.table.getChar(0) shouldEqual '.'
    config.table.getChar(128) shouldEqual '*'
    config.table.getChar(255) shouldEqual '+'
  }

  it should "parse console output option" in {
    val args = Array("--image-random", "--output-console")
    val parser = new CommandLineParser(args)
    val config = parser.parse().get

    config.imageOutput shouldBe a[ConsoleImageOutput]
    config.imageSource shouldBe a[RandomImageSource]
  }


  it should "parse scale filter option" in {
    val args = Array("--scale", "2", "--image-random", "--output-console")
    val parser = new CommandLineParser(args)
    val config = parser.parse().get


    config.asciiFilters(0) shouldBe (a[ScaleFilter])
  }

  it should "throw when scale filter option is not a double" in {

    val args = Array("--scale", "xyz")

    an[IllegalArgumentException] should be thrownBy {
      new CommandLineParser(args).parse()
    }
  }

  it should "parse brightness filter option" in {
    val args = Array("--brightness", "5", "--image-random", "--output-console")
    val parser = new CommandLineParser(args)
    val config = parser.parse().get

    config.grayscaleFilters(0) shouldBe a[BrightnessFilter]
  }

  it should "throw when brightness filter option is not a valid integer" in {

    val args = Array("--brightness", "xyz")

    an[IllegalArgumentException] should be thrownBy {
      new CommandLineParser(args).parse()
    }
  }

  it should "parse flip filter option" in {
    val args = Array("--flip", "horizontal", "--image-random", "--output-console")
    val parser = new CommandLineParser(args)
    val config = parser.parse().get

    config.asciiFilters(0) shouldBe (a[FlipFilter])
  }

  // Additional tests for other options like --custom-table, --rotate, --invert, etc.

  it should "throw when provided with invalid argument" in {
    val args = Array("--invalid-option")
    val parser = new CommandLineParser(args)

    an[IllegalArgumentException] shouldBe thrownBy {
      parser.parse()
    }
  }

}

