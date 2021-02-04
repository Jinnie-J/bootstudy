package com.jinnie.guestbook.service;

import com.jinnie.guestbook.dto.BoardDTO;
import com.jinnie.guestbook.dto.PageRequestDTO;
import com.jinnie.guestbook.dto.PageResultDTO;
import com.jinnie.guestbook.entity.Board;
import com.jinnie.guestbook.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    //목록처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    //조회
    BoardDTO get(Long bno);

    //삭제
    void removeWithReplies(Long bno);

    //수정
    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){
        Member member= Member.builder().email(dto.getWriterEmail()).build();

        Board board= Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }
}
