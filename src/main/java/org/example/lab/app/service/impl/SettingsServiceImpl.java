package org.example.lab.app.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.lab.app.domain.SettingsType;
import org.example.lab.app.domain.Settings;
import org.example.lab.app.repository.SettingsTypeRepository;
import org.example.lab.app.service.SettingsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SettingsServiceImpl implements SettingsService {

    private SettingsTypeRepository settingsTypeRepository;

    @Override
    public Optional<SettingsType> getSettingsByName(String nome) {
        return settingsTypeRepository.findByNome(nome);
    }

    // Método para buscar uma configuração específica por chave
    @Override
    public Optional<Object> getConfigValueByKey(Long settingsTypeId, String key) {
        return settingsTypeRepository.findById(settingsTypeId)
                .map(settingsType -> {
                    Settings config = settingsType.getConfiguracoes().get(key);
                    return config != null ? Optional.of(config.getValue()) : Optional.empty();
                })
                .orElse(Optional.empty());
    }


    // Método para adicionar ou atualizar uma configuração
    @Override
    public SettingsType addOrUpdateConfig(Long settingsTypeId, String key, Settings newSettings) {
        return settingsTypeRepository.findById(settingsTypeId).map(settingsType -> {
            // Atualiza ou adiciona a configuração no Map
            settingsType.getConfiguracoes().put(key, newSettings);
            return settingsTypeRepository.save(settingsType);
        }).orElseThrow(() -> new IllegalArgumentException("SettingsType não encontrado!"));
    }

    // Método para remover uma configuração existente
    @Override
    public void removeConfig(Long settingsTypeId, String key) {
        settingsTypeRepository.findById(settingsTypeId).ifPresent(settingsType -> {
            settingsType.getConfiguracoes().remove(key);
            settingsTypeRepository.save(settingsType);
        });
    }
}
