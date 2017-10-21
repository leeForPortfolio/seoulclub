package com.example.lkoon.seoulclub.model;

import java.util.List;

/**
 * Created by lkoon on 2017-10-17.
 */

public class Concerns {
    List<Concern> concern;

    public Concerns(List<Concern> concerns) {
        this.concern = concerns;
    }



    public List<Concern> getConcerns() {
        return concern;
    }

    public void setConcerns(List<Concern> concerns) {
        this.concern = concerns;
    }
}
