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

