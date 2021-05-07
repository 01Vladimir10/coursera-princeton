package week2;

public class VideoAnswers {

    public static final int TRIALS = 7;
    public static void main(String[] args) {
        // Observation video
        System.out.println("Observation video");
        executeFunctions(1000, 7, new IFunction[]{
                n -> 3.3 * 0.0001 * n,              // 3.3 * 10 ^-4 * n
                n -> 5.0 * 0.000000001 * n * n,     // 5.5 * 10^-9 * n^2
                n -> 6.25 * 0.000000001 * n * n,    // 6.25 * 10^-9 * n^2
                n -> n * n,                         // n^2
        });
        System.out.println();

        // Mathematical Video
        /* Execution results:
            n: 100, accessTimes: 34650
            n: 200, accessTimes: 159200
            n: 400, accessTimes: 718200
            n: 800, accessTimes: 3196000
        */
        System.out.println("Mathematical video");
        executeFunctions(
                100,
                4,
                new IFunction[] {
                        n -> 3f * n * n,                        // ~3n^3
                        n -> (3f / 2f) * n * n * Math.log10(n), // ~ 3/2 * n^2 * log(n)
                        n -> (3f / 2f) * n * n * n,             // ~ 3/2 * n^2
                        n -> 3f * n * n * n,                    // 3 * n^3
                }
        );
    }

    private static void executeFunctions(double N, int trials, IFunction[] functions) {
        for (int i = 0; i < trials; i ++){
            System.out.printf("n: %.2f\t", N);
            for (IFunction function : functions) System.out.printf("%-10.5f\t", function.calculate(N));
            System.out.println();
            N *= 2;
        }
    }

    public interface IFunction {
        double calculate(double n);
    }
}
