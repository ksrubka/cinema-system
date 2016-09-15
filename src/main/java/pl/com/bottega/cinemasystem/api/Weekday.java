package pl.com.bottega.cinemasystem.api;

enum Weekday {

    Sunday(1), Monday(2), Tuesday(3), Wednesday(4), Thursday(5),
    Friday(6), Saturday(7);

    private int order;

    Weekday(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
