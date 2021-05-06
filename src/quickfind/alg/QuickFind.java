package quickfind.alg;

import quickfind.model.UnionFind;

public class QuickFind extends UnionFind {
    public QuickFind(int length) {
        super(length);
        createDefaultComponents();
    }

    private void createDefaultComponents(){
        for(var i = 0; i < items.length; i++)
            items[i] = i;
    }

    @Override
    public void union(int p, int q) {
        //items are already in the same component.
        if(items[p] == items[q]) return;

        var newComponent = items[p];
        var currentComponent = items[q];

        for(var i = 0; i < items.length; i ++)
            if(items[i] == currentComponent)
                items[i] = newComponent;
    }

    @Override
    public boolean connected(int p, int q) {
        return items[p] == items[q];
    }
}
