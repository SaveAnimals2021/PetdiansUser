package org.kent.board.repository.search;

import org.kent.board.dto.PageRequestDTO;
import org.kent.board.entity.Board;
import org.springframework.data.domain.Page;

public interface SearchBoardRepository {

    Board searchOne();

    Page<Object[]> searchPage(PageRequestDTO pageRequestDTO);

}
