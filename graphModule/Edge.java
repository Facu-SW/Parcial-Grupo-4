package graphModule;

public class Edge<T> {
    public int weight;
    public T destination = null;

    public Edge(T destination, int weight){
        if(destination == null) throw new IllegalArgumentException("Destination vertex cannot be null");
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object other){
        if(other.getClass() != getClass()) return false;
        @SuppressWarnings("unchecked")
        Edge<T> edge = (Edge<T>) other;
        if(destination != edge.destination) return false;
        if(weight != edge.weight) return false;
        return true;
    }
}
