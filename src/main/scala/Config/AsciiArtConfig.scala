package Config

import Filter.{Filter, IdentityFilter}

import java.nio.file.Path

class AsciiArtConfig(val image_source: ImageSource,
                     val image_output: ImageOutput,
                     val table: Table,
                     val filters: Array[Filter]
                    )
{
}

