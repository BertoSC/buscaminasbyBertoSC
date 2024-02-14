package principal;
import buscaminas.BuscaMinas;
import java.util.Scanner;
public class InterfaceConsola {
    private BuscaMinas game;
    private Scanner entrada;
    private int fi;
    private int co;

    /** Constructor de la clase */
    public InterfaceConsola(){
        game= new BuscaMinas();
        entrada= new Scanner(System.in); 
        instrucciones();
        estructura();
    }

    /** Método de presentación de la partida */
    private void instrucciones(){
        System.out.println();
        System.out.println("                                    --BUSCAMINAS--");
        System.out.println("                                      by BertoSC ");
        System.out.println();
        System.out.println("¡Hola! Bienvenid@ a esta versión casera del clásico BUSCAMINAS.");
        System.out.println("Las reglas son sencillas:");
        System.out.println("1. Descubre las celdas tapadas (T) con una coordenada fila-columna (entre 1 y 10).");
        System.out.println("2. Si es una mina (identificada con un 9), pierdes la partida.");
        System.out.println("3. Si es una celda libre, un número de indicará el total de minas que limitan con la casilla.");
        System.out.println("4. Hay un total de 10 minas, pero en esta versión no hay banderillas para marcar las celdas sospechosas.");
        System.out.println("5. Ganarás la partida si destapas todas las celdas hasta que solo queden 10, que serán las minas tapadas.");
        System.out.println("¡BUENA SUERTE!");
        System.out.println();



    }

    /** Método que dialoga con el jugador y consulta los resultados de sus decisiones con la clase BuscaMinas*/
    private void estructura() {
        imprimirTablero(game.getTableroOc());
        System.out.println();
        System.out.print("Introduce un número de fila (entre 1 y 10): ");
        fi=entrada.nextInt()-1;
        System.out.print("Introduce un número de columna (entre 1 y 10): ");
        co=entrada.nextInt()-1;
        System.out.println();

        if (game.gameOver(fi, co)) {
            System.out.println();
            System.out.println("¡¡BOOM!!, HAS PERDIDO");
            System.out.println();
            imprimirTableroO(game.getTablero());
            System.out.println();

        } else {    
            while (game.verificarVic()==false) {
            game.destapar(fi, co);

                if(game.verificarVic()){
                    System.out.println();
                    System.out.println("GENIAL, HAS GANADO");
                    System.out.println();
                    imprimirTableroO(game.getTablero());
                    System.out.println();
                    break;
                }
                
                if (game.gameOver(fi, co)) {
                    System.out.println();
                    System.out.println("¡¡BOOM!!, HAS PERDIDO");
                    System.out.println();
                    imprimirTableroO(game.getTablero());
                    System.out.println();
                    break;
                }

            System.out.println();        
            imprimirTablero(game.getTableroOc());
            System.out.println();
            System.out.print("Introduce un número de fila (entre 1 y 10): ");
            fi=entrada.nextInt()-1;
            System.out.print("Introduce un número de columna (entre 1 y 10): ");
            co=entrada.nextInt()-1;
            System.out.println();
              
                
            }
        }



    }


    //* Método que se encarga de imprimir el tablero tapado*/
    private void imprimirTablero(String [][] tab){
        for (int i=0; i<tab.length; i++){
            for (int j=0; j<tab[i].length; j++){
                System.out.print("| "+tab[i][j]+"  |");
            }       
            System.out.println(); 
        }
          

    }
    //* Método que se encarga de imprimir el tablero descubierto */
    private void imprimirTableroO(int [][] tab){
        for (int i=0; i<tab.length; i++){
            for (int j=0; j<tab[i].length; j++){
                System.out.print("| "+tab[i][j]+"  |");
            }       
            System.out.println(); 
        }
          

    }
    public static void main(String[] args) throws Exception {
        new InterfaceConsola();
    }
}
