package aima.core.environment.selatt;

import aima.core.search.framework.problem.GoalTest;

/**
 * @author R. Lunde
 */
public class SelAttGoalTest implements GoalTest {

    // devolverá siempre falso al no saber de antemano el mejor subconjunto.
    public boolean isGoalState(Object state) {
        return false;
    }
}
