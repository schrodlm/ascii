package Image

import Config.{PathImageSource, RandomImageSource}
import Image.ImageLoaderStrategy.JPEGImageLoadingStrategy
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.{FlatSpec, Matchers}

class RGBImageFactoryTest extends FlatSpec with Matchers {

  "RGBImageFactory" should "create an RGBImage from a PathImageSource with a JPG file" in {
    val path = "test.jpg"
    val imageSource = PathImageSource(path)
    val mockImage = mock[RGBImage]

    // Mock the JPEGImageLoadingStrategy and its load method
    val mockStrategy = mock[JPEGImageLoadingStrategy]
    when(mockStrategy.load()).thenReturn(mockImage)

    // Inject the mock strategy into the factory (may require modifying RGBImageFactory to allow this)
    // ...
    val createdImage = RGBImageFactory.createRGBImage(imageSource)
    createdImage shouldBe mockImage
  }

  it should "create an RGBImage from a PathImageSource with a PNG file" in {
    val path = "test.png"
    val imageSource = PathImageSource(path)
    val mockImage = mock[RGBImage]

    // Similar mocking for PNGImageLoadingStrategy
    // ...

    val createdImage = RGBImageFactory.createRGBImage(imageSource)
    createdImage shouldBe mockImage
  }

  it should "create an RGBImage from a RandomImageSource" in {
    val imageSource = RandomImageSource()
    val mockImage = mock[RGBImage]

    // Mock the RandomImageLoadingStrategy
    // ...

    val createdImage = RGBImageFactory.createRGBImage(imageSource)
    createdImage shouldBe mockImage
  }

  it should "throw IllegalArgumentException for unsupported file formats" in {
    val path = "unsupported_format.xyz"
    val imageSource = PathImageSource(path)

    an[IllegalArgumentException] should be thrownBy RGBImageFactory.createRGBImage(imageSource)
  }

}
