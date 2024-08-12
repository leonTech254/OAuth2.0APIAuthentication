package com.leonteqsecurity.cysec.Services;

import com.leonteqsecurity.cysec.Models.MoviesModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private List<MoviesModel> moviesModels;

    public MovieService() {
        moviesModels = new ArrayList<>();
        moviesModels.add(new MoviesModel(1, "The Matrix", "1999", "Sci-Fi"));
        moviesModels.add(new MoviesModel(2, "WarGames", "1983", "Thriller"));
        moviesModels.add(new MoviesModel(3, "Hackers", "1995", "Drama"));
        moviesModels.add(new MoviesModel(4, "The Fifth Estate", "2013", "Biography"));
        moviesModels.add(new MoviesModel(5, "Snowden", "2016", "Biography"));
        moviesModels.add(new MoviesModel(6,"Blindspot","2015","Crime"));

    }

    public List<MoviesModel> getMoviesModels() {
        return moviesModels;
    }
}
