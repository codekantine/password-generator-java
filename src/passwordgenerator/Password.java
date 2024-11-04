package passwordgenerator;

import java.util.Random;

public class Password {

    private final Random random = new Random();
    private static final char[] SYMBOLS;
    private static String password;
    private int upper, lower, digit;

    static {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (char ch = '2'; ch <= '9'; ++ch) { // no 0 1
                tmp.append(ch);
            }
        }
        for (char ch = 'a'; ch <= 'k'; ++ch) { // no l
            tmp.append(ch);
        }
        for (char ch = 'm'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'A'; ch <= 'H'; ++ch) { // no I O
            tmp.append(ch);
        }
        for (char ch = 'J'; ch <= 'N'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'P'; ch <= 'Z'; ++ch) {
            tmp.append(ch);
        }
        SYMBOLS = tmp.toString().toCharArray();
    }

    public Password() {
        password = "";
        upper = lower = digit = 0;
        for (int i = 0; i < 4; i++) { // loop four times
            for (int j = 0; j < 3; j++) { // add three characters
                password += nextChar();
            }
            if (password.length() < 15) {
                password += "-"; // add a dash
            }
        }
    }

    private char nextChar() {
        char nextChar = SYMBOLS[random.nextInt(SYMBOLS.length)];
        int count = password.length() - password.replaceAll(Character.toString(nextChar), "").length(); // counts how often the char is already in use
        if (count > 1) {
            return nextChar(); // char is too often  in use -> recursive run
        }
        if (Character.isUpperCase(nextChar) && upper < 5) {
            upper++;
            return nextChar;
        } else if (Character.isLowerCase(nextChar) && lower < 5) {
            lower++;
            return nextChar;
        } else if (Character.isDigit(nextChar) && digit < 3 && password.length() != 0) {
            digit++;
            return nextChar;
        } else {
            return nextChar(); // too many chars of the same type or first char is a digit -> recursive run
        }
    }

    public static String getPassword() {
        return password;
    }
}
