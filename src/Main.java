public class Main {

    public static void main(String[] args) {
        DFS dfs = new DFS();
        dfs.degreeHeuristic(); // omit this call if you don't want to reorder variables based off degree heuristic
        dfs.checklevel(0,dfs.stateVector);


    }
}
