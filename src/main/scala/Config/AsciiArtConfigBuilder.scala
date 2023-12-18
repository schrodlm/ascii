package Config

import Config.table.{CustomTable, DefaultNonLinearTable, DefaultTable, PaulBorkesTable, Table, TableNameMapper}
import Filter.{Filter, IdentityFilter}

/**
 * Builder for creating an AsciiArtConfig instance.
 *
 * This class provides a fluent interface for configuring ASCII art generation.
 */
class AsciiArtConfigBuilder {
  private var imageSource: Option[ImageSource] = None
  private var imageOutput: Option[ImageOutput] = None
  private var table: Table = new PaulBorkesTable()
  private var filters: Array[Filter] = Array(new IdentityFilter())

  private var imageProvided : Boolean = false
  // ========================= Builder setter methods =============================

  /**
   * Sets the image source
   *
   * @param source the image source
   * @return the updated builder instance
   */
  def withImageSource(source: ImageSource) : AsciiArtConfigBuilder = {
    if(imageProvided.equals(true)) {
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
  def withImageOutput(output: ImageOutput) : AsciiArtConfigBuilder = {
    this.imageOutput = Some(output)
    this
  }

  /**
   * Sets the mapping table based on a name (for predefined tables)
   *
   * @param name the predefined table identification
   * @return the updated builder instance
   */
  def withTable(name: String) : AsciiArtConfigBuilder = {
    this.table = TableNameMapper(name)
    this
  }

  /**
   * Sets the custom mapping table based on provided character array
   *
   * @param chars provided custom array
   * @return the updated builder instance
   */
  def withTable(chars: Array[Char]) : AsciiArtConfigBuilder = {
    val custom_table : CustomTable = new CustomTable(chars)
    this.table = custom_table
    this
  }

  /**
   * Sets filter array for an image
   *
   * @param filters provided array of filters
   * @return the updated builder instance
   */
  def withFilters(filters: Array[Filter]) : AsciiArtConfigBuilder = {
    this.filters = this.filters ++ filters
    this
  }


  /**
   * Adds single filter
   *
   * @param filter provided filter
   * @return the updated builder instance
   */
  def addFilter(filter: Filter ) : AsciiArtConfigBuilder = {
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
    if(imageSource.isEmpty || imageOutput.isEmpty){
      throw new IllegalStateException("Missing required fields")
    }

    new AsciiArtConfig(imageSource.get, imageOutput.get, table, filters)
  }
}