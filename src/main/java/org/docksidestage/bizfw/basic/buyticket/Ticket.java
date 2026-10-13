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

// TODO hoshi もうほしさんのTicketを正にして、既存Ticketは削除もしくは上書きしちゃっていいかなと by jflute (2026/10/01)

import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.*;

/**
 * @author jflute
 */
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


// #1on1: $publicにするかprivateにするか迷った。呼ばれてるからpublicにしたけど (2026/10/01)
// ボトムアップで作ったクラスなので、「呼ばれてるからpublicにした」は正しいのでGood。
// TODO hoshi Ticket.javaに上書きでいいんじゃないかと by jflute (2026/10/01)
public class Ticket {
    // TODO hoshi インスタンス変数、immutableにできるならimmutableにしちゃいましょう by jflute (2026/10/01)
    // つまり、finalをつける。(コンストラクターで受け取った固定値はfinalで保持しておきたい)
    // hoshi dayはfinalつけれそう
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

    public int getDay() {
        return day;
    }

    public int getDisplayPrice() {
        if(ticketType == FULL_DAY_PASS_NAME){
            if (day == 1)
                return ONE_DAY_PRICE;
            else
                return TWO_DAY_PRICE;
        }else{
            return NIGHT_ONLY_TWO_DAY_PRICE;
        }
    }

    public boolean isAlreadyIn() {
        return isIn;
    }

    public void doInPark(String time) {
        if(ticketType.equals(FULL_DAY_PASS_NAME)) {
            if (count >= 1)
                count--;
            else
                throw new RuntimeException("すでに使用されています");
            isIn = true;
        }else{
            if(time.equals("night")){
                if (count >= 1)
                    count--;
                else
                    throw new RuntimeException("すでに使用されています");
                isIn = true;
            }
            else
                throw new RuntimeException("現在の時刻では使用できません。17時以降に再度お越しください");
        }
    }

    public void doOutPark() {
        isIn = false;
    }
}
