package org.example.lab.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MESORREGIAO")
public class Mesorregiao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CD_MESORREGIAO")
    @SequenceGenerator(allocationSize = 1, name = "CD_MESORREGIAO")
    @Column(name = "CD_MESORREGIAO")
    private Long id;

    @Column(name = "NM_MESORREGIAO", nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_UF", nullable = false)
    private Uf uf;
}
