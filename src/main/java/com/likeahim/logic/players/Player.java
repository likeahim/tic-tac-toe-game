package com.likeahim.logic.players;

import com.likeahim.logic.marks.Marker;

public interface Player {
    String getName();
    int getWins();
    void setWins(int wins);
    Marker getMark();
    void setName(String name);
}