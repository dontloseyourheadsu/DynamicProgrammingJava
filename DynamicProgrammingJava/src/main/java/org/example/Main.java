package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    public static boolean sumPossible(int amount, List<Integer> numbers) {
        return sumPossible(amount, numbers, new HashMap<>());
    }

    public static boolean sumPossible(int amount, List<Integer> numbers, HashMap<Integer, Boolean> memo) {
        if (amount == 0) return true;

        if (amount < 0) {
            return false;
        }

        if (memo.containsKey(amount)) {
            memo.put(amount, true);
            return memo.get(amount);
        }

        for (int num : numbers) {
            int subAmount = amount - num;
            if (sumPossible(subAmount, numbers))
                return true;
        }

        memo.put(amount, false);
        return false;
    }

    public static int minChange(int amount, List<Integer> coins) {
        return minChange(amount, coins, new HashMap<>());
    }

    public static int minChange(int amount, List<Integer> coins, HashMap<Integer, Integer> memo) {
        if (amount == 0) return 0;

        if (amount < 0) return -1;

        if (memo.containsKey(amount)) return memo.get(amount);

        int minCoins = -1;
        for (int coin: coins) {
            int subAmount = amount - coin;
            int subCoins = minChange(subAmount, coins, memo);
            if (subCoins != -1) {
                int numCoins = subCoins + 1;
                if (numCoins < minCoins || minCoins == -1) {
                    minCoins = numCoins;
                }
            }
        }

        memo.put(amount, minCoins);
        return minCoins;
    }

    public static int countPaths(List<List<String>> grid) {
        return countPaths(0,0, grid, new HashMap<>();
    }

    public static int countPaths(int r, int c, List<List<String>> grid, HashMap<List<Integer>, Integer> memo) {
        if (r == grid.size() || c == grid.get(0).size()) return 0;
        if (Objects.equals(grid.get(r).get(c), "X")) return 0;
        if (r == grid.size() - 1 && c == grid.get(0).size() - 1) return 1;

        List<Integer> pos = List.of(r, c);
        if (memo.containsKey(pos)) return memo.get(pos);

        int result = countPaths(r + 1, c, grid, memo) + countPaths(r + 1, c, grid, memo);
        memo.put(pos, result);
        return result;
    }

    public static int maxPathSum(List<List<Integer>> grid) {
        return (int) maxPathSum(0,0, grid, new HashMap<>());
    }

    public static double maxPathSum(int r, int c, List<List<Integer>> grid, HashMap<List<Integer>, Double> memo) {
        if (r == grid.size() || c == grid.get(0).size()) return Double.NEGATIVE_INFINITY;
        if (r == grid.size() - 1 && c == grid.get(0).size() - 1) return 1;

        List<Integer> pos = List.of(r, c);
        if (memo.containsKey(pos)) return memo.get(pos);

        double result = grid.get(r).get(c) +
                Math.max(
                maxPathSum(r + 1, c, grid, memo),
                maxPathSum(r + 1, c, grid, memo)
                );
        memo.put(pos, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(8));
        System.out.println(tribonacci(8));
    }
}