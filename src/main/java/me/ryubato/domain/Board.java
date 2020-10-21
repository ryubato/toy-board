package me.ryubato.domain;

import lombok.*;
import me.ryubato.config.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "boards")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String name;

    @Builder
    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Board create(String name) {
        return Board.builder()
                .name(name).build();
    }
}

