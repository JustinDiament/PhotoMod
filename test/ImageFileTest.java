import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import model.image.file.ImageFile;
import org.junit.Test;

/**
 * Test class for the ImageFile interface.
 */
public class ImageFileTest {

  // test that a null file cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testReadNullFile() {
    ImageFile.readFile(null);
  }

  // test that readFile returns null if the file cannot be found
  @Test
  public void testReadFakeFile() {
    assertNull(ImageFile.readFile(""));
  }

  // test that a valid file can be correctly read
  // correctly read files have a line separator between lines and ignore any lines beginning with #
  @Test
  public void testReadCorrectFile() {
    assertEquals("test\r\n123\r\n", ImageFile.readFile("res\\test\\test.txt"));
  }
}
