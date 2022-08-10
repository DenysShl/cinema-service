package cinema.spring.dao.impl;

import cinema.spring.dao.AbstractDao;
import cinema.spring.dao.MovieDao;
import cinema.spring.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory factory) {
        super(factory, Movie.class);
    }
}
