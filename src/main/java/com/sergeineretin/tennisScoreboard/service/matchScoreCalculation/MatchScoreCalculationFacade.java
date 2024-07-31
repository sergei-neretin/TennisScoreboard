package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation;

import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.*;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl.*;

public class MatchScoreCalculationFacade {
    public static SetsUpdater getSetUpdater() {
        PointUpdater pointUpdater = new PointUpdaterImpl();
        GameUpdater gameUpdater = new GameUpdaterImpl(pointUpdater);
        TieBreakUpdater tieBreakUpdater = new TieBreakUpdaterImpl();
        return new SetsUpdaterImpl(gameUpdater, tieBreakUpdater);
    }

    public static WinnerGetter getWinnerGetter() {
        return new WinnerGetterImpl();
    }
}
