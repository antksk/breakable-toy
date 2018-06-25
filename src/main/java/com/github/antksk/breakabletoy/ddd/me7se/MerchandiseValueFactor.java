package com.github.antksk.breakabletoy.ddd.me7se;

import com.github.antksk.breakabletoy.ddd.me7se.price.Priceable;
import com.github.antksk.breakabletoy.ddd.me7se.unit.Unitable;

import java.util.Optional;

/**
 * 유통 되는 상품을 구성하는 기본 인자(가격(Priceable), 수량(Quantity), 단위(Unitable))
 */
public interface MerchandiseValueFactor<T extends Number> extends Comparable<MerchandiseValueFactor<T>> {
    T getValue();

    default String toDisplayValue(){
        return String.format("%,d",getValue());
    }

    /**
     * 현재 설정된 값과 비교 할 때, factor값이 크면 양수를 리턴함
     * 결론적으로, 기본 정렬 방식은 오름차순으로 설정됨
     * @param factor
     * @return
     */
    default int compareToFactor(T factor){
        if(factor instanceof Short){
            short result = factor.shortValue();
            if( 0 > result ) return 1;
            if( 0 < result ) return -1;
            return 0;
        }

        if(factor instanceof Integer){
            int result = factor.intValue();
            if( 0 > result ) return 1;
            if( 0 < result ) return -1;
            return 0;
        }
        if(factor instanceof Long){
            long result = factor.longValue();
            if( 0 > result ) return 1;
            if( 0 < result ) return -1;
            return 0;
        }
        if(factor instanceof Float){
            float result = factor.floatValue();
            if( 0 > result ) return 1;
            if( 0 < result ) return -1;
            return 0;
        }
        if(factor instanceof Double){
            double result = factor.doubleValue();
            if( 0 > result ) return 1;
            if( 0 < result ) return -1;
            return 0;
        }
        throw new NumberFormatException();
    }

    default boolean equalTo(MerchandiseValueFactor<T> factor){
        return equals(factor) && 0 == compareTo(factor);
    }

    /**
     * this &lt; target factor
     * @param factor
     * @return
     */
    default boolean lessThan(MerchandiseValueFactor<T> factor){
        return 0 > compareTo(factor);
    }

    /**
     * this &gt; target factor
     * @param factor
     * @return
     */
    default boolean greaterThan(MerchandiseValueFactor<T> factor){
        return 0 < compareTo(factor);
    }

    default Optional<Priceable> getPriceable(){
        return Optional.empty();
    }

    default Optional<Quantity> getQuantity(){
        return Optional.empty();
    }

    default Optional<Unitable> getUnitable(){
        return Optional.empty();
    }
}
