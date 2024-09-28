package org.example.lab.app.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CIDADE")
public class Cidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CD_CIDADE")
    @SequenceGenerator(allocationSize = 1, name = "CD_CIDADE")
    @Column(name = "CD_CIDADE")
    private Long id;


    @Column(name = "NM_CIDADE", nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_MICRORREGIAO", nullable = false)
    private Microrregiao microrregiao;
}
