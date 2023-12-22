package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import org.scalatest.{FlatSpec, Matchers}
import Conversion.BufferedToRgbConverter
import Image.RGBImage
import org.mockito.MockitoSugar.{mock, verify, when}

import java.awt.image.BufferedImage
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

class RandomImageLoadingStrategyTest extends FlatSpec with Matchers{

  "RandomImageLoadingStrategy" should "load an image and convert it" in {
    val mockBufferedImage = mock[BufferedImage]
    val mockRGBImage = mock[RGBImage]
    val mockConverter: BufferedImage => RGBImage = mock[BufferedImage => RGBImage]
    when(mockConverter.apply(mockBufferedImage)).thenReturn(mockRGBImage)


    // Subclass to override readImage
    val strategy = new RandomImageLoadingStrategy(mockConverter) {
      override protected def readImage(inputStream: InputStream): BufferedImage = mockBufferedImage
    }

    val resultImage = strategy.load()

    resultImage shouldBe mockRGBImage
    verify(mockConverter).apply(mockBufferedImage)
  }
}
