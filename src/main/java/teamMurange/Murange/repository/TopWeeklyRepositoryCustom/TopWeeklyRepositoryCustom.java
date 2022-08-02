package teamMurange.Murange.repository.TopWeeklyRepositoryCustom;

import teamMurange.Murange.domain.TopDaily;
import teamMurange.Murange.domain.TopWeekly;

import java.util.List;

public interface TopWeeklyRepositoryCustom {

    // 8일전 노래 삭제하기
    List<TopWeekly> deleteLastTopWeekly();


}
