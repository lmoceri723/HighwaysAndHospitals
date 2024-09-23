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

    private static int find(int[] rootNodes, int node) {
        // First find the root of the tree
        int root = node;
        while (rootNodes[root] > 0) {
            root = rootNodes[root];
        }

        // Traverse over each node leading to the root and set its parent to the root
        while (rootNodes[node] > 0) {
            int parent = rootNodes[node];
            rootNodes[node] = root;
            node = parent;
        }
        return node;
    }



    private static int union(int[] rootNodes, int root1, int root2) {
        // If the roots are different, we merge the two subgraphs
        if (root1 != root2) {
            // Merge the smaller subgraph into the larger one
            if (rootNodes[root1] < rootNodes[root2]) {
                rootNodes[root2] = root1;
            } else if (rootNodes[root1] > rootNodes[root2]) {
                rootNodes[root1] = root2;
            } else {
                rootNodes[root2] = root1;
                // This is the only point where the tree increases in size beyond its current weight
                // To handle this we have to add one to its weight to reflect this
                rootNodes[root1]--;
            }

            // Return 1 to indicate that we merged
            return 1;
        }
        // Return 0 to indicate that we didn't merge
        return 0;
    }

    public static long cost(int n, int hospitalCost, int highwayCost, int[][] cities) {
        // If the hospital cost is less than the highway cost, we can simply build a hospital in each city
        // In this context, highways don't actually matter, so we don't even need to look at the graph
        // We can just return the number of cities times the hospital cost
        if (hospitalCost <= highwayCost) {
            return (long) n * hospitalCost;
        }

        // We want to treat each node as its own subgraph initially
        int numSubgraphs = n;

        // For each node, track its parent node and its weight
        int[] rootNodes = new int[n + 1];

        // Iterate over each edge in cities and build the subgraphs
        for (int[] city : cities) {
            int edgeStart = city[0];
            int edgeEnd = city[1];

            // Get the root node attached to each edge's tree
            int rootStart = find(rootNodes, edgeStart);
            int rootEnd = find(rootNodes, edgeEnd);

            // Try to union them, if union returns 1, we merged two subgraphs and can subtract 1 from the total
            // If union returns 0, we didn't merge anything and so subtracting 0 doesn't change anything
            numSubgraphs -= union(rootNodes, rootStart, rootEnd);
        }

        // Now we have all subgraphs processed, we can calculate the cost using the number of subgraphs
        int numHighways = n - numSubgraphs;
        int numHospitals = numSubgraphs;

        return (long) numHighways * highwayCost + (long) numHospitals * hospitalCost;
    }
}