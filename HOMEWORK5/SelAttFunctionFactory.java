package aima.core.environment.atributos;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;

/**
 * Provides useful functions for two versions of the n-queens problem. The
 * incremental formulation and the complete-state formulation share the same
 * RESULT function but use different ACTIONS functions.
 *
 * @author Ciaran O'Reilly
 * @author R. Lunde
 */
public class SelAttFunctionFactory {

    private static ActionsFunction _actionsFunction = null;
    private static ResultFunction _resultFunction = null;

    public static ActionsFunction getActionsFunction() {
        if (null == _actionsFunction) {
            _actionsFunction = new MCActionsFunction();
        }
        return _actionsFunction;
    }

    public static ResultFunction getResultFunction() {
        if (null == _resultFunction) {
            _resultFunction = new MCResultFunction();
        }
        return _resultFunction;
    }

    private static class MCActionsFunction implements ActionsFunction {

        public Set<Action> actions(Object state) {
            SelAttBoard board = (SelAttBoard) state;

            Set<Action> actions = new LinkedHashSet<Action>();

            actions.add(SelAttBoard.A1);
            actions.add(SelAttBoard.A2);
            actions.add(SelAttBoard.A3);
            actions.add(SelAttBoard.A4);
            actions.add(SelAttBoard.A5);
            actions.add(SelAttBoard.A6);
            actions.add(SelAttBoard.A7);
            actions.add(SelAttBoard.A8);
            actions.add(SelAttBoard.A9);
            return actions;
        }
    }

    private static class MCResultFunction implements ResultFunction {

        public Object result(Object s, Action a) {
            SelAttBoard board = (SelAttBoard) s;

            if (SelAttBoard.A1.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA1();
                return newBoard;
            } else if (SelAttBoard.A2.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA2();
                return newBoard;
            } else if (SelAttBoard.A3.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA3();
                return newBoard;
            } else if (SelAttBoard.A4.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA4();
                return newBoard;
            } else if (SelAttBoard.A5.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA5();
                return newBoard;
            } else if (SelAttBoard.A6.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA6();
                return newBoard;
            } else if (SelAttBoard.A7.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA7();
                return newBoard;
            } else if (SelAttBoard.A8.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA8();
                return newBoard;
            } else if (SelAttBoard.A9.equals(a)) {
                SelAttBoard newBoard = new SelAttBoard(board);
                newBoard.SelA9();
                return newBoard;
            }
            // The Action is not understood or is a NoOp
            // the result will be the current state.
            return s;
        }
    }
}
