package org.example.lab.app.domain.preferences;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "SETTINGS_TYPE")
public class SettingsType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SETTINGS_TYPE")
    @SequenceGenerator(allocationSize = 1, name = "SQ_SETTINGS_TYPE")
    @Column(name = "SQ_SETTINGS_TYPE")
    private Long id;

    @Column(name = "NAME")
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "settings_map",
            joinColumns = @JoinColumn(name = "SQ_SETTINGS_TYPE"),
            inverseJoinColumns = @JoinColumn(name = "SQ_SETTINGS")
    )
    @MapKeyColumn(name = "key")
    private Map<String, Settings> configuracoes;
}
