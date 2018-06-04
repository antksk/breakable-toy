package com.github.antksk.breakabletoy.ddd.me7se.price;

import lombok.EqualsAndHashCode;

/**
 * 부과 가치세
 */
@EqualsAndHashCode
public final class ValueAddedTax implements Priceable {
    private static final long MIN_VAT = 0L;
    private static final ValueAddedTax ZERO = new ValueAddedTax(MIN_VAT);

    private long vat;

    private ValueAddedTax(long vat){
        this.vat = Math.abs(vat);
    }

    public static ValueAddedTax of( long vat ){
        if( MIN_VAT >= vat ) {
            return ZERO;
        }
        return new ValueAddedTax(vat);
    }

    /**
     * 한국의 부과 가치세 기준으로 판매금액에 10%를 vat로 결정함
     * @param p
     * @return
     */
    public static ValueAddedTax of( Priceable p ){
        return of( (long)(p.getValue() * 0.1) );
    }

    public static ValueAddedTax zero(){
        return ZERO;
    }

    public boolean hasVat(){
        return MIN_VAT < vat;
    }

    @Override
    public long getPrice() {
        return vat;
    }

    @Override
    public Type getType() {
        return Type.TAX;
    }


}
