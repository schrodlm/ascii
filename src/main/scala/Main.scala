package Main

import Image.ImageLoader


object Main extends App {

  if (args.length == 0) {
    println("Provide image path.")
    throw new RuntimeException("Provide image path.")
  }

  val imgLoader: ImageLoader = new ImageLoader;
  imgLoader.load("/home/schrodlm/dev/img_for_scala/luffy.jpg");

  val test: Int = 19;
}