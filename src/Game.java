import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *  autor: Kocmierowski Szymon
 */

/**
* Obiekt <code> Board </code> posiada atrybuty dwoch plansz
* oraz metody okreslajace zasady gry 
*/

public class Game extends JPanel implements MouseListener{
	
    /**
    * Atrybut <code> Board1 </code> odpowiada za rozmieszczenie statkow 
    * i wykonanych akcji na planszy komputera
    */
	
	public int [][] Board1 = new int [][]{
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},		
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}
	};
	
    /**
    * Atrybut <code> Board2 </code> odpowiada za rozmieszczenie statkow 
    * i wykonanych akcji na planszy gracz
    */
	
	public int [][] Board2 = new int [][]{
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},		
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}
	};
	
    /**
    * Atrybut <code> row </code> odpowiada za przechowywanie 
    * wartosci wiersza, ktora bedzie wykorzystywana w metodach
    */
	
	int row;
	
    /**
    * Atrybut <code> col </code> odpowiada za przechowywanie 
    * wartosci kolumny, ktora bedzie wykorzystywana w metodach
    */
	
	int col;
	
    /**
    * Atrybut <code> moves </code> odpowiada za przechowywanie 
    * wartosci ilosci wykonanych ruchow przez gracz
    */
	
	int moves = 0;
	
    /**
    * Atrybut <code> enemyMoves </code> odpowiada za przechowywanie 
    * wartosci ilosci wykonanych ruchow przez komputer
    */
	
	int enemyMoves = 0;
	
    /**
     * Atrybut <code> random </code> umozliwiajacy losowe generowanie waratosci 
     */
	
	public Random random = new Random();
	
    /**
    * Flaga <code> enemyTurn </code> odpowiedajaca za ruch przeciwnika
    */
	
	public boolean enemyTurn = false;
	
    /**
    * Integer <code> ships </code> okresla liczbe statkow ktore mozna 
    * umiescic na planszy
    */
    public int ships = 5;

    /**
    * Integer <code> enemyShips </code> okresla liczbe statkow ktore mozna 
    * umiescic na planszy
    */
    
    public int enemyShips = 5;
    
    /**
    * Integer <code> strikes </code> zlicza liczbe wszystkich trafien 
    * w statki komputera 
    */
    public int strikes = 0;
    
    /**
    * Integer <code> strikes </code> zlicza liczbe wszystkich trafien 
    * w statki gracza 
    */
    
    public int enemyStrikes = 0;
    
    /**
    * Konstruktor <code> Game </code> definiuje rozmiar okna
    * i dodaje obiekt sledzacy akcje myszy
    */
    
    
	public Game() {
		addMouseListener(this);
		setPreferredSize(new Dimension(800, 800));
	}
	
    /**
    * Metoda <code> paintComponent </code> odpowiada za narysowanie plansz gracza
    * i komputera zgodnie z zadanymi macierzami oraz napisanie instrukcji
    * @param g - reprezentuje grafike, ktora jest wyswietlana na ekranie
    */
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Threa draw = new Threa(g, enemyTurn, moves, enemyMoves);
		draw.run();
		g.setColor(Color.BLACK);
		g.fillRect(49, 39, 311, 311);
		g.drawString("Gra w statki", 500, 100);
		g.drawString("LPM - Ustawienie statku w Pionie", 500, 200);
		g.drawString("PPM - Ustawienie statku w Poziomie", 500, 220);
	    g.drawString("Statki w pionie sa ustawiane", 500, 240);
	    g.drawString("od wybranego pola w dol.", 500, 260);
	    g.drawString("Statki w poziomie sa ustawiane", 500, 280);
	    g.drawString("od wybranego pola w prawo.", 500, 300);
	    g.drawString(strikes+" - Gracz vs Komputer - "+enemyStrikes, 500, 500);
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(Board1[i][j] == 0){
					g.setColor(Color.WHITE);
					g.fillRect(j*30+50+j, i*30+40+i, 30, 30);
				}
				
				if(Board1[i][j] == 1){
					g.setColor(Color.WHITE);
					g.fillRect(j*30+50+j, i*30+40+i, 30, 30);
				}
				
				if(Board1[i][j] == 3){
					g.setColor(Color.RED);
					g.fillRect(j*30+50+j, i*30+40+i, 30, 30);
				}
				
				if(Board1[i][j] == 4){
					g.setColor(Color.GRAY);
					g.fillRect(j*30+50+j, i*30+40+i, 30, 30);
				}
				
				if(Board1[i][j] == 9){
					g.setColor(Color.WHITE);
					g.fillRect(j*30+50+j, i*30+40+i, 30, 30);
				}
			}
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(49, 419, 311, 311);
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(Board2[i][j] == 0){
					g.setColor(Color.WHITE);
					g.fillRect(j*30+50+j, 400+i*30+20+i, 30, 30);
				}
				
				if(Board2[i][j] == 1){
					g.setColor(Color.BLUE);
					g.fillRect(j*30+50+j, 400+i*30+20+i, 30, 30);
				}
				
				if(Board2[i][j] == 3){
					g.setColor(Color.RED);
					g.fillRect(j*30+50+j, 400+i*30+20+i, 30, 30);
				}
				
				if(Board2[i][j] == 4){
					g.setColor(Color.GRAY);
					g.fillRect(j*30+50+j, 400+i*30+20+i, 30, 30);
				}
				
				if(Board2[i][j] == 9){
					g.setColor(Color.WHITE);
					g.fillRect(j*30+50+j, 400+i*30+20+i, 30, 30);
				}
			}			
		}
	}
	
    /**
    * Metoda <code> setShipHorizontal </code> odpowiada za umieszczenie statku w pozycji
    * poziomej na planszy gracza i oznaczenie pol sasiednich w macierzy
    * @param x - wartosc wiersza dziobu statku 
    * @param y - wartosc kolumny dziobu statku
    * @param z - dlugosc statku
    */
	
	public void setShipHorizontal(int x, int y, int z){
		for(int i=y;i<y+z;i++) {
			Board2[x][i] = 1;
			if((x!=0 && x!=9) && (y!=0 && y!=10-z)){
				Board2[x][y-1]=9;
				Board2[x-1][y-1]=9;
				Board2[x+1][y-1]=9;
				Board2[x-1][i]=9;
				Board2[x+1][i]=9;
				Board2[x][y+z]=9;
				Board2[x+1][y+z]=9;
				Board2[x-1][y+z]=9;
			}
			
			else if((x!=0 && x!=9) && y==0){
				Board2[x-1][i]=9;
				Board2[x+1][i]=9;
				Board2[x][y+z]=9;
				Board2[x+1][y+z]=9;
				Board2[x-1][y+z]=9;
			}
			
			else if(x==0 && (y==0)){
				Board2[x+1][i]=9;
				Board2[x][y+z]=9;
				Board2[x+1][y+z]=9;
			}
			
			else if(x==9 && (y==0)){
				Board2[x-1][i]=9;
				Board2[x][y+z]=9;
				Board2[x-1][y+z]=9;
			}
			
			else if((x!=0 && x!=9) && (y==10-z)){
				Board2[x][y-1]=9;
				Board2[x-1][y-1]=9;
				Board2[x+1][y-1]=9;
				Board2[x-1][i]=9;
				Board2[x+1][i]=9;
			}
			
			else if(x==0 && (y==10-z)){
				Board2[x][y-1]=9;
				Board2[x+1][y-1]=9;
				Board2[x+1][i]=9;
			}
			
			else if(x==9 && (y==10-z)){
				Board2[x][y-1]=9;
				Board2[x-1][y-1]=9;
				Board2[x-1][i]=9;
			}
			
			else if((x==9) && (y!=0 && y!=10-z)){
				Board2[x][y-1]=9;
				Board2[x-1][y-1]=9;
				Board2[x-1][i]=9;
				Board2[x][y+z]=9;
				Board2[x-1][y+z]=9;
			}
			
			else if((x==0) && (y!=0 && y!=10-z)){
				Board2[x][y-1]=9;
				Board2[x+1][y-1]=9;
				Board2[x+1][i]=9;
				Board2[x][y+z]=9;
				Board2[x+1][y+z]=9;
			}
		}
	}
	
    /**
    * Metoda <code> setShipVertical </code> odpowiada za umieszczenie statku w pozycji
    * pionowej na planszy gracza i oznaczenie pol sasiednich w macierzy
    * @param x - wartosc wiersza dziobu statku 
    * @param y - wartosc kolumny dziobu statku
    * @param z - dlugosc statku
    */
	
	public void setShipVertical(int x, int y, int z){
		for(int i=x;i<x+z;i++) {
			Board2[i][y] = 1;
			if((x!=0 && x!=10-z) && (y!=0 && y!=9)){
				Board2[x-1][y]=9;
				Board2[x-1][y+1]=9;
				Board2[x-1][y-1]=9;
				Board2[i][y-1]=9;
				Board2[i][y+1]=9;
				Board2[x+z][y]=9;
				Board2[x+z][y-1]=9;
				Board2[x+z][y+1]=9;
			}
			
			else if(x==0 && (y!=0 && y!=9)){
				Board2[i][y-1]=9;
				Board2[i][y+1]=9;
				Board2[x+z][y]=9;
				Board2[x+z][y-1]=9;
				Board2[x+z][y+1]=9;
			}
			
			else if(x==0 && (y==0)){
				Board2[i][y+1]=9;
				Board2[x+z][y]=9;
				Board2[x+z][y+1]=9;
			}
			
			else if(x==0 && (y==9)){
				Board2[i][y-1]=9;
				Board2[x+z][y]=9;
				Board2[x+z][y-1]=9;
			}
			
			else if(x==10-z && (y!=0 && y!=9)){
				Board2[x-1][y]=9;
				Board2[x-1][y+1]=9;
				Board2[x-1][y-1]=9;
				Board2[i][y-1]=9;
				Board2[i][y+1]=9;
			}
			
			else if(x==10-z && (y==0)){
				Board2[x-1][y]=9;
				Board2[x-1][y+1]=9;
				Board2[i][y+1]=9;
			}
			
			else if(x==10-z && (y==9)){
				Board2[x-1][y]=9;
				Board2[x-1][y-1]=9;
				Board2[i][y-1]=9;
			}
			
			else if((x!=0 && x!=10-z) && (y==0)){
				Board2[x-1][y]=9;
				Board2[x-1][y+1]=9;
				Board2[i][y+1]=9;
				Board2[x+z][y]=9;
				Board2[x+z][y+1]=9;
			}
			
			else if((x!=0 && x!=10-z) && (y==9)){
				Board2[x-1][y]=9;
				Board2[x-1][y-1]=9;
				Board2[i][y-1]=9;
				Board2[x+z][y]=9;
				Board2[x+z][y-1]=9;
			}
		}
	}
	
    /**
    * Metoda <code> setEnemyShipHorizontal </code> odpowiada za umieszczenie statku w pozycji
    * poziomej na planszy komputera i oznaczenie pol sasiednich w macierzy
    * @param x - wartosc wiersza dziobu statku 
    * @param y - wartosc kolumny dziobu statku
    * @param z - dlugosc statku
    */
	
	public void setEnemyShipHorizontal(int x, int y, int z){
		for(int i=y;i<y+z;i++) {
			Board1[x][i] = 1;
			if((x!=0 && x!=9) && (y!=0 && y!=10-z)){
				Board1[x][y-1]=9;
				Board1[x-1][y-1]=9;
				Board1[x+1][y-1]=9;
				Board1[x-1][i]=9;
				Board1[x+1][i]=9;
				Board1[x][y+z]=9;
				Board1[x+1][y+z]=9;
				Board1[x-1][y+z]=9;
			}
			
			else if((x!=0 && x!=9) && y==0){
				Board1[x-1][i]=9;
				Board1[x+1][i]=9;
				Board1[x][y+z]=9;
				Board1[x+1][y+z]=9;
				Board1[x-1][y+z]=9;
			}
			
			else if(x==0 && (y==0)){
				Board1[x+1][i]=9;
				Board1[x][y+z]=9;
				Board1[x+1][y+z]=9;
			}
			
			else if(x==9 && (y==0)){
				Board1[x-1][i]=9;
				Board1[x][y+z]=9;
				Board1[x-1][y+z]=9;
			}
			
			else if((x!=0 && x!=9) && (y==10-z)){
				Board1[x][y-1]=9;
				Board1[x-1][y-1]=9;
				Board1[x+1][y-1]=9;
				Board1[x-1][i]=9;
				Board1[x+1][i]=9;
			}
			
			else if(x==0 && (y==10-z)){
				Board1[x][y-1]=9;
				Board1[x+1][y-1]=9;
				Board1[x+1][i]=9;
			}
			
			else if(x==9 && (y==10-z)){
				Board1[x][y-1]=9;
				Board1[x-1][y-1]=9;
				Board1[x-1][i]=9;
			}
			
			else if((x==9) && (y!=0 && y!=10-z)){
				Board1[x][y-1]=9;
				Board1[x-1][y-1]=9;
				Board1[x-1][i]=9;
				Board1[x][y+z]=9;
				Board1[x-1][y+z]=9;
			}
			
			else if((x==0) && (y!=0 && y!=10-z)){
				Board1[x][y-1]=9;
				Board1[x+1][y-1]=9;
				Board1[x+1][i]=9;
				Board1[x][y+z]=9;
				Board1[x+1][y+z]=9;
			}
		}
	}
	
    /**
    * Metoda <code> setEnemyShipVertical </code> odpowiada za umieszczenie statku w pozycji
    * pionowej na planszy komputera i oznaczenie pol sasiednich w macierzy
    * @param x - wartosc wiersza dziobu statku 
    * @param y - wartosc kolumny dziobu statku
    * @param z - dlugosc statku
    */
	
	public void setEnemyShipVertical(int x, int y, int z){
		for(int i=x;i<x+z;i++) {
			Board1[i][y] = 1;
			if((x!=0 && x!=10-z) && (y!=0 && y!=9)){
				Board1[x-1][y]=9;
				Board1[x-1][y+1]=9;
				Board1[x-1][y-1]=9;
				Board1[i][y-1]=9;
				Board1[i][y+1]=9;
				Board1[x+z][y]=9;
				Board1[x+z][y-1]=9;
				Board1[x+z][y+1]=9;
			}
			
			else if(x==0 && (y!=0 && y!=9)){
				Board1[i][y-1]=9;
				Board1[i][y+1]=9;
				Board1[x+z][y]=9;
				Board1[x+z][y-1]=9;
				Board1[x+z][y+1]=9;
			}
			
			else if(x==0 && (y==0)){
				Board1[i][y+1]=9;
				Board1[x+z][y]=9;
				Board1[x+z][y+1]=9;
			}
			
			else if(x==0 && (y==9)){
				Board1[i][y-1]=9;
				Board1[x+z][y]=9;
				Board1[x+z][y-1]=9;
			}
			
			else if(x==10-z && (y!=0 && y!=9)){
				Board1[x-1][y]=9;
				Board1[x-1][y+1]=9;
				Board1[x-1][y-1]=9;
				Board1[i][y-1]=9;
				Board1[i][y+1]=9;
			}
			
			else if(x==10-z && (y==0)){
				Board1[x-1][y]=9;
				Board1[x-1][y+1]=9;
				Board1[i][y+1]=9;
			}
			
			else if(x==10-z && (y==9)){
				Board1[x-1][y]=9;
				Board1[x-1][y-1]=9;
				Board1[i][y-1]=9;
			}
			
			else if((x!=0 && x!=10-z) && (y==0)){
				Board1[x-1][y]=9;
				Board1[x-1][y+1]=9;
				Board1[i][y+1]=9;
				Board1[x+z][y]=9;
				Board1[x+z][y+1]=9;
			}
			
			else if((x!=0 && x!=10-z) && (y==9)){
				Board1[x-1][y]=9;
				Board1[x-1][y-1]=9;
				Board1[i][y-1]=9;
				Board1[x+z][y]=9;
				Board1[x+z][y-1]=9;
			}
		}
	}
	
    /**
    * Metoda <code> check </code> odpowiada za sprawdzenie, czy ukladany statek nie bedzie
    * sasiadowal z statkiem juz znajdujacy sie na planszy gracza
    * @param x - wartosc wiersza dziobu statku 
    * @param y - wartosc kolumny dziobu statku
    * @param z - dlugosc statku
    * @param vertical - ulozenie statku 
    * @return - wiadomosc czy mozna ustawic czy nie
    */
	
	public boolean check(int x, int y, int z, int vertical){
		if(vertical == 1){
			for(int i=y;i<y+z;i++){
				if(Board2[x][i] == 9){
					return false;
				}
			}
			return true;
		}
		
		else{
			for(int i=x;i<x+z;i++){
				if(Board2[i][y] == 9){
					return false;
				}
			}
			return true;
		}	
	}
	
    /**
    * Metoda <code> checkEnemy </code> odpowiada za sprawdzenie, czy ukladany statek nie bedzie
    * sasiadowal z statkiem juz znajdujacy sie na planszy komputera
    * @param x - wartosc wiersza dziobu statku 
    * @param y - wartosc kolumny dziobu statku
    * @param z - dlugosc statku
    * @param vertical - ulozenie statku 
    * @return - wiadomosc czy mozna ustawic czy nie
    */
	
	public boolean checkEnemy(int x, int y, int z, int vertical){
		if(vertical == 0){
			for(int i=y;i<y+z;i++){
				if(Board1[x][i] == 9){
					return false;
				}
			}
			return true;
		}
		
		else{
			for(int i=x;i<x+z;i++){
				if(Board1[i][y] == 9){
					return false;
				}
			}
			return true;
		}
	}
	
    /**
    * Metoda <code> cell </code> odpowiada za sprawdzenie ktore pole zostalo
    * wskazane przez gracza za pomoca myszy na planszy gracza
    * @param x - wartosc x lokalizacji myszy
    * @param y - wartosc y lokalizacji myszy
    */
	
	public void cell(int x, int y){
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(x>i*30+50+i&&x<i*30+50+i+30 && y>400+j*30+20+j && y<400+j*30+20+j+30){
					col=i+1;
					row=j+1;
				}
			}
		}
    }
	
    /**
    * Metoda <code> cellEnemy </code> odpowiada za sprawdzenie ktore pole zostalo
    * wskazane przez gracza za pomoca myszy na planszy komputera
    * @param x - wartosc x lokalizacji myszy
    * @param y - wartosc y lokalizacji myszy
    */
	
	public void cellEnemy(int x, int y)
    {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(x>i*30+50+i&&x<i*30+50+i+30 && y>j*30+20+j && y<j*30+20+j+30){
					col=i+1;
					row=j+1;
				}
			}
		}
    }

    /**
    * Metoda <code> checkMatrix </code> odpowiada za sprawdzenie wyswietlenie macierzy
    * planszy komputera w celu szybkiego sprawdzenia poprawnosci dzialania programu
    */
	
	public void checkMatrix(){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(Board1[i][j]+" ");
			}
			System.out.println();
		}
	}

    /**
    * Metoda <code>enemySet </code> odpowiada za wylosowanie ustawienia statku komputera
    * na planszy komputera
    */
	
	public void enemySet(){
    	if(ships == 0){
    		while (enemyShips != 0) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                int vertical = random.nextInt(2);
                boolean flaga = true;
                if(y+enemyShips<10 && Board1[x][y]!=1 && vertical==0){
                	flaga = checkEnemy(x, y, enemyShips, vertical);
                	if(flaga){
                		setEnemyShipHorizontal(x, y, enemyShips);
                		enemyShips--;
                	}
                }
                
                if(x+enemyShips<10 && Board1[x][y]!=1 && vertical==1){
                	flaga = checkEnemy(x, y, enemyShips, vertical);
                	if(flaga){
                		setEnemyShipVertical(x, y, enemyShips);
                		enemyShips--;
                	}
                }
    		
    		}
    		checkMatrix();
    	}
	}
	
    /**
    * Metoda <code>enemyTurn </code> odpowiada za przebieg tury komputera i w przypdku
    * zwyciestwa komputera wyswietlenie komunikatu o przegranej
    */
	
    public void enemyTurn() { 	
    	while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
    		
            if(Board2[x][y]==1){
            	Board2[x][y]=3;
            	enemyMoves++;
            	enemyStrikes++;
            	repaint();
            	if(enemyStrikes==15){
					JLabel text = new JLabel("!!!!!!!!!!!!!!!!!!YOU LOSE!!!!!!!!!!!!!!!!!!");
					JFrame win = new JFrame();
					win.add(text);
					win.setSize(200, 200);
					win.setVisible(true);
					break;
            	}
            }
            
            else if(Board2[x][y]==0 || Board2[x][y]==9){
            	Board2[x][y]=4;
            	enemyMoves++;
            	enemyTurn = false;
            	repaint();
            }
    	}
	}
	
    /**
    * Metoda <code>mouseClicked</code> odpowiada za dzialanie programu po 
    * wcisnieciu odpowiedniego przycisku myszy
    */
    
	@Override
    public void mouseClicked(MouseEvent e) 
    {
    	if(ships != 0){
    		boolean flaga = true;
    		cell(e.getX(), e.getY());
    		if(e.getButton() == 1){
    			if(col+ships-1<=10 && Board2[row-1][col-1]!=1){
    				flaga = check(row-1, col-1, ships, e.getButton());
    				if(flaga){
    					setShipHorizontal(row-1, col-1, ships);
    					repaint();
    					ships--;
    				}
    			}
    		}
        
    		else if(e.getButton() == 3){
    			if(row+ships-1<=10&&Board2[row-1][col-1]!=1){
    				flaga = check(row-1, col-1, ships, e.getButton());
    				if(flaga){
    					setShipVertical(row-1, col-1, ships);
    					repaint();
    					ships--;
    				}
        		}
    		}
    		enemySet();
    	}
    	
    	else{
    		if(!enemyTurn){
    			cellEnemy(e.getX(), e.getY());
    			if(e.getButton() == 1){
    				if(Board1[row-1][col-1]==1){
    					Board1[row-1][col-1]=3;
    					moves++;
    					strikes++;
    					repaint();
    					if(strikes==15){
    						JLabel text = new JLabel("!!!!!!!!!!!!!!!!!!YOU WIN!!!!!!!!!!!!!!!!!!");
    						JFrame win = new JFrame();
    						win.add(text);
    						win.setSize(200, 200);
    						win.setVisible(true);
    					}
    				}
                
    				else if(Board1[row-1][col-1]==0 || Board1[row-1][col-1]==9){
    					Board1[row-1][col-1]=4;
    					moves++;
    					enemyTurn = true;
    					repaint();
    					enemyTurn();
    				}
    			}
    		}
    	}
    }
    
    /**
    * Metoda <code>mousePressed</code> odpowiada za dzialanie programu po 
    * przytrzymaniu odpowiedniego przycisku myszy
    */
	
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
    * Metoda <code>mouseReleased</code> odpowiada za dzialanie programu po 
    * puszczeniu odpowiedniego przycisku myszy
    */
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
    * Metoda <code>mouseEntered</code> odpowiada za dzialanie programu po 
    * najechaniu mysz¹ na odpowiednie pole
    */
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    /**
    * Metoda <code>mouseExited</code> odpowiada za dzialanie programu po 
    * zjechaniu mysz¹ z odpowiedniego pola
    */
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}

