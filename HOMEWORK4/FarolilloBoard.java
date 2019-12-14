package aima.core.environment.farolillo;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

import java.util.Arrays;

public class FarolilloBoard {
    // MOVIMIENTO INDIVIDUAL

    public static Action M1 = new DynamicAction("M1");
    public static Action M2 = new DynamicAction("M2");
    public static Action M3 = new DynamicAction("M3");
    public static Action M4 = new DynamicAction("M4");
    // MOVIMIENTO PAREJAS
    public static Action M1M2 = new DynamicAction("M1M2");
    public static Action M1M3 = new DynamicAction("M1M3");
    public static Action M1M4 = new DynamicAction("M1M3");

    public static Action M2M3 = new DynamicAction("M2M3");
    public static Action M2M4 = new DynamicAction("M2M4");

    public static Action M3M4 = new DynamicAction("M3M4");

    private boolean[] state;
    private int time;

    public FarolilloBoard() {
        /**
         * Se utiliza un array de datos Booleanos(true / false).Cada posición
         * representa si el farol(indice 0 del array) o la persona determinada
         * por el índice del array(de 1 a cuatro) se encuentra en la orilla
         * inicia (valor false) o en la de destino (valor true) this constructor
         * will set the whole array as false
         *
         */
        state = new boolean[5];
        time = 15;
    }

    public FarolilloBoard(boolean[] state) {
        this.state = new boolean[state.length];
        System.arraycopy(state, 0, this.state, 0, state.length);
    }

    public FarolilloBoard(FarolilloBoard copyBoard) {
        this(copyBoard.getState());
    }

    public boolean[] getState() {
        return state;
    }

    public boolean getPersona1() {
        return state[0];
    }

    public boolean getPersona2() {
        return state[1];
    }

    public boolean getPersona3() {
        return state[2];
    }

    public boolean getPersona4() {
        return state[3];
    }
    // si es true el farol esta en lado inicial (izquierda).
    //gracias bro gonzalo

    public boolean getFarol() {
        return state[4];
    }

    public int getTiempo() {
        return time;
    }

    public void movePerson1() {
        state[4] = !state[4];
        state[0] = !state[0];
        time -= (int) Math.pow(2, 0);
    }

    public void movePerson2() {
        state[4] = !state[4];
        state[1] = !state[1];
        time -= (int) Math.pow(2, 1);
    }

    public void movePerson3() {
        state[4] = !state[4];
        state[2] = !state[2];
        time -= (int) Math.pow(2, 2);
    }

    public void movePerson4() {
        state[4] = !state[4];
        state[3] = !state[3];
        time -= (int) Math.pow(2, 3);
    }

    public void movePerson1Person2() {
        state[4] = !state[4];
        state[0] = !state[0];
        state[1] = !state[1];
        time -= (int) Math.pow(2, 1);
    }

    public void movePerson1Person3() {
        state[4] = !state[4];
        state[0] = !state[0];
        state[2] = !state[2];
        time -= (int) Math.pow(2, 2);
    }

    public void movePerson1Person4() {
        state[4] = !state[4];
        state[0] = !state[0];
        state[3] = !state[3];
        time -= (int) Math.pow(2, 3);
    }

    public void movePerson2Person3() {
        state[4] = !state[4];
        state[1] = !state[1];
        state[2] = !state[2];
        time -= (int) Math.pow(2, 2);
    }

    public void movePerson2Person4() {
        state[4] = !state[4];
        state[1] = !state[1];
        state[3] = !state[3];
        time -= (int) Math.pow(2, 3);
    }

    public void movePerson3Person4() {
        state[4] = !state[4];
        state[2] = !state[2];
        state[3] = !state[3];
        time -= (int) Math.pow(2, 3);
    }

    // mirar 
    /**
     *
     * public static Action M1M2 = new DynamicAction("M1M2"); public static
     * Action M1M3 = new DynamicAction("M1M3"); public static Action M1M4 = new
     * DynamicAction("M1M3");
     *
     * public static Action M2M3 = new DynamicAction("M2M3"); public static
     * Action M2M4 = new DynamicAction("M2M4");
     *
     * public static Action M3M4 = new DynamicAction("M3M4");
     */
    public boolean canMoveBoat(Action accion) {
        boolean retVal = true;

        if (accion.equals(M1)) {
            //se mueve donde quiera bro
            retVal = (getFarol() == getPersona1() && !estadoPeligroso(0, -1));
        } else if (accion.equals(M2)) {

            retVal = (getFarol() == getPersona2() && !estadoPeligroso(1, -1));
        } else if (accion.equals(M3)) {

            retVal = (getFarol() == getPersona3() && !estadoPeligroso(2, -1));
        } else if (accion.equals(M4)) {

            retVal = (getFarol() == getPersona4() && !estadoPeligroso(3, -1));
        } else if (accion.equals(M1M2)) {

            retVal = (getFarol() == getPersona1() == getPersona2() && !estadoPeligroso(0, 1));
        } else if (accion.equals(M1M3)) {

            retVal = (getFarol() == getPersona1() == getPersona3() && !estadoPeligroso(0, 2));
        } else if (accion.equals(M1M4)) {

            retVal = (getFarol() == getPersona1() == getPersona4() && !estadoPeligroso(0, 3));
        } else if (accion.equals(M2M3)) {

            retVal = (getFarol() == getPersona2() == getPersona3() && !estadoPeligroso(1, 2));
        } else if (accion.equals(M2M4)) {

            retVal = (getFarol() == getPersona2() == getPersona4() && !estadoPeligroso(1, 3));
        } else if (accion.equals(M3M4)) {

            retVal = (getFarol() == getPersona3() == getPersona4() && !estadoPeligroso(2, 3));
        }

        return retVal;
    }

    private boolean estadoPeligroso(int p1, int p2) {
        int consumir;

        if (p1 > p2) {
            consumir = (int) Math.pow(2, p1);
        } else {
            consumir = (int) Math.pow(2, p2);
        }
        if ((getTiempo() - consumir) < 0) {
            return true;
        }
        return false;
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
        String retVal = "[" + state[0] + ", " + state[1] + ", " + state[2] + ", " + state[3] + "] farol: " + state[4];
        return retVal;
    }
}
