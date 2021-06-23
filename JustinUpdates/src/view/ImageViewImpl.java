package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.ImageUtil;
import model.image.Image;
import model.image.file.PNG;

// todo: make this implement interface
public class ImageViewImpl extends JFrame {

  private final JPanel mainPanel;
  private final JScrollPane mainScrollPane;
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

  private final JButton createLayerButton;
  private final JTextField createLayerTextField;

  // these two buttons use the same dropdown
  private final JButton setCurrentLayerButton;
  private final JButton removeLayerButton;
  private final JComboBox<String> layerNamesDropdown;

  private final JCheckBox visibilityCheckBox; // check to toggle invisible
  private final JTextField visibilityTextField;


  public ImageViewImpl() {
    super();
    setTitle("Image Processor");
    setSize(900, 500);

    setFocusable(true);
    requestFocus();

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1, 2));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Currently viewing layer:"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    JLabel label = new JLabel();
    JScrollPane scrollPane = new JScrollPane(label);
    PNG png = new PNG();
    Image img = png.importFile("res//mosaic//popeyes_original.png");
    label.setIcon(new ImageIcon(ImageUtil.convertImage(img)));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    scrollPane.setPreferredSize(new Dimension(100, 200));
    imagePanel.add(scrollPane);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Currently selected layer:"));
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

    this.mainPanel.add(buttonPanel);

    JPanel operationPanel = new JPanel();
    this.blurButton = new JButton("Blur");
    this.monochromeButton = new JButton("Monochrome");
    this.monochromeButton.setPreferredSize(new Dimension(115, 26));
    this.sepiaButton = new JButton("Sepia");
    this.sharpenButton = new JButton("Sharpen");
    operationPanel.add(this.blurButton);
    operationPanel.add(this.monochromeButton);
    operationPanel.add(this.sepiaButton);
    operationPanel.add(this.sharpenButton);

    buttonPanel.add(operationPanel);

    JPanel operationTextPanel = new JPanel();
    operationTextPanel.setLayout(new BoxLayout(operationTextPanel, BoxLayout.PAGE_AXIS));
    JPanel downscalePanel = new JPanel();
    this.downscaleButton = new JButton("Downscale");
    this.downscaleXTextField = new JTextField(5);
    this.downscaleYTextField = new JTextField(5);
    downscalePanel.add(this.downscaleButton);
    downscalePanel.add(this.downscaleXTextField);
    downscalePanel.add(this.downscaleYTextField);
    operationTextPanel.add(downscalePanel);

    JPanel mosaicPanel = new JPanel();
    this.mosaicButton = new JButton("Mosaic");
    this.mosaicSeedTextField = new JTextField(5);
    mosaicPanel.add(this.mosaicButton);
    mosaicPanel.add(this.mosaicSeedTextField);
    operationTextPanel.add(mosaicPanel);

    JPanel checkerboardPanel = new JPanel();
    this.createCheckerboardButton = new JButton("Checkerboard");
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
    this.exportButton = new JButton("Export Topmost Visible Layer");
    this.exportAllButton = new JButton("Export All Layers");
    filePanel.add(this.importButton);
    filePanel.add(this.exportButton);
    filePanel.add(this.exportAllButton);

    buttonPanel.add(filePanel);

    JPanel operationLayerPanel = new JPanel();
    operationLayerPanel.setLayout(new BoxLayout(operationLayerPanel, BoxLayout.PAGE_AXIS));

    JPanel createLayerPanel = new JPanel();
    this.createLayerButton = new JButton("Create Layer");
    this.createLayerTextField = new JTextField(10);
    createLayerPanel.add(this.createLayerButton);
    createLayerPanel.add(this.createLayerTextField);
    operationLayerPanel.add(createLayerPanel);

    JPanel layerNamePanel = new JPanel();
    this.setCurrentLayerButton = new JButton("Set Current Layer");
    this.removeLayerButton = new JButton("Remove Layer");
    this.layerNamesDropdown = new JComboBox<>();
    layerNamePanel.add(this.setCurrentLayerButton);
    layerNamePanel.add(this.removeLayerButton);
    layerNamePanel.add(this.layerNamesDropdown);
    operationLayerPanel.add(layerNamePanel);

    JPanel visibilityPanel = new JPanel();
    this.visibilityCheckBox = new JCheckBox();
    this.visibilityTextField = new JTextField("Make Invisible");
    visibilityPanel.add(this.visibilityCheckBox);
    visibilityPanel.add(this.visibilityTextField);
    operationLayerPanel.add(visibilityPanel);

    buttonPanel.add(operationLayerPanel);
  }

  public static void main(String[] args) {
    ImageViewImpl.setDefaultLookAndFeelDecorated(false);
    ImageViewImpl frame = new ImageViewImpl();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
