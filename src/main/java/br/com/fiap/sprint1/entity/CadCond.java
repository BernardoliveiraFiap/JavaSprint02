package br.com.fiap.sprint1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_CAD_COND")
public class CadCond {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAD_COND")
    @SequenceGenerator(name = "SQ_CAD_COND", sequenceName = "SQ_CAD_COND", allocationSize = 1)
    @Column(name = "ID_CAD_COND")
    private Long id;

    @Column(name = "CPNJ_CACO")
    private String cpnj_caco;

    @Column(name = "NM_CACO")
    private String nm_caco;

    @Column(name = "END_CACO")
    private String end_caco;

    @Column(name = "FN_CACO")
    private String fn_caco;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_RESP_COND",
            referencedColumnName = "ID_RESP_COND",
            foreignKey = @ForeignKey(
                    name = "CadCond_RespCond_FK"
            )
    )

    private RespCond cadastroCond;


}
