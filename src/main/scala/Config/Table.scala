package Config

package Table

/**
 * Represents a character table used in ASCII art generation.
 *
 * This class holds an array of characters that are used to convert image pixels
 * into ASCII characters based on their intensity.
 *
 * @param chars An array of characters used for ASCII art conversion.
 */
class Table(val chars: Array[Char])


/**
 * A default character table for ASCII art generation.
 *
 * This table uses a predefined set of characters for a general-purpose ASCII art style.
 */
class DefaultTable() extends Table(Array('#', '*', 'a'))

/**
 * A mathematical character table for ASCII art generation.
 *
 * This table uses mathematical symbols.
 */
class MathematicalTable() extends Table(Array('+', '*', '-', '/'))

/**
 * A custom character table for ASCII art generation.
 *
 * This table allows for user-defined characters, providing flexibility in the style of the generated ASCII art.
 *
 * @param chars An array of custom characters provided by the user.
 */
class CustomTable(chars: Array[Char]) extends Table(chars)