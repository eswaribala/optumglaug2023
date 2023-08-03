package com.example.reviewdgs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private Integer starRating;


    @Override
    public String toString() {
        return "Review{" + "starRating='" + starRating + "'" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review that = (Review) o;
        return java.util.Objects.equals(starRating, that.starRating);
    }





}
