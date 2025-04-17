package albin2501.entity;

import java.time.LocalDateTime;

public class TextArt {
    public String art;
    public Long id;
    public String name;
    public LocalDateTime time;
    public Long width;
    public Long height;

    public TextArt(String art, Long id, String name, LocalDateTime time, Long width, Long height) {
        this.art = art;
        this.id = id;
        this.name = name;
        this.time = time;
        this.name = name;
        this.time = time;
        this.width = width;
        this.height = height;
    }

    public TextArt() { }

    public String toString() {
        return (new StringBuilder("art: ").append(art).
        append('\n').append("id: ").append(id).
        append('\n').append("name: ").append(name).
        append('\n').append("time: ").append(time).
        append('\n').append("width: ").append(width).
        append('\n').append("height: ").append(height)).toString();
    }
}
