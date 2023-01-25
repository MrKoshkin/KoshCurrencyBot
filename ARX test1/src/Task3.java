import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Task3 {
    public static void main(String[] args) {
        System.out.println(toCamelCase("camel-case_loL"));
    }

    static String toCamelCase(String s) {
        ArrayList<Character> list = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            list.add(ch);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
//            if (i == 0) {
//                if (Character.isLowerCase(list.get(i))) {
//                    list.set(i, Character.toUpperCase(list.get(i)));
//                }
//            }
            if (list.get(i) == '-' || list.get(i) == '_') {
                list.set(i+1,Character.toUpperCase(list.get(i+1)));
                list.remove(i);
            }
            result.append(list.get(i));
        }

        return result.toString();
    }
}
