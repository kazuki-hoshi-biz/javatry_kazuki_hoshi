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
package org.docksidestage.javatry.basic;

import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.FULL_DAY_PASS_NAME;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author your_name_here
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400); // salesProceedsの内容が変わってそう、quantityは1減ってる
        int sea = booth.getQuantity(); // 最初10で1へって9、コードの命名的にもそんな感じする
        log(sea); // your answer? => 9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000); // どう変わってるか見なきゃか10000
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000, 修正後：7400
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 0, 修正後：null
    }
    // 考えメモ：Integerのnulllがどう扱われるか確認の仕方を忘れてしまいました（log見ればわかるけど、、）

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity(); //quantityが入るはずだから追う
        log(sea); // your answer? => 9, 修正後：10
    }
    // 答えは合ってるけど、try, catchはよくわからないんで読み飛ばした
    // 勉強しました、実行時に投げられる例外を用意している、quantityがデクリメントされるところは実行される

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }
    // throwの後にquantityを変えるようにした
    // #1on1: Good その通り。よくある典型的なバグの一つ。 (2026/10/01)
    // 流れを見て、複数行の関係性を捉えないと見つけられないバグ。
    // もっと構造が複雑になってくると、順序が見えづらくなって、密かに順序バグが生まれることもある。

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }
    // #1on1: Good OK。よくある典型的なバグの一つ。 (2026/10/01)
    // こっちは、その一行だけ業務的に間違ってるとわかるので見つけやすい。

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        //         uncomment after making the method
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        int change = booth.buyTwoDayPassport(money).getChange(); // 後続の変更でgetChangeを付け足す必要がありました
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money

        // and show two-day passport quantity here
        int land = booth.getQuantity();
        log(land);
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark("morning");
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    // どこにクラスを定義すればいいかわからず、TicketBoothクラスの中にpublicで定義しました、そしたらメソッドもpublicにする必要が出てきてうーんという気持ちになりました
    // #1on1: javatry的には、TicketBoothの隣に実はTicket.javaがいました (2026/10/01)
    // でも、自分でTicketを0から作るというのはトレーニングとしてはとても良いので結果オーライ。
    // しかも、自力Ticketがちゃんと既存Ticketを網羅しているのでGood。

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    // #1on1: $思想が読まれてると思った (2026/10/01)
    // 正しくボトムアップができた証拠。
    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        Ticket twoDayPassport = booth.buyTwoDayPassport(15000).getTicket();
        try {
            // 入場を２回行う
            twoDayPassport.doInPark("morning");
            twoDayPassport.doInPark("evening");
            log("2回入場する処理に成功");
        } catch (RuntimeException e) {
            log("2回入場する処理に失敗");
        }
        try {
            // done jflute 上の入場2回がクリアしている前提になってしまっている気がします、手探りのジャストアイディアを出すしかうまい方法を考える手段を持ち合わせていません、考え方が知りたいです
            // #1on1: 入退場のシミュレーションとしてはGood。try/catchもこれで良いと思います。 (2026/10/01)
            // 入れない時の表現が例外になっているから、呼び出し側でそれを検知するならtry/catchにならざるをえない。
            // $3日とか4日にも対応しないといけない？
            // それでいうと、1日にも対応していないといけない。 → コード的には大丈夫そう。
            twoDayPassport.doInPark("evening");
        } catch (RuntimeException e) {
            log("3回目の入場を制限する処理に成功");
        }
    }

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statement. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        showTicketIfNeeds(oneDayPassport);
        //        TicketBuyResult buyResult = booth.buyTwoDayPassport(10000);
        TicketBuyResult buyResult = booth.buyTwoDayPassport(20000);
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
    }

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        // #1on1: 日数で判定すると、違う種類の同じ日数のパスポートが出てきた時に破綻する (2026/10/16)
        // 当時は日数が2の人が他にいなかったけど、仕様追加で出てきたので、既存ifが曖昧になってしまうケース。
        // 新しい機能を使いする時は、既存コードへの論理的な影響を探るべし。
        // (一方で、最初の段階でifを書く人も、将来曖昧にならないか？というのを意識して書く)
        // DONE hoshi (nightが入らないよう)正確に判定できるようにしてみましょう by jflute (2026/10/16)
        if (ticket.getDays() == 2 && ticket.ticketType.equals(FULL_DAY_PASS_NAME)) { // write determination for two-day passport
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        int money = 24000;
        TicketBuyResult fourDayPassport = booth.buyFourDayPassport(money);
        Integer sea = booth.getSalesProceeds() + fourDayPassport.getChange();
        log(sea); // should be same as money
        int land = booth.getQuantity();
        log(land);
    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        int money = 24000;
        TicketBuyResult buyResult = booth.buyNightOnlyTwoDayPassport(money);
        Integer sea = booth.getSalesProceeds() + buyResult.getChange();
        log(sea); // should be same as money
        int land = booth.getQuantity();
        log(land);
        Ticket nightOnlyTwoDayPassport = buyResult.getTicket();
        try {
            nightOnlyTwoDayPassport.doInPark("morning");
        } catch (RuntimeException e) {
            log(e);
        }
        try {
            nightOnlyTwoDayPassport.doInPark("night");
            nightOnlyTwoDayPassport.doInPark("night");
            nightOnlyTwoDayPassport.doInPark("night");
        } catch (RuntimeException e) {
            log(e);
        }

    }

    // 考えメモ：久しぶりにここから開始しました。まず、使うという行動がコードでどう表現され得ているか確認し、それができるようにナイトデイパスポートを作り、他のも修正しようと方針を立てた。
    // 一日と夜って概念が出てきたので、今までのクラスの何に関係するかと、どう変更させるか考えた方が良さそう？？
    // チケットクラスでは初期日数と全日or夜で良さそう
    // いろんなクラスの実装見ながらお互いに直していくの大変そう、、
    // 実装中の考えメモ：定数はticketBoothにまとめた方が見やすそうなのでチケットタイプ置いとく
    // if文で網羅的でない時に出る警告はどうするのがいい？？
    // #1on1: $1day, 2dayとあるが、2dayでelse ifするとelse書けよ警告 (2026/10/16)
    /*
    if (days == 1)
        return ONE_DAY_PRICE;
    else if (days == 2)
        return TWO_DAY_PRICE;
     or
    if (days == 1)
        return ONE_DAY_PRICE;
    else
        return TWO_DAY_PRICE;
    
    // jfluteは、こういうif文書かないといけない時はelseで例外をthrowする
    // (原始的なやり方ではあるが、Javaだとこういった網羅の支援文法なので自分はこう書く)
    // 本当に、fourDayが来ちゃった時に、twoDayで動いちゃうのはマズイので、落としておきたい。
    if (days == 1)
        return ONE_DAY_PRICE;
    else if (days == 2)
        return TWO_DAY_PRICE;
    else
        throw new IllegalStateException("Unknown days: " + days);
     */

    // ticketBuyResultインスタンスを作るときのseaがなんのことかわからず実装見にいく必要あり面倒だった、命名大事そう
    // 夜以外に入れないことと、２日分しか入れないことをテストにした

    // ===================================================================================
    //                                                                         Bonus Stage
    //                                                                         ===========
    /**
     * Refactor the code to the best readable code you can think of. <br>
     * (自分の中で思う最高に可読性の高いコードにリファクタリングしてみましょう)
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    /**
     * Write intelligent JavaDoc comments seriously on the public classes/constructors/methods of the Ticket class. <br>
     * (Ticketクラスのpublicなクラス/コンストラクター/メソッドに、気の利いたJavaDocコメントを本気で書いてみましょう) <br>
     * <br>
     * Seriously → With the intention that the Ticket class (for example) becomes open source and is used by hundreds of people. <br>
     * (本気で → Ticketクラスが(例えば)オープンソースになって何百人の人から利用される想定のつもりで。)
     */
    public void test_class_moreFix_yourSuperJavaDoc() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                         Devil Stage
    //                                                                         ===========
    /**
     * If your specification is to share inventory (quantity) between OneDay/TwoDay/...,
     * change the specification to separate inventory for each OneDay/TwoDay/.... <br>
     * (もし、OneDay/TwoDay/...で在庫(quantity)を共有する仕様になってたら、
     * OneDay/TwoDay/...ごとに在庫を分ける仕様に変えてみましょう)
     */
    public void test_class_moreFix_zonedQuantity() {
        // your confirmation code here
    }
}
