package by.bsuir.wt_lab.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;

public class Entity implements Serializable, Comparable<Entity> {
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    public Entity() {}
    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  String.format(
                "%s@%h {id:%d}",
                getClass().getSimpleName(),
                this,
                id
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        final int SHIFT_BITS_COUNT = 16;
        return PRIME_NUMBER + (id ^ id >>> SHIFT_BITS_COUNT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int compareTo(Entity o) {
        return Integer.compare(id, o.id);
    }
}
