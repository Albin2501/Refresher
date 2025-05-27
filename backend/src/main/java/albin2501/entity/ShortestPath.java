package albin2501.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Check;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShortestPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Check(constraints = "method1Time >= 0")
    @Column(nullable = false)
    private Long method1Time;

    @Check(constraints = "method2Time >= 0")
    @Column(nullable = false)
    private Long method2Time;
    
    @Check(constraints = "method3Time >= 0")
    @Column(nullable = false)
    private Long method3Time;

    @Check(constraints = "method4Time >= 0")
    @Column(nullable = false)
    private Long method4Time;

    @Column(nullable = false)
    private String startNode;

    @Column(nullable = false)
    private String endNode;

    @Check(constraints = "nodes_amount >= 0")
    @Column(nullable = false)
    private float nodesAmount;
}
