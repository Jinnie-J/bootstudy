package com.jinnie.guestbook.service;

import com.jinnie.guestbook.dto.ReviewDTO;
import com.jinnie.guestbook.entity.Member;
import com.jinnie.guestbook.entity.Mmember;
import com.jinnie.guestbook.entity.Movie;
import com.jinnie.guestbook.entity.Review;

import java.util.List;

public interface ReviewService {
    //영화의 모든 리뷰 출력
    List<ReviewDTO> getListOfMovie(Long mno);

    //영화 리뷰 추가
    Long register(ReviewDTO movieReviewDTO);

    //특정 영화 리뷰 수정
    void modify(ReviewDTO movieReviewDTO);

    //영화 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO){
        Review movieReview = Review.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .mmember(Mmember.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();
        return movieReview;
    }
    default ReviewDTO entityToDto(Review movieReview){
        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewnum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMmember().getMid())
                .nickname(movieReview.getMmember().getNickname())
                .email(movieReview.getMmember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate((movieReview.getModDate()))
                .build();

        return movieReviewDTO;
    }
}
