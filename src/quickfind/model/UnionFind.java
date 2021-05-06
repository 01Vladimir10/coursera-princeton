package quickfind.model;

public abstract class UnionFind {
    private final int length;
    protected final int[] items;

    public UnionFind(int length) {
        this.length = length;
        this.items = new int[length];
    }

    public int getLength() {
        return length;
    }

    public abstract void union(int p, int q);
    public abstract boolean connected(int p, int q);

    public int[] getItems() {
        return items;
    }
}
