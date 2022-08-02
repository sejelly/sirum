package teamMurange.Murange.repository.TopWeeklyRepositoryCustom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import teamMurange.Murange.domain.QTopWeekly;
import teamMurange.Murange.domain.TopWeekly;
import teamMurange.Murange.repository.TopDailyRepositoryCustom.TopDailyRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

import static teamMurange.Murange.domain.QTopDaily.topDaily;

public class TopWeeklyRepositoryImpl implements TopWeeklyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TopWeeklyRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<TopWeekly> deleteLastTopWeekly(){
        return queryFactory
                .select(QTopWeekly.topWeekly)
                .from(QTopWeekly.topWeekly)
                .where(QTopWeekly.topWeekly.date.eq(LocalDate.now().minusDays(8)))
                .fetch();
    }


}
