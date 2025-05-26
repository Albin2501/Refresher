package albin2501.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Check;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
