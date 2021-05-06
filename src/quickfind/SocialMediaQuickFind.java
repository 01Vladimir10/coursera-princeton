package quickfind;

import java.io.FileInputStream;
import java.util.HashMap;

public class SocialMediaQuickFind {
    private final HashMap<String, String> usersMap;
    private int totalUsers = 0;
    private int highestHeight = 1;
    private final HashMap<String, Integer> treesHeight;

    public SocialMediaQuickFind(String[] users) {
        this.usersMap = new HashMap<>();
        this.treesHeight = new HashMap<>();
        // init trees.
        for (String user : users){
            // check for invalid users or users that already exist.
            if(user.equals("") || usersMap.containsKey(user)) continue;
            totalUsers ++;
            // each tree references itself on the root.
            this.usersMap.put(user, user);
            // the default height of a tree is 1.
            this.treesHeight.put(user, 1);
        }

    }

    public void union(String p, String q) {
        // skip if both users are the same.
        if(p.equals(q)) return;
        String rootP = findRoot(p);
        String rootQ = findRoot(q);
        // skip if both users are already part of the same tree.
        if(rootP.equals(rootQ)) return;

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
        String parent = usersMap.get(user);
        if (user.equals(parent)) return user;
        // implement path compression....
        usersMap.put(user, usersMap.get(parent));
        return findRoot(parent);
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

    public boolean areAllConnected(){
        return totalUsers == highestHeight;
    }

    // receive file paths by argument, first file contains the users and the second file must contain the logs.
    public static void main(String[] args) {
        if(args == null || args.length < 2) throw new IllegalArgumentException("please provide the path to users list and the path to the log files");
        String usersPath = args[0];
        String logsPath = args[1];
        FileInputStream inputStream;
        SocialMediaQuickFind uf;

        try {
            System.out.println("Reading users...");
            // read users from log ile.
            inputStream = new FileInputStream(usersPath);
            String usersData = new String(inputStream.readAllBytes());
            // init data structure
            uf = new SocialMediaQuickFind(usersData.split("[\\r\\n]"));
            // print users count
            System.out.printf("I found %d users\n", uf.getTotalUsers());

            // read logs
            System.out.println("Reading logs...");
            inputStream = new FileInputStream(logsPath);
            String logsData = new String(inputStream.readAllBytes());
            String ts = "";
            // logs entries must be split by new lines.
            for (String x: logsData.split("\n")) {
                // each log entry has the following structure ts userA userB
                String[] log = x.split("\\s+");
                ts = log[0];
                String userA = log[1];
                String userB = log[2];

                uf.union(userA, userB);
                if(uf.areAllConnected()) break;
            }

            if (uf.areAllConnected())
                System.out.printf("All users were connected at %s\n", ts);
            else
                System.out.println("All users were never connected.");

        } catch (Exception e) {
            System.out.println("Something went wrong while parsing the data. => " + e.getMessage());
            e.printStackTrace();
        }

    }
}
