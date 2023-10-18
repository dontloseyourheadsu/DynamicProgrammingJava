package org.example;

import java.util.HashMap;

public class Main {

    public static int fib(int n) {
        return fib(n, new HashMap<>());
    }
    public static int fib(int n, HashMap<Integer, Integer> memo) {
        if (n == 0 || n == 1) return n;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = fib(n - 1, memo) + fib(n - 2, memo);
        memo.put(n, result);

        return result;
    }

    public static int tribonacci(int n) {
        return tribonacci(n, new HashMap<>());
    }

    public static int tribonacci(int n, HashMap<Integer, Integer> memo) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 1;

        if (memo.containsKey(n)) return memo.get(n);

        int result = tribonacci(n - 1, memo) + tribonacci(n - 2, memo) + tribonacci(n - 3, memo);
        memo.put(n, result);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(8));
        System.out.println(tribonacci(8));
    }
}