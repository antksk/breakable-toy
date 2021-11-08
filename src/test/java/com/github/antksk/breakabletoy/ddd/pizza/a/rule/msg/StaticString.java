package com.github.antksk.breakabletoy.ddd.pizza.a.rule.msg;

public class StaticString implements Msg {
    private final String msg;

    public StaticString(String msg) {
        this.msg = msg;
    }

    public static StaticString staticString(String msg){
        return new StaticString(msg);
    }

    @Override
    public String toMsg(){
        return msg;
    }
}
