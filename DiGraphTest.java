import java.util.*;
import java.lang.*;
import java.io.*;

public class DiGraphTest{

public static void main(String args[])
{
   Scanner in = new Scanner(System.in);
   System.out.print("enter the number of vertices: ");
   DiGraph graph = new DiGraph(in.nextInt());
   in.nextLine();
   System.out.println();

   System.out.println("Choose one of the following operations: ");
   System.out.println("- add edge (enter a)");
   System.out.println("- delete edge (enter d)");
   System.out.println("- edge count (enter e)");
   System.out.println("- vertex count (enter v)");
   System.out.println("- print graph (enter p)");
   System.out.println("- topologicalsort (enter t)");
   System.out.println("- is there a path (enter i)");
   System.out.println("- length of the path (enter l)");
   System.out.println("- print the shortest path (enter s)");
   System.out.println("- print the BFS tree (enter b)");
   System.out.println("- Quit (enter q)");
   String line;
   System.out.println("enter an operation: ");
   while(!((line = in.nextLine()).equals("q")))
   {
      int from;
      int to;
      switch(line)
      {
         case "a":
            System.out.println("enter a from and to edge:");    
            from= in.nextInt();     
            to= in.nextInt();     
            in.nextLine();
            graph.addEdge(from,to);
            System.out.println("added edge from " +from + " to " +to);
            break;
         case "b":
            System.out.println("enter a source vertex:");    
            from= in.nextInt();     
            in.nextLine();
            System.out.println("generated tree from: " +from+ "\n");
            graph.printTree(from);
            break;
         case "d":
            System.out.println("enter a from and to edge:");    
            from= in.nextInt();     
            to= in.nextInt();     
            in.nextLine();
            graph.deleteEdge(from,to);
            System.out.println("deleted edge from " +from + " to " +to);
            break;

         case "e":
            System.out.println("number of edges: " +graph.edgeCount());
            break;
         
         case "i":
            System.out.println("enter a from and to edge:");    
            from= in.nextInt();     
            to= in.nextInt();     
            in.nextLine();
            boolean Result = graph.isTherePath(from,to);
            System.out.println("is there a path from " + from + " to " +to +": " + Result);
            break;
         
         case "l":
            System.out.println("enter a from and to edge:");    
            from= in.nextInt();     
            to= in.nextInt();     
            in.nextLine();
            int length = graph.lengthOfPath(from,to);
            System.out.println("length of path from " +from + " to " +to + ": " + length);
            break;
         
         case "p":
            graph.print();
            break;
         
         case "s":
            System.out.println("enter a from and to edge:");    
            from= in.nextInt();     
            to= in.nextInt();     
            in.nextLine();
            graph.printPath(from,to);
            break;
         
         case "t":
            try{
               graph.printTop();
               break;
            }
            catch(IllegalArgumentException e){
               System.out.println("Error: Graph contains a Cycle");
               break;
            }
         case "v":
            System.out.println("number of vertices: " +graph.vertexCount());
            break;

         default:
            System.out.println("invalid option");
      }


   if(line.equals("q"))
      System.out.println("Good bye.");
   else
      System.out.println("\nenter another operation: ");
   }


}






}
