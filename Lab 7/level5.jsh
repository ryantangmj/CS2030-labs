Log<Integer> sum(int n) {
    String output = "hit base case!\n";
    int sum = 0;

    for (int i = 1; i <= n; i++) {
        output += String.format("adding %d\n", i);
        sum += i;
    }

    return Log.<Integer>of(sum, output.trim());
}

Log<Integer> f(int n) {
   if (n == 1) {
      return Log.<Integer>of(n, "1");
   } else if (n % 2 == 0) {
      return Log.<Integer>of(n, String.format("%d / 2", n)).flatMap(x -> f(n / 2));
   } else {
      return Log.<Integer>of(n, String.format("3(%d) + 1", n)).flatMap(x -> f(3 * n + 1));
   }
}
