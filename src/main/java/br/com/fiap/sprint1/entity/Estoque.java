package br.com.fiap.sprint1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CAD_ESTOQUE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAD_ESTOQUE")
    @SequenceGenerator(
            name = "SQ_CAD_ESTOQUE",
            sequenceName = "SQ_CAD_ESTOQUE",
            initialValue = 1,
            allocationSize = 1

    )

    @Column(name = "ID_CAES")
    private Long id;

    @Column(name = "NM_CAES")
    private String nome;

    @Column(name = "END_CAES")
    private String endereco;

    @Column(name = "CGD_CAES")
    private String chegada;

    @Column(name = "STT_CAES")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_ADM_ESTOQUE",
            referencedColumnName = "ID_ADMEST",
            foreignKey = @ForeignKey(
                    name = "CadEstoque_AdmEstoque_FK"
            )
    )

    private Administrador administrador;
}
