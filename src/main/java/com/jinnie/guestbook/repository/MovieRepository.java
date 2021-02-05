package com.jinnie.guestbook.repository;

import com.jinnie.guestbook.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
