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
    // TODO hoshi インスタンス変数、immutableにできるならimmutableにしちゃいましょう by jflute (2026/10/01)
    // つまり、finalをつける。(コンストラクターで受け取った固定値はfinalで保持しておきたい)
    // hoshi dayはfinalつけれそう
    // TODO hoshi final private でも private final でも動くんですけど、private final が多いので合わせてみて by jflute (2026/10/16)
    // TODO hoshi dayだとカレンダー的な日(3月20日の20)という感じで、日数感がない。daysにすると良い by jflute (2026/10/16)
    // あと、ここだと、他にも残りの日数(回数)みたいな概念もあるので、daysだけではなく、
    // e.g. maxDays, ticketDays とか何かしらニュアンスがあるともうちょい良い。
    // TODO hoshi countも「残り日数」というニュアンスがあると良い。 by jflute (2026/10/16)
    // 入った回数なのか、残りの回数なのか、勘違いしやすいので、明確に。
    // e.g. remainingDays, enterableDays
    // TODO hoshi isIn, 本来はこれでもいいだろうけど、isが汎用利用されすぎてて、現在感がちょい薄い by jflute (2026/10/16)
    // なので、もうちょい明確に、e.g. isInNow, isInCurrently とか「今の状態」を明示すると良いかも。
    final private int day;
    private boolean isIn;
    private int count;
    final private String ticketType;

    public Ticket(int day, String ticketType) {
        this.day = day;
        this.isIn = false;
        this.count = day;
        this.ticketType = ticketType;
    }

    // TODO hoshi シンプルなgetterは、わりとクラスの一番下に定義することが多いので移動を by jflute (2026/10/16)
    // というのは、あまり業務理解で重要なコードじゃない (動作的には大切だけど)
    // (それよりも、doInPark()とか業務的なメソッドが大事なので、それらは上に)
    public int getDay() {
        return day;
    }

    public int getDisplayPrice() {
        // TODO hoshi 表示料金は、TicketBoothが持ってるのでもらっちゃった方が世話ない by jflute (2026/10/16)
        // もともとわかってたはずなので、またここで戻し判定してるみたいな感じになってる。
        // (同じ値段もちゃんと戻るだろうか？というのが不安になる)
        if (ticketType == FULL_DAY_PASS_NAME) {
            if (day == 1)
                return ONE_DAY_PRICE;
            else
                return TWO_DAY_PRICE;
        } else {
            return NIGHT_ONLY_TWO_DAY_PRICE;
        }
    }

    // #1on1: isAlreadyInとisInは概念的に同じものだろうか？ (2026/10/16)
    // isAlreadyInは、既存から存在していて、特に説明もないので...いやあった。
    // true means this ticket is unavailable
    // ということなので、現在入っているかどうか？のisInとはちょっとニュアンス違う。
    // TODO hoshi 「すでに入り終わったかどうか(使い切ったかどうか)」のニュアンスで実装 by jflute (2026/10/16)
    public boolean isAlreadyIn() {
        return isIn;
    }

    public void doInPark(String time) {
        // TODO hoshi if (count >= 1) から isIn = true; までのくだりが... by jflute (2026/10/16)
        // fullDayとnight で全く同じなので(意味も同じ)、どうにか統一したい。
        if (ticketType.equals(FULL_DAY_PASS_NAME)) {
            if (count >= 1)
                count--;
            else
                throw new RuntimeException("すでに使用されています");
            isIn = true;
        } else {
            if (time.equals("night")) {
                if (count >= 1)
                    count--;
                else
                    throw new RuntimeException("すでに使用されています");
                isIn = true;
            } else
                throw new RuntimeException("現在の時刻では使用できません。17時以降に再度お越しください");
        }
    }

    public void doOutPark() {
        isIn = false;
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
