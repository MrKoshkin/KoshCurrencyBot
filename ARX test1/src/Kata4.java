public class Kata4 {
    public static void main(String[] args) {
        makeReadable(3600);
    }
    public static String makeReadable(int seconds) {
        StringBuilder sb = new StringBuilder();
        if (seconds >= 3600 && seconds < 359999) {
            sb.append(seconds/3600);
        } else if (seconds > 60 && seconds < 3600) {
            int i = seconds / 60;
        }
        System.out.println(sb);
        return Integer.toString(seconds);
    }
}
