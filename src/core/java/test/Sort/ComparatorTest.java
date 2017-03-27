package core.java.test.Sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class ComparatorTest {

    public class Employee implements Comparable<Employee> {
        private int empId;
        private String name;
        private int age;

        public Employee(int empId, String name, int age) {
            this.empId = empId;
            this.name = name;
            this.age = age;
        }

        public int getEmpId() {
            return empId;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Employee o) {
            return empId - o.empId;
        }

    }

    public List<Employee> getEmployees() {
        List<Employee> col = new ArrayList<>();
        col.add(new Employee(5, "Frank", 28));
        col.add(new Employee(1, "Jorge", 19));
        col.add(new Employee(6, "Bill", 34));
        col.add(new Employee(3, "Michel", 10));
        col.add(new Employee(7, "Simpson", 8));
        col.add(new Employee(4, "Clerk",16 ));
        col.add(new Employee(8, "Lee", 40));
        col.add(new Employee(2, "Michel", 30));
        return col;
    }


    @Test
    public void testNaturalOrdering() {
        // java.util.Collections.sort(List) 和 java.util.Arrays.sort(Object[]) 方法被用来排列使用内在排序（natural ordering）方式的对象
        // Comparable.compareTo方法是Employee这个类的实例的内在排序
        // 所以如果是一个Employee对象的集合使用Collections.sort(list)来排序，排序会根据compareTo()方法里面定义的规则来完成
        List<Employee> list = getEmployees();
        Collections.sort(list);

        list.forEach(e -> System.out.println(e.getEmpId() + "\t" + e.getName() + "\t" + e.getAge()));
    }

    public class EmpSortByName implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    @Test
    public void testOtherOrdering() {
        // 如果你需要根据employee的其他字段来进行排序，我们需要修改Employee的类的compareTo()方法。但是我们也会因此而失去基于Employee的ID的排序机制。
        // 如果我们在不同的场合需要有不同的字段来进行排序的话，这样使用可能不是一个好的选择。
        // 这时候我们可以写一个类实现java.util.Comparator的接口，可以对Employees使用你希望使用的任何字段进行排序，而不需要对Employee的类本身进行任何改动。
        List<Employee> list = getEmployees();
        Collections.sort(list, new EmpSortByName()); // 使用Collections.sort()和
        Collections.sort(list, Employee::compareTo); // 使用Collections.sort()和method reference
        list.sort(Employee::compareTo); // 使用list.sort()和method reference
        list.sort((e1, e2) -> e1.getName().compareTo(e2.getName())); // 使用匿名内部类或lambda表达式
        list.sort(Comparator.comparing(Employee::getName)); // 使用Comparator.comparing()

        list.forEach(e -> System.out.println(e.getEmpId() + "\t" + e.getName() + "\t" + e.getAge()));
    }

    @Test
    public void testOtherOrderings() {
        // 职员同时使用name，age，id来进行排序（例如：当name相等的时候，接下来对比age，如果相等继续尝试id）
        List<Employee> list = getEmployees();
        list.sort((e1, e2) -> {
            if(e1.getName().equals(e2.getName())) {
                if(e1.getAge() == e2.getAge()) {
                    return e1.getEmpId() - e2.getEmpId();
                }
                else {
                    return e1.getAge() - e2.getAge();
                }
            }
            else {
                return e1.getName().compareTo(e2.getName());
            }
        });

        list.forEach(e -> System.out.println(e.getEmpId() + "\t" + e.getName() + "\t" + e.getAge()));
    }
}
