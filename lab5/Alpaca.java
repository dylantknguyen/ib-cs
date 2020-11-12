
/**
 * An app that contains the Alpaca Class, which reads and stores information about Alpacas
 * @author Dylan Nguyen
 **/
// Creates Alpaca Classes
public class Alpaca {
    // Defines Properties of Alpaca Class
    private String name;
    private int age;
    private double weight;
    private boolean likesPets;
    
    // Sets Default Values
    public Alpaca()
    {
        name = "none";
        age = 0;
        weight = 0.0;
        likesPets = false;
    }
    
    // Overloaded Constructor
    // Allows for Overwriting of Default Values
    public Alpaca(String n, int a, double w, boolean lp)
    {
        name = n;
        age = a;
        weight = w;
        likesPets = lp;
    }
    
    // Returns Name of Alpaca
    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public double getWeight()
    {
        return weight;
    }

    public boolean getLikesPets()
    {
        return likesPets;
    }
    // Returns Data in String Format
    public String toString()
    {
        return("Name = " + name + "; age = " + age + "; weight = " + weight + "; likes pets = " + likesPets);
    }
}
