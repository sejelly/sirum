package teamMurange.Murange.repository.TopDailyRepositoryCustom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import teamMurange.Murange.domain.TopDaily;

import java.util.List;

import static teamMurange.Murange.domain.QTopDaily.topDaily;

public class TopDailyRepositoryImpl implements TopDailyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TopDailyRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public int countTopDaily () {
        return queryFactory
                .select(topDaily)
                .from(topDaily)
                .fetch()
                .size();
    };

    @Override
    public TopDaily searchLowestTopDaily() {
        return queryFactory
                .select(topDaily)
                .from(topDaily)
                .orderBy(topDaily.music.streamingCnt.asc())
                .fetchFirst();
    };

    @Override
    public List<TopDaily> getTopDailyAll() {
        return queryFactory
                .select(topDaily)
                .from(topDaily)
                .orderBy(topDaily.music.streamingCnt.desc())
                .limit(100)
                .fetch();
    };



}
