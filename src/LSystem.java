import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LSystem extends JFrame {
    private final static String inputFile = "INPUT.TXT"; // Файл с параметрами L-системы
    public final static int w  = 1000;
    public final static int h  = 600;

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //String commands = scanner.nextLine();

        // Загружаем параметры L-системы из файла
        BufferedReader rd = null;
        String commands = "";
        String [] left;
        String [] right;
        try {
            rd = new BufferedReader(new FileReader(new File(inputFile)));
            String initialCommands = rd.readLine();
            left = rd.readLine().split(",");
            right = rd.readLine().split(",");

            // Применяем набор правил к строке
            commands = apply(initialCommands, left, right);
        } catch(
            IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rd.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Рисуем черепашью графику
        JFrame frame = new JFrame("L-System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w,h);
        frame.setLocationRelativeTo(null);
        frame.add(new Tortoise(commands, 90, 1, 50, w / 2, h / 2, 0));
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    public static String apply(String str, String[] left, String[] right) {
        for (int i = 0; i < left.length; i++) {
            str = str.replace(left[i], right[i]);
        }
        return str;
    }
}