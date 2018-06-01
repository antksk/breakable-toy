package com.github.antksk.breakabletoy.ddd.me7se.price;

public interface Priceable{
    long getPrice();

    String toDisplayPrice();

    Type getType();

    enum Type{
          COST          /* 비용, 원가, 경비 */
        , VALUE         /* 가치, 값어치, 진가 */
        , RATE          /* 대금, 시세, 가격, 평가; 급료, 임금; 운송료, 운임 */
        , CHARGE        /* 청구 금액, 요금, 대금; 부채의 기입, 지불 계정의 기재, 외상; 비용, 경비 */
        , AMOUNT        /* 총액, 총계; 원리 합계 */
        , PENALTY       /* 벌금, 과료; 위약금 */
        , TOLL          /* 사용세, 요금 */
        , FORFEIT       /* 벌금, 과료; 추징금 */
    }
}
