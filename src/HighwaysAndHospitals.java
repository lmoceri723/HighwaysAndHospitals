/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Landon Moceri
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     *
     *  Plan:
     *  If the hospital cost is less than the highway cost, we can simply build a hospital in each city
     *  In this context, highways don't actually matter, so we don't even need to look at the graph
     *  We can just return the number of cities times the hospital cost
     *
     *  Have an ArrayList to store all found subgraphs (clusters of towns)
     *  For each town, store the root node of its graph in a map
     *
     *  Now iterate over each edge in cities and build the subgraphs
     *
     *  Five cases, first is both towns are not in a subgraph
     *  So create a subgraph
     *
     *  Second is first is in a subgraph and second is not
     *  So add the second to the first's subgraph
     *
     *  Third is first is not in a subgraph and second is
     *  So add the first to the second's subgraph
     *
     *  Fourth is both are in different subgraphs
     *  So merge the two subgraphs
     *
     *  Final case is both are already in the same subgraph
     *  So do nothing
     *
     *  Because we know that highways are cheaper, we only want one highway per subgraph
     *  This makes calculating the cost super easy
     *  Add num subgraphs * hospital cost and n - num subgraphs * highway cost
     */

    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        return 0;
    }
}
