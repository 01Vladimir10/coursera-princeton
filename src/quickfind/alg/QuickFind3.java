package quickfind.alg;

import quickfind.model.UnionFind;

/**
 * We need to build the tree so that the largest element
 * is always on the top, the is will increase the construction time
 * but it will significantly decrease the remove and query times.
 */
public class QuickFind3 extends UnionFind {
    public QuickFind3(int length) {
        super(length);
    }

    @Override
    public void union(int p, int q) {
        if(p > q) items[q] = items[p];
        else      items[p] = items[q];
    }
    private int findRoot(int i){
        return items[i] == i ? i : findRoot(items[i]);
    }

    @Override
    public boolean connected(int p, int q) {
        return false;
    }
}
