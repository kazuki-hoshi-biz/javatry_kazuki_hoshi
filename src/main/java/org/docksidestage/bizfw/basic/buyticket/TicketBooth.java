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
    public static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    public static final int TWO_DAY_PRICE = 13200;
    public static final int FOUR_DAY_PRICE = 22400;
    public static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;
    public static final String FULL_DAY_PASS_NAME = "1日パス";
    public static final String NIGHT_PASS_NAME = "ナイトパス";

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
    // TODO hoshi JavaDoc, 戻り値追加したので、@returnを追加で (日本語でOK) by jflute (2026/10/16)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return チケットを返す
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
        int change = doBuyPassport(handedMoney, ONE_DAY_PRICE);
        Ticket ticket = new Ticket(1, FULL_DAY_PASS_NAME, ONE_DAY_PRICE);
        return ticket;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        // done hoshi そろそろseaじゃなくてもOKです。業務的な名前を付けましょう by jflute (2026/10/16)
        // 考えメモ：お釣りが帰ってくるのでchangeにしました
        // TODO jflute お釣りが帰ってくることはメソッドの実装（戻り値）を見に行かないといけなかったのですが面倒な気がしました、確認はもっと楽にできるものですか？？
        int change = doBuyPassport(handedMoney, TWO_DAY_PRICE);
        Ticket ticket = new Ticket(2, FULL_DAY_PASS_NAME, TWO_DAY_PRICE);
        TicketBuyResult result = new TicketBuyResult(change, ticket);
        return result;
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        int change = doBuyPassport(handedMoney, FOUR_DAY_PRICE);
        Ticket ticket = new Ticket(4, FULL_DAY_PASS_NAME, FOUR_DAY_PRICE);
        TicketBuyResult result = new TicketBuyResult(change, ticket);
        return result;
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(int handedMoney) {
        int change = doBuyPassport(handedMoney, NIGHT_ONLY_TWO_DAY_PRICE);
        Ticket ticket = new Ticket(2, NIGHT_PASS_NAME, NIGHT_ONLY_TWO_DAY_PRICE);
        TicketBuyResult result = new TicketBuyResult(change, ticket);
        return result;
    }

    // #1on1: doBuyPassport()という名前Good, ぼくもよく使います (2026/10/01)
    // $命名のやり方とかを調べて...
    // doを付けて実処理感を出して、prefixも区別してpublicのbuyと紛れないように。
    // =====10/23=====
    // 考えメモ：チケット買ったらお釣りだけでなく結果全体が帰ってきてくるのが自然に感じたので戻り値をintからticketbuyresultにした
    // と思ったけど買うチケットの種類をこのメソッドに渡すのも責務が大きくなりすぎな気がして戻り値intに戻しました
    private int doBuyPassport(int handedMoney, int price) {
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
}
