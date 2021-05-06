package quickfind.alg;

import quickfind.model.UnionFind;


/**
 * The approach to this is fairly simple, we keep track of the largest
 * elements of each component in a separate array.
 * */
public class QuickFindLargestElement extends UnionFind {
    private final int[] heights;
    private final int[] largestElements;

    public QuickFindLargestElement(int length) {
        super(length);
        heights = new int[length];
        largestElements = new int[length];
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
            largestElements[rootP] = Math.max(largestElements[rootP], largestElements[rootQ]);
        }
        else{
            items[rootP] = rootQ;
            heights[rootQ] += heights[rootP];
            largestElements[rootQ] = Math.max(largestElements[rootP], largestElements[rootQ]);
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

    public int find(int x){
        return largestElements[findRoot(x)];
    }

    @Override
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    private void createDefaultComponents(){
        for(var i = 0; i < items.length; i++){
            items[i] = largestElements[i] = i;
            heights[i] = 1;
        }
    }

    public int[] getItems() {
        return items;
    }

}
