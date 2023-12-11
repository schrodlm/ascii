package Image

object ImageFactory {
  def createImage(input: String): Image = {

    val fileFormat = input.split("\\.").last

    fileFormat match {
      case "jpg" => {
        new JPEGImageLoader(input).load()
      }
      case "png" => throw new NotImplementedError("PNG");
      case _ => throw new NotImplementedError("_");

    }
  }
}
