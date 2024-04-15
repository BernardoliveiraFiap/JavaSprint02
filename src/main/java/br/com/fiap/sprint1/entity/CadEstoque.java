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
public class CadEstoque {
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
    private String nm_caes;

    @Column(name = "END_CAES")
    private String end_caes;

    @Column(name = "CGD_CAES")
    private String cgd_caes;

    @Column(name = "STT_CAES")
    private String stt_caes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_ADM_ESTOQUE",
            referencedColumnName = "ID_ADMEST",
            foreignKey = @ForeignKey(
                    name = "CadEstoque_AdmEstoque_FK"
            )
    )

    private AdmEstoque admin;
}
