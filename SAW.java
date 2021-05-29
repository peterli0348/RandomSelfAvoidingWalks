import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

//SAW (Self Avoiding Walk)
public class SAW extends Thread {
    private HashSet<Integer> visited;
    private int[] vertex;
    public double[][] data;

    public void run() {
        int currentStep;
        for (int walk = 0; walk < Variable.walkCount; walk++) {
            resetWalk();
            currentStep = 0;
            while (currentStep < Variable.maxSteps) {
                walkStep();
                if (!visited.contains(Arrays.hashCode(vertex))) {
                    if(Variable.polygon && isOrigin()) {
                        data[1][currentStep]++;
                        break;
                    } else {
                        data[0][currentStep] += getDistance();
                        data[1][currentStep]++;
                    }
                    visited.add(Arrays.hashCode(vertex));
                } else break;
                currentStep++;
            }
        }
    }

    public SAW() {
        visited = new HashSet<Integer>();
        vertex = new int[Variable.dimensions];
        data = new double[2][Variable.maxSteps];
    }

    private void walkStep() {
        int random = ThreadLocalRandom.current().nextInt(2 * Variable.dimensions);
        if (random < Variable.dimensions) vertex[random % Variable.dimensions]--;
        else vertex[random % Variable.dimensions]++;
    }

    private int getDistance() {
        int distance = 0;
        for (int axis = 0; axis < Variable.dimensions; axis++) {
            distance += vertex[axis] * vertex[axis];
        }
        return distance;
    }

    private boolean isOrigin() {
        for (int axis = 0; axis < Variable.dimensions; axis++) {
            if (vertex[axis] != 0) return false;
        }
        return true;
    }

    private void resetWalk() {
        visited.clear();
        vertex = new int[Variable.dimensions];
        if (!Variable.polygon) visited.add(Arrays.hashCode(vertex));
    }
}
