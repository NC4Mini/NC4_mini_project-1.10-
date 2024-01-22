package com.nc.project.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="board")
public class Board extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // 크기 255, null 가능
    private String boardCategory;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

}
