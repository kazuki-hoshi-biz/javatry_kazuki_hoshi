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
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4"); // amba = 9.4

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1); //bonvoのmonthが10(新しいLocalDateインスタンスを立ててそっちを参照)
        land = bonvo.getMonthValue(); //land = 10
        land--; //land = 9
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) {
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            if (amba == 2.3D) { //上のifに入りそうで、そこからここまでambaが関わっていないからここにも入りそう
                sea = (byte) amba;
            }
        }
        if ((int) dstore > piari) { //ここには入らなそう
            sea = 0;
        }
        log(sea); // your answer? => 2
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName(); // hangarしか文字出てきていない
        log(sea); // your answer? => hangar
    }

    // #1on1: immutableクラスの基本形 (2026/09/11)
    // finalは必須ではないが、付けてた方が安全で可読性も高い。
    private static class St3ImmutableStage {

        // インスタンス変数 (Stageがnewされたら、stageNameの空間も確保される)
        // finalを付いているインスタンス変数は、コンストラクターで初期化代入した後は...
        // メソッドなどで再代入ができないようになる。
        private final String stageName; // ここで変数宣言

        public St3ImmutableStage(String stageName/*引数変数*/) {
            // #1on1: 名前がバッティングしたら、スコープが短い方が優先される
            // thisは今の自分自身のインスタンスを指し示すオブジェクト
            // ベタに書くと savedStageName = acceptedStageName; だが、
            // もうそう書く人はいない。わかりきってるから。
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
