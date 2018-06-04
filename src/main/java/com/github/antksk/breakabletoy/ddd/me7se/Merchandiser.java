package com.github.antksk.breakabletoy.ddd.me7se;

import java.time.LocalDate;

public interface Merchandiser {

    /**
     * 유통 기한
     * @return
     */
    LocalDate getExpirationDate();

    /**
     * 유통 기한 만료 여부
     * @return
     */
    boolean isExpireDate();

    /**
     * 유통되고 있는 상품의 기본 특징
     * @return
     */
    MerchandiseType getType();

    /**
     * 유통상품의 품질
     * @return
     */
    Quality getQuality();
}
