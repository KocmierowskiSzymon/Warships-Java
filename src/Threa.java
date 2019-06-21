import java.awt.*;

/**
 *  autor: Kocmierowski Szymon
 */

/**
 * Klasa <code>Threa</code> jest watkiem pobocznym, ktory ma na celu zliczanie
 * ruchow graczy i pokazywanie ktorega gracza jest obecnie ruch
 */

public class Threa extends Thread {
	
	/**
	 * Atrybut <code>g</code> odpowiada za wyswietlanie grafiki na ekranie
	 */
	
	Graphics g;
	
    /**
    * Flaga <code> enemyTurn </code> odpowiedajaca za ruch przeciwnika
    */
	
	boolean enemyTurn;
	
    /**
    * Atrybut <code> moves </code> odpowiada za przechowywanie 
    * wartosci ilosci wykonanych ruchow przez gracz
    */
	
	int moves;
	
    /**
    * Atrybut <code> enemyMoves </code> odpowiada za przechowywanie 
    * wartosci ilosci wykonanych ruchow przez komputer
    */
	
	int enemyMoves;
	
	/**
     * Konstruktor klasy <code>Threa</code>
     * @param g1 - reprezentuje grafike, ktora jest wyswietlana na ekranie
     * @param enemyTurn1 - reprezentuje czyja jest tura
     * @param moves1 - reprezentuje liczbe ruchow graczy
     * @param enemyMoves1 - reprezentuje liczbe ruchow komputera
     */
	
	public Threa(Graphics g1, boolean enemyTurn1, int moves1, int enemyMoves1){
		g=g1;
		enemyTurn = enemyTurn1;
		moves = moves1;
		enemyMoves = enemyMoves1;
	}

	 /**
     * Metoda <code>run</code> rysuje znak czyja jest tura i liczba ruchow wykonana
     * przez graczy
     */
	
	public void run() {
		if(enemyTurn){
			String ruchEnemy = (enemyMoves + " ");
			enemyMoves++;
			g.setColor(Color.RED);
			g.fillRect(700, 700, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("Enemy turn", 640, 715);
			g.drawString(ruchEnemy, 720, 715);
		}
		
		else{
			String ruch = (moves + " ");
			moves++;
			g.setColor(Color.GREEN);
			g.fillRect(700, 700, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("Your turn", 640, 715);
			g.drawString(ruch, 730, 715);
		}
	}
}
