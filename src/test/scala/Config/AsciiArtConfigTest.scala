package Config

import Config.table.{CustomTable, DefaultTable, PaulBorkesTable}
import Filter.{AsciiArtFilter, GrayscaleImageFilter, RGBImageFilter}
import org.mockito.MockitoSugar.mock
import org.scalatest.{FlatSpec, Matchers}

class AsciiArtConfigTest extends FlatSpec with Matchers {

  "AsciiArtConfig builder" should "correctly build a config with all properties set" in {
    val source = mock[ImageSource]
    val output = mock[ImageOutput]
    val chars = Array('.', ':', '-', '=', '+', '*')
    val mockAsciiFilter = mock[AsciiArtFilter]
    val mockGrayscaleFilter = mock[GrayscaleImageFilter]
    val mockRgbFilter = mock[RGBImageFilter]

    val config = new AsciiArtConfig.Builder()
      .withImageSource(source)
      .withImageOutput(output)
      .withTable(chars)
      .withFilters(Array(mockAsciiFilter, mockGrayscaleFilter, mockRgbFilter))
      .build()

    config.image_source shouldBe source
    config.image_output shouldBe output
    config.table shouldBe a[CustomTable]
    config.asciiFilters should contain theSameElementsAs Array(mockAsciiFilter)
    config.grayscaleFilters should contain theSameElementsAs Array(mockGrayscaleFilter)
    config.rgbImageFilters should contain theSameElementsAs Array(mockRgbFilter)

  }

  it should "build when name of available tables are provided" in {
    val source = mock[ImageSource]
    val output = mock[ImageOutput]
    val PaulBorkeTable = "PaulBorkes"

    val config = new AsciiArtConfig.Builder()
      .withImageSource(source)
      .withImageOutput(output)
      .withTable(PaulBorkeTable)
      .build()

    config.table shouldBe a[PaulBorkesTable]

  }

  it should "build when no custom table is provided (by using default table)" in {
    val source = mock[ImageSource]
    val output = mock[ImageOutput]

    val config = new AsciiArtConfig.Builder()
      .withImageSource(source)
      .withImageOutput(output)
      .build()

    config.image_source shouldBe source
    config.image_output shouldBe output
    config.table shouldBe a[DefaultTable]
  }


  it should "throw an exception when image source is not set" in {

    val output = mock[ImageOutput]

    an[IllegalStateException] should be thrownBy {
      new AsciiArtConfig.Builder()
        .withImageOutput(output)
        .build()
    }
  }

  it should "throw an exception when image output is not set" in {

    val source = mock[ImageSource]

    an[IllegalStateException] should be thrownBy {
      new AsciiArtConfig.Builder()
        .withImageSource(source)
        .build()
    }
  }

  it should "build correctly with only image source and image output " in {

    val source = mock[ImageSource]
    val output = mock[ImageOutput]

     val config =  new AsciiArtConfig.Builder()
        .withImageSource(source)
        .withImageOutput(output)
        .build()

    config.image_source shouldBe source
    config.image_output shouldBe output
    config.table shouldBe a[DefaultTable]
    config.asciiFilters shouldBe empty
    config.grayscaleFilters shouldBe empty
    config.rgbImageFilters shouldBe empty

  }

  it should "correctly categorize filters" in {
    val asciiFilter = mock[AsciiArtFilter]
    val grayscaleFilter = mock[GrayscaleImageFilter]
    val rgbFilter = mock[RGBImageFilter]

    val config = new AsciiArtConfig.Builder()
      .withImageSource(mock[ImageSource])
      .withImageOutput(mock[ImageOutput])
      .addFilter(asciiFilter)
      .addFilter(grayscaleFilter)
      .addFilter(rgbFilter)
      .build()

    config.asciiFilters should contain only asciiFilter
    config.grayscaleFilters should contain only grayscaleFilter
    config.rgbImageFilters should contain only rgbFilter
  }

  it should "throw an exception when provided with two image sources" in {

    val source1 = mock[ImageSource]
    val source2 = mock[ImageSource]

    an[IllegalArgumentException] should be thrownBy {
      new AsciiArtConfig.Builder()
        .withImageSource(source1)
        .withImageSource(source2)
        .build()
    }
  }

}
