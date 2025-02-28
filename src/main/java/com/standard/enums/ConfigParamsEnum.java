package com.standard.enums;

import lombok.Getter;

@Getter
public enum ConfigParamsEnum {

    SESSION_TIMEOUT(ConfigParamTypeEnum.INTEGER);

    private final ConfigParamTypeEnum type;

    ConfigParamsEnum(ConfigParamTypeEnum type) {
        this.type = type;
    }

}


