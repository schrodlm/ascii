package Config

import Filter.{Filter, IdentityFilter}

import java.nio.file.Path

sealed trait ImageSource
case class PathImageSource(path: String) extends ImageSource
case class RandomImageSource() extends ImageSource


sealed trait ImageOutput
case class PathImageOutput(path: String) extends ImageOutput
case class ConsoleImageOutput() extends ImageOutput


class AsciiArtConfig(val image_source: ImageSource,
                     val image_output: ImageOutput,
                     val table: Table,
                     val filters: Array[Filter]
                    )
{
}

class AsciiArtConfigBuilder {
  private var imageSource: Option[ImageSource] = None
  private var imageOutput: Option[ImageOutput] = None
  private var table: Table = new DefaultTable()
  private var filters: Array[Filter] = Array(new IdentityFilter())


  def withImageSource(source: ImageSource) : AsciiArtConfigBuilder = {
    this.imageSource = Some(source)
    this
  }

  def withImageOutput(output: ImageOutput) : AsciiArtConfigBuilder = {
    this.imageOutput = Some(output)
    this
  }

  def withTable(name: String) : AsciiArtConfigBuilder = {
    name.toLowerCase match {
      case "default" => this.table = new DefaultTable
      case "mathematical" => this.table = new MathematicalTable()
      case _ => throw new IllegalArgumentException("Unknown table type")
    }
    this
  }

  def withTable(chars: Array[Char]) : AsciiArtConfigBuilder = {
    val custom_table : CustomTable = new CustomTable(chars)
    this.table = custom_table
    this
  }

  def withFilters(_filters: Array[Filter]) : AsciiArtConfigBuilder = {
    this.filters = this.filters ++ _filters
    this
  }
  def addFilter(_filter: Filter ) : AsciiArtConfigBuilder = {
    this.filters = this.filters ++ _filter
    this
  }


  def build(): AsciiArtConfig = {
    // ensure all required fields are correctly set
    if(imageSource.isEmpty || imageOutput.isEmpty){
      throw new IllegalStateException("Missing required fields")
    }

    new AsciiArtConfig(imageSource.get, imageOutput.get, table, filters)
  }
}