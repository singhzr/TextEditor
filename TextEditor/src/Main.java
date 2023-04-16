import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class TextEditor implements ActionListener{
    //Declaring properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    //File Menu items
    static JMenuItem newFile;
    JMenuItem openFile;
    JMenuItem saveFile;

    //Edit menu Items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;
    TextEditor(){

        //Initialize a frame
        frame = new JFrame();

        //Initialise menubar
        menuBar = new JMenuBar();

        //Initialise textarea
        textArea = new JTextArea();

        //Initialise menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialise File Menu Items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menu Items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialise edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

        //Add action listeners to file menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menu to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //Set MenuBar to Frame
        frame.setJMenuBar(menuBar);

        //Create content panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll panel
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);

        //Set Dimensions of Frame
        frame.setBounds(0,0, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            //Perform cut
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            //Perform copy
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            //Perform paste
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            //Perform selectAll
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            //Perform close
            System.exit(0);
        }

        if (actionEvent.getSource() == openFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C");
            int chooseOption = fileChooser.showOpenDialog(null);

            //If we have clicked on Open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try {
                    //Initialise file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialise Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read contents of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == saveFile) {
            //Initialise file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we clicked on save button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile().getAbsoluteFile() + ".txt");

                try {
                    //Initialise file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialise buffered write
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor textEditor = new TextEditor();
        }
    }

    public static void main(String[] args){
        TextEditor textEditor = new TextEditor();
    }
}
