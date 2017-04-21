import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class NumberText extends JFrame {
    JLabel l = new JLabel("Введите цифры:");
    JTextField tf = new JTextField(10);
    //Подпись над полем ввода
    JLabel podpis=new JLabel("Введите цифры:");
    //Поле ввода
    JTextField tablo=new JTextField(10);

    NumberText() {
        super("text");
        setLayout(new FlowLayout());
        // Вставляем фильтр вводимых символов
        //((PlainDocument) tf.getDocument()).setDocumentFilter(new NumberFilter());
        // Тут задаем цвет текста при выделении текста
        tablo.setSelectedTextColor(Color.red);
        // Тут задаем цвет фона при выделении текста
        tablo.setSelectionColor(Color.black);
        // Тут задаем цвет курсора
        tablo.setCaretColor(Color.white);
        //В чем смысл этой фигни
        //l.setLabelFor(tf);
        add(podpis);
        add(tablo);
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
