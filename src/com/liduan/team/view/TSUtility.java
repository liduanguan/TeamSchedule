package com.liduan.team.view;

import java.util.*;
/**
 * 
 * @Description TSUtility.java class,get input from keyword
 * @author shkstart  Email:shkstart@126.com
 * @version 
 *
 */
public class TSUtility {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * 
     * @Description This method reads the keyboard, and if the user types any character in '1'-'4', the method returns. The return value is the character typed by the user.
     * @author shkstart
     * @return
     */
	public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' &&
                c != '3' && c != '4') {
                System.out.print("Wrong Selection, Plesase input enter again：");
            } else break;
        }
        return c;
    }
	/**
	 * 
	 * @Description Prompt a message and wait user to press enter button
	 * @author shkstart
	 * @date 2019年2月12日上午12:03:50
	 */
    public static void readReturn() {
        System.out.print("Press enter to continue...");
        readKeyBoard(100, true);
    }
    /**
     * 
     * @Description Read an integer with a length of no more that 2 digits and return
     * @author shkstart
     * @date 2019年2月12日上午12:04:04
     * @return
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Wrong input, Please enter again：");
            }
        }
        return n;
    }
    /**
     * 
     * @Description Read ‘Y’ or ‘N’ from the keyboard and use it as the return value of the method.
     * @author shkstart
     * @date 2019年2月12日上午12:04:45
     * @return
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Selection error, please re-enter：");
            }
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("Input length(no more than " + limit + "）error,pleaes re-enter：");
                continue;
            }
            break;
        }

        return line;
    }
}

