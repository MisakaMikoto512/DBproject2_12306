package com.mzy.enumpackege;

public enum ManageEnum {
     SUCCESS(1,"操作成功"),
     FILED(0,"操作失败,请检查信息");
    int stateCode;
    String message;

    ManageEnum(int stateCode, String message) {
        this.stateCode = stateCode;
        this.message = message;
    }
}
