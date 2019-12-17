/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.farolillo;

import aima.core.search.framework.evalfunc.HeuristicFunction;

/**
 * @author Ravi Mohan
 *
 */
public class FarolilloHeuristicFunction implements HeuristicFunction {

    public double h(Object state) {
        FarolilloBoard board = (FarolilloBoard) state;
        return getNumberOfNeededMoves(board);
    }
    //heuristica teniendo en cuenta que no se necesita el farolillo para pasar 
    // de un lado a 
    private int getNumberOfNeededMoves(FarolilloBoard board) {
        int left = board.getPersonasleft();
        int pasos = 0;
        if (left / 2 > 0) {
            if (left < 2) {
                pasos = 1;
            } else {
                pasos = left / 2;
            }
        }
        return pasos;
    }
}
