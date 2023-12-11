package Main

import Image.{Image, ImageFactory}


object Main extends App {

  if (args.length == 0) {
    println("Provide image path.")
    throw new RuntimeException("Provide image path.")
  }
  val image : Image = ImageFactory.createImage("D:\\dev\\university\\images_for_asciiart\\image_2.jpg")
  println(s"${image.getWidth()} x ${image.getHeight()}");

}