/**
 * UnfairLottery - distributes prizes to winners based on concept of "fair"
 * reverse - this function reverses a sorted array
 * PrizeDistributor - uses the reversed array to distribute prizes to winners
 *      - the function aims to distribute the prizes to winners such that 
 *      there is minimal difference between different winners
 *      the goal is to achive an optimum choice: "FAIR"
 */
import java.util.*;


public class UnfairLottery{
    public static void main(String[] args){
        try (Scanner sc = new Scanner(System.in)) {
            String pz_line;
            String wn_line;
            String [] pz_line_split;
            int [] prizes;
            String [] winners;

            // read the entire line
            System.out.println("Prizes won: ");
            pz_line = sc.nextLine();
            System.out.println("Winners: ");
            wn_line = sc.nextLine();

            // Split the input prizes with , and optional space
            pz_line_split = pz_line.split(",\\s*");
            winners = wn_line.split(",\\s*");

            // Assign values to their arrays
            // parse prizes to int
            prizes = new int[pz_line_split.length];
   

            for (int i=0; i<prizes.length; i++){
                prizes[i] = Integer.parseInt(pz_line_split[i]);
            }
            // sort the list
            Arrays.sort(prizes);
            reverse(prizes);

            // distribute prizes
            Map<String, List<Integer>> distribution = PrizeDistributor(prizes, winners);
            for (String winner: winners){
                // display the results
                List<Integer> prize_list = distribution.get(winner);
                String won_prize_list = String.join(",", prize_list.stream().map(String::valueOf)
                .toArray(String[]::new)
                );
                System.out.println(winner + ": " + won_prize_list);
            }
        } catch (NumberFormatException e) {
            // remove the Scanner close exception
            e.printStackTrace();
        }
    }


    private static void reverse(int[] prizes) {
        // reverse the sorted list
        int left = 0, right = prizes.length - 1;
        while (left < right){
            int temp = prizes[left];
            prizes[left] = prizes[right];
            prizes[right] = temp;
            left++;
            right--;
        }
    }


    static Map<String, List<Integer>> PrizeDistributor(int[] prizes, String[] winners) {
        // maps the winner to their prizes based on 'fairness'
        int n = winners.length;
        Map<String, List<Integer>> distribution = new HashMap<>();
        int[] ttl_values = new int[n];

        // initialize distribution map
        for (String winner: winners){
            distribution.put(winner, new ArrayList<>());
        }

        // distribute prizes to the different winners
        // goal is to be "fair"

        for (int prize: prizes){
            // find the winner with smallest value, find their index
            int min_prize_id = 0;
            for (int i = 1; i < n; i++){
                if (ttl_values[i] < ttl_values[min_prize_id]){
                    min_prize_id = i;
                }
            }
            // Assign the prize to the selected winner
            String selected_winner = winners[min_prize_id];
            distribution.get(selected_winner).add(prize);
            ttl_values[min_prize_id] += prize;
        }
        return distribution;
    }
}