package com.github.antksk.breakabletoy.encapsulation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static com.github.antksk.breakabletoy.encapsulation.StudentsData.students;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Slf4j
public class EncapsulationTest {

    public interface ToDisplay{
        String toDisplay();
    }
    static abstract class AbstractStudentFilter implements ToDisplay {
        protected final List<Student> studentList;
        protected AbstractStudentFilter(List<Student> students, Predicate<Student> filter){
            studentList = students.stream().filter(filter)
                    .collect(toList());
        }
        @Override
        public String toDisplay() {
            return studentList.stream()
                    .map(this::getDisplayData)
                    .collect(joining("\n"));
        }
        abstract String getDisplayData(Student s);
    }

    static final class ElementarySchool extends AbstractStudentFilter {
        ElementarySchool(List<Student> students){
            super(students, Student::isToAttendElementarySchool);
        }

        @Override
        public String getDisplayData(Student s) {
            return String.format("초등학생 [%s] %d", s.getName(), s.getAge());
        }
    }

    static final class MiddleSchool implements ToDisplay {
        private final List<Student> studentList;
        MiddleSchool(List<Student> students){
            studentList = students.stream()
                    .filter(Student::isToAttendMiddleSchool)
                    .collect(toList());
        }

        @Override
        public String toDisplay() {
            return studentList.stream()
                    .map(s->String.format("중학생 [%s] %d", s.getName(), s.getAge()))
                    .collect(joining("\n"));
        }
    }

    static final class StudentsWithPhones implements ToDisplay {
        private final List<Student> studentList;
        StudentsWithPhones(List<Student> students){
            studentList = students.stream().filter(Student::hasTel)
                    .collect(toList());
        }

        @Override
        public String toDisplay() {
            return studentList.stream()
                    .map(s->String.format("콜 ~ [%s]",s.getTel()))
                    .collect(joining("\n"));
        }
    }

    // [일시적으로 저장하는 쪽]
    static final class StudentProcessService {
        ToDisplay elementarySchool(){
            return new ElementarySchool(students);
        }
        ToDisplay middleSchool(){
            return new MiddleSchool(students);
        }
        ToDisplay studentsWithPhones() {
            return new StudentsWithPhones(students);
        }
    }

    // [사용하는 쪽]
    @Test
    public void elementarySchool() {
        log.debug("\n{}", new StudentProcessService().elementarySchool().toDisplay());
    }

    // [사용하는 쪽]
    @Test
    public void middleSchool() {
        log.debug("\n{}", new StudentProcessService().middleSchool().toDisplay());
    }

    // [사용하는 쪽]
    @Test
    public void sendTel() {
        log.debug("\n{}", new StudentProcessService().studentsWithPhones().toDisplay());
    }
}
