package org.kent.board.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.kent.board.dto.PageRequestDTO;
import org.kent.board.entity.Board;
import org.kent.board.entity.QBoard;
import org.kent.board.entity.QMember;
import org.kent.board.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {


    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public SearchBoardRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board searchOne() {
        log.info("=========== SEARCH ONE =============");
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        // == select * from Board b;
        JPQLQuery<Board> jpqlQuery = from(board);

        // 이렇게 하면 on 조건이 없어도 된다. (나에게 참조가 있기 때문이다.)
        //jpqlQuery.leftJoin(board.writer);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        // == select b from Board b;
        // jpqlQuery.select(board);

        // 조건문 생성
        BooleanExpression ex = board.bno.eq(99L);

        // jpqlQuery.select(board, member, reply, reply).where(ex);
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count()).where(ex).groupBy(board);



        // 실제 쿼리를 날리기 위해서 fetch를 사용
        // List<Board> result = jpqlQuery.fetch();
        // Tuple로 받았다면 이렇게...
        List<Tuple> result = tuple.fetch();

        // Long count = jpqlQuery.fetchCount();
        Long count = (long)result.size();

        log.info(jpqlQuery);
        log.info(result);
        log.info(count);

        log.info("=========== SEARCH ONE OVER =============");

        return null;
    }

    @Override
    public Page<Object[]> searchPage(PageRequestDTO pageRequestDTO) {
        log.info("=========== SEARCH PAGE =============");

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        // 1. 테이블 선택
        JPQLQuery<Board> jpqlQuery = from(board);

        // 2. 조인
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        // 3. 조건 생성
        BooleanBuilder bb = new BooleanBuilder();
        BooleanExpression be = board.bno.gt(0L);

        // and bno > 0
        bb.and(be);

        // 검색 조건문 생성
        BooleanBuilder condition = getCondition(pageRequestDTO, board, member, reply);

        log.info("condition : " + condition);

        if(null != condition){
            bb.and(condition);
        }

        // 4. 조건문 삽입...
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count()).where(bb).groupBy(board);

        // 4-1. sort 설정
        Pageable page = pageRequestDTO.getPageable();
        getOrderSpecifier(page.getSort()).forEach(o->{
            tuple.orderBy(o);
        });

        tuple.offset(page.getOffset());
        tuple.limit(page.getPageSize());

        // 5. 쿼리 실행
        List<Tuple> result = tuple.fetch();

        // 6. count 가져오기
        long count = tuple.fetchCount();

        // 7. Tuple을 Object[]로 변환
        List<Object[]> objectList = result.stream().map(tempTuple->tempTuple.toArray()).collect(Collectors.toList());
        
        // 8. Page<>로 변환
        return new PageImpl<Object[]>(objectList, page, count);
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort){
        List<OrderSpecifier> result = new ArrayList<>();

        sort.stream().forEach(s->{
            // bno 꺼내기
            String property = s.getProperty();

            // order 꺼내기
            Order direction = s.isAscending() ? Order.ASC : Order.DESC;

            PathBuilder<Board> orderbyExpression = new PathBuilder<Board>(
                    Board.class, "board"
            );

            // order 설정
            // tuple.orderBy(new OrderSpecifier(direction, orderbyExpression.get(property)));
            result.add(new OrderSpecifier(direction, orderbyExpression.get(property)));

        });

        return result;
    }


    private BooleanBuilder getCondition(PageRequestDTO pageRequestDTO, QBoard board, QMember member, QReply reply){
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder condition = null;
        // method로 빼면 좋다
        if(null != type){
            condition = new BooleanBuilder();
            String[] typeArr = type.split("");

            log.info(Arrays.toString(typeArr));

            for(String t : typeArr){
                switch(t){
                    case "t":
                        condition.or(board.title.contains(keyword));
                        break;

                    case "w":
                        condition.or(member.name.contains(keyword));
                        break;

                    case "c":
                        condition.or(board.content.contains(keyword));
                        break;

                    case "r":
                        condition.or(reply.text.contains(keyword));
                        break;
                }
            } // end for

        } // end if

        return condition;
    }

}
