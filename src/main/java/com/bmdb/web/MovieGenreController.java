package com.bmdb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.bmdb.business.Genre;
import com.bmdb.business.MovieGenre;
import com.bmdb.db.GenreRepo;
import com.bmdb.db.MovieGenreRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/movie-genres")
public class MovieGenreController {

	@Autowired
	private MovieGenreRepo movieGenreRepo;
	@Autowired
	private GenreRepo genreRepo;
	
	
	@GetMapping("/")
	public Iterable<MovieGenre> getAll() {
		return movieGenreRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<MovieGenre> get(@PathVariable int id) {
		return movieGenreRepo.findById(id);
	}
	
	@PostMapping("/")
	public MovieGenre add(@RequestBody MovieGenre movieGenre) {
		return movieGenreRepo.save(movieGenre);
	}
	
	@PutMapping("/")
	public MovieGenre update(@RequestBody MovieGenre movieGenre) {
		return movieGenreRepo.save(movieGenre);
	}
	
	@DeleteMapping("/{id}")
	public Optional<MovieGenre> delete(@PathVariable Integer id) {
		Optional<MovieGenre> movieGenre=movieGenreRepo.findById(id);
		if (movieGenre.isPresent()) {
			try {
			movieGenreRepo.deleteById(id);
			}
			catch (DataIntegrityViolationException dive) {
				//catch dive when movie exists as fk on another table
				System.err.println(dive.getRootCause().getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Foreign Key Constraint Issue- movie genre id: "+id+"is referred to elsewhere.");
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during movie genre delete");
			}
		}
		else {
			System.err.println("Movie genre delete error- no movie genre found for id"+id);
		}
		return movieGenre;
	}
	
	@GetMapping("/genre")
	public Iterable<MovieGenre> getAllByGenreStr(@RequestParam String genreStr) {
		Genre genre= genreRepo.findByName(genreStr);
		return movieGenreRepo.findAllByGenreId(genre.getId());
	}
	
	
	
}
