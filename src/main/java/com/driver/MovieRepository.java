package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieHashMap;
    HashMap<String, Director> directorHashMap;
    HashMap<String, List<String>> directorMovieMap;

    public MovieRepository() {
        this.movieHashMap = new HashMap<>();
        this.directorHashMap = new HashMap<>();
        this.directorMovieMap = new HashMap<>();
    }


    /*<----------- Get Methods ---------------->*/


    public Movie getMovieByName(String name) {
        return movieHashMap.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorHashMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return directorMovieMap.get(director);
    }

    public List<String> getAllMoviesName() {
        return new ArrayList<>(movieHashMap.keySet());
    }


    /*<----------- Post Methods ---------------->*/

    public void addMovie(Movie movie) {
        movieHashMap.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorHashMap.put(director.getName(), director);
    }

    /*<----------- Put Methods ---------------->*/

    public void addMovieDirectorPair(String movie ,String director) {

        if(movieHashMap.containsKey(movie) && directorHashMap.containsKey(director)) {
            List<String> listOfMovies = new ArrayList<>();
            if(directorMovieMap.containsKey(director)) {
                listOfMovies = directorMovieMap.get(director);
            }
            listOfMovies.add(movie);
            directorMovieMap.put(director, listOfMovies);
        }
    }

    /*<----------- Delete Methods ---------------->*/

    public void deleteDirector(String director) {

        if(directorMovieMap.containsKey(director)) {
            List<String> listOfMovies = new ArrayList<>();
            listOfMovies = directorMovieMap.get(director);

            for(String movie : listOfMovies) {
                movieHashMap.remove(movie);
            }
            directorMovieMap.remove(director);
        }

        directorHashMap.remove(director);
    }

    public void deleteAllDirectors() {

        HashSet<String> removeMovieSet = new HashSet<>();

        for(String director : directorMovieMap.keySet()) {
            removeMovieSet.addAll(directorMovieMap.get(director));
        }

        for(String movie : removeMovieSet) {
            movieHashMap.remove(movie);
        }
        directorHashMap.clear();
        directorMovieMap.clear();
    }
}
