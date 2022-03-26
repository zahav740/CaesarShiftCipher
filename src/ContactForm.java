import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    JRadioButton encryption;
    JRadioButton decryption;

    public ContactForm() {
        super("Caesar Cipher");
        super.setBounds(400, 200, 300, 200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(4, 2, 10, 2));

        JLabel name = new JLabel("                  Сделайте выбор:");
        // JTextField name_field = new JTextField("", 1);
        container.add(name);

        encryption = new JRadioButton("    Шифровка");
        decryption = new JRadioButton("    Дешифровка");
        JButton send_button = new JButton("Выполнить");

        encryption.setSelected(true);
        container.add(encryption);
        container.add(decryption);

        ButtonGroup group = new ButtonGroup();
        group.add(encryption);
        group.add(decryption);

        container.add(send_button);

        send_button.addActionListener(new ButtonEventManager());
    }

    class ButtonEventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String encrypt = encryption.getText();
            String decrypt = encryption.getText();

            String isEncryption = "a";
            if(encryption.isSelected())
                isEncryption = "b";

            JOptionPane.showMessageDialog(null, "", "Privet " + encrypt, JOptionPane.PLAIN_MESSAGE);
        }
    }

}
