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
   if(i != list.length)
      throw new IllegalArgumentException();
   return result;
}

public void printTop(){
   int[] num = topSort();
   for(int i = 0; i < num.length-1; i++)
      System.out.print((num[i]+1)+ ",");
   System.out.println((num[num.length-1]+1));
   
}

private class VertexInfo{

   private int distance;
   private int parent;

   public VertexInfo(int distance, int parent)
   {
      this.distance =distance;
      this.parent = parent;

   }

}


private VertexInfo[] BFS(int s){
   LinkedList<Integer> q=new LinkedList<Integer>();
   int N = list.length;
   VertexInfo[] vinfo =new VertexInfo[N];
   int u = 0;
   for(u= 0; u < N; u++)
   {
      vinfo[u] = new VertexInfo(-1,-1);
   }
   vinfo[s].distance = 0;
   q.addLast(s);
   while(!(q.isEmpty()))
   {
      u = q.removeFirst();
      for(int v : list[u])
      {
         if(vinfo[v].distance == -1)
         {
            vinfo[v].distance = vinfo[u].distance +1;
            vinfo[v].parent = u;
            q.addLast(v);
         }
     }
  }
//  for(int j = 0; j < N; j++)
  //   System.out.println(j + ", " + vinfo[j].parent + "," + vinfo[j].distance);
  return vinfo;
}


public boolean isTherePath(int from, int to){
   VertexInfo[] vinfo = BFS(from -1);
   if((vinfo.length > to-1) &&(vinfo[to-1].parent != -1 || from == to))
      return true;
   return false;
}

public int  lengthOfPath(int from, int to){
   
   VertexInfo[] vinfo = BFS(from -1);
   int  length = 0;
   int val = to-1;
   if((vinfo.length > to-1) && (vinfo[to-1].distance == -1))
      return -1;
   else if((vinfo.length > to-1)){
      while(val != from-1)
      {
         length++;
         val = vinfo[val].parent;
      }
      return length;
   }
   else
      return -1;
}


public void printPath(int from, int to){
   VertexInfo[] vinfo = BFS(from -1);
   String output = "";
   int val = to-1;
   if((vinfo.length > to-1) && (vinfo[to-1].distance == -1))
      System.out.println("No Path");
   else if((vinfo.length > to-1)){
      while(val != from-1)
      {
         output = "->" + (val+1) + output;
         val = vinfo[val].parent;
      }
      output = (val+1)+output;
      System.out.println(output);
   }
   else
      System.out.println("No Path");
}

private class TreeNode{
   private int vnum;
   private LinkedList<TreeNode> child;

   public TreeNode(int vnum){
   this.vnum = vnum;
   this.child = new LinkedList<TreeNode>();
   }
}


private TreeNode buildTree(int s){
   VertexInfo[] vinfo = BFS(s-1);
   TreeNode[] treenodes = new TreeNode[vinfo.length];
   for(int j = 0; j < list.length;j++)
   {
      treenodes[j]= new TreeNode(j);
   }
   
   for(int i =0; i < list.length; i ++)
   {
      if(vinfo[i].parent != -1)
      {
         int parent = vinfo[i].parent;
         treenodes[parent].child.addFirst(treenodes[i]);
      }
   }
   return treenodes[s-1];
}

public void printTree(int s){
   TreeNode root = buildTree(s);
   int distance = 0;
   printRec(distance,root);

}

private void printRec(int distance, TreeNode root){
   String spaces = "";
   if(root == null)
      System.out.println();
   else
   {
      for(int k = 0; k < distance; k ++)
         spaces += "   ";
      System.out.println(spaces + (root.vnum +1));
      for(int i = 0; i < root.child.size(); i++)
      {
         printRec(distance+1,root.child.get(i));  
      
      }

   }

}



}
