package Config

sealed trait ImageSource
case class PathImageSource(path: String) extends ImageSource
case class RandomImageSource() extends ImageSource