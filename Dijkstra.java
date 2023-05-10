import java.util.*;

public class Dijkstra {
    String[] nomiNodi = {"Casa", "A", "B", "C", "D", "E", "Ufficio"};
    Vector<String> nodi;    //Vettore contenente i nomi dei nodi del percorso più efficente

    public int[] algoritmo(int[][] graph, int start){
        nodi = new Vector<String>();
        //Inizializzazione del vettore delle distanze dalla Casa ai nodi
        int[] dist = new int[graph.length];
        //Inizializzazione del vettore dei nodi visitati
        boolean[] visited = new boolean[graph.length];
        //Inizialmente la distanza di tutti i nodi è infinita...
        Arrays.fill(dist, Integer.MAX_VALUE);
        //...tranne per il nodo iniziale, che è a 0
        dist[start] = 0;

        for(int i=0; i<graph.length-1; i++){
            int tmp = -1;      //Selezione del nodo con la distanza minima
            for(int j=0; j<graph.length; j++){
                if(!visited[j] && (tmp == -1 || dist[j]<dist[tmp])) {
                    tmp = j;
                }
            }
            //Segna il nodo selezionato come visitato
            visited[tmp]=true;

            //Aggiornamento delle distanze dei nodi adiacenti
            for(int j=0; j<graph.length; j++){
                int alt = dist[tmp] + graph[tmp][j];
                if(!visited[j] && graph[tmp][j] != 0 && alt<dist[j]){
                    dist[j]=alt;
                }
            }
        }

        // determina il percorso dai nomi dei nodi

        int current = graph.length-1;   //QUello corrente è Ufficio
        while (current != start) {
            boolean bl = true;
            //Aggiunge il nome del nodo corrente al vettore dei nodi visitati
            nodi.add(0, nomiNodi[current]);
            //Cerca il nodo precedente al nodo corrente lungo il percorso minimo
            for (int j = 0; j < graph.length && bl; j++) {
                if (graph[j][current] > 0 && dist[current] == dist[j] + graph[j][current]) {
                    current = j;
                    bl = false;
                }
            }
        }
        //Aggiunge la partenza, cioè Casa
        nodi.add(0, nomiNodi[start]);

        //Restituisce il vettore delle distanze
        return dist;
    }

    public static void main(String[] args) {
        //Matrice di adiacenza
        int[][] grafico = {
                {0,2,0,0,8,0,0},
                {2,0,6,2,0,0,0},
                {0,6,0,0,0,0,5},
                {0,2,0,0,2,9,0},
                {8,0,0,2,0,3,0},
                {0,0,0,9,3,0,1},
                {0,0,5,0,0,1,0}
        };
        Dijkstra instance = new Dijkstra();
        int[] dist = instance.algoritmo(grafico, 0);
        //Stampa output
        System.out.println("Percorso: " + instance.nodi.toString());
        System.out.print("Distanza totale: " + dist[dist.length-1]);
    }
}