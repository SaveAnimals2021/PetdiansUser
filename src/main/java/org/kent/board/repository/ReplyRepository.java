package org.kent.board.repository;

import org.kent.board.entity.Board;
import org.kent.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository  extends JpaRepository<Reply, Long> {

    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(Long bno);

    // 게시글에 해당하는 댓글 모두 가져오기
    @Query("select r from Reply r where r.board.bno = :bno order by r.rno asc")
    List<Reply> getRepliesByBoard(Long bno);
}
