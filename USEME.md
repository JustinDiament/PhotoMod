# Image Processing

CS3500 Object-Oriented Design. _Northeastern University Summer 1 2021_

## Running the Application

The _res/_ directory will be submitted as a zip file _res.zip_ within the root _/_ directory. In
order for the JAR file or any of the test classes within the _test/_ directory to run
successfully, **THE CONTENTS OF _res.zip_ MUST BE EXTRACTED AND PLACED IN A _res/_ DIRECTORY LOCATED
IN THE ROOT _/_ DIRECTORY.** The _res/_ directory was compressed to a zip file in order to
accommodate the size restrictions of the Handins server.

A JAR file, ImageProcessing.jar, can be found in the _res/_ directory. A user may run this file by
moving it from the _res/_ directory to the root _/_ directory, then navigating to this directory
within a command terminal and entering `java -jar ImageProcessing.jar`, then following the printed
instructions to either run one of the example script files, _res/ScriptOne.txt_ and
_res/ScriptTwo.txt_, or enter commands via the command line. **ATTEMPTING TO RUN EITHER OF THE
EXAMPLE SCRIPT FILES WILL FAIL IF THE JAR FILE IS NOT IN THE ROOT DIRECTORY.**

## Script Commands

The following batch script commands are supported by this image processing application.

Example Format: **command name** _[additional arguments]_: description, conditions

1. **blur**: blurs the current layer of the image  
   _Example: blur_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
2. **sharpen**: sharpens the current layer of the image  
   _Example: sharpen_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
3. **sepia**: applies the sepia effect on the current layer of the image  
   _Example: sepia_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
4. **monochrome**: applies the monochrome effect on the current layer of the image  
   _Example: monochrome_
    1. A layer must already be created and selected as the current layer
    2. The current layer must have an image imported
5. **current** _[layer_name]_: changes the current layer to the layer with the given name  
   _Example: current layerOne_
    1. A layer must already be created that has the given name
6. **createlayer** _[layer_name]_: adds a new layer to the top of the layers with the given name  
   _Example: createlayer layerOne_
    1. A layer must not already exist with the same name
    2. Newly created layers have a null image and are visible by default
7. **removelayer** _[layer_name]_: removes the layer with the given name from the image  
   _Example: removelayer layerOne_
    1. A layer must already be created that has the given name
8. **visibility** _[visibility]_: sets the visibility of the current layer  
   _Example: visibility invisible_
    1. A layer must already be created and selected as the current layer
    2. _visibility_ must be either "visible" or "invisible"
9. **createcheckerboard** _[size num_squares color_1 color_2]_: sets the image of the current layer
   to a programmatically generated checkerboard  
   _Example: createcheckerboard 16 4 black white_
    1. _size_ must be a positive integer
    2. _num_squares_ must be a positive integer
    3. _color_1_ must be a valid color supported by Java's Color library, as a lowercase single word
    4. _color_2_ must be a valid color supported by Java's Color library, as a lowercase single word
    5. A layer must already be created and selected as the current layer
10. **import** _[filepath extension]_: imports the image file at the given file path to the current
    layer  
    _Example: import res//test//layer//test.jpg jpg_  
    _Example: import res//test//layer//test.txt txt_  
    1. _filepath_ must be a valid absolute or relative file path
    2. _filepath_ must point to a file of a supported image file type or text file
        1. If _filepath_ points to a text file, the text file must be in the correct format for a
           multi-layered image of _[image_file_path image_file_extension]_ and all the file paths
           and extensions must be valid. Importing a multi-layered image will automatically create
           new layers at the top of the image in the order listed in the text file and set the
           current layer to the topmost layer
        2. If _filepath_ points to a supported image file, a layer must already be created and
           selected as the current layer
    3. _extension_ must be a supported image file type, as a lowercase single word
11. **exportall** _[filepath extension]_: exports all layers of a multi-layer image to a new
    directory containing all layers as image files of the given file type, and a text file in the
    correct format for a multi-layered image of _[image_file_path image_file_extension]_ for each
    layer in the image  
    _Example: exportall res//test//layer//test png_
    1. _filepath_ should not be given an extension, as it represents the name of the directory to
       which the multi-layered image will be saved.
    2. _extension_ must be a supported image file type, as a lowercase single word
12. **export** _[filepath extension]_: exports the topmost visible layer to an image file with the
    given filepath and extension  
    _Example: export res//test//layer//test.ppm ppm_
    1. _filepath_ should point to a supported image file
    2. _extension_ must be a supported image file type, as a lowercase single word
    3. A visible layer must exist within the image
13. **quit**: immediately stops running the application when entered  
    _Example: quit_
    
## Supported Colors

The following colors are supported by the program to be used to create images programmatically.

- blue
- black
- white
- green
- red
- orange
- yellow
- cyan
- magenta
- gray
