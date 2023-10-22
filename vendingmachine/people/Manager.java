package vendingmachine.people;

import vendingmachine.material.discrete.Metal;
import vendingmachine.material.discrete.Paper;

import java.util.ArrayList;

public class Manager extends NormalPeople {
    public Manager(ArrayList<Paper> papers, ArrayList<Metal> metals) {
        super(papers, metals, null);
    }
}
