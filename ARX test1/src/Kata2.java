public class Kata2 {
    public static void main(String[] args) {
        int[] arr = twoSum(new int[]{2, 2, 3}, 4);
        for (int number: arr) {
            System.out.println(number);
        }
    }
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if ( j != i) {
                    if (numbers[i] + numbers[j] == target) {
                        return new int[] {i ,j};
                    }
                }
            }
        }
        return null;
    }
}
