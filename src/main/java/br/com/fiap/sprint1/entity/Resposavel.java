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
@Table(name = "TB_RESP_COND")
public class Resposavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESP_COND")
    @SequenceGenerator(name = "SQ_RESP_COND", sequenceName = "SQ_RESP_COND", allocationSize = 1)
    @Column(name = "ID_RESP_COND")
    private Long id;

    @Column(name = "NM_RESP_COND")
    private String nome;

    @Column(name = "CPF_RESP_COND")
    private String cpf;

    @Column(name = "FN_RESP_COND")
    private String telefone;
}
