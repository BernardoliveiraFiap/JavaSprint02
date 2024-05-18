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
@Table(name = "TB_MORADOR")
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MORADOR")
    @SequenceGenerator(name = "SQ_MORADOR", sequenceName = "SQ_MORADOR", allocationSize = 1)
    @Column(name = "ID_MRD")
    private Long id;

    @Column(name = "NM_MRD")
    private String nome;

    @Column(name = "CPF_MRD")
    private String cpf;

    @Column(name = "FN_MRD")
    private String telefone;

    @Column(name = "END_MRD")
    private String endereco;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_RESP_COND",
            referencedColumnName = "ID_RESP_COND",
            foreignKey = @ForeignKey(
                    name = "Morador_RespCond_FK"
            )
    )

    private Responsavel responsavel;
}
