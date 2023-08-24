import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Main {
    public static int speedLimit(final int speed, final int[] signals) {
        return IntStream.of(signals)
                .map(e->speed-e)
                .filter(e->e!=0)
                .map(e->e>10? e < 20? 100 : e<30? 250 : 500 : 0).sum();
    }
    public static void main(String[] args) {

    }
}
