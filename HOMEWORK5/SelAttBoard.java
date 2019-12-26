/*
 * The MIT License
 *
 * Copyright 2019 kzr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package aima.core.environment.atributos;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import java.util.Arrays;

/**
 *
 * @author kzr
 */
public class SelAttBoard {

    public static Action A1 = new DynamicAction("a1");
    public static Action A2 = new DynamicAction("a2");
    public static Action A3 = new DynamicAction("a3");
    public static Action A4 = new DynamicAction("a4");
    public static Action A5 = new DynamicAction("a5");
    public static Action A6 = new DynamicAction("a6");
    public static Action A7 = new DynamicAction("a7");
    public static Action A8 = new DynamicAction("a8");
    public static Action A9 = new DynamicAction("a9");

    private int[] state;

    private final double[][] correlation = {{0.64491, 0.65459, 0.48636, 0.52182,
        0.58730, 0.55843, 0.53583, 0.35003}, {0.90688, 0.70558, 0.75180, 0.68680,
        0.75572, 0.72286, 0.45869}, {0.68308, 0.71967, 0.70961, 0.73595, 0.71945,
        0.43891}, {0.59960, 0.66505, 0.66672, 0.60335, 0.41763}, {0.58126,
        0.61610, 0.62888, 0.47910}, {0.67590, 0.57736, 0.33874}, {0.66588, 0.34417},
    {0.42834}};

    public SelAttBoard() {
        state = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public SelAttBoard(int[] state) {
        this.state = new int[state.length];
        System.arraycopy(state, 0, this.state, 0, state.length);
    }

    public SelAttBoard(SelAttBoard copyBoard) {
        this(copyBoard.getState());
    }

    SelAttBoard(int boardSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] getState() {
        return state;
    }

    public int getA1() {
        return state[0];
    }

    public int getA2() {
        return state[1];
    }

    public int getA3() {
        return state[2];
    }

    public int getA4() {
        return state[3];
    }

    public int getA5() {
        return state[4];
    }

    public int getA6() {
        return state[5];
    }

    public int getA7() {
        return state[6];
    }

    public int getA8() {
        return state[7];
    }

    public int getA9() {
        return state[8];
    }

    public int getFarol() {
        return state[4];
    }

    public int getTiempo() {
        return state[5];
    }

    public void SelA1() {
        state[0] = 1 - state[0];
    }

    public void SelA2() {
        state[1] = 1 - state[1];
    }

    public void SelA3() {
        state[2] = 1 - state[2];
    }

    public void SelA4() {
        state[3] = 1 - state[3];
    }

    public void SelA5() {
        state[4] = 1 - state[4];
    }

    public void SelA6() {
        state[5] = 1 - state[5];
    }

    public void SelA7() {
        state[6] = 1 - state[6];
    }

    public void SelA8() {
        state[7] = 1 - state[7];
    }

    public void SelA9() {
        state[8] = 1 - state[8];
    }

    public boolean canMove(Action where) {

        boolean retVal = true;

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
        SelAttBoard aBoard = (SelAttBoard) o;

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
