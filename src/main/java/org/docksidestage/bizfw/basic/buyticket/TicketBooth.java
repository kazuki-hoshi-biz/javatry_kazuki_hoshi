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

    // #1on1: $TicketがticketTypeでこっちはnameで、統一感が、役割なのか？中身なのか？ (2026/10/29)
    // まさしく、役割(種別識別子)を名前にするのか？中身(種別名)を名前にするか？
    // 兼任しちゃってる。種別識別子と種別名を一つの値で表現してしまっている。
    // 種別識別子としてしか使ってないから、極端な話、a, b でも良かったりはする。
    // ただ、メンテナンス的な可読性が悪いから、もうちょいわかる「コード」を付けたい。
    // プログラマーにとってわかりやすいコード e.g. all_day_pass, night_pass
    // TODO hoshi 識別子になるので、識別子っぽい定数名、そして、中身にしましょう by jflute (2026/10/29)
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
    // done hoshi JavaDoc, 戻り値追加したので、@returnを追加で (日本語でOK) by jflute (2026/10/16)
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
        // TODO hoshi 文法的に不要な戻り値は受け取らなくてもいいのでchangeは削除でOK by jflute (2026/10/29)
        int change = doBuyPassport(handedMoney, ONE_DAY_PRICE);
        Ticket ticket = new Ticket(1, FULL_DAY_PASS_NAME, ONE_DAY_PRICE);
        return ticket;
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        // done hoshi そろそろseaじゃなくてもOKです。業務的な名前を付けましょう by jflute (2026/10/16)
        // 考えメモ：お釣りが帰ってくるのでchangeにしました
        // done jflute お釣りが帰ってくることはメソッドの実装（戻り値）を見に行かないといけなかったのですが面倒な気がしました、確認はもっと楽にできるものですか？？
        // #1on1: 一つは、JavaDocを書いておけば、IDE上でメソッド補完時に閲覧できる。 (2026/10/29)
        // privateメソッドにJavaDoc書くか？はpublicに比べれば書かないことの方が多い。
        // 同じクラスで目の前にコードがあるし、呼ぶ人と呼ばれる人が同じ可能性も高いし。
        // 一方で、このdoBuyはクラスの重要人物、重要ロジックなので書いておいても良いかなと。
        //
        // 一方で一方で、TicketBuyResultが戻って来てたらたぶん気にせず呼べる。
        // 戻り値の型に業務的な意味合いが含まれているから。(intは業務色がない)
        // 例えば、Changeというクラスを作って表現するという方法もある。
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
    // TODO hoshi せっかくなので、doBuyにもJavaDocを書いてみましょう by jflute (2026/10/29)
    // =====10/23=====
    // 考えメモ：チケット買ったらお釣りだけでなく結果全体が帰ってきてくるのが自然に感じたので戻り値をintからticketbuyresultにした
    // と思ったけど買うチケットの種類をこのメソッドに渡すのも責務が大きくなりすぎな気がして戻り値intに戻しました
    // #1on1: 今の状態だとdoBuyが役割大きいイメージはその通りだけど、それを軽減する策として... (2026/10/29)
    // doBuyをコントローラー的なメソッドにして、中の処理をもうちょい小分けにするやり方。
    // e.g.
    //  validatePurchable();
    //  reduceQuantity();
    //  calculateSalesProceeds();
    //  calculateChange();
    //  return createBuyResult();
    // こういうメソッドを、ファサードメソッド。ファサード(玄関)メソッド。
    // 銀行の受付の人をイメージ。受付の人の機能的には責務が多い気もするけど、
    // 実際は、受付の人が全部やっているわけじゃなく、コントロールしてるだけ。
    // TODO hoshi TicketBuyResult まで戻り値で戻してみましょう by jflute (2026/10/29)
    // そして、中の処理をネストのprivateメソッドで部品かしてみましょう。
    // (doBuyはファサードメソッドにする)
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
