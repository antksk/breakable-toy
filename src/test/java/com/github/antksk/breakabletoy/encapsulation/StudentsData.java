package com.github.antksk.breakabletoy.encapsulation;

import java.util.Arrays;
import java.util.List;

final class StudentsData {
    static List<Student> students = Arrays.asList(
            new Student("a", 5, ""),
            new Student("b", 7, "010-010-0100"),
            new Student("c", 8, ""),
            new Student("d", 13, "010-030-0300"),
            new Student("e", 14, "010-040-0400"),
            new Student("f", 16, "010-050-0500")
    );
}
