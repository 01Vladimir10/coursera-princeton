package quickfind;

import java.util.HashMap;

public class SocialMediaQuickFind {
    private final HashMap<String, String> usersMap;
    private final int totalUsers;
    private int highestHeight = 1;
    private final HashMap<String, Integer> treesHeight;

    public SocialMediaQuickFind(String[] users) {
        totalUsers = users.length;
        this.usersMap = new HashMap<>();
        this.treesHeight = new HashMap<>();
        // init trees.
        for (String user : users){
            // each tree references itself on the root.
            this.usersMap.put(user, user);
            // the default height of a tree is 1.
            this.treesHeight.put(user, 1);
        }

    }

    public void union(String p, String q) {
        if(p.equals(q)) return;
        String rootP = findRoot(p);
        String rootQ = findRoot(q);

        int newHeight = treesHeight.get(rootP) + treesHeight.get(rootQ);
        // Keep track of the highest tree.
        highestHeight = Math.max(highestHeight, newHeight);

        if (treesHeight.get(rootP) > treesHeight.get(rootQ)) {
            usersMap.put(rootQ, rootP);
            treesHeight.put(rootP, newHeight);
        }
        else {
            usersMap.put(rootP, rootQ);
            treesHeight.put(rootQ, newHeight);
        }
    }

    private String findRoot(String user){
        if(user.equals(usersMap.get(user))) return user;
        // implement path compression....
        usersMap.put(user, usersMap.get(usersMap.get(user)));
        return findRoot(usersMap.get(user));
    }

    public boolean connected(String p, String q) {
        return findRoot(p).equals(findRoot(q));
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getHighestHeight() {
        return highestHeight;
    }

    public boolean areAllconnected(){
        return totalUsers == highestHeight;
    }

    public static void main(String[] args) {

    }
}
