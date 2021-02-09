package com.jinnie.guestbook.service;

import com.jinnie.guestbook.dto.MovieDTO;
import com.jinnie.guestbook.dto.PageRequestDTO;
import com.jinnie.guestbook.dto.PageResultDTO;
import com.jinnie.guestbook.entity.Movie;
import com.jinnie.guestbook.entity.MovieImage;
import com.jinnie.guestbook.repository.MovieImageRepository;
import com.jinnie.guestbook.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO){
        Map<String,Object> entityMap = dtoToEntity(movieDTO);
        Movie movie= (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage->{
            imageRepository.save(movieImage);
        });
        return movieDTO.getMno();
    }

    @Override
    public MovieDTO getMovie(Long mno) {
        List<Object[]> result = movieRepository.getMovieWithAll(mno);
        Movie movie =(Movie)result .get(0)[0];

        List<MovieImage> movieImageList = new ArrayList<>();

        result.forEach(arr ->{
            MovieImage movieImage = (MovieImage)arr[1];
            movieImageList.add(movieImage);
        });
        Double avg= (Double) result.get(0)[2]; //평균평점
        Long reviewCnt= (Long)result.get(0)[3]; //리뷰개수

        return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);
        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie)arr[0],
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );
        return new PageResultDTO<>(result, fn);
    }
}
