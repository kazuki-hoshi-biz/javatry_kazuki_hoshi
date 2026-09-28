/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
////        if (quantity <= 0) {
////            throw new TicketSoldOutException("Sold out");
////        }
////        --quantity;
//        if (handedMoney < ONE_DAY_PRICE) {
//            throw new TicketShortMoneyException("Short money: " + handedMoney);
//        }
//        if (quantity <= 0) {
//            throw new TicketSoldOutException("Sold out");
//        }
//        --quantity;
//        if (salesProceeds != null) { // second or more purchase
////            salesProceeds = salesProceeds + handedMoney;
//            salesProceeds += ONE_DAY_PRICE;
//        } else { // first purchase
////            salesProceeds = handedMoney;
//            salesProceeds = ONE_DAY_PRICE;
//        }
        int sea = doBuyPassport(handedMoney, ONE_DAY_PRICE);
        Ticket ticket = new Ticket(1);
        return ticket;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        int sea = doBuyPassport(handedMoney, TWO_DAY_PRICE);
        Ticket ticket = new Ticket(2);
        TicketBuyResult result = new TicketBuyResult(sea, ticket);
        return result;
    }

    private int doBuyPassport(int handedMoney, int price){
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds += price;
        } else { // first purchase
            salesProceeds = price;
        }
        return handedMoney - price;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }

    public class Ticket {
        private int day;
        private boolean isIn;
        private int count;

        Ticket(int day) {
            this.day = day;
            this.isIn = false;
            this.count = day;
        }

        public int getDisplayPrice() {
            if (day == 1)
                return ONE_DAY_PRICE;
            else
                return TWO_DAY_PRICE;
        }

        public boolean isAlreadyIn() {
            return isIn;
        }

        public void doInPark() {
            if(count >= 1)
                count--;
            else
                throw new RuntimeException("すでに使用されています");
            isIn = true;
        }

        public void doOutPark() {
            isIn = false;
        }
    }

    public class TicketBuyResult {
        private Ticket ticket;
        private int change;

        TicketBuyResult (int change, Ticket ticket){
            this.ticket = ticket;
            this.change = change;
        }

        public Ticket getTicket(){
            return ticket;
        }

        public int getChange(){
            return change;
        }
    }
}
