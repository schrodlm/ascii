package Config

import Config.Tables.{CustomTable, DefaultTable, PaulBorkesTable, Table, TableNameMapper}
import Filter.{AsciiArtFilter, Filter, GrayscaleImageFilter, IdentityFilter, RGBImageFilter}
import Image.Image

/**
 * Configuration class for ASCII art generation.
 *
 * This class encapsulates all the necessary settings and components required
 * to generate ASCII art, including the source of the image, the output destination,
 * the character table to use, and any image filters.
 *
 * @param imageSource The source of the image to be converted into ASCII art.
 * @param imageOutput The destination for the generated ASCII art output.
 * @param table The character table used for mapping pixel values to characters.
 * @param filters An array of filters to apply to the image before ASCII conversion.
 */
class AsciiArtConfig private
                    (val imageSource: ImageSource,
                     val imageOutput: ImageOutput,
                     val table: Table,
                     val asciiFilters: Array[AsciiArtFilter],
                     val grayscaleFilters : Array[GrayscaleImageFilter],
                     val rgbImageFilters: Array[RGBImageFilter]
                    )

object AsciiArtConfig{
  /**
   * Builder for creating an AsciiArtConfig instance.
   *
   * This class provides a fluent interface for configuring ASCII art generation.
   */
  class Builder {
    private var imageSource: Option[ImageSource] = None
    private var imageOutput: Option[ImageOutput] = None
    private var table: Table = new DefaultTable()
    private var filters: Array[Filter[_ <: Image]] = Array()

    private var imageProvided: Boolean = false
    // ========================= Builder setter methods =============================

    /**
     * Sets the image source
     *
     * @param source the image source
     * @return the updated builder instance
     */
    def withImageSource(source: ImageSource): Builder = {
      if (imageProvided.equals(true)) {
        throw new IllegalArgumentException("Provided more than one image argument")
      }
      imageProvided = true

      this.imageSource = Some(source)
      this
    }

    /**
     * Sets the image output
     *
     * @param output the image output
     * @return the updated builder instance
     */
    def withImageOutput(output: ImageOutput): Builder = {
      this.imageOutput = Some(output)
      this
    }

    /**
     * Sets the mapping table based on a name (for predefined tables)
     *
     * @param name the predefined table identification
     * @return the updated builder instance
     */
    def withTable(name: String): Builder = {
      this.table = TableNameMapper(name)
      this
    }

    /**
     * Sets the custom mapping table based on provided character array
     *
     * @param chars provided custom array
     * @return the updated builder instance
     */
    def withTable(chars: Array[Char]): Builder = {
      val custom_table: CustomTable = new CustomTable(chars)
      this.table = custom_table
      this
    }

    /**
     * Sets filter array for an image
     *
     * @param filters provided array of filters
     * @return the updated builder instance
     */
    def withFilters(filters: Array[Filter[_ <: Image]]): Builder = {
      this.filters = this.filters ++ filters
      this
    }


    /**
     * Adds single filter
     *
     * @param filter provided filter
     * @return the updated builder instance
     */
    def addFilter(filter: Filter[_ <: Image]): Builder = {
      this.filters = this.filters :+ filter
      this
    }

    // ========================= Build methods =============================

    /**
     * Constructs the AsciiArtConfig with the current builder settings.
     *
     * @return the configured AsciiArtConfig instance
     * @throws IllegalStateException if required fields are not set
     */
    def build(): AsciiArtConfig = {
      // ensure all required fields are correctly set
      if (imageSource.isEmpty || imageOutput.isEmpty) {
        throw new IllegalStateException("Missing required fields")
      }

      val asciiArtFilters = filters.collect { case f: AsciiArtFilter => f }
      val grayscaleFilters = filters.collect { case f: GrayscaleImageFilter => f }
      val rgbImageFilters = filters.collect { case f: RGBImageFilter => f }

      new AsciiArtConfig(imageSource.get, imageOutput.get, table, asciiArtFilters, grayscaleFilters, rgbImageFilters)
    }
  }
}