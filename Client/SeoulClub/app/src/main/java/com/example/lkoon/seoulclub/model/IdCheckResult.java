package com.example.lkoon.seoulclub.model;

/**
 * Created by lbc on 2017-10-21.
 */

public class IdCheckResult {
//    String result;

    boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IdCheckResult{" +
                "result=" + result +
                '}';
    }
//    @Override
//    public String toString() {
//        return "IdCheckResult{" +
//                "result='" + result + '\'' +
//                '}';
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }
}
