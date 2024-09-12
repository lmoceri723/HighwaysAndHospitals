import java.util.ArrayList;
import java.util.Arrays;

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

    public static class Subgraph {
        int rootNode;
        // We don't actually need to track the number of nodes
        // This is because we can take how many subgraphs exist and replace one node with a hospital in each one

        public Subgraph(int rootNode)
        {
            this.rootNode = rootNode;
        }
    }

    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        // If the hospital cost is less than the highway cost, we can simply build a hospital in each city
        // In this context, highways don't actually matter, so we don't even need to look at the graph
        // We can just return the number of cities times the hospital cost
        if (hospitalCost <= highwayCost) {
            return (long) n * hospitalCost;
        }

        // ArrayList to store all found subgraphs (clusters of towns)
        ArrayList<Subgraph> subgraphs = new ArrayList<>();

        // For each node, store the root node of its tree
        int[] rootNodes = new int[n+1];
        Arrays.fill(rootNodes, -1);

        // TODO iterate over each edge in cities and build the subgraphs
        for (int edge = 0; edge < cities.length; edge++)
        {
            int edgeStart = cities[edge][0];
            int edgeEnd = cities[edge][1];

            // If they are both not in any subgraph, create a new subgraph and add them to it
            if (rootNodes[edgeStart] == -1 && rootNodes[edgeEnd] == -1)
            {
                Subgraph newSubgraph = new Subgraph(edgeStart);
                subgraphs.add(newSubgraph);
                rootNodes[edgeStart] = edgeStart;
                rootNodes[edgeEnd] = edgeStart;
            }
            // If the start node is in a subgraph and the end node is not,
            // we only need to add the end node to the subgraph
            else if (rootNodes[edgeStart] != -1 && rootNodes[edgeEnd] == -1)
            {
                rootNodes[edgeEnd] = rootNodes[edgeStart];
            }
            // If the end node is in a subgraph and the start node is not, then we want to add the start node
            // to the end node's subgraph
            else if (rootNodes[edgeStart] == -1 && rootNodes[edgeEnd] != -1)
            {
                rootNodes[edgeStart] = rootNodes[edgeEnd];
            }
            // Now we know that these two nodes are both already in subgraphs.
            // We don't necessarily know if they are in the same subgraph, though, so we need to check if they're !=
            else if (rootNodes[edgeStart] != rootNodes[edgeEnd])
            {
                // Find the subgraph that the start node is in
                for (Subgraph subgraph : subgraphs)
                {
                    if (subgraph.rootNode == rootNodes[edgeStart])
                    {
                        // At this point, we need to merge the two subgraphs
                        int toChange = rootNodes[edgeEnd];
                        // TODO this also makes me wonder if there is a faster way to go
                        for (int i = 0; i < rootNodes.length; i++)
                        {
                            if (rootNodes[i] == toChange)
                            {
                                rootNodes[i] = rootNodes[edgeStart];
                            }
                        }

                        subgraphs.remove(subgraph);
                        break;
                    }
                }
            }
        }

        // Now we have all subgraphs processed, we can calculate the cost using the number of subgraphs
        int numHighways = n - subgraphs.size();
        int numHospitals = subgraphs.size();

        return (long) numHighways * highwayCost + (long) numHospitals * hospitalCost;
    }
}
