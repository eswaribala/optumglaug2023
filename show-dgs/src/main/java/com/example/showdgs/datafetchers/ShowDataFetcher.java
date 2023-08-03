package com.example.showdgs.datafetchers;

import com.example.showdgs.models.Show;
import com.example.showdgs.services.ShowService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ShowDataFetcher {
    @Autowired
    private ShowService showService;

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter){
       if(titleFilter!=null){
          return this.showService.shows().stream().filter(s->s.getTitle().contains(titleFilter)==true).collect(Collectors.toList());
       }
       else
           return this.showService.shows();

    }


}


