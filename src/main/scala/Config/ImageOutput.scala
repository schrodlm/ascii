package Config

import Image.AsciiArt

import java.io.{BufferedWriter, FileWriter}

/**
 * Represents the output destination for generated ASCII art.
 *
 * This sealed trait is used as a base to define different types of output destinations
 * for ASCII art, such as to a file or to the console.
 */
sealed trait ImageOutput{
  def save(asciiArt : AsciiArt)
}

/**
 * Represents an ASCII art output to a specified file path.
 *
 * @param path the file path where the ASCII art will be saved.
 */
case class PathImageOutput(path: String) extends ImageOutput {
  def save(asciiArt: AsciiArt): Unit = {
    val width = asciiArt.getWidth()
    val height = asciiArt.getHeight()
    val asciiData = asciiArt.getData()

    // Convert ASCII data to a string representation
    val asciiString = (0 until height).map { y =>
      (0 until width).map { x =>
        asciiData(y * width + x).toChar
      }.mkString
    }.mkString("\n")

    // Write the ASCII string to a file
    val file = new BufferedWriter(new FileWriter(path))
    try {
      file.write(asciiString)
    } finally {
      file.close()
    }
  }
}

/**
 * Represents an ASCII art output to the console.
 *
 * This case class is used when the ASCII art is intended to be printed directly
 * to the standard console output.
 */
case class ConsoleImageOutput() extends ImageOutput {

  def save(img: AsciiArt) = ???

}
