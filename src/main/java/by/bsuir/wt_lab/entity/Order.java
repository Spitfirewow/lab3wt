package by.bsuir.wt_lab.entity;

public class Order extends Entity{
    private String dateTime;
    private boolean ready;
    private int userId;

    public Order() {
        super();
    }
    public Order(int id, String dateTime, boolean ready, int userId) {
        super(id);
        this.dateTime = dateTime;
        this.ready = ready;
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public boolean isReady() {
        return ready;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, dateTime:'%s', ready:%b, userId:%d}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                dateTime,
                ready,
                userId
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        final int SHIFT_BITS_COUNT = 16;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (dateTime == null ? 0 : dateTime.hashCode());
        hashCode = PRIME_NUMBER * hashCode + (ready ? 1 : 0);
        hashCode = PRIME_NUMBER * hashCode + (userId ^ userId >>> SHIFT_BITS_COUNT);
        return hashCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order other = (Order) o;
        return dateTime.equals(other.dateTime)
                && Boolean.compare(ready, other.ready) == 0
                && Integer.compare(userId, other.userId) == 0;
    }
}


