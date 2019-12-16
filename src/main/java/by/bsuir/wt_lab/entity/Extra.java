package by.bsuir.wt_lab.entity;

public class Extra extends Entity {
    private String name;
    private double price;

    public Extra() {
        super();
    }
    public Extra(int id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, name:'%s', price:%f}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                name,
                price
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (name == null ? 0 : name.hashCode());
        hashCode = PRIME_NUMBER * hashCode + (int) Double.doubleToLongBits(price);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Extra other = (Extra) o;
        return name.equals(other.name)
                && Double.compare(price, other.price) == 0;
    }

    public int compareTo(Extra o) {
        return name.compareTo(o.name);
    }
}
