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
@Table(name = "TB_ADM_ESTOQUE")
public class Administrador{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ADM_ESTOQUE")
    @SequenceGenerator(name = "SQ_ADM_ESTOQUE", sequenceName = "SQ_ADM_ESTOQUE", allocationSize = 1)
    @Column(name = "ID_ADMEST")
    private Long id;

    @Column(name = "NM_ADMEST")
    private String nome;

    @Column(name = "US_ADMEST")
    private String usuario;

    @Column(name = "SN_ADMEST")
    private String senha;

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_LOGIN",
            referencedColumnName = "ID_LG",
            foreignKey = @ForeignKey(
                    name = "AdmEstoque_Login_FK"
            )
    )

    private Login login;



}
