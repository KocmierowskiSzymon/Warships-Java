import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

/**
 *  autor: Kocmierowski Szymon
 */

/**
 * <code> Warships </code> - glowna klasa aplikacji
 */

public class Warships {
	
    /**
     * Metoda <code> main</code> glowne wywolanie programu
     * @param args strumien wyjsciowy
     */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Window();
			}
		});
}
}
