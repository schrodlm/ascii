package Filter

import Image.AsciiArt

trait AsciiArtFilter extends Filter[AsciiArt]{
  def apply(image: AsciiArt) : AsciiArt
}
