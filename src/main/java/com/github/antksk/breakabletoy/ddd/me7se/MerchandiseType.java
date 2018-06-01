package com.github.antksk.breakabletoy.ddd.me7se;

public final class MerchandiseType {
    /**
     * 유형상품
     */
    enum Materiality{
        CUSTOMIZE, // 주문 제작
        IRREGULAR_SHAPES, // 불규칙한 모양(이형)을 가진 상품
    }

    /**
     * 무형상품
     */
    enum Immateriality {
        MULTIPLE_TIMES_TICKET, // 다회권
        DAY_TICKET // 당일권
    }
}
