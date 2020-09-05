package machura.przemyslaw.informationmanagerdomain.tmp;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TmpTest {
    public static int solution(int[] A) {

        int[] sortedWithPositives = Arrays.stream(A)
                .parallel()
                .filter(value -> value > 0)
                .distinct()
                .sorted()
                .toArray();

        show(sortedWithPositives);

        for (int i = 1; i <= sortedWithPositives.length; i++) {
            System.out.println("i " + i  + "   array[i]: " + sortedWithPositives[i]);
            if (i != sortedWithPositives[i -1]) {
                System.out.println("Result i " + i);
                return i;
            }
        }

        int result = sortedWithPositives.length > 0 ? sortedWithPositives.length : 1;
        System.out.println("Result" + result);
        return result;
    }

    private static void show(int[] sortedWithPositives) {
        for(int i = 0; i < sortedWithPositives.length; i++){
            System.out.println(sortedWithPositives[i]);
        }
    }

    public static void main(String[] args) {
        solution(new int[]{1,4,5, -2, -3});
    }
}
