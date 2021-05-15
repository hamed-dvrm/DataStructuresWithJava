import javax.management.StandardEmitterMBean;
import javax.swing.event.ListDataEvent;
import java.util.*;

public class Graph {
    HashMap<GraphNode,LinkedList<Edge>> adjList;
    int size;

    public Graph(){
        adjList = new HashMap<>();
        size = 0;
    }

    public int getSize(){
        return size;
    }


    private static class Edge{
        int weight;
        GraphNode source;
        GraphNode dest;

        public Edge(GraphNode source , GraphNode destination ){
            weight = 0;
            this.source = source;
            this.dest = destination;
        }

        public Edge(GraphNode source, GraphNode dest , int weight){
            this.weight = weight;
            this.source = source;
            this.dest = dest;
        }

        public boolean equals(Object obj){
            if (obj == null)
                return false;
            if (!(obj instanceof Edge))
                return false;
            Edge temp = (Edge) obj;
            if ((this.source.equals(temp.source)) && (this.dest.equals(temp.dest)) && (this.weight == temp.weight))
                return true;
            return false;
        }

    }

    static  class GraphNode {
        String label;


        public GraphNode(){
            label  = null;
        }

        public GraphNode(String label){
             this.label = label;
        }

        public int hashCode(){
            return label.hashCode();
        }

        public boolean equals(Object obj){
            if (obj == this)
                return true;
            if (!(obj instanceof GraphNode)){
                return false;
            }
            GraphNode temp = (GraphNode) obj;
            if (temp.label.compareTo(this.label) == 0)
                return true;
            return false;

        }





    }

    public void addEdge(GraphNode source , GraphNode dest){
        if (!(adjList.containsKey(source)) || !(adjList.containsKey(dest))){
            System.out.print("The edge can not be added");
            return;
        }
        Edge tempEdge = new Edge(source,dest);
        addEdge(tempEdge);

    }

    public void addEdge(GraphNode sourceNode,GraphNode dest,int weight){
        if (!(adjList.containsKey(sourceNode)) || !(adjList.containsKey(dest))){
            System.out.print("The edge can not be added");
            return;
        }
        Edge tempEdge = new Edge(sourceNode,dest,weight);
        addEdge(tempEdge);

    }

    public void addNode(GraphNode node){
        if (!adjList.containsKey(node)) {
            adjList.put(node, new LinkedList<>());
            size++;
            return;
        }
        return;
    }

    public void BreadthFirstTraversal(GraphNode root)  {
        if (!adjList.containsKey(root)) {
            System.out.print("root is not here");
            return;
        }

        HashSet<GraphNode> visitedList = new HashSet<>();
        Queue<GraphNode> gQueue = new LinkedList<>();

        gQueue.add(root);
        visitedList.add(root);


        while (!gQueue.isEmpty()){
            GraphNode temp = gQueue.poll();
            LinkedList <Edge> lTemp = adjList.get(temp);
            processNode(temp);
            for (int i = 0 ; i < lTemp.size() ; i++){
                if (!visitedList.contains(lTemp.get(i).dest)) {
                    gQueue.add(lTemp.get(i).dest);
                    visitedList.add(lTemp.get(i).dest);
                }
            }


        }
        return;


    }

    public void ConnectedComponent(){
        HashSet<GraphNode> visitedList = new HashSet<>();
        int i = 0;
        for (GraphNode temp : adjList.keySet())
        {
            if (!visitedList.contains(temp)) {
                i++;
                System.out.print("Component" + i + " : ");
                BFS(temp, visitedList);
                System.out.println();

            }
        }
    }

    public boolean IsBiPartite(){
        HashMap<GraphNode,String> colorList = new HashMap<>();
        HashSet<GraphNode> visitedList = new HashSet<>();
        boolean isPartite = true;

        for (GraphNode temp : adjList.keySet()){
            if (!visitedList.contains(temp)){
                isPartite = bfsColoring(temp,colorList,visitedList);

            }

            if (isPartite == false)
                return false;


        }

        return true;


    }

    public void DepthFirstTraversal(GraphNode root){
        HashSet<GraphNode> visitedList = new HashSet<>();
        Stack<GraphNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()){
            GraphNode temp = stack.pop();
            LinkedList<Edge> lTemp = adjList.get(temp);
            if (!visitedList.contains(temp)){
                processNode(temp);
                visitedList.add(temp);
            }
            else
                continue;
            for (int i = 0 ; i <lTemp.size() ; i++){
                if (!visitedList.contains(lTemp.get(i).dest)){
                    stack.push(lTemp.get(i).dest);
                }
            }
        }
    }

    public boolean HasCycle(){
        HashMap<GraphNode,GraphNode> parentList = new HashMap<>();
        HashSet<GraphNode> visitedList = new HashSet<>();
        boolean hasCycle = false;

        for (GraphNode temp : adjList.keySet()){
            if (!visitedList.contains(temp))
                hasCycle = DFScycle(temp,parentList,visitedList);
            if (hasCycle)
                return true;

        }
        return false;

    }

    private boolean DFScycle(GraphNode node,HashMap<GraphNode,GraphNode> parentList , HashSet<GraphNode>  visitedList) {
        Stack<GraphNode> stack = new Stack<>();

        stack.push(node);

        while (!stack.isEmpty()) {
            GraphNode temp = stack.pop();
            LinkedList<Edge> lTemp = adjList.get(temp);
            if (!visitedList.contains(temp)) {
                visitedList.add(temp);
            } else {
                continue;
            }
            for (int i = 0; i < lTemp.size(); i++) {
                if (!visitedList.contains(lTemp.get(i))) {
                    stack.push(lTemp.get(i).dest);
                    if (!parentList.containsKey(lTemp.get(i).dest)) {
                        parentList.put(lTemp.get(i).dest, temp);
                    }
                } else if (!(lTemp.get(i).dest.equals(parentList.get(lTemp.get(i)))))
                    return true;

            }


        }
        return false;
    }

    private boolean bfsColoring(GraphNode node,HashMap<GraphNode,String> colorList,HashSet<GraphNode> visitedList){
        Queue<GraphNode> gQueue = new LinkedList<>();
        gQueue.add(node);
        visitedList.add(node);
        colorList.put(node,"blue");
        String prev;

        while (!gQueue.isEmpty()){
            GraphNode temp = gQueue.poll();
            prev = colorList.get(temp);
            LinkedList<Edge> lTemp = adjList.get(temp);
            for (int i = 0 ; i < lTemp.size() ; i++){

                if (!visitedList.contains(lTemp.get(i).dest)) {
                    gQueue.add(lTemp.get(i).dest);
                    visitedList.add(lTemp.get(i).dest);
                    colorList.put(lTemp.get(i).dest, null);
                    colorNode(colorList,lTemp.get(i).dest,prev);

                }
                else {
                    if (colorList.get(lTemp.get(i).dest).compareTo(prev) == 0){
                        return false;
                    }

                }
            }
        }
        return true;

    }

    private void colorNode(HashMap<GraphNode,String> colorList,GraphNode node , String prevColor){
        if (prevColor.compareTo("blue")== 0 ){
            colorList.put(node,"red");

        }
        else
            colorList.put(node,"blue");

    }

    private void BFS(GraphNode node , HashSet<GraphNode> visitedList){
        if (!adjList.containsKey(node)){
            System.out.print("The node is not here");
            return;
        }

        Queue<GraphNode> gQueue = new LinkedList<>();
        gQueue.add(node);
        visitedList.add(node);

        while (!gQueue.isEmpty()){
            GraphNode temp = gQueue.poll();
            processNode(temp);
            LinkedList<Edge> lTemp = adjList.get(temp);
            for (int i = 0 ; i < lTemp.size() ; i++){
                if (!visitedList.contains(lTemp.get(i).dest)){
                    gQueue.add(lTemp.get(i).dest);
                    visitedList.add(lTemp.get(i).dest);
                }
            }
        }
        return;
    }


    private void processNode(GraphNode node){
        System.out.print(node.label + ",");
        return;
    }

    private void addEdge(Edge temp){
       if (!adjList.get(temp.source).contains(temp)){
           adjList.get(temp.source).addFirst(temp);
       }
       Edge reversedEdge = new Edge(temp.dest,temp.source);
       if (!adjList.get(reversedEdge.source).contains(reversedEdge)){
           adjList.get(reversedEdge.source).addFirst(reversedEdge);
       }
    }



}
