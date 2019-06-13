package Parallel;

import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.DoubleStream;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;



public class MyStream {
    // 生成Stream
    public static void handleMakeStream() {
        ArrayList<Stream<String>> list = new ArrayList<>();
        // 空流
        list.add(Stream.empty());
        // 由值创建流
        list.add(Stream.of("1", "2", "3", "4"));
        // 由数组创建刘
        list.add(Arrays.stream(new String[] {"1", "2", "3", "4"}));
        // 由文件创建流
        try {
            list.add(Files.lines(Paths.get("README.md"), Charset.defaultCharset()));
        } catch(IOException e) {

        }
        // 由集合创建
        list.stream();
        // 由函数创建流
        list.add(Stream.iterate("0", n -> "0" + n).limit(4));

        for (Stream<String> item: list) {
            item.forEach(System.out::print);
            System.out.println("");
        }
    }

    // 中间操作
    public static void handleChainStream() {
        Stream<String> stream = Stream.of("1", "2", "3", "4");

        // filter T -> boolean
        // map T -> R
        // skip
        // limit
        // sorted Comparator<T>(T, T) -> int
        // distinct

    }

    public static void handleTerminateStream() {
        // forEach
        // count
        //
        // allMatch / anyMatch / noneMatch
        // findFirst / findAny
        //
        // reduce
        //
        // collect 归约 & 高级归约
        // - java.util.stream.Collectors.*
        // - joining
        // - counting
        // - reducing
        // - groupingBy
        // - partitioningBy
        // - toCollection
        // - toSet
        // - toList
        //  - maxBy / minBy / averagingInt / summarizingInt
    }

    public static void handleCustomizeCollector() {
        Collector<String, List<String>, List<String>> collector = new Collector<>() {

            // Supplier: () -> T
            // BiConsumer: (T, A) -> void
            // BinaryOperator: (T, T) -> T
            // Function T -> R

            @Override
            public Supplier<List<String>> supplier() { return ArrayList<String>::new; }


            @Override
            public BiConsumer<List<String>, String> accumulator() {
                // return List::add;
                return (list, item) -> list.add(item);
            }

            @Override
            public BinaryOperator<List<String>> combiner() {
                return (list1, list2) -> {
                    list1.addAll(list2);
                    return list2;
                };
            }

            @Override
            public Function<List<String>, List<String>> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return new HashSet<Characteristics>();
            }
        };
    }

    public static void main(String[] args) {
        handleMakeStream();
        handleChainStream();
        handleTerminateStream();
        handleCustomizeCollector();
    }
}
