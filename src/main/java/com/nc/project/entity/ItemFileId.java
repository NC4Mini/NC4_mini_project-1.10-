package com.nc.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemFileId implements Serializable {
    private long item;
    private long itemFileId;
}
