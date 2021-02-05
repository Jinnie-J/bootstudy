package com.jinnie.guestbook.service;

import com.jinnie.guestbook.dto.MovieDTO;
import com.jinnie.guestbook.entity.Movie;
import com.jinnie.guestbook.entity.MovieImage;
import com.jinnie.guestbook.repository.MovieImageRepository;
import com.jinnie.guestbook.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

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
}
