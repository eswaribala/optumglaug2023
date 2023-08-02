package com.optum.patientapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Bills")
public class OptumBill {
    @BsonId
    private long billNo;
    private long billAmount;
    private LocalDate billDate;
    private String adharCardNo;
}
