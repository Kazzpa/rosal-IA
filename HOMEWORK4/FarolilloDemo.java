/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.farolillo.*;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.*;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;

/**
 *
 * @author kzr
 */
public class FarolilloDemo {

    static FarolilloBoard boardInicial = new FarolilloBoard();

    public static void main(String[] args) {
        mcBreadthDemo();
        mcDepthGraphDemo();
    }

    private static void mcBreadthDemo() {
        System.out.println("\nFarolilloDemo breadth -->");
        try {
            Problem problem = new Problem(boardInicial, FarolilloFunctionFactory.getActionsFunction(),
                    FarolilloFunctionFactory.getResultFunction(), new FarolilloGoalTest());
            SearchForActions search = new BreadthFirstSearch();
            SearchAgent agent = new SearchAgent(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void mcDepthGraphDemo() {
        System.out.println("\nFarolilloDemo depth graph -->");
        try {
            Problem problem = new Problem(boardInicial, FarolilloFunctionFactory.getActionsFunction(),
                    FarolilloFunctionFactory.getResultFunction(), new FarolilloGoalTest());
            SearchForActions search = new DepthFirstSearch(new GraphSearch());
            SearchAgent agent = new SearchAgent(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printInstrumentation(Properties properties) {
        Iterator<Object> keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List<Action> actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = actions.get(i).toString();
            System.out.println(action);
        }
    }
}
