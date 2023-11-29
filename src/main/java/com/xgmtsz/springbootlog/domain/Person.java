package com.xgmtsz.springbootlog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private BigDecimal num;
    private Integer level;
}
