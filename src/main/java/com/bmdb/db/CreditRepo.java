package com.bmdb.db;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.bmdb.business.Actor;
import com.bmdb.business.Credit;
import com.bmdb.business.Movie;

public interface CreditRepo extends CrudRepository<Credit, Integer>{

	//get all credits by movie
	List<Credit> findAllByMovieId(int id);
	//List<Credit> findAllByMovie(Movie movie); <-this also works
	
	//get all credits by actor
	List<Credit> findAllByActor(Actor actor);
	
	
}
