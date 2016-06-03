package org.jefrienalvizures.clashtournament.clases;

import org.jefrienalvizures.clashtournament.bean.Clan;


/**
 * Created by Familia on 01/06/2016.
 */
public class Clanes {
    public static Clan clan = null;

    public static void setClan(Clan c){
        clan = c;
    }

    public static Clan getClan(){
        return clan;
    }

}
