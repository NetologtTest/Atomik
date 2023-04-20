import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

    private static AtomicInteger Three = new AtomicInteger();
    private static AtomicInteger Four = new AtomicInteger();
    private static AtomicInteger Five = new AtomicInteger();

    public static boolean isPalindromeUsingIntStream(String text) { // ���������
        String temp = text.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, temp.length() / 2)
                .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
    }

    public static boolean isAlpha(String text) { // ���������
        char[] chars = text.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAlphabet(String text) { // ���������
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
                    Three.getAndAdd(1);
                }
                if (isPalindromeUsingIntStream(b)) {
                    Four.getAndAdd(1);
                }
                if (isPalindromeUsingIntStream(c)) {
                    Five.getAndAdd(1);
                }
            }).start();

            new Thread(() -> {
                if (isAlpha(a)) {
                    Three.getAndAdd(1);
                }

                if (isAlpha(b)) {
                    Four.getAndAdd(1);
                }

                if (isAlpha(c)) {
                    Five.getAndAdd(1);
                }
            }).start();

            new Thread(() -> {

                if (isAlphabet(a)) {
                    Three.getAndAdd(1);
                }
                if (isAlphabet(b)) {
                    Four.getAndAdd(1);
                }
                if (isAlphabet(c)) {
                    Five.getAndAdd(1);
                }


            }).start();
        }
        System.out.println("�������� ���� � ������ 3:" + Three + "��");
        System.out.println("�������� ���� � ������ 4:" + Four + "��");
        System.out.println("�������� ���� � ������ 5:" + Five + "��");

    }


}