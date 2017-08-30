package com.pwc.aml.dashboard.entity;

import java.util.ArrayList;
import java.util.List;

public class DashboardEntity {

    private List<String> labels;
    private List<String> series;
    private List<ArrayList<Integer>> data;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }

    public List<ArrayList<Integer>> getData() {
        return data;
    }

    public void setData(List<ArrayList<Integer>> data) {
        this.data = data;
    }
}
