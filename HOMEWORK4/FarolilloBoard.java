package aima.core.environment.farolillo;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import java.util.Arrays;

public class FarolilloBoard {

    public static Action M1 = new DynamicAction("M1");
    public static Action M2 = new DynamicAction("M2");
    public static Action M3 = new DynamicAction("M3");
    public static Action M4 = new DynamicAction("M4");
    public static Action M1M2 = new DynamicAction("M1M4");
    public static Action M1M3 = new DynamicAction("M1M3");
    public static Action M1M4 = new DynamicAction("M1M4");
    public static Action M2M3 = new DynamicAction("M2M3");
    public static Action M2M4 = new DynamicAction("M2M4");
    public static Action M3M4 = new DynamicAction("M3M4");

    private int[] state;

    private int[] timePerson = new int[]{1, 2, 4, 8};

    public FarolilloBoard() {
        state = new int[]{1, 1, 1, 1, 1, 15};
    }

    public FarolilloBoard(int[] state) {
        this.state = new int[state.length];
        System.arraycopy(state, 0, this.state, 0, state.length);
    }

    public FarolilloBoard(FarolilloBoard copyBoard) {
        this(copyBoard.getState());
    }

    public int[] getState() {
        return state;
    }

    public int getPersonasleft() {
        int ret = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 1) {
                ret++;
            }
        }
        return ret;
    }

    public int getP1() {
        return state[0];
    }

    public int getP2() {
        return state[1];
    }

    public int getP3() {
        return state[2];
    }

    public int getP4() {
        return state[3];
    }

    public int getFarol() {
        return state[4];
    }

    public int getTiempo() {
        return state[5];
    }

    public void movePerson1() {
        state[4] = 1 - state[4];
        state[0] = state[4];
        state[5] += timePerson[0];
    }

    public void movePerson2() {
        state[4] = 1 - state[4];
        state[1] = state[4];
        state[5] += timePerson[1];
    }

    public void movePerson3() {
        state[4] = 1 - state[4];
        state[2] = state[4];
        state[5] += timePerson[2];
    }

    public void movePerson4() {
        state[4] = 1 - state[4];
        state[3] = state[4];
        state[5] += timePerson[3];
    }

    public void movePerson1Person2() {
        state[4] = 1 - state[4];
        state[0] = state[4];
        state[1] = state[4];
        state[5] += timePerson[1];
    }

    public void movePerson1Person3() {
        state[4] = 1 - state[4];
        state[0] = state[4];
        state[2] = state[4];
        state[5] += timePerson[2];
    }

    public void movePerson1Person4() {
        state[4] = 1 - state[4];
        state[0] = state[4];
        state[3] = state[4];
        state[5] += timePerson[3];
    }

    public void movePerson2Person3() {
        state[4] = 1 - state[4];
        state[1] = state[4];
        state[2] = state[4];
        state[5] += timePerson[2];
    }

    public void movePerson2Person4() {
        state[4] = 1 - state[4];
        state[1] = state[4];
        state[3] = state[4];
        state[5] += timePerson[3];
    }

    public void movePerson3Person4() {
        state[4] = 1 - state[4];
        state[2] = state[4];
        state[3] = state[4];
        state[5] += timePerson[3];
    }

    public boolean canMove(Action where) {

        boolean retVal = true;

        if (where.equals(M1)) {

            retVal = (getP1() == getFarol()) && (getTiempo() + 1 <= 15);

        } else if (where.equals(M2)) {

            retVal = (getP2() == getFarol()) && (getTiempo() + 2 <= 15);

        } else if (where.equals(M3)) {

            retVal = (getP3() == getFarol()) && (getTiempo() + 4 <= 15);

        } else if (where.equals(M4)) {

            retVal = (getP4() == getFarol()) && (getTiempo() + 8 <= 15);

        } else if (where.equals(M1M2)) {

            retVal = (getP1() == getP2() && getFarol() == getP1())
                    && (getTiempo() + 2 <= 15);

        } else if (where.equals(M1M3)) {

            retVal = (getP1() == getP3() && getP1() == getFarol())
                    && (getTiempo() + 4 <= 15);

        } else if (where.equals(M1M4)) {

            retVal = (getP1() == getP4() && getP1() == getFarol())
                    && (getTiempo() + 8 <= 15);

        } else if (where.equals(M2M3)) {

            retVal = (getP2() == getP3() && getP2() == getFarol())
                    && (getTiempo() + 4 <= 15);

        } else if (where.equals(M2M4)) {

            retVal = (getP2() == getP4() && getP2() == getFarol())
                    && (getTiempo() + 8 <= 15);

        } else if (where.equals(M3M4)) {

            retVal = (getP3() == getP4() && getP3() == getFarol())
                    && (getTiempo() + 8 <= 15);

        }
        return retVal;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        FarolilloBoard aBoard = (FarolilloBoard) o;

        for (int i = 0; i < state.length; i++) {

            if (this.state[i] != aBoard.state[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.state);
        return hash;
    }

    @Override
    public String toString() {
        String retVal = "[" + state[0] + ", " + state[1] + ", " + state[2] + ", " + state[3] + "] " + state[4];
        return retVal;
    }
}
