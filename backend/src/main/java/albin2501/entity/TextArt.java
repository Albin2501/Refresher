package albin2501.entity;

import java.time.LocalDateTime;

public class TextArt {
    private String art;
    private Long id;
    private String name;
    private LocalDateTime time;
    private Long width;
    private Long height;
    
    public TextArt() { }

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

    public String getArt() {
        return this.art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String toString() {
        return (new StringBuilder("art: ").append(art).
        append('\n').append("id: ").append(id).
        append('\n').append("name: ").append(name).
        append('\n').append("time: ").append(time).
        append('\n').append("width: ").append(width).
        append('\n').append("height: ").append(height)).toString();
    }
}
