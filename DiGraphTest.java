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
         
         case "v":
            System.out.println("number of vertices: " +graph.vertexCount());
            break;
         
         case "p":
            graph.print();
            break;
         
         case "t":
            try{
               graph.printTop();
               break;
            }
            catch(IllegalArgumentException e){
               System.out.println("Cycle");
               break;
            }

         default:
            System.out.println("invalid option");
      }



   System.out.println("enter another operation: ");
   }


}






}
