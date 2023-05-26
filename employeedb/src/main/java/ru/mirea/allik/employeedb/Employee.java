package ru.mirea.allik.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String superpower;
    public String nemesis;

    public long getId() {return id;}
    public String getName() {return name;}
    public String getSuperpower() {return superpower;}
    public String getNemesis(){return nemesis;}
}