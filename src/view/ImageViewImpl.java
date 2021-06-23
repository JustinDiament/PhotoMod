package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageUtil;
import model.image.Image;
import model.image.file.PNG;

// todo: make this implement interface
public class ImageViewImpl extends JFrame {

  private final List<Features> features;

  private final JPanel mainPanel;
  private final JScrollPane mainScrollPane;

  private JPanel imagePanel;
  private JLabel imageLabel;
  private JScrollPane imageScrollPane;

  private final JButton blurButton;
  private final JButton monochromeButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;

  // need associated text box(es)
  private final JButton downscaleButton;
  private final JTextField downscaleXTextField;
  private final JTextField downscaleYTextField;

  private final JButton mosaicButton;
  private final JTextField mosaicSeedTextField;

  private final JButton createCheckerboardButton;
  private final JTextField checkerboardSizeTextField;
  private final JTextField checkerboardSquaresTextField;
  private final JComboBox<String> checkerboardColorOneDropdown;
  private final JComboBox<String> checkerboardColorTwoDropdown;

  // file explorer stuff
  private final JButton importButton;
  private final JButton exportButton;
  private final JButton exportAllButton;
  private final JComboBox<String> exportAllDropDown;

  private final JButton createLayerButton;
  private final JTextField createLayerTextField;

  // these two buttons use the same dropdown
  private final JButton setCurrentLayerButton;
  private final JButton removeLayerButton;
  private final JComboBox<String> layerNamesDropdown;

  private final JCheckBox visibilityCheckBox; // check to toggle invisible
  private final JTextField visibilityTextField;

  private final JPanel buttonPanel;


  private String currentLayerName;
  private String filepath;

  // todo: pull features interface changes, add list of features as field of the class,
  //  initialized as empty list, emit method for every event that calls the corresponding handle
  //  method in the features, method to add new listeners


  public ImageViewImpl() {
    super();

    this.features = new ArrayList<>();

    // todo: clean up constructor to create/add to panels in helper methods
    //  depending on how this is implemented, consider making the components local variables

    setTitle("Image Processor");
    setSize(900, 500);

    setFocusable(true);
    requestFocus();

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1, 2));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imagePanel = new JPanel();
    // todo: currently visible layer name is not exposed by model, maybe leave this titled
    //  border out
//    imagePanel.setBorder(BorderFactory.createTitledBorder("Currently viewing layer:"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    imagePanel.add(imageScrollPane);

    PNG png = new PNG();
//    this.renderImage(png.importFile("res//mosaic//popeyes_original.png"));

//    JPanel imagePanel = new JPanel();
//    imagePanel.setBorder(BorderFactory.createTitledBorder("Currently viewing layer:"));
//    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
//    mainPanel.add(imagePanel);
//
//    JLabel label = new JLabel();
//    JScrollPane scrollPane = new JScrollPane(label);
//    PNG png = new PNG();
//    Image img = png.importFile("res//mosaic//popeyes_original.png");
//    label.setIcon(new ImageIcon(ImageUtil.convertImage(img)));
//    label.setHorizontalAlignment(SwingConstants.CENTER);
//    scrollPane.setPreferredSize(new Dimension(100, 200));
//    imagePanel.add(scrollPane);

    this.buttonPanel = new JPanel();

    this.buttonPanel.setBorder(
        BorderFactory.createTitledBorder("Currently selected layer: None"));
    this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.PAGE_AXIS));

    this.mainPanel.add(this.buttonPanel);

    // todo: add action commands for each button that call features methods when pressed
    // button.addActionListener + anonymous class
//    new ActionListener() {
//
//      @Override
//      public void actionPerformed(ActionEvent e) {
//          for feature in features, feature.method();
//      }
//    }

    // todo: add borderlayout titles to all of these mini panels in the buttonpanel

    JPanel operationPanel = new JPanel();
    this.blurButton = new JButton("Blur");
    this.blurButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleBlurEvent();
      }
    });
    this.monochromeButton = new JButton("Monochrome");
    this.monochromeButton.setPreferredSize(new Dimension(115, 26));
    this.monochromeButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleMonochromeEvent();
      }
    });
    this.sepiaButton = new JButton("Sepia");
    this.sepiaButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleSepiaEvent();
      }
    });
    this.sharpenButton = new JButton("Sharpen");
    this.sharpenButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleSharpenEvent();
      }
    });
    operationPanel.add(this.blurButton);
    operationPanel.add(this.monochromeButton);
    operationPanel.add(this.sepiaButton);
    operationPanel.add(this.sharpenButton);

    buttonPanel.add(operationPanel);

    JPanel operationTextPanel = new JPanel();
    operationTextPanel.setLayout(new BoxLayout(operationTextPanel, BoxLayout.PAGE_AXIS));
    JPanel downscalePanel = new JPanel();
    this.downscaleButton = new JButton("Downscale");
    this.downscaleButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleDownscaleEvent();
      }
    });
    this.downscaleXTextField = new JTextField(5);
    this.downscaleYTextField = new JTextField(5);
    downscalePanel.add(this.downscaleButton);
    downscalePanel.add(new JLabel("x-scale (1-100%):"));
    downscalePanel.add(this.downscaleXTextField);
    downscalePanel.add(new JLabel("y-scale (1-100%):"));
    downscalePanel.add(this.downscaleYTextField);
    operationTextPanel.add(downscalePanel);

    JPanel mosaicPanel = new JPanel();
    this.mosaicButton = new JButton("Mosaic");
    this.mosaicButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleMosaicEvent();
      }
    });
    this.mosaicSeedTextField = new JTextField(5);
    mosaicPanel.add(this.mosaicButton);
    mosaicPanel.add(new JLabel("seeds:"));
    mosaicPanel.add(this.mosaicSeedTextField);
    operationTextPanel.add(mosaicPanel);

    JPanel checkerboardPanel = new JPanel();
    this.createCheckerboardButton = new JButton("Checkerboard");
    this.createCheckerboardButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleCreateCheckerboard();
      }
    });
    this.checkerboardSizeTextField = new JTextField(5);
    this.checkerboardSquaresTextField = new JTextField(5);
    this.checkerboardColorOneDropdown = new JComboBox<>();
    this.checkerboardColorTwoDropdown = new JComboBox<>();

    String[] colors = {"red", "orange", "yellow", "green", "cyan", "blue", "magenta", "white",
        "gray", "black"};
    for (String color : colors) {
      this.checkerboardColorOneDropdown.addItem(color);
      this.checkerboardColorTwoDropdown.addItem(color);
    }

    checkerboardPanel.add(this.createCheckerboardButton);
    checkerboardPanel.add(this.checkerboardSizeTextField);
    checkerboardPanel.add(this.checkerboardSquaresTextField);
    checkerboardPanel.add(this.checkerboardColorOneDropdown);
    checkerboardPanel.add(this.checkerboardColorTwoDropdown);
    operationTextPanel.add(checkerboardPanel);

    buttonPanel.add(operationTextPanel);

    JPanel filePanel = new JPanel();
    this.importButton = new JButton("Import");
    this.importButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleImport();
      }
    });
    this.exportButton = new JButton("Export Topmost Visible Layer");
    this.exportButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleExportLayer();
      }
    });
    this.exportAllButton = new JButton("Export All Layers");
    this.exportAllButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleExportAll();
      }
    });
    this.exportAllDropDown = new JComboBox<>();
    exportAllDropDown.addItem("JPEG");
    exportAllDropDown.addItem("PNG");
    exportAllDropDown.addItem("PPM");
    exportAllDropDown.addItem("TXT");
    filePanel.add(this.importButton);
    filePanel.add(this.exportButton);
    filePanel.add(this.exportAllButton);
    filePanel.add(this.exportAllDropDown);

    // todo: make this popup when button is pressed
//    JFileChooser fileChooser = new JFileChooser(".");
//    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image or Text Files",
//        "jpg", "jpeg", "png", "ppm", "txt");
//    fileChooser.setFileFilter(extensionFilter);
//    // todo: may need separate logic for save/load - showOpenDialog vs showSaveDialog, perhaps through class integer field
//    if (fileChooser.showOpenDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
//      File file = fileChooser.getSelectedFile();
//      this.filepath = file.getAbsolutePath();
//    }

    buttonPanel.add(filePanel);

    JPanel operationLayerPanel = new JPanel();
    operationLayerPanel.setLayout(new BoxLayout(operationLayerPanel, BoxLayout.PAGE_AXIS));

    JPanel createLayerPanel = new JPanel();
    this.createLayerButton = new JButton("Create Layer");
    this.createLayerButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleAddLayerEvent();
      }
    });
    this.createLayerTextField = new JTextField(10);
    createLayerPanel.add(this.createLayerButton);
    createLayerPanel.add(this.createLayerTextField);
    operationLayerPanel.add(createLayerPanel);
    JPanel layerNamePanel = new JPanel();
    this.setCurrentLayerButton = new JButton("Set Current Layer");
    this.setCurrentLayerButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleCurrentLayerEvent();
      }
    });
    this.removeLayerButton = new JButton("Remove Layer");
    this.removeLayerButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleRemoveLayerEvent();
      }
    });
    this.layerNamesDropdown = new JComboBox<>();
    layerNamePanel.add(this.setCurrentLayerButton);
    layerNamePanel.add(this.removeLayerButton);
    layerNamePanel.add(this.layerNamesDropdown);
    operationLayerPanel.add(layerNamePanel);

    JPanel visibilityPanel = new JPanel();
    this.visibilityCheckBox = new JCheckBox();
    this.visibilityCheckBox.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleVisibility();
      }
    });
    this.visibilityTextField = new JTextField("Make Invisible");
    // todo: consider making this two buttons so don't have to reset checkbox when switching layers
    visibilityPanel.add(this.visibilityCheckBox);
    visibilityPanel.add(this.visibilityTextField);
    operationLayerPanel.add(visibilityPanel);

    buttonPanel.add(operationLayerPanel);

//    this.renderImage(null);
//    this.renderImage(png.importFile("res//mosaic//popeyes_1000_seeds.png"));

  }


  public void addViewEventListener(Features feature) throws IllegalArgumentException {
    this.features.add(ImageUtil.requireNonNull(feature));
  }

  public void renderImage(Image img) {
//    imagePanel.removeAll();
//    imagePanel.revalidate();
//    imagePanel.repaint();
    ;

//    imageLabel.removeAll();
//    imageLabel.revalidate();
//    imageLabel.repaint();
//    JLabel label = new JLabel();

//    if (imageScrollPane != null) {
//
//      imageScrollPane.removeAll();
//      imageScrollPane.revalidate();
//      imageScrollPane.repaint();
//    }

    ImageIcon icon = null;
    if (img != null) {
      icon = new ImageIcon(ImageUtil.convertImage(img));
    }

    imageLabel.setIcon(icon);
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageScrollPane.setPreferredSize(new Dimension(100, 200));
  }


  // todo: catch nullpointerexceptions for all text fields, unless we convert to dropdown menus in
  //  which case this isn't necessary
  public String getXScale() {
    return this.downscaleXTextField.getText();
  }

  public String getYScale() {
    return this.downscaleYTextField.getText();
  }

  public String getSeeds() {
    return this.mosaicSeedTextField.getText();
  }

  public String getCheckerboardSize() {
    return this.checkerboardSizeTextField.getText();
  }

  public String getNumSquares() {
    return this.checkerboardSquaresTextField.getText();
  }

  public String getColorOne() {
    return (String) this.checkerboardColorOneDropdown.getSelectedItem();
  }

  public String getColorTwo() {
    return (String) this.checkerboardColorTwoDropdown.getSelectedItem();
  }

  public String getNewLayerName() {
    return this.createLayerTextField.getText();
  }

  public void addNewLayerToDropdown(String layerName) throws IllegalArgumentException {
    this.layerNamesDropdown.addItem(ImageUtil.requireNonNull(layerName));
  }

  public String getFilePath() {
    // todo: input: fileChooser.showOpenDialog(ImageViewImpl.this) or fileChooser.showSaveDialog(ImageViewImpl.this)
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image or Text Files",
        "jpg", "jpeg", "png", "ppm", "txt");
    fileChooser.setFileFilter(extensionFilter);
    if (fileChooser.showOpenDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      // todo: check for null path in controller
      return null;
    }
  }

  public String getExportAllFileType() {
    return (String) this.exportAllDropDown.getSelectedItem();
  }

  public boolean getVisibilityState() {
    // todo: this is BACKWARDS from how it is implemented in the controller
    return this.visibilityCheckBox.isSelected();
  }

  public String getSelectedLayer() {
    return (String) this.layerNamesDropdown.getSelectedItem();
  }

  public void changeCurrentLayerText(String layerName) {
    ImageUtil.requireNonNull(layerName);
    this.buttonPanel.setBorder(
        BorderFactory.createTitledBorder("Currently selected layer: " + layerName));
  }

  // todo: unused?
  public void removeLayerName(String layerName) {
    this.layerNamesDropdown.removeItem(layerName);
  }

  public void run() {
    // todo: move this to constructor eventually
    ImageViewImpl.setDefaultLookAndFeelDecorated(false);
    ImageViewImpl frame = new ImageViewImpl();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    ImageViewImpl.setDefaultLookAndFeelDecorated(false);
    ImageViewImpl frame = new ImageViewImpl();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
