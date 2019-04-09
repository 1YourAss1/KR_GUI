import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JTextField textField;
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JTextArea textArea1;
    private JButton doButton;
    private JTextField KeyTextField;
    private DES des;
    private GOST_28147_89 gost89;
    private RSA rsa;
    private HASH hash;
    private DigSign digSign;

    public GUI() {
        this.setTitle("Курсовая работа");
        this.getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
        this.doButton.addActionListener(new doButtonListener());
        this.comboBox1.addItem("DES");
        this.comboBox1.addItem("ГОСТ 28147-89");
        this.comboBox1.addItem("RSA");
        this.comboBox1.addItem("Хеширование");
        this.comboBox1.addItem("ЭЦП");
        //this.textArea1.setPreferredSize(new Dimension(100, 300));
    }

    public class doButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) comboBox1.getSelectedItem();
            if (s.equals("DES")) {
                des = new DES();
                textArea1.setText(des.encrypt(textField.getText(), KeyTextField.getText()));
            } else if (s.equals("ГОСТ 28147-89")) {
                gost89 = new GOST_28147_89();
                textArea1.setText(gost89.encrypt(textField.getText(), KeyTextField.getText()));
            } else if (s.equals("RSA")) {
                rsa = new RSA();
                if (KeyTextField.getText().trim().length() == 0) {
                    rsa.GenerateKeys(0);
                } else {
                    rsa.GenerateKeys(Integer.parseInt(KeyTextField.getText()));
                }

                String rs = "p = " + rsa.getP() + "\nq = " + rsa.getQ() + "\nn = p*q = " + rsa.getN() + "\nf(n) = (p-1)(q-1) = "
                        + rsa.getF() + "\nПусть d = " + rsa.getD() + "\nТогда e = " + rsa.getE();

                rs = rs + "\nЗакрытый ключ: " + rsa.getPrivateKey()[0] + ", " + rsa.getPrivateKey()[1]
                        + "\nОткрытый ключ: " + rsa.getPublicKey()[0] + ", " + rsa.getPublicKey()[1];
                long encrypt[] = rsa.EncryptText(textField.getText());
                for (int i = 0; i < encrypt.length; i++) {
                    rs = rs + "\nC" + (i+1) + " = " + encrypt[i];
                }
                textArea1.setText(rs);
            } else if (s.equals("Хеширование")) {
                hash = new HASH();
                String rs = "Пусть H0 = " + hash.getH0();
                int H[] = hash.hashing(textField.getText());
                for (int i = 0; i < H.length; i++) {
                    rs = rs + "\nH" + (i + 1) + " = " + H[i];
                }
                textArea1.setText(rs + "\nХэш-образ сообщения равен " + H[H.length - 1]);
            } else if (s.equals("ЭЦП")) {
                digSign = new DigSign();
                int ecp;
                if (KeyTextField.getText().trim().length() == 0) {
                    ecp = digSign.creatSign(textField.getText(), 0);
                } else {
                    ecp = digSign.creatSign(textField.getText(), Integer.parseInt(KeyTextField.getText()));
                }
                textArea1.setText("H = " + digSign.getH() + "\nЗакрытый ключ: " + digSign.getPrKey()[0] + ", " + digSign.getPrKey()[1] + "\nЭЦП = " + ecp);
            }
        }
    }
}
