public class Main {

    public static void main(String... args){
        Graph myGraph = new Graph();
       myGraph.addNode(new Graph.GraphNode("1"));
        myGraph.addNode(new Graph.GraphNode("2"));
        myGraph.addNode(new Graph.GraphNode("3"));
        myGraph.addNode(new Graph.GraphNode("4"));
        myGraph.addNode(new Graph.GraphNode("5"));

        myGraph.addEdge(new Graph.GraphNode("1"),new Graph.GraphNode("2"));
        myGraph.addEdge(new Graph.GraphNode("1"),new Graph.GraphNode("3"));
        myGraph.addEdge(new Graph.GraphNode("2"),new Graph.GraphNode("4"));
        myGraph.addEdge(new Graph.GraphNode("3"),new Graph.GraphNode("5"));
        myGraph.addEdge(new Graph.GraphNode("4"),new Graph.GraphNode("5"));


        System.out.println(myGraph.IsBiPartite());


    }
}
