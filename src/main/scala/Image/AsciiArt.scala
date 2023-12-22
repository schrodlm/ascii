package Image

/**
 * Represents ASCII art as an image.
 *
 * Extends `BaseImage` to handle ASCII art, where each pixel value corresponds to an ASCII character.
 *
 * @param width Width of the ASCII art in characters.
 * @param height Height of the ASCII art in characters.
 * @param data Array containing ASCII values representing the art.
 */
class AsciiArt(width: Int, height: Int, data: Array[Int]) extends BaseImage(width,height,data){
}
