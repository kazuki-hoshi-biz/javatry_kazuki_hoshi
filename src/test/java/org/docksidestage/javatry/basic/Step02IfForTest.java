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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) { //該当しない
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) { //該当しない
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) { //ここに入る
            if (sea % 2 == 0) {
                sea = sea++ * 2; //sea = 1808
            }
            if (!land) { //該当する
                land = true; // #1on1 ここを通ったらseaは10
            } else if (sea <= 903) { //上のifに入るからここには入らない
                sea++;
            }
            if (sea < 1810) {
                sea = 8; //sea = 8
            }
        } else if (sea == 8) { //上のelse ifに入るからここには入らない
            sea++;
            land = false;
        } else { //上のelse ifに入るからここには入らない
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) { //該当する
            sea--;
            if (sea % 2 == 1) { //該当する
                sea++;
            }
        }
        if (land) { //landはtrue
            sea = 10; //sea = 10
        }
        log(sea); // your answer? => 10
    }
    // 考えメモ：landがtrueかどうかだけ追えば答えわかったのに、、、
    // done hoshi [いいね] それを思い付けたのが素晴らしい(^^ by jflute (2026/08/13)
    // done jflute 1on1にて、漠然読みの話をする予定 (2026/08/13)
    //
    // (まずスクロールして...)
    // o 漠然読みで構造把握
    //  → ここだと変数宣言、大中小if文、ログの5つのパート
    //
    // (当たりを付ける) // 裏ルートがないか？
    //  → ここだと逆さ読みでlog(sea)の直近を追ってみる
    // o 当たりを付けて、フォーカス読み
    //
    // ただもちろんギャンブルに負けることはあります。でも損はない。
    // 構造把握しているし、ある程度深掘りしているので、０から読むより速く読めるようになっている。
    // 頭の中で地図を持った状態で読み進めていけば現在地がわかるので安定して読める。
    //
    // あと、次の当たりが見つかるかもしれない。そして再びフォーカス読み。
    // それを3,4回繰り返す。それでも網羅読みよりも速いかもしれない。
    //
    // $以下の三つのパターン:
    // A. 影響範囲を調べる (原因) // ビタっとフィットするところ
    // B. 他の人のコードが合ってるか？ // 何が合ってるか？を調べるであればフィットする
    // C. AIのコードが自分の要求 // ↑と同じくピンポイントで探しに行くのであればフィットする
    //
    // BとCで全部のコードの責任を追うために読むのであれば、
    // 確かに網羅読みはするけれども...でも漠然読みはする(速く読むため)。
    //
    // よもやま話: 仮説思考的なコードリーディング!?
    // done hoshi [読み物課題] My Favorite Book: 仮説思考 by jflute (2026/08/28)
    // https://jflute.hatenadiary.jp/entry/20150111/kasetsu

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => null
    }

    //考えもメモ：prepareStageList()っていう空のリストを作っているのかと思った。
    // done jflute newしてないから考えメモのようにはならないなと気づくべきでしたか？？ by hoshi
    // done hoshi [へんじ] まあメソッド呼び出しの感覚に慣れていけばもう勘違いすることはないと思います。 by jflute (2026/08/13)
    // 1on1にて、もうちょい深掘りさせてください。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
    }
    // 考えメモ：上から順なので

    // #1on1: Java文法としてのfor文 (2026/08/28)
    // o intあいのfor文: Java当初とから (1995年)
    // o 拡張for文(foreach文): Java10年目くらいから (2005年くらい)
    // 普通のfor文とは？ → 現場ではほぼ拡張for文

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
    }
    // 考えメモ：docksideがappendされると上のifに引っかかる

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        List<String> aList = new ArrayList<>();
        stageList.forEach(stage -> {
            if (stage.contains("a")) {
                aList.add(stage);
            }
        });
        aList.forEach(a -> log(a));
    }

    // StreamAPI？？

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea = null;
        boolean land = true;
//                for (String stage : stageList) {
//                    if (stage.startsWith("br")) {
//                        continue;
//                    }
//                    sea = stage;
//                    if (stage.contains("ga")) {
//                        break;
//                    }
//                }
        StringBuilder tmp = new StringBuilder("");
        stageList.forEach(stage -> {
//            if (!stage.startsWith("ga") && land) {
//                sea = stage;
//            }
//            if (stage.contains("ga")) {
//                land = false;
//            }
//            if (stage.startsWith("br")) {
//                continue;
//            }
//            sea = stage;
//            if (stage.contains("ga")) {
//                break;
//            }
        });
        log(sea); // should be same as before-fix
    }

    // done jflute ラムダ式で使用されるる変数は final または実質的に final でなければなりません。と書いてありどうすればいいかわかりませんでした by hoshi
    // done hoshi [へんじ] hint1: sea変数の型を変えてしまっても構いません。 by jflute (2026/08/13)

    // #1on1: forEach()メソッドの仕組み (2026/08/28)
    // 拡張for文とかは、あくまでtest_メソッドの一部コードが繰り返される。そういう文法。
    // forEach()メソッドのコードリーディングしてみた。
    // -> {} は実は、クラス＆メソッド。その場でクラスとメソッドを定義してnewしている。
    // そのインスタンスをforEach()メソッドの引数に入れている。
    // つまり、-> {} (Lambda式) は、別クラス別メソッド。
    // だから、sea変数をLambda式の中で書き換えることができない。
    // test_メソッドのsea変数(ローカル変数)を、
    // 別クラス別メソッドにいじらせるわけにはいかない。
    // いったん簡単に仕組みを把握した上で、何なら代替できるのか？を考えてみましょう。
    // hoshi StringBuilderでforEachメソッド内で書き換える
    // hoshi booleanを変更できるようにする方法が分かりませんでした、、

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
    }

    // todo hoshi 次回1on1でfor文もっと知ってから取り組む
    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
