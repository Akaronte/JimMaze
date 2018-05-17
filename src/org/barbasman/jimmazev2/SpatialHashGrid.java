package org.barbasman.jimmazev2;

import java.util.ArrayList;
import java.util.List;

import android.util.FloatMath;
import android.util.Log;


public class SpatialHashGrid {
    List<GameObject>[] dynamicCells;
    List<GameObject>[] staticCells;
    int cellsPerRow;
    int cellsPerCol;
    float cellSize;
    int[] cellIds = new int[4];
    List<GameObject> foundObjects;
	
    
    @SuppressWarnings("unchecked")
    public SpatialHashGrid(float worldWidth, float worldHeight, float cellSize) {
        this.cellSize = cellSize;
        this.cellsPerRow = (int)Math.ceil(worldWidth/cellSize);
        this.cellsPerCol = (int)Math.ceil(worldHeight/cellSize);
        int numCells = cellsPerRow * cellsPerCol;
		
        dynamicCells = new List[numCells];
        staticCells = new List[numCells];
        for(int i = 0; i < numCells; i++) {
            dynamicCells[i] = new ArrayList<GameObject>(100);
            staticCells[i] = new ArrayList<GameObject>(100);
        }
        foundObjects = new ArrayList<GameObject>(100);
    }
    
    public void insertStaticObject(GameObject obj) {
        int[] cellIds = getCellIds(obj);
        //int i=0;
        int cellId = -1;
		int len=cellIds.length;
		for(int i=0;i<len;i++){
			cellId=cellIds[i];
			if(cellId!=-1)
			staticCells[cellId].add(obj);
		}
    }
    
    public void insertDynamicObject(GameObject obj) {
        int[] cellIds = getCellIds(obj);
        //int i = 0;
        int cellId = -1;
		int len=cellIds.length;
		for(int i=0;i<len;i++){
			cellId=cellIds[i];
			if(cellId!=-1)
				dynamicCells[cellId].add(obj);
		}
    }
    
    public void removeObject(GameObject obj) {
        int[] cellIds = getCellIds(obj);
        //int i = 0;
        int cellId = -1;
		int len=cellIds.length;
		for(int i=0;i<len;i++){
			cellId=cellIds[i];
			if(cellId!=-1){
				dynamicCells[cellId].remove(obj);
				staticCells[cellId].remove(obj);
				}
		}
    }
    
    public void clearDynamicCells(GameObject obj) {
        int len = dynamicCells.length;
        for(int i = 0; i < len; i++) {
            dynamicCells[i].clear();
        }
    }
    
    public List<GameObject> getPotentialColliders(GameObject obj) {
        foundObjects.clear();
        int[] cellIds = getCellIds(obj);
		int cell;
		for(int c=0;c<4;c++){
			if(cellIds[c]!=-1){
				cell=cellIds[c];
				int lenDyn=dynamicCells[cell].size();
				for(int cd=0;cd<lenDyn;cd++){
				GameObject collider=dynamicCells[cell].get(cd);
					if(!foundObjects.contains(collider)){
						foundObjects.add(collider);
					}
				}
				int lenStat=staticCells[cell].size();
				for(int cs=0;cs<lenStat;cs++){
				GameObject collider=staticCells[cell].get(cs);
					if(!foundObjects.contains(collider)){
						foundObjects.add(collider);
					}
				}
			}
		}
        
        /*int cellId = -1;
		int len=cellIds.length;
		for(int i=0;i<len;i++){
			cellId=cellIds[i];
			if(cellId!=-1){
				int ListDynObj=dynamicCells[cellId].size();
				for(int j=0;j<ListDynObj;j++){
					GameObject collider = dynamicCells[cellId].get(j);
					if(!foundObjects.contains(collider)){
						foundObjects.add(collider);
					}
				}
				int ListStatObj=staticCells[cellId].size();
				for(int j=0;j<ListStatObj;j++){
					GameObject collider=staticCells[cellId].get(j);
					if(!foundObjects.contains(collider)){
						foundObjects.add(collider);
					}
				}
			}
		}*/
        
        return foundObjects;
    }
    
	public int[] getCellIds(GameObject obj){
		
		int x1 = (int)Math.floor(obj.bounds.lowerLeft.x / cellSize);
		int y1 = (int)Math.floor(obj.bounds.lowerLeft.y / cellSize);
		int x2 = (int)Math.floor((obj.bounds.lowerLeft.x + obj.bounds.width) / cellSize);
		int y2 = (int)Math.floor((obj.bounds.lowerLeft.y + obj.bounds.height) / cellSize);
		//text="x1="+x1+" y1="+y1+" x2="+x2+" y2="+y2;
		//Log.d("org.barbasman",text);
		
		for(int h=0;h<4;h++){ 
			cellIds[h]=-1;
		}
		
		if(x1==x2&&y1==y2){
			if(x1>=0&&x1<cellsPerRow&&y1>=0&&y1<cellsPerCol){
				cellIds[0] = x1 + y1 * cellsPerRow;
			}
		}else if(x1==x2){
			int i=0;
			if(x1 >= 0 && x1 < cellsPerRow) {
                if(y1 >= 0 && y1 < cellsPerCol)
                    cellIds[i++] = x1 + y1 * cellsPerRow;
                if(y2 >= 0 && y2 < cellsPerCol)
                    cellIds[i++] = x1 + y2 * cellsPerRow;
            }
		}else if(y1==y2){
			int i=0;	
			if(y1 >= 0 && y1 < cellsPerCol) {
                if(x1 >= 0 && x1 < cellsPerRow)
                    cellIds[i++] = x1 + y1 * cellsPerRow;
                if(x2 >= 0 && x2 < cellsPerRow)
                    cellIds[i++] = x2 + y1 * cellsPerRow;
            }
		}else{
			int i=0;
			if(x1 >= 0 && x1 < cellsPerRow && y1 >= 0 && y1 < cellsPerCol)
                cellIds[i++] = x1 + y1 * cellsPerRow;
				
			if(x2 >= 0 && x2 < cellsPerRow && y1 >= 0 && y1 < cellsPerCol)
                cellIds[i++] = x2 + y1 * cellsPerRow;
			
			if(x2 >= 0 && x2 < cellsPerRow && y2 >= 0 && y2 < cellsPerCol)
                cellIds[i++] = x2 + y2 * cellsPerRow;
			
			if(x1 >= 0 && x1 < cellsPerRow && y2 >= 0 && y2 < cellsPerCol)
                cellIds[i++] = x1 + y2 * cellsPerRow;
		}
		return cellIds;
	}
}
