package org.example.lab.app.service.preferences;

import org.example.lab.app.domain.preferences.Settings;
import org.example.lab.app.domain.preferences.SettingsType;

import java.util.Optional;

public interface SettingsService {

    // Método para buscar uma configuração por chave
    Optional<SettingsType> getSettingsByName(String nome);

    // Método para buscar o valor de uma configuração específica
    Optional<Object> getConfigValueByKey(Long settingsTypeId, String key);

    // Método para adicionar ou atualizar uma configuração
    SettingsType addOrUpdateConfig(Long settingsTypeId, String key, Settings settings);

    // Método para remover uma configuração
    void removeConfig(Long settingsTypeId, String key);

}
