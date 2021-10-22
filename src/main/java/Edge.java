public class Edge {

    private int weight;
    private boolean isIncluded = false;
    private boolean isCounted = false;
    private boolean isBlocked = false;

    public Edge(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isIncluded() {
        return isIncluded;
    }

    public void setIncluded(boolean included) {
        isIncluded = included;
    }

    public boolean isCounted() {
        return isCounted;
    }

    public void setCounted(boolean counted) {
        isCounted = counted;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}