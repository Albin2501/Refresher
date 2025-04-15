package albin2501.dto;

public class TextArtDto {
    public String art;

    public TextArtDto(String art) {
        this.art = art;
    }

    public TextArtDto() { }
    
    public String toString() {
        return (new StringBuilder("art: ").append(art)).toString();
    }
}
