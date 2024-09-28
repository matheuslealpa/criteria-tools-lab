package org.example.lab.app.domain.preferences;

import jakarta.persistence.*;
import lombok.*;
import org.example.lab.app.domain.enums.SettingsType;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SETTINGS")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SETTINGS")
    @SequenceGenerator(allocationSize = 1, name = "SQ_SETTINGS")
    @Column(name = "SQ_SETTINGS")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    @Check(constraints = "TYPE IN ('STRING', 'LONG', 'BYTE_ARRAY')")
    private SettingsType tipo;

    @Column(name = "VALUE_STRING")
    private String valorString;

    @Column(name = "VALUE_LONG")
    private Long valorLong;

    @Lob
    @Column(name = "VALUE_BYTE")
    private byte[] valorByte;

    public void setValue(Object valor) {
        if (valor instanceof String) {
            this.valorString = (String) valor;
            this.valorLong = null;
            this.valorByte = null;
            this.tipo = SettingsType.STRING;
        } else if (valor instanceof Long) {
            this.valorLong = (Long) valor;
            this.valorString = null;
            this.valorByte = null;
            this.tipo = SettingsType.LONG;
        } else if (valor instanceof byte[]) {
            this.valorByte = (byte[]) valor;
            this.valorString = null;
            this.valorLong = null;
            this.tipo = SettingsType.BYTE_ARRAY;
        }
    }

    public Object getValue() {
        switch (this.tipo) {
            case STRING:
                return valorString;
            case LONG:
                return valorLong;
            case BYTE_ARRAY:
                return valorByte;
            default:
                throw new IllegalStateException("Unknown type: " + this.tipo);
        }
    }
}
