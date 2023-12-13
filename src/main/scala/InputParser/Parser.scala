package InputParser

import Config.AsciiArtConfig

trait Parser {
  def parse() : Option[AsciiArtConfig]
}
