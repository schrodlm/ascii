package Image.ImageLoaderStrategy

import Image.Image

trait LoadingImageStrategy {
  def load(): Image
}
