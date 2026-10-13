package org.docksidestage.bizfw.basic.buyticket;

// TODO hoshi Resultクラスも、TicketBoothの隣に独立ファイルで作るでいいかなと by jflute (2026/10/01)
public class TicketBuyResult {
    // TODO hoshi インスタンス変数、immutableにできるならimmutableにしちゃいましょう by jflute (2026/10/01)
    // つまり、finalをつける。(コンストラクターで受け取った固定値はfinalで保持しておきたい)
    // TODO jflute インスタンス変数がインスタンスで、そのインスタンス変数が変更される可能性がある場合はfinalについて、どうするのでしょうか(ticketのカウントが減る)
    private Ticket ticket;
    private int change;

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
