import java.awt.event.*;
import java.util.ArrayList;

public class ActionController implements ActionListener {
    PaintFrame viewPaintFrame;
    String dialog = "";
    boolean erase = false;
    String operand = "";
    String prevNum = "";
    String ansNum = "";

    public ActionController(PaintFrame vPaint) {
        viewPaintFrame = vPaint;
    }

    public void eventListener() {
        for (int i = 0; i < viewPaintFrame.buttons.length; i++) {
            for (int j = 0; j < viewPaintFrame.buttons[0].length; j++) {
                viewPaintFrame.buttons[i][j].addActionListener(this);
            }
        } 
    }

    private String Operations(String operand) {
        double num1 = Double.parseDouble(prevNum);
        double num2 = Double.parseDouble(dialog);
        double result = 0;

        if (operand.equals("+")) {
            result = num1 + num2;
        }else
        if (operand.equals("–")) {
            result = num1 - num2;
        }else
        if (operand.equals("×")) {
            result = num1 * num2;
        }else
        if (operand.equals("/")) {
            result = num1 / num2;
        }
        
        String strResult = String.valueOf(result);

        //Lógica para que el resultado nunca tenga mas caracteres de los que entran en el cuadro de texto
        if (strResult.length()>11 && strResult.indexOf(".")!=-1) {
            if (strResult.indexOf("E")!=-1){
                int numCola = strResult.length() - strResult.indexOf("E");
                return strResult.substring(0, 10-numCola) + strResult.substring(strResult.indexOf("E"), strResult.length());
            }else{
                return strResult.substring(0, 11);
            }
        }else if (strResult.substring(strResult.length()-2, strResult.length()).equals(".0")){
            return strResult.substring(0, strResult.length()-2);
        }

        return strResult;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        

       for (int i = 0; i < viewPaintFrame.buttons.length; i++) {
            for (int j = 0; j < viewPaintFrame.buttons[0].length; j++) {
                if(ae.getSource().equals(viewPaintFrame.buttons[i][j])){
                    if (viewPaintFrame.buttons[i][j].getText().matches("[\\d.]")) {  
                        if(erase){
                            dialog="";
                            erase=false;
                        }
                        if(dialog.length()<=10){
                            dialog = dialog + viewPaintFrame.buttons[i][j].getText();
                            viewPaintFrame.numField.setText(dialog);
                        }
                    }
                    if (viewPaintFrame.buttons[i][j].getText().matches("[+–×/]") && !dialog.equals(prevNum)) {
                        if (!prevNum.equals("")) {
                            dialog=Operations(operand);
                            viewPaintFrame.numField.setText(dialog);
                            
                        }                    
                        operand = viewPaintFrame.buttons[i][j].getText();
                        prevNum = dialog;
                        erase = true;
                    } 
                    if (viewPaintFrame.buttons[i][j].getText().equals("=")) {
                        dialog = Operations(operand);
                        viewPaintFrame.numField.setText(dialog);
                        ansNum = dialog;
                        prevNum = "";
                        erase = false;
                    }
                    
                    if (viewPaintFrame.buttons[i][j].getText().equals("AC")) {
                        dialog = String.valueOf("0");
                        viewPaintFrame.numField.setText(dialog);
                        prevNum = "";
                        erase=true;
                    }

                    if (viewPaintFrame.buttons[i][j].getText().equals("ans")) {
                        dialog = ansNum;
                        viewPaintFrame.numField.setText(dialog);
                        erase=true;
                    }
                    
                    if (viewPaintFrame.buttons[i][j].getText().equals("+/–")) {
                        if (Double.parseDouble(dialog)<0) {
                            dialog = dialog.substring(1, dialog.length());
                        }else if (Double.parseDouble(dialog)>=0) {
                            dialog = "-" + dialog;
                        }
                        
                        viewPaintFrame.numField.setText(dialog);
                        erase=true;
                    }   
                }                
            }
        }
    }
}
