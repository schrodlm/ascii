package Conversion

import Config.{PathImageSource, RandomImageSource}
import Image.ImageLoaderStrategy.{JPEGImageLoadingStrategy, PNGImageLoadingStrategy, RandomImageLoadingStrategy}
import Image.RGBImage
import org.mockito.MockitoSugar.{mock, verify, when}
import org.scalatest.{FlatSpec, Matchers}

class SourceToRgbImageTest extends FlatSpec with Matchers {

  "SourceToRgbImage" should "use a JPEG loading strategy for JPG files" in {
    val mockStrategy = mock[JPEGImageLoadingStrategy]
    val mockImage = mock[RGBImage]
    when(mockStrategy.load()).thenReturn(mockImage)

    val converter = new SourceToRgbImage(PathImageSource("test.jpg"), Some(mockStrategy))
    val resultImage = converter.convert()

    resultImage shouldBe mockImage
    verify(mockStrategy).load()
  }

  it should "use a PNG loading strategy for PNG files" in {
    val mockStrategy = mock[PNGImageLoadingStrategy]
    val mockImage = mock[RGBImage]
    when(mockStrategy.load()).thenReturn(mockImage)

    val converter = new SourceToRgbImage(PathImageSource("test.png"), Some(mockStrategy))
    val resultImage = converter.convert()

    resultImage shouldBe mockImage
    verify(mockStrategy).load()
  }

  it should "use a random image loading strategy for RandomImageSource" in {
    val mockStrategy = mock[RandomImageLoadingStrategy]
    val mockImage = mock[RGBImage]
    when(mockStrategy.load()).thenReturn(mockImage)

    val converter = new SourceToRgbImage(RandomImageSource(), Some(mockStrategy))
    val resultImage = converter.convert()

    resultImage shouldBe mockImage
    verify(mockStrategy).load()
  }

  it should "throw IllegalArgumentException for unsupported file formats" in {
    val converter = new SourceToRgbImage(PathImageSource("unsupported.xyz"))

    an[IllegalArgumentException] should be thrownBy converter.convert()
  }

}
