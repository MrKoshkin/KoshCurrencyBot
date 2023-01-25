public class Kata3 {
    public static void main(String[] args) {
        System.out.println(pigIt("Pig latin is cool !"));
    }
    public static String pigIt(String str) {
        String[] arr = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word: arr) {
            StringBuilder sb = new StringBuilder();
            char[] chars = word.toCharArray();
            for (int i = 1; i < chars.length; i++) {
                sb.append(chars[i]);
            }
            if (Character.isAlphabetic(chars[chars.length-1])) {
                sb.append(chars[0]);
                sb.append("ay");
                sb.append(" ");
            } else {
                sb.append(chars[chars.length-1]);
                sb.append(" ");
            }
            result.append(sb);
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }
}
