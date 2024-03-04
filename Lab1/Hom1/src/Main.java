//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main <a> <b> <k>");
            return;
        }

        int a, b, k;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid integers for a, b, and k.");
            return;
        }

        if (a < 0 || b < 0 || k < 0) {
            System.out.println("a, b, and k should be non-negative integers.");
            return;
        }

        StringBuilder result = new StringBuilder();
        long startTime = System.nanoTime();

        for (int num = a; num <= b; num++) {
            int reducedNum = reduceToK(num, k);
            if (reducedNum == k) {
                result.append(num).append(" ");
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;


        System.out.println("K-Reductible Numbers in the interval [" + a + ", " + b + "] for k=" + k + ": " + result);
        System.out.println("Running time: " + elapsedTime + " nanoseconds");
    }

    private static int reduceToK(int num, int k) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                int digit = num % 10;
                sum += digit * digit;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }


}