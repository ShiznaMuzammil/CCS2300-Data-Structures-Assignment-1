import java.util.*;

//Main class is SmartCityRoute
public class SmartCityRoute {
	
	Tree tree = new Tree();   // Tree to store locations
    Graph graph = new Graph(); // Graph used to store locations and roads 

// Tree class is used to store the locations (Nodes) in a Binary Search Tree (BST)
	public class Tree{
		Node root;        //Declaring the node
		
		
/*Node used to store location and node will have left side where key value is smaller than the parent root and right side key value
is larger than parent root*/	
		class Node {
		String location;
		Node leftChild;
		Node rightChild;	
			
//Constructor used to initilize the Location (Node)		
	Node(String location) {
        this.location = location;
        leftChild = null;
        rightChild = null;
        
	   }
    }
		
// Below method is used to insert locations (nodes) in the tree
		 public void insert(String location) {
	            root = insertLocation(root, location); // Two values are taken as it starts from root and then find the location
	        }

		// Compares words alphabetically to decide whether to go left or right in the tree
		 private Node insertLocation(Node root, String location) {
			    if (root == null) {
			        return new Node(location);
			    }

			    if (location.compareTo(root.location) < 0) {
			        root.leftChild = insertLocation(root.leftChild, location);
			    } else if (location.compareTo(root.location) > 0) {
			        root.rightChild = insertLocation(root.rightChild, location);
			    }

			    return root; 
			}
		 
// In this method is used to remove the locations.		 
		 public void remove(String location) {
			    root = removeLocation(root, location);
			}
	
//  Removes a location (node) from the BST. 
		 private Node removeLocation(Node root, String location) {
			    if (root == null) {
			    	return null; // If location is not found it will return null
			    }
			    
// If the roots location is less than 0 then it will check the left side value and delete
			    if (location.compareTo(root.location) < 0) {
			        root.leftChild = removeLocation(root.leftChild, location);

//if it is greater than zero right side value will be checked and removed
			    } else if (location.compareTo(root.location) > 0) {
			        root.rightChild = removeLocation(root.rightChild, location);
			    } else {
			       

//When the left side node and right side node has no child (leaf) it will return null.
			        if (root.leftChild == null && root.rightChild == null) {
			            return null;
			        }
			        
//When the node has one child and when that node is deleted it will replace with its child.		        
			        else if (root.leftChild == null) {
			            return root.rightChild;
			        } else if (root.rightChild == null) {
			            return root.leftChild;
			        }
			        
//When there are two child we must find the smallest node in the right subtree and replace the deleted node with its value
			        else {
			        	Node successor = findSuccessor(root.rightChild); 
		                root.location = successor.location;
		                root.rightChild = removeLocation(root.rightChild, successor.location);
		            }
			    }  
			        	    return root;
			        	}
			        	
// Finds the successor (smallest node in right subtree)	    
			private Node findSuccessor(Node root) {
			   	while (root.leftChild != null) {
			   		root = root.leftChild;
			   	}
				
			   	return root;	
           }
	
			// Maps tree locations into the graph in sorted (in-order) order
	public void mapTreeToGraph(Graph graph) {
	    inorderMap(root, graph);
	}

	private void inorderMap(Node root, Graph graph) {
	    if (root != null) {
	        inorderMap(root.leftChild, graph);
	        graph.addLocation(root.location);   // mapping happens here
	        inorderMap(root.rightChild, graph);
	    }
	}
	
	}

public static void main(String[] args) {

	SmartCityRoute location = new SmartCityRoute();

	Scanner sc = new Scanner(System.in);
	
	// This is used to add the locations in the tree
	location.tree.insert("university");
	location.tree.insert("childrens park");
	location.tree.insert("zoo");
	location.tree.insert("shopping mall");
	location.tree.insert("food court");
	location.tree.insert("post office");
	location.tree.insert("museum");

	//Maps the locations in the graph
	location.tree.mapTreeToGraph(location.graph);

	//This add roads between 2 locations
	location.graph.addRoad("zoo", "post office");
	location.graph.addRoad("museum", "university");
	location.graph.addRoad("shopping mall", "food court");
	location.graph.addRoad("childrens park","zoo");

	//Display the locations 
	location.graph.displayGraph();

	System.out.println("Transfersal from zoo: ");
	location.graph.dfsTraversal("zoo");

	boolean exit = false;
    while (!exit) {
        System.out.println(" Smart City Route Menu Driven Interface");
        System.out.println("1. Add Location (Node)");
        System.out.println("2. Remove Location (Node)");
        System.out.println("3. Add Road (Edge)");
        System.out.println("4. Remove Road (Edge)");
        System.out.println("5. Display Map");
        System.out.println("6. DFS Traversal");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");

        int choice; // Choose a choice from menu driven 
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) { // If the choice is wrong you will get an error
            System.out.println("Invalid input! Please enter a number from 1 to 7.");
            continue; 
        }
 
        
        switch (choice) {
        case 1: 
            System.out.print("Enter location: ");
            String loc = sc.nextLine().trim().toLowerCase();
            if (!loc.isEmpty()) {
            	if (location.graph.adjList.containsKey(loc)) { // Checks if the location exists
            	    System.out.println("Location already exists.");
            	} else { // If location does not exist location will be added
            	    location.tree.insert(loc);
            	    location.graph.addLocation(loc);
            	    System.out.println(loc + " has been added.");
            	}

            } else { // Location should have a name
                System.out.println("Location name cannot be empty.");
            }
            break;

            
        case 2: // Remove Location
            System.out.print("Enter location to remove: ");
            loc = sc.nextLine().trim().toLowerCase();
            if (location.graph.adjList.containsKey(loc)) {
                location.tree.remove(loc);
                location.graph.removeLocation(loc);
                System.out.println(loc + " has been removed.");
            } else {
                System.out.println("Location does not exist.");
            }
            break;
            
            
        case 3: 
            System.out.print("Enter first location: ");
            String from = sc.nextLine().trim().toLowerCase();
            System.out.print("Enter second location: ");
            String to = sc.nextLine().trim().toLowerCase();
            if (location.graph.adjList.containsKey(from) && location.graph.adjList.containsKey(to)) {
                location.graph.addRoad(from, to);
                System.out.println("Road has been added between " + from + " and " + to);
            } else {
                System.out.println("One or both locations do not exist. Road not added.");
            }
            break;

        
        
        case 4: 
            System.out.print("Enter first location: ");
            from = sc.nextLine().trim().toLowerCase();
            System.out.print("Enter second location: ");
            to = sc.nextLine().trim().toLowerCase();
            if (location.graph.adjList.containsKey(from) && location.graph.adjList.containsKey(to)) {
                location.graph.removeRoad(from, to);
                System.out.println("Road has been removed between " + from + " and " + to);
            } else {
                System.out.println("One or both locations do not exist. Road not removed.");
            }
            break;
        
        case 5:
            location.graph.displayGraph();
            break;

        case 6:
            System.out.print("Enter starting location for DFS Traversal: ");
            String start = sc.nextLine().trim().toLowerCase();
            if (location.graph.adjList.containsKey(start)) {
                location.graph.dfsTraversal(start);
            } else {
                System.out.println("Location does not exist in the map.");
            }
            break;

        
        case 7:
            exit = true;
            break;

        default:
            System.out.println("Invalid choice! Please enter a number from 1 to 7.");
            break;
    }
}

     sc.close();
     System.out.println("Exiting Smart City Route Planner. Thank you!");

}
}	
//Graph  is used to store locations and road. And display all connections between locations. 
class Graph { 
	 public Map<String, List<String>> adjList;
	 
	 //Constructor used to initialize the adjlist to new hashmap
	 public Graph() {
		 adjList = new HashMap<>();
	      
	    }

// Adds a new location to the adjacency list with no connected locations yet
public void addLocation(String location) {
 adjList.putIfAbsent(location, new ArrayList<>());
}


//Used to remove location
public void removeLocation(String location) {
 if (!adjList.containsKey(location)) {
     return;
 }

 for (String neighbour : adjList.get(location)) {
     adjList.get(neighbour).remove(location);
 }
 adjList.remove(location);
}


//addRoad used to add a road (edge) between two existing locations (nodes) 
public void addRoad(String from, String to) {
	// Ensure both locations exist in the graph before connecting them
  if (adjList.containsKey(from) && adjList.containsKey(to)) {
	// Connect 'from' to 'to' if not already connected
  if (!adjList.get(from).contains(to)) adjList.get(from).add(to);
// Connect 'to' to 'from' (undirected graph)
  if (!adjList.get(to).contains(from)) adjList.get(to).add(from); 
}

}

//removeRoad method removes the edges (roads) between the two locations
public void removeRoad(String from, String to) {
//Remove 'to' from 'from's adjacency list, if present 
if (adjList.containsKey(from)) adjList.get(from).remove(to);
//Remove 'from' from 'to's adjacency list, if present
if (adjList.containsKey(to)) adjList.get(to).remove(from);
}

//dfsTransversal is used to traverse the location
public void dfsTraversal(String start) {
	
	// The if condition checks the starting location and if the starting location is not found it prints the statement
if (!adjList.containsKey(start)) {
  System.out.println("First location is not found.");
  return;
  }


//Hash Set used to store visited locations and stack used to implement the traversal
Set<String> visited = new HashSet<>();
Stack<String> stack = new Stack<>();

//Push each locations into the stack
stack.push(start);

//Loop continues until all the locations are visited
while (!stack.isEmpty()) {
String currentLocation = stack.pop();

//Checks the printed locations and prints the locations
if (!visited.contains(currentLocation)) {
   System.out.println("Heading to: " + currentLocation);
   visited.add(currentLocation);

//Loops through all connected locations (roads) of the current location.
   for (String neighbour : adjList.get(currentLocation)) {
       if (!visited.contains(neighbour)) {
           stack.push(neighbour);
         }
      }
    }
  }
}
//Displays all locations(nodes) and roads(edges)
public void displayGraph() {
System.out.println("Graph connections");

//for loop is used to loop all locations and print the list of connected locations.
for (String location : adjList.keySet()) {
  System.out.println(location + " : " + String.join(", ", adjList.get(location)));
    }     
 }



}


