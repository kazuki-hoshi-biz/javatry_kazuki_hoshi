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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() {
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }
    // 考えメモ: ここは例として動きを確認するメソッド。次の問題から自分で考える。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai
    }
    // 考えメモ:
    // - piari は文字列リテラルとして定義していないため、最初は出力に入らないと予想した。
    // - 答え合わせ: 文字列を + で連結すると、null は "null" という文字列として扱われる。
    // done hoshi [ふぉろー] プログラミング言語の決めで色々なケースがあります by jflute (2026/07/29)
    // 空文字になる言語、エラーになる言語、全部nullになる言語、色々と。
    // Javaの場合、本番で画面やメールに "null" って出やすい。
    // 一方で、開発時は "null" って出てわかりやすい。
    // 些細なことでメリデメ考える習慣を。A or B を正しく選択できるように。
    // TODO hoshi [読み物課題] 応援してる "A" にもデメリットはあるよ by jflute (2026/07/30)
    // https://jflute.hatenadiary.jp/entry/20181008/yourademerit

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1 (1丁目1番地)
        String land = "oneman"; // 2 (2丁目2番地)
        sea = land; // seaはoneman(2)
        // → seaの1丁目1番地と書かれた紙を消しゴムで消して、2丁目2番地に書き換えるだけ
        // なので、この瞬間は、seaもlandも同じインスタンスを指し示している。
        land = land + "'s dreams"; // 3('s dreams)
        log(land); // oneman's dreams(2? → 4)
        log(sea); // your answer? => oneman(2)
        // TODO jflute 1on1にて、変数とインスタンスの関係性についてフォロー予定 (2026/07/29)
        // #1on1: インスタンスとは？ (2026/07/30)
        // 一軒家の例
        // インスタンスフォーカスの大切さ。
        //
        // 変数とは？
        // Javaのオブジェクト型の場合は、変数にアドレスが入っている。
        //
        // 変数とインスタンスの関係性
        // 1:1ではなく、n:1もありえる。
        // 後半エクササイズで色々とイメージした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 416
    }
    // 考えメモ:
    // - BigDecimal の add() は新しい値を返す。元の値を変えるには結果を代入し直す。
    // - 最終出力だけを見ると sea = land; は次の行で上書きされるため、なくても結果は同じ。
    // - sea.add(...) の戻り値を受け取らないと、sea は 416 のまま。
    // done hoshi [いいね] add()の特徴をしっかり捉えることできてますね by jflute (2026/07/29)
    // done jflute 1on1にて、immutableの話をする予定 (2026/07/29)
    // #1on1: インスタンス変数の理解をBigDecimalのコードでやったみた (2026/07/30)
    // TODO jflute 次回1on1にて、BigDecimalのimmutable性のついて、add()のコードリーディング (2026/07/30)

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => null
    }
    // 考えメモ:
    // - 最初は null と予想した。
    // - 答え合わせ: インスタンス変数には初期値が入る。今回は int 型なので 0 が入る。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|magician
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }
    // 考えメモ: 引数 instanceMagiclamp はフィールドと別の変数。ここで "burn" を代入してもフィールドは変わらない。
    // done hoshi [いいね] メソッド呼び出しの時は、変数自体は渡らず、中の値(アドレス)が渡っていくだけなので... by jflute (2026/07/29)
    // 「別の変数」という理解できてるの素晴らしいです。

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        // #1on1: ローカル変数、ライフサイクルは実行時にその場で作られる(確保される) (2026/07/30)
        // まず new String(harbor) が実行されて6丁目6番地にインスタンスが生成される。
        // =の代入で、左側の変数の紙に6丁目6番地と書かれる。
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        // この行では、helpメソッドのローカル変数たちはすでに破棄されている
        log(sea); // your answer? => harbor
    }

    // #1on1: 引数変数もローカル変数の一つ、メソッド呼び出し時にその場で作られる。 (2026/07/30)
    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land);
        sea.concat(landStr);
    }
    // 考えメモ:
    // - String.valueOf(land) は数値 416 を文字列 "416" に変換する。
    // - String はimmutable。concat() は新しい文字列を返すだけで、sea 自体は変わらない。
    // - 戻り値を代入も return もしていないため、呼び出し元の sea は "harbor" のまま。

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor
    }
    // 考えメモ:
    // - 最初は呼び出し元の sea も "harbor" のままだと予想した。
    // - 答え合わせ: append() は StringBuilder のメソッドで、同じオブジェクトの中身を変える。
    // - そのため呼び出し元の sea も "harbor416" になる。

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }
    // 考えメモ: Java は値渡しだが、渡した参照値の先にある同じ StringBuilder を append() が書き換える。

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }
    // 考えメモ:
    // - 新しい StringBuilder("harbor416") を作るが、代入し直すのはメソッド内の引数 sea だけ。
    // - 呼び出し元の StringBuilder は書き換わらないため、最後の出力は "harbor"。
    // - 呼び出し元の sea を変更したいなら、元のオブジェクトに append() するか、戻り値を返して呼び出し元で代入し直す。
    // TODO hoshi [いいね] しっかりインスタンスの区別が付いているので素晴らしい by jflute (2026/07/29)

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */

    private int piari;

    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        log(sea + "," + land + "," + piari);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * 次の log() の出力は何でしょうか？
     * - dockside を favorite に代入した後で、dockside に " Diner" をつなげます。
     * - plan は StringBuilder なので、append() で中身が変わります。
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        String dockside = "Dockside";
        String favorite = dockside;
        dockside = dockside + " Diner";

        StringBuilder plan = new StringBuilder("Next: ");
        plan.append(favorite);

        log(plan + " / " + dockside); // your answer? => Next: Dockside / Dockside Diner
    }
    // 考えメモ:
    // - favorite は dockside を代入した時点の "Dockside" のまま。
    // - dockside = dockside + " Diner" は、元の文字列を変えるのではなく、新しい "Dockside Diner" を同じ変数名に代入し直す。
    // - plan は StringBuilder なので、append(favorite) により "Next: Dockside" になる。
    // - そのため出力は "Next: Dockside / Dockside Diner"。
}
