package com.leonteqsecurity.cysec.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviesModel {
    private int Id;
    private String title;
    private String year;
    private String genre;
}
