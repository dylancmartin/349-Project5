import java.util.*;
import java.lang.*;
import java.io.*;

public class DiGraph{

private LinkedList<Integer>[] list;

public DiGraph(int n){
   this.list = new LinkedList[n];
   for(int i = 0; i <n; i++)
   {
      LinkedList<Integer> l=new LinkedList<Integer>();
      list[i] = l;
      list[i].add(i);
   }

}

public void addEdge(int from, int to)
{
   if(list[from-1].contains(to-1));
   else
   {
      list[from-1].add(to-1);

   }

}


public void deleteEdge(int from, int to){
   int store;
   if(from != to)
      if((store = list[from-1].indexOf(to-1)) >= 0)
         list[from-1].remove(store);
}

public int edgeCount()
{
   int sum = 0;
   for(LinkedList<Integer> l : list)
      sum+= l.size() -1;
   return sum;
}

public int vertexCount()
{
   return list.length;
}

public void print()
{

   System.out.println("The graph is the following:");
   for(int i = 0; i < list.length; i++)
   {
      System.out.print((i+1)+ " is connected to: ");
      for(int j = 1; j < list[i].size(); j++)
      {
         if(j != list[i].size()-1)
            System.out.print((list[i].get(j)+1)+ ",");
         else
            System.out.print((list[i].get(j)+1));
      } 
      System.out.println();
   }  
}

private int[] indegrees(){
   int[] indegree = new int[list.length];
   for(int j =0; j < indegree.length; j++)
      indegree[j] = 0;
   for(int i = 0; i < list.length; i++)
      for(int j = 1; j < list[i].size(); j++)
         indegree[list[i].get(j)] += 1;
   return indegree;
}

public int[] topSort(){
   int[] result = new int[list.length];
   LinkedList<Integer> q = new LinkedList<Integer>();
   int[] indegrees = indegrees();
   int[] cycle = new int[list.length];
   for(int n = 0; n < indegrees.length; n++)
   {
      if(indegrees[n] == 0 || list[n].size() == 1)
         cycle[n] = -1;
      else
         cycle[n] = 0;
   }
   int u;
   for(u = 0; u < list.length; u++)
      if(indegrees[u] == 0)
         q.addLast(u);
   int i =0;
   int count = 0;
   while(!(q.isEmpty()))
   {
      u = q.removeFirst();
      result[i] = u;
      i++;
      count++;
      for(int j =1;  j < list[u].size(); j++)
      {
         indegrees[list[u].get(j)] -=1;
         if(indegrees[list[u].get(j)] == 0)
            q.addLast(list[u].get(j));
     }
  }
   int length = list.length;
   for(int z= 0; z < list.length; z++){
      i += cycle[z];
      length += cycle[z];}
//   System.out.println(i);
//   System.out.println(length);
   if(i != length)
      throw new IllegalArgumentException();
   return result;
}

public void printTop(){
   int[] num = topSort();
   for(int i : num)
      System.out.print((i+1)+ " ");
   System.out.println();
   
}


}
