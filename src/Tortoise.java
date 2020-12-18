import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*; // for Ellipse2D
import java.util.Stack;

public class Tortoise extends JPanel implements ActionListener {
    Stack<Double>  stackAngle        = new Stack<>();
    Stack<Double>  stackCurrentAngle = new Stack<>();
    Stack<Float>   stackWidth        = new Stack<>();
    Stack<Double>  stackLength       = new Stack<>();
    Stack<Double>  stackX            = new Stack<>();
    Stack<Double>  stackY            = new Stack<>();
    Stack<Boolean> stackOpposite     = new Stack<>();

    private static double lengthFactor = 1;
    private static double angleIncrement = 15;

    // L-System vars
    private static String commands;
    private static double angle;
    private static float  width;
    private static double length;
    private static double x;
    private static double y;
    private static boolean opposite = false;
    private static double currentAngle;

    public Tortoise(String commands, double angle, float width, double length, double x, double y, double currentAngle) {
        this.commands = commands;
        this.angle = angle;
        this.width = width;
        this.length = length;
        this.x = x;
        this.y = y;
        this.currentAngle = currentAngle;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for (int i = 0; i <commands.length(); i++) {
            char ch = commands.charAt(i);
            switch (ch) {
                case 'F':
                    // Двигаемся вперед рисуя линию длины length
                    g2d.setStroke(new BasicStroke(width));
                    g2d.drawLine((int) x, (int) y, (int)(x + Math.cos(Math.toRadians(currentAngle)) * length),
                                 (int)(y + Math.sin(Math.toRadians(currentAngle)) * length));
                    x = x + Math.cos(Math.toRadians(currentAngle)) * length;
                    y = y + Math.sin(Math.toRadians(currentAngle)) * length;
                    break;
                case 'f':
                    // Двигаемся веред на length не рисуя линию
                    x = x + Math.cos(Math.toRadians(currentAngle)) * length;
                    y = y + Math.sin(Math.toRadians(currentAngle)) * length;
                    break;
                case '-':
                    // Поварачиваем налево на угол angle
                    if (!opposite) {
                        currentAngle += angle;
                    } else {
                        currentAngle -= angle;
                    }
                    break;
                case '+':
                    // Поворачиваем направо на угол angle
                    if (!opposite) {
                        currentAngle -= angle;
                    } else {
                        currentAngle += angle;
                    }
                    break;
                case  '|':
                    // Поварачиваем на 180'
                    currentAngle -= 180;
                    break;
                case '[':
                    // Запоминаем состояние в массив состояний
                    stackAngle.push(angle);
                    stackWidth.push(width);
                    stackLength.push(length);
                    stackX.push(x);
                    stackY.push(y);
                    stackOpposite.push(opposite);
                    stackCurrentAngle.push(currentAngle);
                    break;
                case ']':
                    // Вспоминаем последнее запомненное в массиве состояний состояние и удаляе его
                    angle = stackAngle.pop();
                    width = stackWidth.pop();
                    length = stackLength.pop();
                    x = stackX.pop();
                    y = stackY.pop();
                    opposite = stackOpposite.pop();
                    currentAngle = stackCurrentAngle.pop();
                    break;
                case '#':
                    // Увеличиваем ширину линии width на константу
                    width++;
                    break;
                case '!':
                    // Уменьшаем ширину линии  на константу
                    width--;
                    break;
                case '@':
                    // Рисуем круг радиусом в ширину линии width
                    Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, width);
                    g2d.fill(circle);
                    break;
                case '>':
                    // Умножить длину линии length на константу lengthFactor
                    length *= lengthFactor;
                    break;
                case '<':
                    // Разделить длину линии length на константу lengthFactor
                    length /= lengthFactor;
                    break;
                case '&':
                    // Изменить значение коммнд + и - на противоположные
                    opposite = true;
                    break;
                case '(':
                    // Уменьшить угол поворота angle на angleIncrement
                    angle -= angleIncrement;
                    break;
                case ')':
                    // Увеличить угол поворота angle на angleIncrement
                    angle += angleIncrement;
                    break;
                case 'R':
                    // Поделить width и angle на 1.456
                    angle /= 1.456;
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}