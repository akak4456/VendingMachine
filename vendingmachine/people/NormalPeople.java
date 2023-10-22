package vendingmachine.people;

import vendingmachine.material.discrete.Metal;
import vendingmachine.material.discrete.Paper;
import vendingmachine.material.discrete.Plastic;

import java.util.ArrayList;
import java.util.List;

/**
 * 일반적인 사람을 나타냅니다.
 */
public class NormalPeople {
    private ArrayList<Paper> papers;
    private ArrayList<Metal> metals;

    private ArrayList<Plastic> plastics;

    public NormalPeople(ArrayList<Paper> papers, ArrayList<Metal> metals, ArrayList<Plastic> plastics) {
        this.papers = papers;
        this.metals = metals;
        this.plastics = plastics;
    }

    public boolean containsPaper(Paper paper) {
        return papers.contains(paper);
    }

    public void removePaper(Paper paper) {
        papers.remove(paper);
    }

    public boolean containsMetal(Metal metal) {
        return metals.contains(metal);
    }

    public void removeMetal(Metal metal) {
        metals.remove(metal);
    }

    public void addAllPaper(List<Paper> papers) {
        this.papers.addAll(papers);
    }

    public void addAllMetal(List<Metal> metals) {
        this.metals.addAll(metals);
    }

    public Paper getPaperAt(int idx) {
        return papers.get(idx);
    }

    public boolean containsPlastic(Plastic plastic) {
        return plastics.contains(plastic);
    }

    public Plastic getPlasticAt(int idx) {
        return plastics.get(idx);
    }
}
