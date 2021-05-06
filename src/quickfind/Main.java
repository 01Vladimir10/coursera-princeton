package quickfind;

import quickfind.alg.QuickFindLargestElement;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var unionFind = new QuickFindLargestElement(100);

        unionFind.union(1, 3);
        unionFind.union(1, 8);
        unionFind.union(9, 1);
        unionFind.union(10, 9);
        unionFind.union(20, 3);
        unionFind.union(4, 30);
        unionFind.union(0, 18);

        int[] items = {3, 1, 9, 20, 10, 4, 30, 18, 0};
        for(var i : items)
            printf("%d -> %d\n", i, unionFind.find(i));
    }

    private static void print(Object text){
        System.out.println(text);
    }
    private static void println(Object text){
        System.out.println(text);
    }

    private static void printf(String format, Object... args){
        System.out.printf(format, args);
    }
}
