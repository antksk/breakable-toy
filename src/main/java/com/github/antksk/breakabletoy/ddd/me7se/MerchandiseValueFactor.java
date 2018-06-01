package com.github.antksk.breakabletoy.ddd.me7se;

import java.util.Optional;

/**
 * 유통 되는 상품을 구성하는 기본 인자(수량, 단위)
 */
public interface MerchandiseValueFactor {
    int getValue();

    default Optional<Quantity> getQuantity(){
        return Optional.empty();
    }

    default Optional<Unit> getUnit(){
        return Optional.empty();
    }
}
