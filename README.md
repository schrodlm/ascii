# ASCII Art Generator

## Overview
This ASCII Art Generator is a Scala application designed to convert images into ASCII art representations. It supports various image processing steps including RGB to grayscale conversion, application of filters, and ASCII character mapping based on image luminance.

## Features
- **Image Conversion**: Converts RGB images to grayscale.
- **Filter Application**: Supports applying a series of filters to images, including brightness adjustment and scaling.
- **ASCII Conversion**: Transforms grayscale images into ASCII art using a customizable character set.
- **Flexible Input/Output**: Accepts images from different sources and outputs ASCII art to various destinations (console, file).

## Getting Started

### Prerequisites
- Scala [version]
- sbt [version]

### Installation
Clone the repository and navigate to the project directory:
```bash
git clone [repository URL]
cd ascii-art-generator
```

### Usage

Run the application using sbt:

```
sbt run [command line arguments]

```

### Command Line Arguments

    --image [path]: Specify the path of the image file to use as the source.
    --image-random: Use a random image as the source.
    --output-console: Output the ASCII art to the console.
    --output-file [path]: Output the ASCII art to a file at the specified path.
    Additional arguments for filters and ASCII conversion settings.


## Testing

The project includes unit tests for individual components and integration tests for the overall processing flow.
Running Tests

Execute tests using sbt:

```
sbt test
```
