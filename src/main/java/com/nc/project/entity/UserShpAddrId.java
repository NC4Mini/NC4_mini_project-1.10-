package com.nc.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserShpAddrId implements Serializable {
    private int addrId;
    private long userAccount;
}

