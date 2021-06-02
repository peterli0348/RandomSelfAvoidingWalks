import static java.lang.System.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        SAW[] threads = new SAW[Variable.threadCount];

        for (int thread = 0; thread < Variable.threadCount; thread++) {
            threads[thread] = (new SAW());
            threads[thread].start();
        }

        for (int thread = 0; thread < Variable.threadCount; thread++) {
            threads[thread].join();
        }

        for (int step = 0; step < Variable.maxSteps; step++) {
            for (int thread = 0; thread < Variable.threadCount; thread++) {
                Variable.data[0][step] += threads[thread].data[0][step];
                Variable.data[1][step] += threads[thread].data[1][step];
            }
        }

        if (Variable.polygon) {
            for (int step = 1; step < Variable.maxSteps; step = step + 2) {
                System.out.println(step + 1 + " "
                        + Variable.data[1][step] / (Variable.threadCount * Variable.walkCount));
            }
        } else {
            for (int step = 0; step < Variable.maxSteps; step++) {
                System.out.println(step + 1 + " "
                        + Variable.data[0][step] / Variable.data[1][step] + " "
                        + Variable.data[1][step] / (Variable.threadCount * Variable.walkCount) + " "
                        + Variable.data[1][step]);
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
