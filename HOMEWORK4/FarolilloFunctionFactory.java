package aima.core.environment.farolillo;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;

public class FarolilloFunctionFactory {

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
            FarolilloBoard board = (FarolilloBoard) state;

            Set<Action> actions = new LinkedHashSet<Action>();

            if (board.canMoveBoat(FarolilloBoard.M1)) {
                actions.add(FarolilloBoard.M1);
            }
            if (board.canMoveBoat(FarolilloBoard.M2)) {
                actions.add(FarolilloBoard.M2);
            }
            if (board.canMoveBoat(FarolilloBoard.M3)) {
                actions.add(FarolilloBoard.M3);
            }
            if (board.canMoveBoat(FarolilloBoard.M4)) {
                actions.add(FarolilloBoard.M4);
            }
            
            if (board.canMoveBoat(FarolilloBoard.M1M2)) {
                actions.add(FarolilloBoard.M1M2);
            }
            if (board.canMoveBoat(FarolilloBoard.M1M3)) {
                actions.add(FarolilloBoard.M1M3);
            }
            if (board.canMoveBoat(FarolilloBoard.M1M4)) {
                actions.add(FarolilloBoard.M1M4);
            }
            
            if (board.canMoveBoat(FarolilloBoard.M2M3)) {
                actions.add(FarolilloBoard.M2M3);
            }
            if (board.canMoveBoat(FarolilloBoard.M2M4)) {
                actions.add(FarolilloBoard.M2M4);
            }
            
            if (board.canMoveBoat(FarolilloBoard.M3M4)) {
                actions.add(FarolilloBoard.M3M4);
            }

            return actions;
        }
    }

    private static class MCResultFunction implements ResultFunction {

        public Object result(Object s, Action a) {
            FarolilloBoard board = (FarolilloBoard) s;

            if (FarolilloBoard.M1.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M1)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson1();
                return newBoard;
            } else if (FarolilloBoard.M2.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M2)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson2();
                return newBoard;
            } else if (FarolilloBoard.M3.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M3)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson3();
                return newBoard;
            } else if (FarolilloBoard.M4.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M4)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson4();
                return newBoard;
                
            } else if (FarolilloBoard.M1M2.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M1M2)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson1Person2();
                return newBoard;
            } else if (FarolilloBoard.M1M3.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M2M3)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson1Person3();
                return newBoard;
            } else if (FarolilloBoard.M1M4.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M1M4)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson1Person4();
                return newBoard;
                
            } else if (FarolilloBoard.M2M3.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M2M3)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson2Person3();
                return newBoard;
            } else if (FarolilloBoard.M2M4.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M2M4)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson2Person4();
                return newBoard;
                
            } else if (FarolilloBoard.M3M4.equals(a)
                    && board.canMoveBoat(FarolilloBoard.M2M3)) {
                FarolilloBoard newBoard = new FarolilloBoard(board);
                newBoard.movePerson3Person4();
                return newBoard;
            } 

            // The Action is not understood or is a NoOp
            // the result will be the current state.
            return s;
        }
    }
}