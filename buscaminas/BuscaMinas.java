package buscaminas;
import java.util.Random;
/** Versión del clásico Buscaminas, en este caso, 10 minas en un tablero de 10 x 10. 
 *  Funciona como el juego original, salvo por las banderas: las minas se indican con un 9 y el resto de valores de las celdas 
 *  son o 0 o un número que indica la cantidad minas presentes en las celdas colindantes.
 * El jugador solo puede ver un tablero tapado mediante "T" y que se irá descubriendo a medida que el jugador indique posiciones de destape.
 * Si la celda escogida no tiene mina, el programa comprueba si se pueden destapar las ocho celdas colindantes. 
 */
public class BuscaMinas {
    private int [][] tablero;
    private String [][] tableroOc;
 
   /** Constructor del Buscaminas. Inicializa el tablero de juego en dos versiones: una destapada para colocar los valores y otra tapada 
    * que se devolverá a otra clase a medida que avance el juego
    */
    public BuscaMinas(){
        this.tablero = new int[10][10];
        this.tableroOc = new String[10][10];
        llenarT();
        colocarM(tablero);

    }

    /** getTablero: método que devuelve el tablero destapado para usar por otra clase */
    public int [][] getTablero(){
        int[][] tableroCopia = new int[10][10];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tableroCopia[i][j] = tablero[i][j];
            }
        }
        return tableroCopia;


    }

    /** getTableroOc: método que devuelve una copia del tablero tapado para usar por otra clase */
    public String [][] getTableroOc(){
        String[][] tableroOcCopia = new String [10][10];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tableroOcCopia[i][j] = tableroOc[i][j];
            }
        }
        return tableroOcCopia;


    }
   
   /** llenarT: método que se encarga de llenar los dos tableros de forma inicial */
    private void llenarT(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tableroOc[i][j] = "T";
                tablero[i][j] = 0;
            }
        }
    }

    /** colocarM: método para generar 10 minas de forma aleatoria en el tablero y calcular proximidad en celdas adyacentes */
    private void colocarM(int[][] tabla) {
        Random r = new Random();
        int minasColocadas = 0;
        
        while (minasColocadas < 10) {
            int fila = r.nextInt(10);
            int columna = r.nextInt(10);
            
            if (tabla[fila][columna] != 9) {
                tabla[fila][columna] = 9;
                minasColocadas++;
                
                for (int f = Math.max(0, fila - 1); f <= Math.min(9, fila + 1); f++) {
                    for (int c = Math.max(0, columna - 1); c <= Math.min(9, columna + 1); c++) {
                        if (tabla[f][c] != 9) {
                            tabla[f][c]++;
                        }
                    }
                }
            }
        }
    }


    /** destapar: método que destapa una celda en función de dos valores de entrada fila-columna */
    public void destapar(int f, int c) {
        if (gameOver(f, c) || !tableroOc[f][c].equals("T")) {
            return;
        }
    
        if (tablero[f][c] > 0) {
            tableroOc[f][c] = Integer.toString(tablero[f][c]);
            return; 
        }
    
        tableroOc[f][c] = Integer.toString(tablero[f][c]);
    
        for (int fO = Math.max(0, f - 1); fO < Math.min(tablero.length, f + 2); fO++) {
            for (int cO = Math.max(0, c - 1); cO < Math.min(tablero[f].length, c + 2); cO++) {
                if ((fO != f || cO != c) && tableroOc[fO][cO].equals("T")) {
                    destapar(fO, cO);
                }
            }
        }
    }
    
    
    /** verificarVic: método que verifica si el jugador ha encontrado las diez minas */
    public boolean verificarVic(){
        int minasCorrectas = 0;
        int totalCasillasT = 0;
    
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tableroOc[i][j].equals("T")) {
                    totalCasillasT++;
                    if (tablero[i][j] == 9) {
                        minasCorrectas++;
                    }
                }
            }
        }
    
       
        return minasCorrectas == 10 && totalCasillasT == 10;
    }

          
    



    /** gameOver: método que comprueba si la posición indicada por el jugador es una mina y devuelve true por fin de partida */  
    public boolean gameOver(int fila, int columna){
        if (tablero[fila][columna] == 9){
            return true;
        } else {
            return false;
        }
    }


}
