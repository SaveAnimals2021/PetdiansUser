package org.petdians.user.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
    private String type;
    private String keyword;

    public PageRequestDTO() {

        this.page = 1;
        this.size = 10;

    }

    public Pageable getPageable(Sort sort) {

        return PageRequest.of(page-1, size, sort);

    }

}
