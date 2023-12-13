package Config

/**
 * Represents the output destination for generated ASCII art.
 *
 * This sealed trait is used as a base to define different types of output destinations
 * for ASCII art, such as to a file or to the console.
 */
sealed trait ImageOutput

/**
 * Represents an ASCII art output to a specified file path.
 *
 * @param path the file path where the ASCII art will be saved.
 */
case class PathImageOutput(path: String) extends ImageOutput

/**
 * Represents an ASCII art output to the console.
 *
 * This case class is used when the ASCII art is intended to be printed directly
 * to the standard console output.
 */
case class ConsoleImageOutput() extends ImageOutput
