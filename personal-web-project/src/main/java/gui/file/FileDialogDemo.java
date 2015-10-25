package gui.file;

//打开文件窗口；
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileDialogDemo {

    public static void main(String args[]) {

        JFrame frame = new JFrame("文件打开窗口");
        frame.getContentPane().add(new FileDialogDemo().createPanel());

        frame.setSize(300, 200);
        frame.setVisible(true);

    }

    private Component createPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        JButton button = new JButton("打开文件");
        button.addActionListener(new Listener());
        JTextField filePathText = new JTextField(20);
        top.add(button);
        top.add(filePathText);
        mainPanel.add(top, BorderLayout.NORTH);

        JTextArea fileContentArea = new JTextArea(11, 11);
        fileContentArea.setWrapStyleWord(true);
        fileContentArea.setLineWrap(true);
        mainPanel.add(fileContentArea);
        mainPanel.setVisible(true);
        return mainPanel;
    }

    File openFile = new File("");

    // 内部私有类；
    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = null;
            if (openFile != null) {
                fileChooser = new JFileChooser(openFile.getParentFile());
            } else {
                fileChooser = new JFileChooser();
            }
            int state = fileChooser.showOpenDialog(null);
            if (state == JFileChooser.APPROVE_OPTION) {
                openFile = fileChooser.getSelectedFile();
                // filePateText.setText(openFile.getPath());
                // showFileContent(openFile);
            }

            File file = new File("d:\\temp\\001.txt");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String temp = null;
            // while ((temp = reader.readLine()) != null) {
            // fileContentArea.append(temp);
            // }

        }
    }

}
