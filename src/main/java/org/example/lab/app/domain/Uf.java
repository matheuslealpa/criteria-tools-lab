package org.example.lab.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

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
@Table(name = "UF", indexes = {
        @Index(name = "IDX_UF_SIGLA", columnList = "SG_UF")
})
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CD_UF")
    @SequenceGenerator(allocationSize = 1, name = "CD_UF")
    @Column(name = "CD_UF")
    private Long id;

    @Column(name = "NM_UF", unique = true, nullable = false)
    private String nome;

    @Column(name = "SG_UF", unique = true, nullable = false)
    private String sigla;

}
