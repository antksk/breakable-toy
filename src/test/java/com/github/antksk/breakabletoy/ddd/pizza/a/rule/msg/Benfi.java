package com.github.antksk.breakabletoy.ddd.pizza.a.rule.msg;

import java.util.Arrays;

import static com.github.antksk.breakabletoy.ddd.pizza.a.rule.msg.FormatString.formatString;
import static com.github.antksk.breakabletoy.ddd.pizza.a.rule.msg.StaticString.staticString;

public final class Benfi {
    public final Msg staticMessage = staticString("aaaa");
    public final FormatString.FormatStringBuilder formatingMessage = formatString("aa %s aaa %d");
    public final FormatString.FormatStringBuilder formatingMessage2 = formatString("aa %s aaa %d zzzz %s");
    public final String aaa = "aaaa %d, %s";

    public Msg formatingMessage2(String priceName, int price, String a3){
        return formatingMessage2
            .age(priceName).age(price).age(a3).build();
    }

    public Msg formatingMessage3(Object ... ages){
        return formatingMessage2.ages(Arrays.asList(ages)).build();
    }

    static final Benfi benfi = new Benfi();
}
