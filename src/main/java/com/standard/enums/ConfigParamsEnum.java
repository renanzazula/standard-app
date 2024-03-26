package com.standard.enums;

public enum ConfigParamsEnum {

    GOOGLE_2FA(ConfigParamTypeEnum.STRING),
    SESSION_TIMEOUT(ConfigParamTypeEnum.INTEGER);

    private final ConfigParamTypeEnum type;

    ConfigParamsEnum(ConfigParamTypeEnum type) {
        this.type = type;


    }
    public ConfigParamTypeEnum getType() {
        return type;
    }

}


