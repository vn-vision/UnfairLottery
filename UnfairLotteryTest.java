/**
 * UnfairLotteryTest - test distributes prizes to winners based on concept of "fair"
 * testreverse - this function tests reverse of a sorted array
 * testPrizeDistributor - tests the concept of fair:
 *      -uses the reversed array to distribute prizes to winners
 *      the function aims to distribute the prizes to winners such that 
 *      there is minimal difference between different winners
 *      the goal is to achive an optimum choice: "FAIR"
 */
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class UnfairLotteryTest {
    @Test
    public void testPrizeDistributor(){
        // test the distributor function
        // equal outcome
        int[] prizes = {1000, 200, 100, 800, 500, 400};
        String[] winners = {"Joshua", "Mahesh", "Lilian"};
        Map<String, List<Integer>> exp_result = new HashMap<>();
        exp_result.put("Joshua", Arrays.asList(1000));
        exp_result.put("Mahesh", Arrays.asList(800, 200));
        exp_result.put("Lilian", Arrays.asList(500, 400, 100));

        Map<String, List<Integer>> result = UnfairLottery.PrizeDistributor(prizes, winners);
        assertEquals(exp_result, result);

        // "fair" outcome
        int[] prizes2 = {400, 400, 500, 600};
        String[] winners2 = {"Barry", "Sheila", "Onyango", "Wekesa"};
        Map<String, List<Integer>> exp_result_2 = new HashMap<>();
        exp_result_2.put("Barry", Arrays.asList(600));
        exp_result_2.put("Sheila", Arrays.asList(500));
        exp_result_2.put("Onyango", Arrays.asList(400));
        exp_result_2.put("Wekesa", Arrays.asList(400));

        Map<String, List<Integer>> result2 = UnfairLottery.PrizeDistributor(prizes2, winners2);
        assertEquals(exp_result_2, result2);
    }

     @Test
    public void testReverse() {
        // does the function actually reverse
        int[] prizes = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};
        UnfairLottery.reverse(prizes);
        assertArrayEquals(expected, prizes);
    }
}
