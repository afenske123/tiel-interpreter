package de.thm.asc.tiel.interpreter;

import org.junit.jupiter.api.Test;

class TiELTest {

    @Test
    void fibonacci() {
        var sourceCode = """
fun fib(n) {
    if n == 0 then {
        return 0;
    } else if n == 1 then {
        return 1;
    } else {
        return fib(n - 1) + fib(n - 2);
    }
}

print(fib(12));              
                """;
        var expectedOutput = "144%n";

        Assertions.assertPrintOutput(sourceCode, expectedOutput);
    }

    @Test
    void numbersAreFloatsInternally() {
        var source = """
            var a = 2;
            var b = 2.0;
            print(a == b);
            print(b);
        """;
        Assertions.assertPrintOutput(source, "true%n2%n");
    }

    //<< 02-design, unit-test, Test helloWorld()
    @Test
    void helloWorld() {
        var sourceCode = "print(\"Hello World!\");";
        var expectedOutput = "Hello World!%n";

        Assertions.assertPrintOutput(sourceCode, expectedOutput);
    }
    //>>

    @Test
    void unterminatedString() {
        var sourceCode = "var s = \"Hello World!";

        Assertions.assertScanningError(sourceCode, "Unterminated string.");
    }

    @Test
    void closure() {
        var sourceCode = "fun a(x) { fun b(y) {} }";

        Assertions.assertParsingError(sourceCode, "Expect expression.");
    }

    @Test
    void incompatibleTypes() {
        var sourceCode = "var x = 1 + \"A\";";

        Assertions.assertRuntimeError(sourceCode, "Operands to '+' must be numbers.");
    }

    @Test
    void doubleDeclaration() {
        var sourceCode = """
            var a = 5;
            var a = 4;
            """;

        Assertions.assertRuntimeError(sourceCode, "Identifier already declared 'a'.");
    }
}