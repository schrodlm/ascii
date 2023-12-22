package Processing


import Config.Tables.Table
import Config.{AsciiArtConfig, ImageSource}
import Conversion.{GrayscaleToAsciiConverter, RgbToGrayscaleConverter, SourceToRgbImage}
import Filter.Filter
import Image.{AsciiArt, GrayscaleImage, Image, RGBImage}

/**
 * A facade class for converting images to ASCII art.
 *
 * This class provides a simplified interface for the process of converting
 * an image to ASCII art, abstracting the underlying steps such as loading
 * the image, applying filters, and converting to grayscale and ASCII characters.
 *
 * TODO: Refactor this class for it to be testable (no time)
 *
 * @param config The configuration settings for ASCII art generation.
 */
class AsciiArtFacade(config: AsciiArtConfig) {

  /**
   * Processes the image source specified in the configuration and converts it to ASCII art.
   *
   * This method orchestrates the entire process of ASCII art generation. It involves
   * loading the RGB image, applying RGB and grayscale filters, converting the image
   * to grayscale, and finally transforming it into ASCII art.
   *
   * @return The generated ASCII art as an instance of AsciiArt.
   */
  def process(): AsciiArt = {

    // Load the RGB image from the specified source.
    val rgbImage = loadRgbImage(config.imageSource)

    // Apply the configured RGB filters to the image.
    val filteredRgbImage = applyFilters(rgbImage, config.rgbImageFilters)

    // Convert the filtered RGB image to grayscale.
    val grayscaleImage = convertToGrayscale(filteredRgbImage)

    // Apply the configured grayscale filters to the image.
    val filteredGrayscaleImage = applyFilters(grayscaleImage, config.grayscaleFilters)

    // Convert the filtered grayscale image to ASCII art.
    val asciiArt = convertToAsciiArt(filteredGrayscaleImage, config.table)

    // Apply the configured ASCII filters to the ASCII art.
    val filteredAsciiArt = applyFilters(asciiArt, config.asciiFilters)

    // Return the final ASCII art.
    filteredAsciiArt
  }

  /**
   * Converts an RGB image to a grayscale image.
   *
   * @param image The RGB image to be converted.
   * @return The converted grayscale image.
   */
  private def convertToGrayscale(image: RGBImage): GrayscaleImage = {
    new RgbToGrayscaleConverter(image).convert()
  }

  /**
   * Converts a grayscale image to ASCII art.
   *
   * @param image The grayscale image to be converted.
   * @param table The character table used for the conversion.
   * @return The generated ASCII art.
   */
  private def convertToAsciiArt(image: GrayscaleImage, table: Table): AsciiArt = {
    new GrayscaleToAsciiConverter(image, config.table).convert()
  }

  /**
   * Loads an RGB image from a given source.
   *
   * @param source The source from which to load the image.
   * @return The loaded RGB image.
   */
  private def loadRgbImage(source: ImageSource): RGBImage = {
    new SourceToRgbImage(config.imageSource).convert()
  }

  /**
   * Applies a series of filters to an image.
   *
   * @param image The image to which the filters will be applied.
   * @param filters An array of filters to apply to the image.
   * @tparam T The type of the image (e.g., RGBImage, GrayscaleImage, AsciiArt).
   * @return The image after all filters have been applied.
   */
  private def applyFilters[T <: Image](image: T, filters: Array[_ <: Filter[T]]): T = {
    filters.foldLeft(image) { (currentImage, filter) =>
      filter.apply(currentImage)
    }
  }
}
