package com.jinnie.guestbook.service;

import com.jinnie.guestbook.dto.ReplyDTO;
import com.jinnie.guestbook.entity.Board;
import com.jinnie.guestbook.entity.Reply;

import java.util.List;

public interface ReplyService {

    //댓글 등록
    Long register(ReplyDTO replyDTO);
    //특정 게시물의 댓글 목록
    List<ReplyDTO> getList(Long bno);
    //댓글 수정
    void modify(ReplyDTO replyDTO);
    //댓글 삭제
    void remove(Long rno);
    //replydto 를 reply객체로 변환
    default Reply dtoToEntity(ReplyDTO replyDTO){
        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();
        return reply;
    }
    //reply객체를 replydto로 변환
    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
