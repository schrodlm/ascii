package Image.ImageLoaderStrategy

import Conversion.BufferedToRgbConverter
import Image.RGBImage

import java.awt.image.BufferedImage
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

/**
 * Strategy for loading a random image from the internet.
 *
 * Implements `ImageLoadingStrategy` to fetch a random image from a specified URL.
 * Converts the fetched image to an `RGBImage` using the provided converter.
 *
 * @param imageConverter A function to convert `BufferedImage` to `RGBImage`.
 */
class RandomImageLoadingStrategy(imageConverter: BufferedImage => RGBImage) extends ImageLoadingStrategy {

  // URL to fetch a random image.
  private val imageUrl = "https://source.unsplash.com/random"

  /**
   * Loads an image from the internet and converts it to `RGBImage`.
   *
   * Fetches a random image from the specified URL, reads it into a `BufferedImage`,
   * and then converts it to `RGBImage` using the provided converter.
   *
   * @return The loaded and converted `RGBImage`.
   */
  override def load(): RGBImage = {

    // Establish a connection to the image URL.
    val connection = new URL(imageUrl).openConnection()
    connection.setRequestProperty("User-Agent", "Mozilla/5.0")

    // Open an input stream to read the image data.
    val inputStream = connection.getInputStream
    try {
      // Read and convert the image.
      val image = readImage(inputStream)
      imageConverter(image)
    } finally {
      // Ensure the input stream is closed after use.
      inputStream.close()
    }
  }

  /**
   * Reads an image from an input stream.
   *
   * @param inputStream The input stream containing image data.
   * @return The read `BufferedImage`.
   */
  protected def readImage(inputStream: InputStream): BufferedImage = {
    ImageIO.read(inputStream)
  }
}
