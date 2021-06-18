import controller.ImageControllerImpl;
import java.io.StringReader;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

public class ImageControllerImplTest {

  private ImageLayerModel model;
  private ImageLayerModel mockModel;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    //this.mockModel =
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorOneModelCannotBeNull() {
    new ImageControllerImpl(null, new StringReader(""), new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoModelCannotBeNull() {
    new ImageControllerImpl(null,
        "/Users/Justin/Documents/GitHub/image-processing/res/test/scripts/ScriptOne.txt",
        new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorThreeModelCannotBeNull() {
    new ImageControllerImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorOneReadableCannotBeNull() {
    new ImageControllerImpl(this.model, (Readable) null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoFileNameCannotBeNull() {
    new ImageControllerImpl(this.model, (String) null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoAppendableCannotBeNull() {
    new ImageControllerImpl(this.model,
        "/Users/Justin/Documents/GitHub/image-processing/res/test/scripts/ScriptOne.txt", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoFileNameNotFound() {
    new ImageControllerImpl(this.model, "filethatdoesnotexist.txt", new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void runTest() {
    new ImageControllerImpl(this.model, "filethatdoesnotexist.txt", new StringBuilder());
  }
}
