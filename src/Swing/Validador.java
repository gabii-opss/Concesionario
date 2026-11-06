package Swing;

import javax.swing.*;
import java.awt.*;

public class Validador {

    public static boolean esSoloLetras(char c) {
        return Character.isLetter(c) || Character.isWhitespace(c);
    }

    public static boolean esSoloNumeros(char c) {
        return Character.isDigit(c);
    }


}
