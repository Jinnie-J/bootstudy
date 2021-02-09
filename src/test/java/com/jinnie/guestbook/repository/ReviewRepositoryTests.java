package com.jinnie.guestbook.repository;

import com.jinnie.guestbook.entity.Member;
import com.jinnie.guestbook.entity.Mmember;
import com.jinnie.guestbook.entity.Movie;
import com.jinnie.guestbook.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private MmemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMoviewReviews(){
        IntStream.rangeClosed(1,200).forEach(i->{
            //영화 번호
            Long mno = (long)(Math.random()* 100)+1;
            // 리뷰어 번호
            Long mid = ((long)(Math.random()*100)+1);
            Mmember mmember= Mmember.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .mmember(mmember)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();
            reviewRepository.save(movieReview);
        });
    }

    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder().mno(92L).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {
            System.out.println(movieReview.getReviewnum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMmember().getEmail());
            System.out.println("----------------------------------");
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        Long mid = 1L;
        Mmember member = Mmember.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }


}
