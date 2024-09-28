package org.example.lab.app.service.preferences.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lab.app.domain.preferences.SettingsType;
import org.example.lab.app.domain.preferences.Settings;
import org.example.lab.app.repository.SettingsTypeRepository;
import org.example.lab.app.service.preferences.SettingsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SettingsServiceImpl implements SettingsService {

    private SettingsTypeRepository settingsTypeRepository;

    @Override
    public Optional<SettingsType> getSettingsByName(String nome) {
        return settingsTypeRepository.findByNome(nome)
                .map(settingsType -> {
                    settingsType.getConfiguracoes().forEach((key, settings) -> {
                        Object valor = settings.getValue();  // Pega o valor dinâmico baseado no tipo
                        System.out.println("Chave: " + key + ", Valor: " + valor + ", Tipo: " + settings.getTipo());
                    });
                    return settingsType;
                });
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
