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
        return countPaths(0,0, grid, new HashMap<>());
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

    public static int nonAdjecentSum(List<Integer> nums) {
        return nonAdjecentSum(nums, 0, new HashMap<>());
    }


    public static int nonAdjecentSum(List<Integer> nums, int i, HashMap<Integer, Integer> memo) {
        if (i > nums.size()) return 0;

        if (memo.containsKey(i)) return memo.get(i);

        var result = Math.max(
        nums.get(i) + nonAdjecentSum(nums, i + 2, memo),
                nonAdjecentSum(nums, i + 1, memo));

        memo.put(i, result);

        return result;
    }

    public static int sumSquares(int number) {
        return (int) sumSquares(number, new HashMap<>());
    }

    public static double sumSquares(int number, HashMap<Integer, Double> memo) {
        if (number == 0) return 0;

        if (memo.containsKey(number)) return memo.get(number);

        double minSquares = Double.POSITIVE_INFINITY;
        for (int i = 1; i <= Math.sqrt(number); i ++) {
            int square = i * i;
            double numSquares = 1 + sumSquares(number - square, memo);
            if (numSquares < minSquares) {
                minSquares = numSquares;
            }
        }
        double result = minSquares;
        memo.put(number, result);

        return result;
    }

    public static int countingChange(int amount, List<Integer> coins) {
        return countingChange(amount, 0, coins, new HashMap<>());
    }

    public static int countingChange(int amount, int coinIndex, List<Integer> coins, HashMap<List<Integer>, Integer> memo) {
        if (amount == 0) return 1;
        if (coinIndex >= coins.size()) {
            return 0;
        }

        List<Integer> key = List.of(amount, coinIndex);
        if (memo.containsKey(key)) return memo.get(key);

        int totalWays = 0;
        int value = coins.get(coinIndex);

        for (int qty = 0; qty * value <= amount; qty++) {
            int subAmount = amount - (qty * value);
            totalWays += countingChange(subAmount, coinIndex + 1, coins, memo);
        }

        memo.put(key, totalWays);
        return totalWays;
    }

    public static void main(String[] args) {
        System.out.println(fib(8));
        System.out.println(tribonacci(8));
        System.out.println(sumPossible(7, List.of(2, 3)));
        System.out.println(minChange(7, List.of(1, 5, 10, 25)));
        System.out.println(countPaths(List.of(
                List.of("O", "O", "O"),
                List.of("O", "X", "O"),
                List.of("O", "O", "O")
        )));
        System.out.println(maxPathSum(List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        )));
        System.out.println(nonAdjecentSum(List.of(1, 2, 3, 4, 5)));
        System.out.println(sumSquares(13));
        System.out.println(countingChange(4, List.of(1, 2, 3)));
        // Expected values
        // 21
        // 44
        // true
        // 3
        // 2
        // 2
        // 3
        // 2
        // 2
        // 2
    }
}