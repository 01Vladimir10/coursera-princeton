package quickfind.alg;

import quickfind.model.UnionFind;

public class QuickUnionWeighted extends UnionFind {
    private final int[] heights;

    public QuickUnionWeighted(int length) {
        super(length);
        heights = new int[length];
        createDefaultComponents();
    }

    @Override
    public void union(int p, int q) {
        if(p == q) return;
        var rootP = findRoot(p);
        var rootQ = findRoot(q);

        if(heights[p] > heights[q]){
            items[rootQ] = rootP;
            heights[rootP] += heights[rootQ];
        }
        else{
            items[rootP] = rootQ;
            heights[rootQ] += heights[rootP];
        }
    }
    /**
     * This find root variant implements path compression
     */
    private int findRoot(int x){
        if(items[x] == x) return x;
        items[x] = items[items[x]];
        return findRoot(items[x]);
    }

    @Override
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    private void createDefaultComponents(){
        for(var i = 0; i < items.length; i++){
            items[i] = i;
            heights[i] = 1;
        }
    }

    public int[] getItems() {
        return items;
    }

}
