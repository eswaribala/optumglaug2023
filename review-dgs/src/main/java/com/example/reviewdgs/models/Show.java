package com.example.reviewdgs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {
    private String id;

    private List<Review> reviews;


    @Override
    public String toString() {
        return "Show{" + "id='" + id + "'," +"reviews='" + reviews + "'" +"}";
    }

}
