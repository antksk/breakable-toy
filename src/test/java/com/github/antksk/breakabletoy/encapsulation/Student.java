package com.github.antksk.breakabletoy.encapsulation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// [데이터]
@Getter
@EqualsAndHashCode(exclude = {"age", "tel"})
@RequiredArgsConstructor
final class Student {
    private final String name;
    private final int age;
    private final String tel;



    boolean isToAttendElementarySchool(){
        return getAge() >= 8 && getAge() <= 13;
    }

    boolean isToAttendMiddleSchool(){
        return getAge() >= 14 && getAge() <= 16;
    }

    boolean hasTel(){
        return !"".equals(getTel());
    }
}
