package gui.file;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class NewJFrame extends javax.swing.JFrame {

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NewJFrame inst = new NewJFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);

                JPanel mainPanel = new JPanel(new BorderLayout());
                JPanel top = new JPanel();
                JButton button = new JButton("���ļ�");
                // button.addActionListener(new ActionListener());
                JTextField filePathText = new JTextField(20);
                top.add(button);
                top.add(filePathText);
                mainPanel.add(top, BorderLayout.NORTH);

                JTextArea fileContentArea = new JTextArea(11, 11);
                fileContentArea.setWrapStyleWord(true);
                fileContentArea.setLineWrap(true);
                mainPanel.add(fileContentArea);
                mainPanel.setVisible(true);

            }
        });
    }

    public NewJFrame() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pack();
            setSize(400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
