package core.java.test.java8;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class StreamTest {

    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        private Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "status=" + status +
                    ", points=" + points +
                    '}';
        }
    }

    private final Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13), new Task(Status.CLOSED, 8));

    @Test
    public void testUsage() {
    }

    @Test
    public void testExternalAndInternalIteration() {
        List<String> countries = Arrays.asList("France", "India", "China", "USA", "Germany");
        // External iteration
        for(int i = 0; i < countries.size(); ++i) {
            String country = countries.get(i);
            if(country.contains("i")) {
                System.out.println("Hello " + country + "!");
            }
        }
        // Internal iteration with enhanced for
        for(String country : countries) {
            if(country.contains("i")) {
                System.out.println("Hello " + country + "!");
            }
        }
        // Internal iteration with streams
        countries.forEach(System.out::println);
    }

    @Test
    public void testMapReduce() {
        // 所有状态为OPEN的任务一共有多少分数？
        long totalPoints = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println("total points of open tasks: " + totalPoints);
        List<String> list = new ArrayList<>();
//        list.re

        // 所有任务的分数和？
        totalPoints = tasks.stream()
                .parallel()
                .map(Task::getPoints)
                .reduce(0, Integer::sum);
        System.out.println("total points of all tasks: " + totalPoints);
    }

    @Test
    public void testCollect() {
        // group tasks by status
        Map<Status, List<Task>> map = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);
    }

    @Test
    public void testSort() {
        Arrays.asList("a", "b", "c").sort(Comparator.naturalOrder());
    }

    @Test
    public void testReadFile() throws IOException {
        Path path = new File("test.txt").toPath();
        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
        }
    }
}
