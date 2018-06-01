package com.github.antksk.breakabletoy.ddd.me7se;

import java.util.Optional;

public interface Unit extends MerchandiseValueFactor{


    String toDisplayUnit();

    @Override
    default Optional<Unit> getUnit(){
        return Optional.of(this);
    }

}
