package Config

import Filter.{Filter, IdentityFilter}

import java.nio.file.Path

sealed trait ImageSource
case class ImagePath(path: String) extends ImageSource
case class RandomImageGeneration() extends ImageSource


sealed trait ImageOutput
case class OutputPath(path: String) extends ImageOutput
case class ConsoleOutput() extends ImageOutput


case class Table(val name: String = "default", val chars: Array[Char] = Array('*', '#'))

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
  private var table: Table = Table()
  private var filters: Array[Filter] = Array(new IdentityFilter())


  def withImageSource(source: ImageSource) : AsciiArtConfigBuilder = {
    imageSource = Some(source)
    this
  }

  def withImageOutput(output: ImageOutput) : AsciiArtConfigBuilder = {
    imageOutput = Some(output)
    this
  }

  def withTable(_table: Table) : AsciiArtConfigBuilder = {
    table = _table
    this
  }

  def withFilters(_filters: Array[Filter]) : AsciiArtConfigBuilder = {
    filters = filters ++ _filters
    this
  }
  def addFilter(_filter: Filter ) : AsciiArtConfigBuilder = {
    filters = filters ++ _filter
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