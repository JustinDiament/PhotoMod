# Image Processing

CS3500 Object-Oriented Design. _Northeastern University Summer 1 2021_

|Original|Blur|Sharpen|Sepia|Monochrome|Mosaic|Downscale|
|--------|----|-------|-----|----------|------|---------| 
|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121575831-cdb08400-c9f5-11eb-8177-933bc858cfa4.jpg" width="70" height="50"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121575993-f8024180-c9f5-11eb-8090-98be3ac64ca8.png" width="70" height="50"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121576177-23852c00-c9f6-11eb-8198-4485f348e31e.png" width="70" height="50"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121576302-3b5cb000-c9f6-11eb-87d5-f05fc9e94f77.png" width="70" height="50"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121576328-43b4eb00-c9f6-11eb-9469-d4ad8760caef.png" width="70" height="50"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/122719882-c9c60280-d23c-11eb-896b-c3f0d3dbd7a5.png" width="70" height="50"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/122721521-c5024e00-d23e-11eb-8bf3-d85ddfaa30d9.png" width="35" height="25"></p>|
|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121576880-dfdef200-c9f6-11eb-8c39-ce98cb86c958.jpg" width="50" height="60"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121576704-a3ab9180-c9f6-11eb-86b3-6d23cee90119.png" width="50" height="60"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121576963-fd13c080-c9f6-11eb-9887-a6d59cad566b.png" width="50" height="60"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121577018-0d2ba000-c9f7-11eb-8bde-8f14ed4bbc78.png" width="50" height="60"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/121577055-1c125280-c9f7-11eb-8350-da05c75aa907.png" width="50" height="60"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/122720377-67b9cd00-d23d-11eb-95c4-7d4a62f0846c.jpg" width="50" height="60"></p>|<p align="center"><img src="https://user-images.githubusercontent.com/52764831/122720043-f7ab4700-d23c-11eb-80dc-1ab89bf65c24.jpg" width="25" height="30"></p>|

### Design Changes & Justifications

1. Our model implementation in HW5 containing public methods that called other classes that were
   responsible for importing and exporting a specific file type. However, the methods in the model
   still took in a file path from the user, so they contained some I/O. However, I/O is the
   responsibility of the controller, not the model. Thus, we elected to remove these methods from
   the ImageProcessingModel interface and move them to the classes implementing the Command
   interface that are used in our controller implementation. As such, both our old
   ImageProcessingModelImpl and our new ImageLayerModelImpl model classes are completely free of any
   file handling, as it is all contained within the controller. Additionally, exporting is supported
   for both single image files and multi-layered images as directories containing image files for
   each layer in the multi-layered image, as well as a text file with the file paths and file types
   of all the image files. For the multi-layered images, users are able to export the topmost
   visible layer.
2. In our old design, we had an ImageFile interface that contained methods for importing and
   exporting image file types, as well as a PPM class that implemented it that was responsible for
   handling PPM file types. However, with the introduction of supporting JPEG and PNG file formats
   in HW6, we created two new classes to handle this functionality. It turned out that all three
   classes shared similar functionality in terms of the implementation of the import and export
   methods, so this common functionality was abstracted out into an abstract ImageFileFormat class.
   As such, there were no public-facing effects, but code duplication was eliminated. Since all the
   file types share the same signatures for their import and export capabilities, all file types are
   able to be easily converted between one another.
3. In our old design, the supported image processing operations were categorized as either filtering
   operations or color transformation operations. While we included a FilterOperation abstract class
   to factor out similar code between the two filtering operation classes - BlurOperation and
   SharpenOperation, we did not have a similar abstraction for the color transformation operation
   classes - MonochromeOperation and SepiaOperation. As such, we introduced a second abstract class
   implementing the ImageOperation class that factors out the similar functionality between the two
   color transformation classes.
4. Our model implementation in HW5 did not store any objects of our Image representation, but simply
   had methods to create, process, and return Images. With the introduction of the concept of
   layering in HW6, we decided that we needed to be able to store multiple layers of an image within
   the model. We introduced a new Layer interface that represents a single layer of a multi-layered
   image, and we created a new model interface, ImageLayerModel, that extends our old model
   implementation so that we could make use of the code that was already written. We then created a
   new model class ImageLayerModelImpl that extended our old model implementation
   ImageProcessingModelImpl and implemented the new interface, which stores a list of layers as a
   private field of the class. This new model implementation also offers many more public observer
   and setter methods that allow the client to interact with individual layers in the model by
   manipulating and changing the currently selected layer, which allow users to be able to process
   images without having to know our actual representation of images within the Image interface.
5. HW6 requires supporting image manipulation via batch commands, which in turn requires the ability
   to read input from and display output to the user. As such, we introduced a controller interface,
   ImageController, to handle user I/O. The controller reads in specific command keywords from
   either the console, or a prepared script, then executes a particular implementation of the
   Command interface, via the command pattern, based on the user input, along with any additional
   required arguments needed to interact with the model. We also introduced a view interface,
   ImageTextView that allows the controller to render messages to the user. This particular view
   interface is an interactive text view that displays messages to the user when a command is
   entered incorrectly or is not able to execute to completion.

### Design

All parts of the program are complete in terms of both implementation and testing. This includes all
the interfaces and classes listed below. Refer to the _USEME.md_ file for instructions on how to use
this program.

#### Features: An interface that represents event listeners for components within the GUI view

- FeaturesImpl: A class that delegates operations to the controller that modify the model based on
  user input from the view

#### ImageView: An interface that represents a GUI view of an image processing model

- ImageViewImpl: A class that displays a GUI for the user to perform image processing operations and
  view images via menus and panels, and displays messages to the user via popups

#### Command: An interface that represents function objects for image processing operations

- OperationCommand: An abstract class that promotes reuse between image manipulation operations
    - BlurCommand: A function object that blurs the current image layer
    - MonochromeCommand: A function object that applies the monochrome transformation on the current
      image layer
    - SepiaCommand: A function object that applies the sepia transformation on the current image
      layer
    - SharpenCommand: A function object that sharpens the current image layer
- ChangeCurrentLayerCommand: A function object that changes the currently selected image layer
- CreateCheckerboardCommand: A function object that generates a checkerboard image programmatically
- CreateLayerCommand: A function object that adds a new visible layer to the top of the image
- ExportAllCommand: A function object that exports all layers of an image as image files
- ExportCommand: A function object that exports the topmost visible layer of an image
- ImportCommand: A function object that imports either a single image file, or a multi-layered image
  from a valid text file
- RemoveLayerCommand: A function object that removes the selected layer from the image
- VisibilityCommand: A function object that sets the visibility of the currently selected layer
- DownscaleCommand: A function object that downscales an image by the given x and y scaling factors
- MosaicCommand: A function object that mosaics an image by the given number of seeds

#### ImageController: An interface that handles user input and output for processing images

- ImageControllerImpl: A class that allows users to input text commands to process images using the
  supported operations and renders messages

#### ImageInteractiveController: An interface that handles communication between user input via a GUI and outputs of data from the model

- ImageInteractiveControllerImpl: A class that executes commands based on listeners from a GUI view
  and retrieves necessary data from the model to render to the view

#### ImageFile: An interface that represents an image file and handles file importing and exporting

- ImageFileFormat: An abstract class that promotes code reuse when importing and exporting different
  image file formats
    - JPEG: A class that represents the JPG/JPEG image file format and imports and exports JPG/JPEG
      files
    - PNG: A class that represents the PNG image file format and imports and exports PNG files
    - PPM: A class that represents the PPM image file format and imports and exports PPM files

#### Layer: An interface that represents a single layer of a multi-layered image

- LayerImpl: A class that represents a layer as an image with a name and visibility

#### ImageLayerModel: An interface that supports image processing and stores images with multiple layers

- ImageLayerModelImpl: An class that provides image processing operations that can be applied to
  individual layers of an image, stored as a list

#### ImageTextView: An interface that represents a text view of an image processing model

- ImageTextViewImpl: A class that renders text-based messages to the user via the provided
  Appendable

#### ImageOperation: An interface that processes the given image with a particular operation

- FilterOperation: An abstract class that promotes code reuse between filtering operations and
  applies the specified kernel to filter an image
    - BlurOperation: A class that specifies the blur kernel to be used for filtering
    - SharpenOperation: A class that specifies the sharpen kernel to be used for filtering
- ColorOperation: An abstract class that promotes code reuse between color transformation operations
    - MonochromeOperation: A class that applies the sepia color transformation on an image
    - SepiaOperation: A class that applies the monochrome/greyscale color transformation on an image
- DownscaleOperation: A class that downscales an image by and x and y scale
- MosaicOperation: A class that creates a mosaic effect using a given number of seeds randomly
  placed within the image

#### ProgrammaticCreator: An interface that supports creating images programmatically

- CreateCheckerboard: A class that creates a checkerboard image with configurable values

#### Image: An interface that represents image data containing pixels and with defined dimensions

- ImageImpl: A class that represents an image as a 2D-List of pixels

#### ImageProcessingModel: An model interface that supports file handling and image processing

- ImageProcessingModelImpl: A class that supports image file handling, applies supported operations,
  and creates programmatic images

#### Pixel: An interface that represents a pixel of an image with red, green, and blue values

- PixelImpl: A class representing a pixel that clamps its RGB values between 0 and 255, inclusive

##### Operations: An enumeration of the supported image processing operations

##### ImageUtil: A class that contains utility helper methods to be used throughout the project

##### Main: A class that contains a main method to run the application

### Additional Image Operations

Our application supports two additional features, the downscale and mosaic image operations. To
implement these two new features, two classes were written, DownscaleOperation and MosaicOperation,
that implemented the existing ImageOperation interface. Both classes overrode the apply method,
which takes in an Image object and returns a copy of the Image with the desired operation applied.
Following the command pattern, two classes were written, DownscaleCommand and MosaicCommand, that
implemented the existing Command interface. Both classes overrode the execute method, which
performed the desired operation on the given model using the provided parameter values. This allowed
both operations to be supported through both the new GUI view through menu items and buttons, and
the existing text view through batch commands. In the controller implementation that uses the text
view, commands are stored within a hashmap with their corresponding batch commands. In the
controller implementation that uses the GUI view, commands are executed via the executeCommand
method that executes the given Command with the given input parameters.

### Additional Deliverables

The _res/_ directory contains the following additional deliverables:

- A JAR file, _ImageProcessing.jar_
- A screenshot of the program with an image loaded, _gui.jpg_
- An example script file that demonstrates all the working features of the application, _Script.txt_
- A subdirectory, _downscale/_, that contains an original image, _quetzal_original.jpg_, and two
  downsized versions of that image, _quetzal_half_x_half_y.jpg_ and _quetzal_half_x_quarter_y.jpg_,
  the latter of which has a different width:height ratio than the original image.
- A subdirectory, _mosaic/_, that contains an original image, _popeyes_original.png_, and three
  mosaicked versions of that image, _popeyes_1000_seeds.png_, _popeyes_4000_seeds.png_, and _
  popeyes_8000_seeds.png_, which all have a different number of seeds used.

### Citations

All images are owned by Nathan Gong and authorized for use in this project. The _res/test/_
subdirectory contains sample files that are used for code testing purposes only.
