package Main

import Image.{Image, ImageFactory}
import InputParser.CommandLineParser


object Main extends App {

  //Parse Input
  val parser: CommandLineParser = new CommandLineParser(args);
  parser.parse()

  val image : Image = ImageFactory.createImage("D:\\dev\\university\\images_for_asciiart\\image_2.jpg")
  println(s"${image.getWidth()} x ${image.getHeight()}");

}