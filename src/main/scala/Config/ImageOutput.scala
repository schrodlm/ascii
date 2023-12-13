package Config

sealed trait ImageOutput
case class PathImageOutput(path: String) extends ImageOutput
case class ConsoleImageOutput() extends ImageOutput
