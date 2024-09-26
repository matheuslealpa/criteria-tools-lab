package org.example.lab.app.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.lab.app.domain.enums.Regiao;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "UF", indexes = {
        @Index(name = "IDX_UF_SIGLA", columnList = "SG_UF"),
        @Index(name = "IDX_UF_REGIAO", columnList = "NM_REGIAO")
})
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CD_UF")
    @SequenceGenerator(allocationSize = 1, name = "CD_UF")
    @Column(name = "CD_UF")
    private Long id;

    @Column(name = "NM_UF")
    private String nome;

    @Column(name = "SG_UF")
    private String sigla;

    @Column(name = "NM_REGIAO")
    @Enumerated(EnumType.STRING)
    @Check(constraints = "NM_REGIAO IN ('NORTE', 'NORDESTE', 'CENTRO_OESTE', 'SUDESTE', 'SUL')")
    private Regiao regiao;

}
