package com.Carl;

public class Player{
    private String name;
    private int currentPoints;

    public String GetName(){
        return name;
    }

    public int GetPoints(){
        return currentPoints;
    }

    public void AddPoints(int amount){
        currentPoints += amount;
    }

    public void ClearPoints(){
        currentPoints = 0;
    }

    public Player(String name, int startPoints){
        this.name = name;
        currentPoints = startPoints;
    }
}