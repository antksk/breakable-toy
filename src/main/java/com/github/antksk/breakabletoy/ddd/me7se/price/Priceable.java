package com.github.antksk.breakabletoy.ddd.me7se.price;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import java.util.Optional;

public interface Priceable extends MerchandiseValueFactor<Long>{


    long getPrice();

    @Override
    default Long getValue() {
        return getPrice();
    }

    @Override
    default int compareTo(MerchandiseValueFactor<Long> o) {
        return compareToFactor(getValue() - o.getValue());
    }

    @Override
    default Optional<Priceable> getPriceable() {
        return Optional.of(this);
    }

    Type getType();

    enum Type{
          COST          /* 비용, 원가, 경비 */
        , SALES_AMOUNT         /* 매출액, 판매 금액 */
        , DISCOUNT      /* 할인 금액 */
        , RATE          /* 대금, 시세, 가격, 평가; 급료, 임금; 운송료, 운임 */
        , CHARGE        /* 청구 금액, 요금, 대금; 부채의 기입, 지불 계정의 기재, 외상; 비용, 경비 */
        , AMOUNT        /* 총액, 총계; 원리 합계 */
        , PENALTY       /* 벌금, 과료; 위약금 */
        , TOLL          /* 사용세, 요금 */
        , TAX           /** 세금 */
        , FORFEIT       /* 벌금, 과료; 추징금 */
    }
}
