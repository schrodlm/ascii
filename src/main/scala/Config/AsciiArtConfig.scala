package Config

import Config.table.Table
import Filter.Filter

/**
 * Configuration class for ASCII art generation.
 *
 * This class encapsulates all the necessary settings and components required
 * to generate ASCII art, including the source of the image, the output destination,
 * the character table to use, and any image filters.
 *
 * @param image_source The source of the image to be converted into ASCII art.
 * @param image_output The destination for the generated ASCII art output.
 * @param table The character table used for mapping pixel values to characters.
 * @param filters An array of filters to apply to the image before ASCII conversion.
 */
class AsciiArtConfig(val image_source: ImageSource,
                     val image_output: ImageOutput,
                     val table: Table,
                     val filters: Array[Filter]
                    )

