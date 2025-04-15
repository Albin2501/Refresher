package albin2501.entity;

public class TextArt {
    public String art;
    public Long id;

    public TextArt(String art, Long id) {
        this.art = art;
        this.id = id;
    }

    public TextArt() { }

    public String toString() {
        return (new StringBuilder("art: ").append(art).
        append('\n').append("id: ").append(id)).toString();
    }
}
