package com.github.antksk.breakabletoy.ddd.me7se;

import java.time.temporal.Temporal;

public interface Product extends Merchandible {

    /**
     * 제품 생산에 필요한 시간(상품 생산 시작부터 완성까지 걸리는 시간)
     * @return
     */
    Temporal getLeadTime();

    /**
     * 생산되고 있는 제품이 유통가능한 상태(완제품) 인지 확인
     * @return
     */
    boolean isPreparedMerchandible();

}
