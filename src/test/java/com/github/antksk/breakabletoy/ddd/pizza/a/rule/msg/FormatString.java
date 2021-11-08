package com.github.antksk.breakabletoy.ddd.pizza.a.rule.msg;

import java.util.List;

import lombok.Builder;
import lombok.Singular;

@Builder
public final class FormatString implements Msg{
    private final String msg;
    @Singular
    private final List<Object> ages;

    public FormatString(String msg, List<Object> ages) {
        this.msg = msg;
        this.ages = ages;
    }

    public static FormatStringBuilder formatString(String msg) {
        return FormatString.builder().msg(msg);
    }

    @Override
    public String toMsg(){
        return String.format(msg, ages.toArray());
    }
}
