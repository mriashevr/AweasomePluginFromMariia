package plugin;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class InputData extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField VariableType4;
    private JTextField VariableName4;
    private JTextField VariableType3;
    private JTextField VariableName3;
    private JTextField VariableType2;
    private JTextField VariableName2;
    private JTextField VariableType1;
    private JTextField VariableName1;
    private JTextField ClassNameData;
    private Boolean OkayPressed;

    public InputData() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.OkayPressed = Boolean.FALSE;
    }

    private void onOK() {
        this.OkayPressed = Boolean.TRUE;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        InputData dialog = new InputData();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String ClassCodeGeneration () {
        String finalCode = "@Data\n" +
                "public class " + ClassNameData.getText() + "{ \n";

        if (!Objects.equals(VariableType1.getText(), "") && !Objects.equals(VariableName1.getText(), "")) {
            finalCode += "\tprivate " + VariableType1.getText() + " " + VariableName1.getText() + ";\n";
        }

        if (!Objects.equals(VariableType2.getText(), "") && !Objects.equals(VariableName2.getText(), "")) {
            finalCode += "\tprivate " + VariableType2.getText() + " " + VariableName2.getText() + ";\n";
        }

        if (!Objects.equals(VariableType3.getText(), "") && !Objects.equals(VariableName3.getText(), "")) {
            finalCode += "\tprivate " + VariableType3.getText() + " " + VariableName3.getText() + ";\n";
        }

        if (!Objects.equals(VariableType4.getText(), "") && !Objects.equals(VariableName4.getText(), "")) {
            finalCode += "\tprivate " + VariableType4.getText() + " " + VariableName4.getText() + ";\n";
        }

        finalCode += "\n};";
        return finalCode;
    }

    public Boolean GetOKPressed() {
        return OkayPressed;
    }
}
