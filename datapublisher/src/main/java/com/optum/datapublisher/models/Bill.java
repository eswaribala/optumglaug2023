package com.optum.datapublisher.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private long billNo;
    private long billAmount;
    private LocalDate billDate;
    private String adharCardNo;
}
