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
@Table(name = "TB_LOGIN")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LOGIN")
    @SequenceGenerator(name = "SQ_LOGIN", sequenceName = "SQ_LOGIN", allocationSize = 1)
    @Column(name = "ID_LG")
    private Long id;

    @Column(name = "US_LG")
    private String username;

    @Column(name = "SN_LG")
    private String password;

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(
            name = "TB_MORADOR",
            referencedColumnName = "ID_MRD",
            foreignKey = @ForeignKey(
                    name = "Login_Morador_FK"
            )
    )

    private Morador morador;




}
