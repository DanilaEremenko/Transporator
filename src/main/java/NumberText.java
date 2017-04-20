import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class NumberText extends JFrame {
    JTextField tf = new JTextField(10);
    JLabel l = new JLabel("Введите цифры:");

    NumberText() {
        super("text");
        setLayout(new FlowLayout());
        // Вставляем фильтр вводимых символов
        ((PlainDocument) tf.getDocument()).setDocumentFilter(new NumberFilter());
        // Текст будет выделяться только красным цветом
        tf.setSelectedTextColor(Color.red);
        // При выделении текста фона станет черным
        tf.setSelectionColor(Color.black);
        // Курсор будет белым
        tf.setCaretColor(Color.white);
        l.setLabelFor(tf);
        add(l);
        add(tf);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NumberText();
        System.out.println();

    }

}


// Фильтр вводимых данных
class NumberFilter extends DocumentFilter {
    // Переопределяем только один метод
    public void insertString(FilterBypass fb, int pos, String text, AttributeSet attr)
            throws BadLocationException {
        try {
            Integer.parseInt(text);
// Введена цифра?
        } catch (Exception e) {
// Если не цифра, то символ не вводим
            super.insertString(fb, 0, "", attr);
            return;
        }
        //Если введена цифра, то заносим ее в поле
        super.insertString(fb, pos, text, attr);

    }
}
