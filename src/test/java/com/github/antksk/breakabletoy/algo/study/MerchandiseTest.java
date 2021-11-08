package com.github.antksk.breakabletoy.algo.study;

import org.junit.Test;

public class MerchandiseTest{
//    /**
//     * 유통되는 모든 상품, 제품의 기본 정보
//     */
//    public interface Merchandise{
//        String getName();
//        int getPrice();
//    }
//
//    /**
//     * 유형상품
//     */
//    public interface ConcreteMerchandise extends Merchandise{
//        int getWeight();
//        String getUnit();
//    }
//
//    /**
//     * 무형 상품
//     */
//    public interface IntangibleMerchandise extends Merchandise{
//        String getName();
//        LocalDateTime getPeriod();
//    }
//    @Value(staticConstructor = "product")
//    public static class BaseProduct implements Merchandise{
//        private String name;
//        private int price;
//    }
//
//    public static class Cup implements ConcreteMerchandise{
//        private final BaseProduct product;
//        @Getter
//        private final int weight;
//        @Getter
//        private final String unit;
//        @Builder(builderMethodName = "cup", buildMethodName = "create")
//        public Cup(BaseProduct product, int weight, String unit) {
//            this.product = product;
//            this.weight = weight;
//            this.unit = unit;
//        }
//
//        public static CupBuilder is(String name, int price){
//            return cup().product(product(name, price));
//        }
//
//        @Override
//        public String getName() {
//            return product.getName();
//        }
//
//        @Override
//        public int getPrice() {
//            return product.getPrice();
//        }
//    }
//
//    public static class Ticket implements IntangibleMerchandise{
//        final BaseProduct product;
//        @Getter final String name;
//        @Getter final LocalDateTime period;
//
//        @Builder(builderMethodName = "is", buildMethodName = "create")
//        public Ticket(BaseProduct product, String name, LocalDateTime period) {
//            this.product = product;
//            this.name = name;
//            this.period = period;
//        }
//
//        public static TicketBuilder ticket(String productName, int price){
//            TicketBuilder period = is().product(product(productName, price)).period(LocalDateTime.now());
//            return period;
//        }
//
//        @Override
//        public String getName() {
//            return product.getName();
//        }
//
//        @Override
//        public int getPrice() {
//            return product.getPrice();
//        }
//    }
//
//    public static void main(String[] args) {
//        Stream.of(
//            product("일반상품", 1)
//            , Cup.is("작은컵",15_000).weight(120).unit("g").create()
//            , ticket("반도", 10_000).name("[CGV 영화 티켓]").create()
//        ).peek(m->System.out.println(m.getName()))
//              .filter((Merchandise m)->m instanceof ConcreteMerchandise )
////              .map(m->(ConcreteMerchandise)m)
////              .forEach(c->System.out.println(c.getName() + " = " + c.getWeight() + "(" + c.getUnit() + ")"));
//              .forEach(System.out::println);
//    }
//    @Test
//    public void test(){
//        Stream.of(
//            product("일반상품", 1)
//            , Cup.is("작은컵",15_000).weight(120).unit("g").create()
//            , ticket("반도", 10_000).name("[CGV 영화 티켓]").create()
//        ).peek(m->System.out.println(m.getName()))
//              .filter((Merchandise m)->m instanceof ConcreteMerchandise )
////              .map(m->(ConcreteMerchandise)m)
////              .forEach(c->System.out.println(c.getName() + " = " + c.getWeight() + "(" + c.getUnit() + ")"));
//        .forEach(System.out::println);
//    }
    interface A{
        int test();
    }
    interface B{
        int test();
    }
    class C implements A, B{
        @Override
        public int test() {
            return 0;
        }
    }
    @Test
    public void test2(){
        A ac = new C();
        B bc = new C();
        System.out.println(ac.test());
        System.out.println(bc.test());
    }

}
