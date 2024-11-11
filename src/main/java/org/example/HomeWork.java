package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу UPIT из файла contest7_tasks.pdf
     */
    @SneakyThrows
    public void upit(InputStream in, OutputStream out) {
        var treap = new Treap();
        try (var br = new BufferedReader(new InputStreamReader(in)); var ps = new PrintStream(out)) {
            Map<Integer, Consumer<List<Integer>>> operations = Map.of(
                    1, input -> treap.rangeToValue(input.get(1) - 1, input.get(2), input.get(3)),
                    2, input -> treap.addRange(input.get(1) - 1, input.get(2), input.get(3)),
                    3, input -> treap.add(input.get(1) - 1, input.get(2)),
                    4, input -> ps.println(treap.getSumOfValues(input.get(1) - 1, input.get(2)))
            );

            var firstLine = br.readLine();
            Stream.of(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .forEach(treap::add);
            br.lines().forEach(line -> {
                var parsed = Stream.of(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                operations.get(parsed.get(0)).accept(parsed);
            });
        }

    }

}
