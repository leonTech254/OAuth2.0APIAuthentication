package com.leonteqsecurity.cysec.Controllers;


import com.leonteqsecurity.cysec.Models.MoviesModel;
import com.leonteqsecurity.cysec.Services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies/")
@AllArgsConstructor
public class MoviesController {
    private  final MovieService movieService   ;
    @GetMapping("")
    public List<MoviesModel> getallMoveis()
    {
        return  movieService.getMoviesModels();
    }
}
