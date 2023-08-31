package br.ufms.cpcx.api.gamersclub.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name="TB_GAME",
        uniqueConstraints = { @UniqueConstraint(name = "TB_GAME_UQ", columnNames = { "name", "console", "owner" })
        })
@Data
@NoArgsConstructor
public class GameModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConsoleEnum console;

    @Column(nullable = false, length = 100)
    private String owner;

    @Column(nullable = false, length = 15)
    private String ownerPhoneNumber;

}
