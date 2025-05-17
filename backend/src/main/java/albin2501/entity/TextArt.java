package albin2501.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Check;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.util.Objects;

@Entity
public class TextArt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "text:art_seq")
    @SequenceGenerator(name = "art_seq", sequenceName = "art_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String art;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime time;

    @Check(constraints = "width > 0 AND width <= 256")
    @Column(nullable = false)
    private Long width;

    @Check(constraints = "height > 0 AND height <= 128")
    @Column(nullable = false)
    private Long height;
    
    public TextArt() { }

    public TextArt(Long id, String art, String name, LocalDateTime time, Long width, Long height) {
        this.id = id;
        this.art = art;
        this.name = name;
        this.time = time;
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArt() {
        return this.art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getWidth() {
        return this.width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return this.height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public TextArt id(Long id) {
        setId(id);
        return this;
    }

    public TextArt art(String art) {
        setArt(art);
        return this;
    }

    public TextArt name(String name) {
        setName(name);
        return this;
    }

    public TextArt time(LocalDateTime time) {
        setTime(time);
        return this;
    }

    public TextArt width(Long width) {
        setWidth(width);
        return this;
    }

    public TextArt height(Long height) {
        setHeight(height);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TextArt)) {
            return false;
        }
        TextArt textArt = (TextArt) o;
        return Objects.equals(id, textArt.id) && Objects.equals(art, textArt.art) && Objects.equals(name, textArt.name) && Objects.equals(time, textArt.time) && Objects.equals(width, textArt.width) && Objects.equals(height, textArt.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, art, name, time, width, height);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", art='" + getArt() + "'" +
            ", name='" + getName() + "'" +
            ", time='" + getTime() + "'" +
            ", width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            "}";
    }
}
