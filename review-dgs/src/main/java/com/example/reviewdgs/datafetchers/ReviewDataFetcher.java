package com.example.reviewdgs.datafetchers;

import com.example.reviewdgs.models.Review;
import com.example.reviewdgs.models.Show;

import com.netflix.graphql.dgs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DgsComponent
public class ReviewDataFetcher {
    Map<String, List<Review>> reviews = new HashMap<>();

    public ReviewDataFetcher() {
        List<Review> review1 = new ArrayList<>();
        review1.add(new Review(5));
        review1.add(new Review(4));
        review1.add(new Review(5));
        reviews.put("1", review1);

        List<Review> review2 = new ArrayList<>();
        review2.add(new Review(3));
        review2.add(new Review(5));
        reviews.put("2", review2);
    }

    @DgsEntityFetcher(name = "Show")
    public Show movie(Map<String, Object> values) {
        return new Show((String) values.get("id"), null);
    }

    @DgsData(parentType = "Show", field = "reviews")
    public List<Review> reviewsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Show show = dataFetchingEnvironment.getSource();
        return reviews.get(show.getId());
    }


}


