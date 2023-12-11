package Config

import Filter.Filter

import java.nio.file.Path

class AsciiArtConfig(
                           val iimage_path: Path,
                           val oimage_path: Path,
                           val filters: Array[Filter],
                           val table:
                         )
{
  var input_image_path: Path
  var output_image_path: Path

}
