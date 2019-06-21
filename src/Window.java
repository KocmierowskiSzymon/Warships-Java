import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  autor: Kocmierowski Szymon
 */

/**
 * Klasa <code>Window</code> reprezentuje ramke ktora jest wyswitlana
 * w wyniku dzialania programu
 */

public class Window extends JFrame {
	public Window() {
		super("Warships");
		JPanel panel = new Game();
		add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
