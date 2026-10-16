package org.docksidestage.bizfw.basic.buyticket;

// done hoshi Resultクラスも、TicketBoothの隣に独立ファイルで作るでいいかなと by jflute (2026/10/01)
public class TicketBuyResult {
    // TODO hoshi インスタンス変数、immutableにできるならimmutableにしちゃいましょう by jflute (2026/10/01)
    // つまり、finalをつける。(コンストラクターで受け取った固定値はfinalで保持しておきたい)
    // done jflute インスタンス変数がインスタンスで、そのインスタンス変数が変更される可能性がある場合はfinalについて、どうするのでしょうか(ticketのカウントが減る)
    // #1on1: そもそもfinalは、広い意味を持ってなくて...このticket変数のアドレスが差し変わらないこと... (2026/10/16)
    // しか示さない。ので、アドレスの先のインスタンスの中身がimmutableかmutableかは関与しない。
    // mutableオブジェクトだと、全体的なimmutable感は薄くなるが、
    // ちゃんと決まったmutableオブジェクト(Ticketインスタンス)を使い続けるためにfinalを付けると良い。
    // (jflute感想: mutableオブジェクトこそ、状態(インスタンス)が大事なので、差し変わらないようにしておきたい)
    private Ticket ticket;
    private int change;

    // TODO hosh 引数の順序と、インスタンス変数の定義順序が入れ替わってるので、ちょっとおっと思いやすい by jflute (2026/10/16)
    // コード体裁デザインの直感性を演出するってところで言うと、合わせた方が良い。
    public TicketBuyResult(int change, Ticket ticket) {
        this.ticket = ticket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
