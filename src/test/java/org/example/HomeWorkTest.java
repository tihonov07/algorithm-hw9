package org.example;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    @Test
    void dummy1() {
        runTest("upit.dummy.%s.1");
    }

    @Test
    void dummy2() {
        runTest("upit.dummy.%s.2");
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    })
    void upit(int num) {
        runTest("upit.%s." + num);
    }

    private void runTest(String pattern) {
        execute(pattern);
        assertRun(pattern);
    }

    @SneakyThrows
    private void assertRun(String pattern) {
        String answer = Files.readString(Path.of("target/" + String.format(pattern, "answer")));
        String out = Files.readString(Path.of("upit/" + String.format(pattern, "out")));
        assertEquals(out, answer);
    }

    @SneakyThrows
    private void execute(String pattern) {
        try (
                InputStream in = new FileInputStream("upit/" + String.format(pattern, "in"));
                OutputStream answer = new FileOutputStream("target/" + String.format(pattern, "answer"), false);
        ) {
            HomeWork hw = new HomeWork();
            hw.upit(in, answer);

        }

    }

}