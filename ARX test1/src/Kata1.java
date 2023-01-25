public class Kata1 {
    public static void main(String[] args) {
        for (int i = 0; i < 101; i++) {
            System.out.println("i = " + i + " " + isPrime(i));
        }
        System.out.println(isPrime(1572351769));
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num % 2 == 0 && num != 2) {
            return false;
        } else if (num == 2) {
            return true;
        }
        for (int k = 2; k < (int) Math.sqrt(num) + 1; k = k + 1) {
            if (num % k == 0) {
                return false;
            }
        }
        return true;
    }
}
