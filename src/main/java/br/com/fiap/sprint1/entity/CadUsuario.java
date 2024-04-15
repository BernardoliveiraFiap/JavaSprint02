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
@Table(name = "TB_CAD_USUARIO")
public class CadUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAD_USUARIO")
    @SequenceGenerator(name = "SQ_CAD_USUARIO", sequenceName = "SQ_CAD_USUARIO", allocationSize = 1)
    @Column(name = "ID_CAUS")
    private Long id;

    @Column(name = "US_CA_US")
    private String us_ca_us;

    @Column(name = "SN_CA_US")
    private String sn_ca_us;

    @Column(name= "NM_CA_US")
    private String nm_ca_us;

    @Column(name = "CPF_CA_US")
    private String cpf_ca_us;

    @Column(name = "END_CA_US")
    private String end_ca_us;

    @Column(name = "FN_CA_US")
    private String fn_ca_us;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_RESP_COND",
            referencedColumnName = "ID_RESP_COND",
            foreignKey = @ForeignKey(
                    name = "CadUsuario_RespCond_FK"
            )
    )

    private RespCond ResCad;

}
