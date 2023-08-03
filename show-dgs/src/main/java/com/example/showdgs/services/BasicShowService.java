package com.example.showdgs.services;

import com.example.showdgs.models.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicShowService implements ShowService{

    @Autowired
    private Faker faker;

    private List<Show> showList;
    @Override
    public List<Show> shows() {
        showList=new ArrayList<Show>();
        for(int i=0;i<100;i++) {
            var show = Show.builder()
                    .id(faker.country().countryCode2())
                    .releaseYear(faker.random().nextInt())
                    .title(faker.show().play())
                    .build();
            showList.add(show);
        }
        return showList;
    }
}
