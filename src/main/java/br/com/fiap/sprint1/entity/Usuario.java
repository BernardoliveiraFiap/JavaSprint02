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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAD_USUARIO")
    @SequenceGenerator(name = "SQ_CAD_USUARIO", sequenceName = "SQ_CAD_USUARIO", allocationSize = 1)
    @Column(name = "ID_CAUS")
    private Long id;

    @Column(name = "US_CA_US")
    private String username;

    @Column(name = "SN_CA_US")
    private String password;

    @Column(name= "NM_CA_US")
    private String nome;

    @Column(name = "CPF_CA_US")
    private String cpf;

    @Column(name = "END_CA_US")
    private String endereco;

    @Column(name = "FN_CA_US")
    private String telefone;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_RESP_COND",
            referencedColumnName = "ID_RESP_COND",
            foreignKey = @ForeignKey(
                    name = "CadUsuario_RespCond_FK"
            )
    )

    private Responsavel responsavel;

}
