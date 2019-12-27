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

    private int[] state;

    private final double[][] correlation = {
        {0.64491, 0.65459, 0.48636, 0.52182, 0.58730, 0.55843, 0.53583, 0.35003},
        {0.90688, 0.70558, 0.75180, 0.68680, 0.75572, 0.72286, 0.45869},
        {0.68308, 0.71967, 0.70961, 0.73595, 0.71945, 0.43891,},
        {0.59960, 0.66505, 0.66672, 0.60335, 0.41763},
        {0.58126, 0.61610, 0.62888, 0.47910},
        {0.67590, 0.57736, 0.33874},
        {0.66588, 0.34417},
        {0.42834}};
    private final double[] correlationClass = {0.71600, 0.81790, 0.81893,
        0.69680, 0.68278, 0.81605, 0.75662, 0.71224, 0.42317};

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

    public int[] getState() {
        return state;
    }

    public void SelAtribute(int i) {
        state[i] = 1 - state[i];
    }

    public int getNumberOfAtributesSelected() {
        int res = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 1) {
                res++;
            }
        }
        return res;
    }

    public double getCorrelationAtr(int i, int j) {
        double res = 1;

        if (i != j) {
            //swap to get minimun of both index
            if (j < i) {
                int swap = i;
                i = j;
                j = swap;
            }
            //gets correlation from correlation matrix
            res = correlation[i][j];
        }
        return res;

    }

    public double getCorrelationClass(int i) {
        return correlationClass[i];
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
