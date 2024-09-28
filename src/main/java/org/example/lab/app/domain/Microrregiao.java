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
@Table(name = "MICRORREGIAO")
public class Microrregiao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CD_MICRORREGIAO")
    @SequenceGenerator(allocationSize = 1, name = "CD_MICRORREGIAO")
    @Column(name = "CD_MICRORREGIAO")
    private Long id;

    @Column(name = "NM_MICRORREGIAO", nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_MESORREGAO", nullable = false)
    private Mesorregiao mesorregiao;
}
