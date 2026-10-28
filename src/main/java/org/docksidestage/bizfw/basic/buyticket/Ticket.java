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

// done hoshi もうほしさんのTicketを正にして、既存Ticketは削除もしくは上書きしちゃっていいかなと by jflute (2026/10/01)
import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.FULL_DAY_PASS_NAME;
import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.NIGHT_ONLY_TWO_DAY_PRICE;
import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.ONE_DAY_PRICE;
import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.TWO_DAY_PRICE;

// #1on1: $publicにするかprivateにするか迷った。呼ばれてるからpublicにしたけど (2026/10/01)
// ボトムアップで作ったクラスなので、「呼ばれてるからpublicにした」は正しいのでGood。
// done hoshi Ticket.javaに上書きでいいんじゃないかと by jflute (2026/10/01)
public class Ticket {
    // DONE hoshi インスタンス変数、immutableにできるならimmutableにしちゃいましょう by jflute (2026/10/01)
    // つまり、finalをつける。(コンストラクターで受け取った固定値はfinalで保持しておきたい)
    // hoshi daysはfinalつけれそう
    // DONE hoshi final private でも private final でも動くんですけど、private final が多いので合わせてみて by jflute (2026/10/16)
    // DONE hoshi dayだとカレンダー的な日(3月20日の20)という感じで、日数感がない。daysにすると良い by jflute (2026/10/16)
    // あと、ここだと、他にも残りの日数(回数)みたいな概念もあるので、daysだけではなく、
    // e.g. maxDays, ticketDays とか何かしらニュアンスがあるともうちょい良い。
    // DONE hoshi countも「残り日数」というニュアンスがあると良い。 by jflute (2026/10/16)
    // 入った回数なのか、残りの回数なのか、勘違いしやすいので、明確に。
    // e.g. remainingDays, enterableDays
    // DONE hoshi isIn, 本来はこれでもいいだろうけど、isが汎用利用されすぎてて、現在感がちょい薄い by jflute (2026/10/16)
    // なので、もうちょい明確に、e.g. isInNow, isInCurrently とか「今の状態」を明示すると良いかも。
    private final int days;
    // private boolean isInNow;
    private int remainingDays;
    public final String ticketType;
    private final int price;

    public Ticket(int days, String ticketType, int price) {
        this.days = days;
        // this.isInNow = false;
        this.remainingDays = days;
        this.ticketType = ticketType;
        this.price = price;
    }

    // #1on1: isAlreadyInとisInNowは概念的に同じものだろうか？ (2026/10/16)
    // isAlreadyInは、既存から存在していて、特に説明もないので...いやあった。
    // true means this ticket is unavailable
    // ということなので、現在入っているかどうか？のisInNowとはちょっとニュアンス違う。
    // DONE hoshi 「すでに入り終わったかどうか(使い切ったかどうか)」のニュアンスで実装 by jflute (2026/10/16)
    // TODO jflute isAlreadyInを修正したら、フィールド定義で使われていないことがわかりました。isInNowが不要だと思ったので削除してしまおうかと思いましたが本当に不要か心配でした。
    // 消してみるとdoOutParkを使うところで問題起きましたが、doInParkのみで残り日数を管理すればdoOutParkも不要と判断しました。
    public boolean isAlreadyIn() {
        if (remainingDays == 0) {
            return true;
        }else if (remainingDays > 0) {
            return false;
        }else {
            throw new RuntimeException("使用回数で不備があります");
        }
    }

    public void doInPark(String time) {
        // DONE hoshi if (remainingDays >= 1) から isInNow = true; までのくだりが... by jflute (2026/10/16)
        // TODO jflute そのままメソッドにしたらメソッド内でデクリメントしてもdoInParkで扱うインスタンス変数はいじれないことがわかりました、戻り値にデクリメントした値を指定してやりましたが定石みたいなものはありますか？？
        // fullDayとnight で全く同じなので(意味も同じ)、どうにか統一したい。
        if (ticketType.equals(FULL_DAY_PASS_NAME)) {
            remainingDays = decrementRemainingDays(remainingDays);
        } else {
            if (time.equals("night")) {
                remainingDays = decrementRemainingDays(remainingDays);
            } else
                throw new RuntimeException("現在の時刻では使用できません。17時以降に再度お越しください");
        }
    }

    public int decrementRemainingDays(int remainingDays) {
        if (remainingDays >= 1)
            remainingDays--;
        else
            throw new RuntimeException("すでに使用されています");
        return remainingDays;
    }

//    public void doOutPark() {
//        isInNow = false;
//    }

    // DONE hoshi シンプルなgetterは、わりとクラスの一番下に定義することが多いので移動を by jflute (2026/10/16)
    // というのは、あまり業務理解で重要なコードじゃない (動作的には大切だけど)
    // (それよりも、doInPark()とか業務的なメソッドが大事なので、それらは上に)
    public int getDays() {
        return days;
    }

    public int getDisplayPrice() {
        // DONE hoshi 表示料金は、TicketBoothが持ってるのでもらっちゃった方が世話ない by jflute (2026/10/16)
        // もともとわかってたはずなので、またここで戻し判定してるみたいな感じになってる。
        // (同じ値段もちゃんと戻るだろうか？というのが不安になる)
        return price;
    }
}

// 元の
//public class Ticket {
//
//    // ===================================================================================
//    //                                                                           Attribute
//    //                                                                           =========
//    private final int displayPrice; // written on ticket, park guest can watch this
//    private boolean alreadyIn; // true means this ticket is unavailable
//
//    // ===================================================================================
//    //                                                                         Constructor
//    //                                                                         ===========
//    public Ticket(int displayPrice) {
//        this.displayPrice = displayPrice;
//    }
//
//    // ===================================================================================
//    //                                                                             In Park
//    //                                                                             =======
//    public void doInPark() {
//        if (alreadyIn) {
//            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
//        }
//        alreadyIn = true;
//    }
//
//    // ===================================================================================
//    //                                                                            Accessor
//    //                                                                            ========
//    public int getDisplayPrice() {
//        return displayPrice;
//    }
//
//    public boolean isAlreadyIn() {
//        return alreadyIn;
//    }
//}
