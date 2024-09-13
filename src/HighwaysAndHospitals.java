/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 * <p>
 * Completed by: Landon Moceri
 *
 */

public class HighwaysAndHospitals {

    public static long cost(int n, int hospitalCost, int highwayCost, int[][] cities) {
        // If the hospital cost is less than the highway cost, we can simply build a hospital in each city
        // In this context, highways don't actually matter, so we don't even need to look at the graph
        // We can just return the number of cities times the hospital cost
        if (hospitalCost <= highwayCost) {
            return (long) n * hospitalCost;
        }

        // We want to treat each node as its own subgraph
        int numSubgraphs = n;

        // For each node, store the root node of its tree
        int[] rootNodes = new int[n+1];
        // Initialize each node to be its own root, so we only need to merge subgraphs
        for (int i = 0; i < rootNodes.length; i++) {
            rootNodes[i] = i;
        }

        // TODO iterate over each edge in cities and build the subgraphs
        for (int[] city : cities) {
            int edgeStart = city[0];
            int edgeEnd = city[1];

            // Get the root nodes of the two subgraphs of edgeStart and edgeEnd
            int startRoot = rootNodes[edgeStart];
            int endRoot = rootNodes[edgeEnd];

            // We only need to merge subgraphs if they do not hold the same root, otherwise they are different
            if (startRoot != endRoot) {
                // Merge the two subgraphs
                int toChange = rootNodes[edgeEnd];
                for (int i = 0; i < rootNodes.length; i++) {
                    if (rootNodes[i] == toChange) {
                        rootNodes[i] = rootNodes[edgeStart];
                    }
                }

                // Reflect the merge in our total count
                numSubgraphs--;
            }
        }

        // Now we have all subgraphs processed, we can calculate the cost using the number of subgraphs
        int numHighways = n - numSubgraphs;
        int numHospitals = numSubgraphs;

        return (long) numHighways * highwayCost + (long) numHospitals * hospitalCost;
    }
}
