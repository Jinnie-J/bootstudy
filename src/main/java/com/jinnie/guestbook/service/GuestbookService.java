package com.jinnie.guestbook.service;

import com.jinnie.guestbook.dto.GuestbookDTO;
import com.jinnie.guestbook.dto.PageRequestDTO;
import com.jinnie.guestbook.entity.Guestbook;
import com.jinnie.guestbook.dto.PageResultDTO;

public interface GuestbookService {
    //게시글 등록
    Long register(GuestbookDTO dto);
    //게시글 조회
    GuestbookDTO read(Long gno);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
