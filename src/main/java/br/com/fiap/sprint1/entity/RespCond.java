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
public class RespCond {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESP_COND")
    @SequenceGenerator(name = "SQ_RESP_COND", sequenceName = "SQ_RESP_COND", allocationSize = 1)
    @Column(name = "ID_RESP_COND")
    private Long id;

    @Column(name = "NM_RESP_COND")
    private String nm_resp_cond;

    @Column(name = "CPF_RESP_COND")
    private String cpf_resp_cond;

    @Column(name = "FN_RESP_COND")
    private String fn_resp_cond;
}
