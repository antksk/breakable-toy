package com.github.antksk.breakabletoy.encapsulation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.antksk.breakabletoy.encapsulation.StudentsData.students;

@Slf4j
public class BadEncapsulationTest {

    // [일시적으로 저장하는 쪽]
    static final class StudentProcessService {
        private final List<Student> studentList = students;
        List<Student> getStudents() {
            return studentList;
        }

        List<Student> elementarySchool(){
            List<Student> result = new ArrayList();
            for (Student s : studentList) {
                if (s.isToAttendElementarySchool()) {
                    result.add(s);
                }
            }
            return result;
        }
/*
        List<Student> getElementarySchool(){
            return studentList.stream().filter(s -> s.getAge() >= 8 && s.getAge() <= 13)
                    .collect(toList());
        }
 */
    }

    // [사용하는 쪽]
    @Test
    public void elementarySchool() {
        List<Student> students = new StudentProcessService().elementarySchool();
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
           sb.append(String.format("초등학생 [%s] %d\n", s.getName(), s.getAge()));
        }
        log.debug("\n{}", sb);
    }

    // [사용하는 쪽]
    @Test
    public void middleSchool() {
        List<Student> students = new StudentProcessService().getStudents();
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            if (s.getAge() >= 14 && s.getAge() <= 16) {
                sb.append(String.format("중학생 [%s] %d\n", s.getName(), s.getAge()));
            }
        }
        log.debug("\n{}", sb);
    }

    // [사용하는 쪽]
    @Test
    public void sendTel() {
        List<Student> students = new StudentProcessService().getStudents();
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            if (!"".equals(s.getTel())) {
                sb.append(String.format("콜 ~ [%s]\n", s.getTel()));
            }
        }
        log.debug("\n{}", sb);
    }
}
