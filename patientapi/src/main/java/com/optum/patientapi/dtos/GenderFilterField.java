package com.optum.patientapi.dtos;

import com.optum.patientapi.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderFilterField {
  private String operator;
  private Gender value;
    public Predicate generateCriteria(CriteriaBuilder builder, Path field) {
        switch (operator) {
            case "endsWith": return builder.like(field, "%" + value);
            case "startsWith": return builder.like(field, value + "%");
            case "contains": return builder.like(field, "%" + value + "%");
            case "eq": return builder.equal(field, value);
        }

        return null;
    }

}
