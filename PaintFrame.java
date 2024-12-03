import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PaintFrame extends JFrame {
    private ActionController eventClicks;

    private void execActions() {
        eventClicks = new ActionController(this);

        eventClicks.eventListener();
    }

    private void centerFrame(Dimension size){ //Este es un método que hice para centrar la ventana justo en el medio de la pantalla, util cuando el JFrame no tiene unas dimensiones predefinidas por ti
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        int ancho = pantalla.width/2 - (int)size.getWidth()/2;
        int alto = pantalla.height/2 - (int)size.getHeight()/2;

        this.setLocation(ancho, alto);
    }

    JPanel marco = new JPanel();
    PanelRound panel = new PanelRound(25, 25, 25, 25);
    JPanel buttonPanel = new JPanel();
    JTextField numField = new JTextField("0");

    String[][] buttonsData = {  {"AC", "ans", "+/–", "/"},
                                {"7",  "8",    "9",  "×"},
                                {"4",  "5",    "6",  "–"},
                                {"1",  "2",    "3",  "+"},
                                {"0",   "",    ".",  "="},
                            };

    public JButton[][] buttons = new JButton[buttonsData.length][buttonsData[0].length];

    GridBagConstraints c = new GridBagConstraints();
    Color lightGray = new Color(92, 92, 95);
    Color darkGray = new Color(42, 42, 44);
    Color orangito = new Color(255, 159, 10);
    Color mandarin = new Color(179, 110, 41);

    public PaintFrame() {
        try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch (UnsupportedLookAndFeelException e) {
        // handle exception
        }
        catch (ClassNotFoundException e) {
        // handle exception
        }
        catch (InstantiationException e) {
        // handle exception
        }
        catch (IllegalAccessException e) {
        // handle exception
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelConfig();
        this.getContentPane().add(marco);
        this.getContentPane().setBackground(Color.black);
        this.pack();
        this.setResizable(false);
        centerFrame(this.getSize());
    }

    public void panelConfig() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < buttonsData.length; i++) {
            for (int j = 0; j < buttonsData[0].length; j++) {
                buttons[i][j] = new JButton(buttonsData[i][j]);
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                buttons[i][j].setBackground(darkGray);
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].setFont(new Font("Dialog", Font.PLAIN, 23));

                if(buttons[i][j].getText()==null){
                    continue;
                }

                c.fill = GridBagConstraints.BOTH;
                c.gridy = i;
                c.gridx = j;
                c.gridheight = 1;
                if (buttons[i][j].getText().equals("0")) {
                    //buttons[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                    c.gridwidth = 2;
                }else{
                    c.gridwidth = 1;
                }
                c.ipadx = 0;
                c.ipady = 0;
                c.insets = new Insets(1, 1, 1, 1);
        
                buttonPanel.add(buttons[i][j], c);                
            }
        }

        for (int i = 0; i < buttons.length; i++) {
            buttons[i][buttons[0].length-1].setBackground(orangito);
        }
        for (int i = 0; i < buttons[0].length-1; i++) {
            buttons[0][i].setBackground(lightGray);
            buttons[0][i].setForeground(Color.black);
            buttons[0][i].setFont(new Font("Arial", Font.PLAIN, 13));
        }

        //style text field
        numField.setFont(new Font("Dialog", Font.PLAIN, 33));
        numField.setHorizontalAlignment(4);
        numField.setOpaque(false);
        numField.setBorder(new EmptyBorder(new Insets(0, 4, 6, 4)));
        numField.setBackground(Color.BLACK);
        numField.setForeground(Color.white);

        buttonPanel.setBackground(Color.BLACK);

        panel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        panel.setBackground(Color.black);       
        panel.add(numField);
        panel.add(buttonPanel);

        marco.setBackground(mandarin);

        marco.add(panel);

        execActions();
    }
}
