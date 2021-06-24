package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageUtil;
import model.image.Image;

// todo: make this implement interface
//  do this last after all public methods have been finalized
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
  private final JComboBox<String> currentLayerNamesDropdown;
  private final JComboBox<String> removeLayerNamesDropdown;

  //private final JCheckBox visibilityCheckBox; // check to toggle invisible
  //private final JTextField visibilityTextField;

  private final JButton visibilityButton;
  //private final JButton invisibilityButton;

  private final JPanel buttonPanel;


  private String currentLayerName;
  private String filepath;


  public ImageViewImpl() {
    super();

    this.features = new ArrayList<>();

    ImageViewImpl.setDefaultLookAndFeelDecorated(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // todo: clean up constructor to create/add to panels in helper methods
    //  depending on how this is implemented, consider making the components local variables

    setTitle("Image Processor");
    setSize(900, 600);

    setFocusable(true);
    requestFocus();

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1, 2));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imagePanel.add(imageScrollPane);

    // todo: all of this, plus popup input dialogs
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu fileMenu = new JMenu("File");
    JMenuItem importMenuItem = new JMenuItem("Import");
//    importMenuItem.addActionListener();
    JMenuItem exportMenuItem = new JMenuItem("Export");
    JMenuItem exportAllMenuItem = new JMenuItem("Export All");
    fileMenu.add(importMenuItem);
    fileMenu.add(exportMenuItem);
    fileMenu.add(exportAllMenuItem);
    menuBar.add(fileMenu);

    JMenu filterMenu = new JMenu("Filter");
    JMenuItem blurMenuItem = new JMenuItem("Blur");
    blurMenuItem.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleBlurEvent();
      }
    });
    JMenuItem sharpenMenuItem = new JMenuItem("Sharpen");
    sharpenMenuItem.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleSharpenEvent();
      }
    });
    JMenuItem sepiaMenuItem = new JMenuItem("Sepia");
    sepiaMenuItem.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleSepiaEvent();
      }
    });
    JMenuItem monochromeMenuItem = new JMenuItem("Monochrome");
    monochromeMenuItem.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleMonochromeEvent();
      }
    });
    JPanel downscaleMenuPanel = new JPanel();
    this.downscaleXTextField = new JTextField(5);
    this.downscaleYTextField = new JTextField(5);
//    JTextField downscaleXScaleTextField = new JTextField(5);
//    JTextField downscaleYScaleTextField = new JTextField(5);
    downscaleMenuPanel.add(new JLabel("X Scale (1-100%):"));
    downscaleMenuPanel.add(this.downscaleXTextField);
    downscaleMenuPanel.add(new JLabel("Y Scale (1-100%):"));
    downscaleMenuPanel.add(this.downscaleYTextField);
    JMenuItem downscaleMenuItem = new JMenuItem("Downscale");
    downscaleMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, downscaleMenuPanel, "Downscale", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : features) {
          feature.handleDownscaleEvent();
        }
      }
    });
    JPanel mosaicMenuPanel = new JPanel();
    this.mosaicSeedTextField = new JTextField(5);
    mosaicMenuPanel.add(new JLabel("Seeds:"));
    mosaicMenuPanel.add(this.mosaicSeedTextField);
    JMenuItem mosaicMenuItem = new JMenuItem("Mosaic");
    mosaicMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, mosaicMenuPanel, "Mosaic", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : features) {
          feature.handleMosaicEvent();
        }
      }
    });

    filterMenu.add(blurMenuItem);
    filterMenu.add(sharpenMenuItem);
    filterMenu.add(sepiaMenuItem);
    filterMenu.add(monochromeMenuItem);
    filterMenu.add(downscaleMenuItem);
    filterMenu.add(mosaicMenuItem);
    menuBar.add(filterMenu);

    JMenu layerMenu = new JMenu("Layer");

    JPanel createLayerMenuPanel = new JPanel();
    this.createLayerTextField = new JTextField(10);
    createLayerMenuPanel.add(new JLabel("Name:"));
    createLayerMenuPanel.add(this.createLayerTextField);
    JMenuItem createLayerMenuItem = new JMenuItem("Create");
    createLayerMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, createLayerMenuPanel, "Create Layer", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : features) {
          feature.handleAddLayerEvent();
        }
      }
    });

    JPanel currentLayerMenuPanel = new JPanel();
    this.currentLayerNamesDropdown = new JComboBox<>();
    currentLayerMenuPanel.add(new JLabel("Layers:"));
    currentLayerMenuPanel.add(this.currentLayerNamesDropdown);
    JMenuItem currentLayerMenuItem = new JMenuItem("Current");
    currentLayerMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, currentLayerMenuPanel, "Set Current Layer", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : features) {
          feature.handleCurrentLayerEvent();
        }
      }
    });


    JPanel removeLayerMenuPanel = new JPanel();
    this.removeLayerNamesDropdown = new JComboBox<>();
    removeLayerMenuPanel.add(new JLabel("Layers:"));
    removeLayerMenuPanel.add(this.removeLayerNamesDropdown);
    JMenuItem removeLayerMenuItem = new JMenuItem("Remove");
    removeLayerMenuItem.addActionListener(e -> {
      int result = JOptionPane
          .showConfirmDialog(this, removeLayerMenuPanel, "Remove Layer", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : features) {
          feature.handleRemoveLayerEvent();
        }
      }
    });

    JMenuItem visibleLayerMenuItem = new JMenuItem("Visible");
    visibleLayerMenuItem.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleVisibility();
      }
    });

    layerMenu.add(createLayerMenuItem);
    layerMenu.add(currentLayerMenuItem);
    layerMenu.add(removeLayerMenuItem);
    layerMenu.add(visibleLayerMenuItem);
    menuBar.add(layerMenu);

    JMenu imageMenu = new JMenu("Image");
    JPanel checkerboardMenuPanel = new JPanel();
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
    checkerboardMenuPanel.add(new JLabel("Size:"));
    checkerboardMenuPanel.add(this.checkerboardSizeTextField);
    checkerboardMenuPanel.add(new JLabel("Number of Squares:"));
    checkerboardMenuPanel.add(this.checkerboardSquaresTextField);
    checkerboardMenuPanel.add(new JLabel("Color 1:"));
    checkerboardMenuPanel.add(this.checkerboardColorOneDropdown);
    checkerboardMenuPanel.add(new JLabel("Color 2:"));
    checkerboardMenuPanel.add(this.checkerboardColorTwoDropdown);
    JMenuItem checkerboardMenuItem = new JMenuItem("Checkerboard");
    checkerboardMenuItem.addActionListener(e -> {
      // todo: move these to private helpers for reuse between menu and buttons
      int result = JOptionPane
          .showConfirmDialog(this, checkerboardMenuPanel, "Checkerboard", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        for (Features feature : features) {
          feature.handleCreateCheckerboard();
        }
      }
    });

    imageMenu.add(checkerboardMenuItem);
    menuBar.add(imageMenu);


//    JPanel mosaicMenuPanel = new JPanel();
//    this.mosaicSeedTextField = new JTextField(5);
//    mosaicMenuPanel.add(new JLabel("Seeds:"));
//    mosaicMenuPanel.add(this.mosaicSeedTextField);
//    JMenuItem mosaicMenuItem = new JMenuItem("Mosaic");
//    mosaicMenuItem.addActionListener(e -> {
//      int result = JOptionPane
//          .showConfirmDialog(this, mosaicMenuPanel, "Mosaic", JOptionPane.OK_CANCEL_OPTION);
//      if (result == JOptionPane.OK_OPTION) {
//        for (Features feature : features) {
//          feature.handleMosaicEvent();
//        }
//      }
//    });

//    PNG png = new PNG();
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

    // button.addActionListener + anonymous class
//    new ActionListener() {
//
//      @Override
//      public void actionPerformed(ActionEvent e) {
//          for feature in features, feature.method();
//      }
//    }

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
    operationPanel.setBorder(BorderFactory.createTitledBorder("Filter"));
    operationPanel.add(this.blurButton);
    operationPanel.add(this.monochromeButton);
    operationPanel.add(this.sepiaButton);
    operationPanel.add(this.sharpenButton);

    buttonPanel.add(operationPanel);

    JPanel operationTextPanel = new JPanel();
    operationTextPanel.setLayout(new BoxLayout(operationTextPanel, BoxLayout.PAGE_AXIS));
    // todo: rename this. consider reorganizing contents/order of all submenus to match menubar
    operationTextPanel.setBorder(BorderFactory.createTitledBorder("More Operations?"));
    JPanel downscalePanel = new JPanel();
    this.downscaleButton = new JButton("Downscale");
    this.downscaleButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleDownscaleEvent();
      }
    });
    // todo: downscale seems to not work

    // todo: no more inputs in buttonpanel, only buttons
//    this.downscaleXTextField = new JTextField(5);
//    this.downscaleYTextField = new JTextField(5);
    downscalePanel.add(this.downscaleButton);
//    downscalePanel.add(new JLabel("x-scale (1-100%):"));
//    downscalePanel.add(this.downscaleXTextField);
//    downscalePanel.add(new JLabel("y-scale (1-100%):"));
//    downscalePanel.add(this.downscaleYTextField);
    operationTextPanel.add(downscalePanel);

    JPanel mosaicPanel = new JPanel();
    this.mosaicButton = new JButton("Mosaic");
    this.mosaicButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleMosaicEvent();
      }
    });
//    this.mosaicSeedTextField = new JTextField(5);

    mosaicPanel.add(this.mosaicButton);
//    mosaicPanel.add(new JLabel("seeds:"));
//    mosaicPanel.add(this.mosaicSeedTextField);
    operationTextPanel.add(mosaicPanel);

    JPanel checkerboardPanel = new JPanel();
    this.createCheckerboardButton = new JButton("Checkerboard");
    this.createCheckerboardButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleCreateCheckerboard();
      }
    });
//    this.checkerboardSizeTextField = new JTextField(5);
//    this.checkerboardSquaresTextField = new JTextField(5);
//    this.checkerboardColorOneDropdown = new JComboBox<>();
//    this.checkerboardColorTwoDropdown = new JComboBox<>();

//    String[] colors = {"red", "orange", "yellow", "green", "cyan", "blue", "magenta", "white",
//        "gray", "black"};
//    for (String color : colors) {
//      this.checkerboardColorOneDropdown.addItem(color);
//      this.checkerboardColorTwoDropdown.addItem(color);
//    }

    checkerboardPanel.add(this.createCheckerboardButton);
//    checkerboardPanel.add(this.checkerboardSizeTextField);
//    checkerboardPanel.add(this.checkerboardSquaresTextField);
//    checkerboardPanel.add(this.checkerboardColorOneDropdown);
//    checkerboardPanel.add(this.checkerboardColorTwoDropdown);
    operationTextPanel.add(checkerboardPanel);

    buttonPanel.add(operationTextPanel);

    JPanel filePanel = new JPanel();
    filePanel.setBorder(BorderFactory.createTitledBorder("File"));
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
    operationLayerPanel.setBorder(BorderFactory.createTitledBorder("Layer Operation"));
    operationLayerPanel.setLayout(new BoxLayout(operationLayerPanel, BoxLayout.PAGE_AXIS));

    JPanel createLayerPanel = new JPanel();
    this.createLayerButton = new JButton("Create Layer");
    this.createLayerButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleAddLayerEvent();
      }
    });
//    this.createLayerTextField = new JTextField(10);
    createLayerPanel.add(this.createLayerButton);
//    createLayerPanel.add(this.createLayerTextField);
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
//    this.layerNamesDropdown = new JComboBox<>();
    layerNamePanel.add(this.setCurrentLayerButton);
    layerNamePanel.add(this.removeLayerButton);
//    layerNamePanel.add(this.layerNamesDropdown);
    operationLayerPanel.add(layerNamePanel);

    JPanel visibilityPanel = new JPanel();
    this.visibilityButton = new JButton("Visibility");
    this.visibilityButton.addActionListener(e -> {
      for (Features feature : features) {
        feature.handleVisibility();
      }
    });
//    this.invisibilityButton = new JButton("Invisible");
//    this.visibilityCheckBox = new JCheckBox();
//    this.visibilityCheckBox.addActionListener(e -> {
//      for (Features feature : features) {
//        feature.handleVisibility();
//      }
//    });
//    this.visibilityTextField = new JTextField("Make Invisible");
//    visibilityPanel.add(this.visibilityCheckBox);
//    visibilityPanel.add(this.visibilityTextField);
    visibilityPanel.add(visibilityButton);
    operationLayerPanel.add(visibilityPanel);

    buttonPanel.add(operationLayerPanel);

    this.pack();
  }


  public void addViewEventListener(Features feature) throws IllegalArgumentException {
    this.features.add(ImageUtil.requireNonNull(feature));
  }

  public void renderImage(Image img) {
    ImageIcon icon = null;
    if (img != null) {
      icon = new ImageIcon(ImageUtil.convertImage(img));
    }

    imageLabel.setIcon(icon);
  }

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
    ImageUtil.requireNonNull(layerName);
    this.currentLayerNamesDropdown.addItem(layerName);
    this.removeLayerNamesDropdown.addItem(layerName);
  }

  public String getImportFilePath() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image or Text Files",
        "jpg", "jpeg", "png", "ppm", "txt");
    fileChooser.setFileFilter(extensionFilter);
    if (fileChooser.showOpenDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return null;
    }
  }

  public String getExportFilePath() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image or Text Files",
        "jpg", "jpeg", "png", "ppm", "txt");
    fileChooser.setFileFilter(extensionFilter);
    if (fileChooser.showSaveDialog(ImageViewImpl.this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return null;
    }
  }

  public String getExportAllFileType() {
    return (String) this.exportAllDropDown.getSelectedItem();
  }

  public String getSelectedCurrentLayer() {
    String layerName = (String) this.currentLayerNamesDropdown.getSelectedItem();
    return (layerName == null) ? "" : layerName;
  }

  public String getSelectedRemoveLayer() {
    String layerName = (String) this.removeLayerNamesDropdown.getSelectedItem();
    return (layerName == null) ? "" : layerName;
  }

  public void changeCurrentLayerText(String layerName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(layerName);
    this.buttonPanel.setBorder(
        BorderFactory.createTitledBorder("Currently selected layer: " + layerName));
  }

  public void removeLayerName(String layerName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(layerName);
    this.currentLayerNamesDropdown.removeItem(layerName);
    this.removeLayerNamesDropdown.removeItem(layerName);
  }

  public void renderErrorMessage(String str) throws IllegalArgumentException {
    ImageUtil.requireNonNull(str);
    JOptionPane.showMessageDialog(this, str, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
