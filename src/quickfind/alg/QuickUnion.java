package quickfind.alg;

import quickfind.model.UnionFind;

public class QuickUnion extends UnionFind {

    public QuickUnion(int length) {
        super(length);
        createDefaultComponents();
    }

    @Override
    public void union(int p, int q) {
        if(p == q) return;
        var rootP = findRoot(p);
        var rootQ = findRoot(q);
        items[rootP] = rootQ;
    }
    /**
     * Returns the root of the element x
     */
    private int findRoot(int x){
        return items[x] == x ? x : findRoot(items[x]);
    }

    @Override
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    private void createDefaultComponents(){
        for(var i = 0; i < items.length; i++)
            items[i] = i;
    }

}
