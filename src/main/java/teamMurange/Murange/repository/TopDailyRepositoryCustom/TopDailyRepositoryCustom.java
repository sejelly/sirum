package teamMurange.Murange.repository.TopDailyRepositoryCustom;

import teamMurange.Murange.domain.TopDaily;

import java.util.List;

public interface TopDailyRepositoryCustom {

    int countTopDaily();

    TopDaily searchLowestTopDaily();

    List<TopDaily> getTopDailyAll();

}
