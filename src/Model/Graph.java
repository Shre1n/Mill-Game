package Model;

public class Graph {

    private boolean adjMatrix[][];

    private int numVertices;

    Graph(int numVertices){
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    //Add Edges
    public void addEdges(int i, int j){
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    //Remove Edges
    public void removeEdges(int i, int j){
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }


}
