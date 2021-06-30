package com.bmdb.web;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.bmdb.business.Actor;
import com.bmdb.business.Credit;
import com.bmdb.db.ActorRepo;
import com.bmdb.db.CreditRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/credits")
public class CreditController {

	@Autowired
	private CreditRepo creditRepo;
	@Autowired
	private ActorRepo actorRepo;
	
	@GetMapping("/")
	public Iterable<Credit> getAll() {
		return creditRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Credit> get(@PathVariable Integer id) {
		return creditRepo.findById(id);
	}
	
	@PostMapping("/")
	public Credit add(@RequestBody Credit credit) {
		return creditRepo.save(credit);
	}
	
	@PutMapping("/")
	public Credit update(@RequestBody Credit credit) {
		return creditRepo.save(credit);
	}
	
	@DeleteMapping("/")
	public Optional<Credit> delete(@PathVariable Integer id) {
		Optional<Credit> credit=creditRepo.findById(id);
		if (credit.isPresent()) {
			try {
			creditRepo.deleteById(id);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during credit delete");
			}
		}
		else {
			System.err.println("Credit delete error- no credit found for id"+id);
		}
		return credit;
	}
	
	//custom queries
	@GetMapping("/movie/{id}")
	public Iterable<Credit> getAllByMovie(@PathVariable int id) {
		//Optional<Movie> movie= movieRepo.findById(id);
		//return creditRepo.findAllByMovie(movie.get());
		return creditRepo.findAllByMovieId(id);
	}
	
	@GetMapping("/actor/{id}")
	public Iterable<Credit> getAllByActor(@PathVariable int id) {
		Optional<Actor> actor= actorRepo.findById(id);
		return creditRepo.findAllByActor(actor.get());
	}
	
	
}
