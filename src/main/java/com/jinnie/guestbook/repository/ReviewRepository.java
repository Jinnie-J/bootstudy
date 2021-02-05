package com.jinnie.guestbook.repository;

import com.jinnie.guestbook.entity.Mmember;
import com.jinnie.guestbook.entity.Movie;
import com.jinnie.guestbook.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.mmember= :member")
    void deleteByMember(Mmember mmember);

}
