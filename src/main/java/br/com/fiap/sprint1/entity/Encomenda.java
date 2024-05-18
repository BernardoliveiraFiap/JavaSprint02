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
@Table(name = "TB_ENCOMENDA")
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENCOMENDA")
    @SequenceGenerator(name = "SQ_ENCOMENDA", sequenceName = "SQ_ENCOMENDA", allocationSize = 1)
    @Column(name = "ID_EN")
    private Long id;

    @Column(name = "NM_EN")
    private String nome;

    @Column(name = "CPF_DEST_EN")
    private String cpf;

    @Column(name = "NM_DEST_EN")
    private String destinatario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_MORADOR",
            referencedColumnName = "ID_MRD",
            foreignKey = @ForeignKey(
                    name = "Encomenda_Morador_FK"
            )
    )

    private Morador pessoa;



    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_ADM_ESTOQUE",
            referencedColumnName = "ID_ADMEST",
            foreignKey = @ForeignKey(
                    name = "Encomenda_AdmEstoque_FK"
            )
    )

    private Administrador administrador;
}
