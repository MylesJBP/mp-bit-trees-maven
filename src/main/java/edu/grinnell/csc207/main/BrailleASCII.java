package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * A set of code for allowing a user to converts input between ASCII, Braille, and Unicode.
 *
 * @author Myles Bohrer-Purnell
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Gathers command-line input and converts specified input
   * into ASCII, Braille, or Unicode.
   * @param args input with conversion type and input to be converted
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != 2) {
      throw new Exception("Incorrect Number of Inputs.");
    } // if

    // check for valid conversion types
    switch (args[0]) {
      case "braille":
        for (int i = 0; i < args[1].length(); i++) {
          try {
            String tempHold = BrailleAsciiTables.toBraille(args[1].charAt(i));
            pen.printf(tempHold);
          } catch (Exception e) {
            pen.printf("\n");
            pen.println("Trouble Translation because " + e.getMessage());
            i = args[1].length();
          } // try/catch
        } // for
        pen.printf("\n");
        break;
      case "unicode":
        String resultString = "";
        for (int i = 0; i < args[1].length(); i++) {
          String tempHoldUni = BrailleAsciiTables.toBraille(args[1].charAt(i));
          resultString = resultString.concat(tempHoldUni);
        } // for
        String resultStr = BrailleAsciiTables.toUnicode(resultString);
        pen.println(resultStr);
        break;
      case "ascii":
        // check for correct braille input length
        if (args[1].length() % 6 != 0) {
          throw new Exception("Trouble Translating Because Bit Input Size Incorrect");
        } // if
        int startIndex = 0;
        int endIndex = 6;
        while (startIndex < args[1].length()) {
          try {
            String tempHoldAscii = BrailleAsciiTables
                                   .toAscii(args[1].substring(startIndex, endIndex));
            pen.printf(tempHoldAscii);
            startIndex = endIndex;
            endIndex += 6;
          } catch (Exception e) {
            pen.printf("\n");
            pen.println("Trouble translating because "
                        + e.getMessage());
            startIndex = args[1].length();
          } // try/catch
        } // while
        pen.printf("\n");
        break;
      default:
        throw new Exception("Incorrect Conversion Type, Acceptable Types"
                            + "are \"ascii\", \"braille\", and \"unicode\"");
    } // switch
  } // main(String[])
} // class BrailleASCII
