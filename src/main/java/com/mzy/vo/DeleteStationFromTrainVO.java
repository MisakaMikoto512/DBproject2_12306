package com.mzy.vo;

import lombok.Data;

@Data
public class DeleteStationFromTrainVO {
    String train_id;
    String station;


    public DeleteStationFromTrainVO(String station, String train_id) {
        this.station = station;
        this.train_id = train_id;
    }

    public DeleteStationFromTrainVO() {
        this.train_id="G133";
        this.station="要删除的在线路内的车站";

    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
