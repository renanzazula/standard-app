package com.standard.service.configparam;

import com.standard.entity.ConfigParamEntity;
import com.standard.enums.ConfigParamsEnum;
import com.standard.repository.configparam.ConfigParamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfigParamServiceImpl implements ConfigParamService {

    private final ConfigParamRepository configParamRepository;


    @Override
    public Object getParameterValue(ConfigParamsEnum id) {
        ConfigParamEntity param = configParamRepository.getById(id.name());
        String value = param.getValue();
        switch (id.getType()) {
            case BOOLEAN:
                if (StringUtils.hasText(value) && "Y".equals(value)) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            case INTEGER:
                if (StringUtils.hasText(value)) {
                    return Integer.parseInt(value);
                } else {
                    return 0;
                }
            default:
                return value;
        }
    }

    @Override
    public <T> Optional<T> getParameterValue(ConfigParamsEnum id, Class<T> clazz) {
        return getParameterValue(id, clazz, null);
    }

    @Override
    public <T> Optional<T> getParameterValue(ConfigParamsEnum id, Class<T> clazz, T defaultValue) {
        final Object result = getParameterValue(id);
        if (result != null && result.getClass().isAssignableFrom(clazz))
            return Optional.of((T) result);
        return Optional.ofNullable(defaultValue);
    }
}
