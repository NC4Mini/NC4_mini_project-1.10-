package com.nc.project.dto;

import com.nc.project.entity.Board;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFileDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private Long boardId;
}
