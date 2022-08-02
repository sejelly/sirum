package teamMurange.Murange.repository.CalendarRepositoryCustom;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import teamMurange.Murange.domain.QUser;
import teamMurange.Murange.dto.CalendarResponseDto;

import static teamMurange.Murange.domain.QCalendar.calendar;

public class CalendarRepositoryImpl implements CalendarRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CalendarRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public CalendarResponseDto searchUserEmotion(Long userIdCond) {
        CalendarResponseDto result = queryFactory
                .select(Projections.fields(CalendarResponseDto.class,
                        calendar.date,
                        calendar.firstEmotion,
                        calendar.secondEmotion))
                .from(calendar)
                .join(calendar.user, QUser.user)
                .where(userIdEq(userIdCond))
                .orderBy(calendar.date.desc())
                .fetchFirst();
        return result;
    };

    private Predicate userIdEq(Long userIdCond) {
        return userIdCond != null ? QUser.user.id.eq(userIdCond) : null;
    }

}
