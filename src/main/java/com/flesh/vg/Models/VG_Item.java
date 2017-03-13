package com.flesh.vg.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by aaronfleshner on 3/13/17.
 */
public class VG_Item {

    public String type;
    public String id;
    public VG_Attrs attributes;
    public VG_Relationships relationships;



    public class VG_Attrs{
       public Date createdAt;
       public String name;
       public String shardId;
       public VG_Stats stats;
       public String titleId;
    }

    public class VG_Stats{
       public double lifetimeGold;
       public int lossStreak;
       public int winStreak;
       public int level;
       public int played;
       public int played_ranked;
       public int wins;
       public int xp;
    }

    public class VG_Relationships{
        public VG_Assests assests;
    }

    public class VG_Assests{
        public String[] data;
    }

}
