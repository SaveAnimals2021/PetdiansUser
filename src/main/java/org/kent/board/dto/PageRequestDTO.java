package org.kent.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@Log4j2
public class PageRequestDTO {


    private int page;
    private int size;
    private String type;
    private String keyword;
    private Pageable pageable;

    public PageRequestDTO(){
        page = 1;
        size = 10;
        log.info(page + " , " + size);
    }

    public void setSort(Sort sort){
        pageable = PageRequest.of(page - 1, size, sort);
    }
}
