package Image.ImageLoaderStrategy

import Image.RGBImage
import org.mockito.MockitoSugar.{mock, verify, when}
import org.scalatest.{FlatSpec, Matchers}

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageIOLoadingStrategyTest extends FlatSpec with Matchers {
  "ImageIOLoadingStrategy" should "correctly load an image and apply the converter" in {
    val path = "test.jpg"
    val mockBufferedImage = mock[BufferedImage]
    val mockRGBImage = mock[RGBImage]

    // Mock converter function
    val mockConverter: BufferedImage => RGBImage = mock[BufferedImage => RGBImage]
    when(mockConverter.apply(mockBufferedImage)).thenReturn(mockRGBImage)

    val strategy = new ImageIOLoadingStrategy(path, mockConverter) {
      override protected def readImage(path: String): BufferedImage = mockBufferedImage
    }

    val resultImage = strategy.load()

    resultImage shouldBe mockRGBImage
    verify(mockConverter).apply(mockBufferedImage)
  }
}
