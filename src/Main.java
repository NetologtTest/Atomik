import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static AtomicInteger three = new AtomicInteger();
    private static AtomicInteger four = new AtomicInteger();
    private static AtomicInteger five = new AtomicInteger();

    public static boolean isPalindromeUsingIntStream(String text) { // похищенно
        StringBuilder reverse = new StringBuilder();
        String clean = text.replaceAll("\\s+", "").toLowerCase();
        char[] plain = clean.toCharArray();
        for (int i = plain.length - 1; i >= 0; i--) {
            reverse.append(plain[i]);
        }
        return (reverse.toString()).equals(clean);
    }

    public static boolean isAlpha(String text) { // похищенно
        char[] chars = text.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAlphabet(String text) { // похищенно
        int i = 0;
        boolean ascend = false;
        while (i < text.length() - 1) {
            if (text.charAt(i) <= text.charAt(i + 1))
                ascend = true;
            else {
                ascend = false;
                break;
            }
            i++;
        }
        return (ascend);

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            String a = generateText("abc", 3);
            String b = generateText("abc", 4);
            String c = generateText("abc", 5);

            new Thread(() -> {
                if (isPalindromeUsingIntStream(a)) {
                    three.getAndAdd(1);
                }
                if (isPalindromeUsingIntStream(b)) {
                    four.getAndAdd(1);
                }
                if (isPalindromeUsingIntStream(c)) {
                    five.getAndAdd(1);
                }
            }).start();

            new Thread(() -> {
                if (isAlpha(a)) {
                    three.getAndAdd(1);
                }

                if (isAlpha(b)) {
                    four.getAndAdd(1);
                }

                if (isAlpha(c)) {
                    five.getAndAdd(1);
                }
            }).start();

            new Thread(() -> {

                if (isAlphabet(a)) {
                    three.getAndAdd(1);
                }
                if (isAlphabet(b)) {
                    four.getAndAdd(1);
                }
                if (isAlphabet(c)) {
                    five.getAndAdd(1);
                }


            }).start();
        }
        System.out.println("Красивых слов с длиной 3:" + three + "шт");
        System.out.println("Красивых слов с длиной 4:" + four + "шт");
        System.out.println("Красивых слов с длиной 5:" + five + "шт");

    }


}
