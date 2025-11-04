import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Tests {
    @Test public void test01() { test("input01.txt","output01.txt"); }
    @Test public void test02() { test("input02.txt","output02.txt"); }
    @Test public void test03() { test("input03.txt","output03.txt"); }
    @Test public void test04() { test("input04.txt","output04.txt"); }
    @Test public void test05() { test("input05.txt","output05.txt"); }
    @Test public void test06() { test("input06.txt","output06.txt"); }
    @Test public void test07() { test("input07.txt","output07.txt"); }
    @Test public void test08() { test("input08.txt","output08.txt"); }
    @Test public void test09() { test("input09.txt","output09.txt"); }
    @Test public void test10() { test("input10.txt","output10.txt"); }
    @Test public void test11() { test("input11.txt","output11.txt"); }
    @Test public void test12() { test("input12.txt","output12.txt"); }
    @Test public void test13() { test("input13.txt","output13.txt"); }
    @Test public void test14() { test("input14.txt","output14.txt"); }
    @Test public void test15() { test("input15.txt","output15.txt"); }
    @Test public void test16() { test("input16.txt","output16.txt"); }
    @Test public void test17() { test("input17.txt","output17.txt"); }
    @Test public void test18() { test("input18.txt","output18.txt"); }
    @Test public void test19() { test("input19.txt","output19.txt"); }
    @Test public void test20() { test("input20.txt","output20.txt"); }
    @Test public void test21() { test("input21.txt","output21.txt"); }
    @Test public void test22() { test("input22.txt","output22.txt"); }
    @Test public void test23() { test("input23.txt","output23.txt"); }
    @Test public void test24() { test("input24.txt","output24.txt"); }
    @Test public void test25() { test("input25.txt","output25.txt"); }
    @Test public void test26() { test("input26.txt","output26.txt"); }
    @Test public void test27() { test("input27.txt","output27.txt"); }
    @Test public void test28() { test("input28.txt","output28.txt"); }
    @Test public void test29() { test("input29.txt","output29.txt"); }
    @Test public void test30() { test("input30.txt","output30.txt"); }
    @Test public void test31() { test("input31.txt","output31.txt"); }
    @Test public void test32() { test("input32.txt","output32.txt"); }
    @Test public void test33() { test("input33.txt","output33.txt"); }
    @Test public void test34() { test("input34.txt","output34.txt"); }

    private static final File BASE = new File("Tests-P1.v3");

    private PrintStream consoleStream;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        consoleStream = System.out;
        System.setOut(new PrintStream(outContent));
    }

    public void test(String intput, String output) {
        test(new File(BASE, intput), new File(BASE, output));
    }

    public void test(File input, File output) {
        consoleStream.println("Testing!");
        consoleStream.println("Input: " + input.getAbsolutePath());
        consoleStream.println("Output: " + output.getAbsolutePath());

        String fullInput = "", fullOutput = "";
        try {
            fullInput = new String(Files.readAllBytes(input.toPath()));
            fullOutput = new String(Files.readAllBytes(output.toPath()));
            consoleStream.println("INPUT ============");
            consoleStream.println(new String(fullInput));
            consoleStream.println("OUTPUT ESPERADO =============");
            consoleStream.println(new String(fullOutput));
            consoleStream.println("OUTPUT =============");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro a ler o ficheiro");
        }

        try {
            Locale.setDefault(Locale.US);
            System.setIn(new FileInputStream(input));

            // Create an instance of your class and call the main method
            Object gameInstance = Class.forName("Main").newInstance();
            gameInstance.getClass().getMethod("main").invoke(gameInstance);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro no programa");
        } finally {
            byte[] outPrintBytes = outContent.toByteArray();
            consoleStream.println(new String(outPrintBytes));

            assertEquals(removeCarriages(fullOutput), removeCarriages(new String(outContent.toByteArray())));
        }
    }

    private static String removeCarriages (String s){
        return s.replaceAll("\r\n", "\n");
    }
}